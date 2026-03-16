package net.jneto.dataStructures.numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *  An arbitrary-precision integer stored in base 10^9.
 * Supports display with suffixes (Bi, T, Dec, Googol , etc )
 */
public class BigNumInterger implements Comparable<BigNumInterger> {
    /** Internally used base: each "chunk" stores up to 9 decimal digits. */
    private static final long BASE       = 1_000_000_000L;
    private static final int  BASE_DIGS  = 9;

    /** 
     * Chunks stores - index 0 = least significant
     */
    private final ArrayList<Integer> chunks;
    private boolean negative;

    // --- Suffix Table ---------------------------------------------

    public record Suffix(int power, String abbr, String fullName) {}

    /**
     * Table ordered from smallest to largest exponent
     * power = exponent of 10 (e.g., Billion → 10^9 → power=9)
     */
    public static final List<Suffix> SUFFIXES = List.of(
        new Suffix(3,   "K",    "Mil"),
        new Suffix(6,   "Mi",   "Milhão"),
        new Suffix(9,   "Bi",   "Bilhão"),
        new Suffix(12,  "Tri",  "Trilhão"),
        new Suffix(15,  "Qa",   "Quatrilhão"),
        new Suffix(18,  "Qi",   "Quintilhão"),
        new Suffix(21,  "Sx",   "Sextilhão"),
        new Suffix(24,  "Se",   "Setilhão"),
        new Suffix(27,  "Oc",   "Octilhão"),
        new Suffix(30,  "No",   "Nonilhão"),
        new Suffix(33,  "Dec",  "Decilhão"),
        new Suffix(36,  "UnDec","Undecilhão"),
        new Suffix(39,  "DuDec","Duodecilhão"),
        new Suffix(42,  "TrDec","Tredecilhão"),
        new Suffix(45,  "QaDec","Quatuordecilhão"),
        new Suffix(48,  "QiDec","Quindecilhão"),
        new Suffix(51,  "SxDec","Sexdecilhão"),
        new Suffix(54,  "SpDec","Septendecilhão"),
        new Suffix(57,  "OcDec","Octodecilhão"),
        new Suffix(60,  "NoDec","Novemdecilhão"),
        new Suffix(63,  "Vg",   "Vigintilhão"),
        new Suffix(66,  "UnVg", "Unvigintilhão"),
        new Suffix(69,  "DuVg", "Duovigintilhão"),
        new Suffix(72,  "TrVg", "Trevigintilhão"),
        new Suffix(75,  "QaVg", "Quattuorvigintilhão"),
        new Suffix(78,  "QiVg", "Quinvigintilhão"),
        new Suffix(81,  "SxVg", "Sexvigintilhão"),
        new Suffix(84,  "SpVg", "Septemvigintilhão"),
        new Suffix(87,  "OcVg", "Octovigintilhão"),
        new Suffix(90,  "NoVg", "Novemvigintilhão"),
        new Suffix(93,  "Tg",   "Trigintilhão"),
        new Suffix(96,  "UnTg", "Untrigintilhão"),
        new Suffix(100, "Gg",   "Googol"),              // 10^100
        new Suffix(303, "Ct",   "Centilhão")            // 10^303
    );

    // --- CONSTANTS ------------------------------------------------

    public static final BigNumInterger ZERO = new BigNumInterger("0");
    public static final BigNumInterger ONE  = new BigNumInterger("1");
    public static final BigNumInterger TEN  = new BigNumInterger("10");

    // ── Builders ────────────────────────────────────────────────────────

    /** 
     * Build from a decimal string (ex: 123456789) */
    public BigNumInterger(String number) {
        chunks   = new ArrayList<>();
        number   = number.strip();
        negative = number.startsWith("-");
        if (negative) number = number.substring(1);
        if (number.isEmpty() || number.equals("0")) {
            chunks.add(0);
            negative = false;
            return;
        }
        for (int i = number.length(); i > 0; i -= BASE_DIGS) {
            int start = Math.max(0, i - BASE_DIGS);
            chunks.add(Integer.parseInt(number.substring(start, i)));
        }
        trim();
    }

    /** 
     * Build from a long */
    public BigNumInterger(long value) {
        this(Long.toString(value));
    }

    /** 
     * Internal builder using pre-made chunks
     * */
    private BigNumInterger(ArrayList<Integer> chunks, boolean negative) {
        this.chunks   = chunks;
        this.negative = negative;
        trim();
    }

    // --- Arithmetic ---------------------------------------------------------------------------

    public BigNumInterger add(BigNumInterger other) {
        // Same - sum magnitudes
        if (this.negative == other.negative) {
            return new BigNumInterger(addMagnitudes(this.chunks, other.chunks), this.negative);
        }
        // odd signs - subtract the smaller from the larger.
        int cmp = compareMagnitudes(this.chunks, other.chunks);
        if (cmp == 0) return ZERO;
        if (cmp > 0) {
            return new BigNumInterger(subMagnitudes(this.chunks, other.chunks), this.negative);
        }
        return new BigNumInterger(subMagnitudes(other.chunks, this.chunks), other.negative);
    }

    public BigNumInterger subtract(BigNumInterger other) {
    	BigNumInterger neg = new BigNumInterger(new ArrayList<>(other.chunks), !other.negative);
        return this.add(neg);
    }

    public BigNumInterger multiply(BigNumInterger other) {
        int n = this.chunks.size(), m = other.chunks.size();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < n + m; i++) result.add(0);

        for (int i = 0; i < n; i++) {
            long carry = 0;
            for (int j = 0; j < m; j++) {
                long cur = (long) result.get(i + j)
                         + (long) this.chunks.get(i) * other.chunks.get(j)
                         + carry;
                result.set(i + j, (int)(cur % BASE));
                carry = cur / BASE;
            }
            if (carry > 0) result.set(i + m, (int)(result.get(i + m) + carry));
        }
        return new BigNumInterger(result, this.negative != other.negative);
    }

    /** Interger division */
    public BigNumInterger divide(BigNumInterger other) {
        if (other.isZero()) throw new ArithmeticException("Divisão por zero");
        if (other.chunks.size() == 1) {
            return divideByLong(other.chunks.get(0), this.negative != other.negative);
        }
        // Big division
        return divLong(this.abs(), other.abs(), this.negative != other.negative);
    }

    /** Division Remainders */
    public BigNumInterger mod(BigNumInterger other) {
        return this.subtract(this.divide(other).multiply(other));
    }

    /** 
     * Power - no negative exponent
     */
    public BigNumInterger pow(int exp) {
        if (exp < 0) throw new ArithmeticException("Expoente negativo não suportado");
        if (exp == 0) return ONE;
        BigNumInterger base   = this;
        BigNumInterger result = ONE;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result.multiply(base);
            base = base.multiply(base);
            exp >>= 1;
        }
        return result;
    }

    // --- Utils ------------------------------------------------------------------------------

    public boolean isZero()     { return chunks.size() == 1 && chunks.get(0) == 0; }
    public boolean isNegative() { return negative && !isZero(); }
    public BigNumInterger  abs()        { return new BigNumInterger(new ArrayList<>(chunks), false); }

    /** Number of decimal digits (without the sign). */
    public int digitCount() {
        int highChunk = chunks.get(chunks.size() - 1);
        int highDigits = String.valueOf(highChunk).length();
        return highDigits + BASE_DIGS * (chunks.size() - 1);
    }

    // --- Display-Display----------------------------

    /** Full number as decimal String */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (negative) sb.append("-");
        sb.append(chunks.get(chunks.size() - 1));
        for (int i = chunks.size() - 2; i >= 0; i--) {
            sb.append(String.format("%09d", chunks.get(i)));
        }
        return sb.toString();
    }

    /**
     * sHOW the number with the abbreviated suffix
     * Ex.: 1_500_000_000 - "1.50 Bi"
     *
     * @param decimals decimals places (0 - 9)
     */
    public String toSuffix(int decimals) {
        return formatWithSuffix(decimals, false);
    }

    /** Default 2 decimals places to suffix 
     * @return String number with 2 decimal places
     */
    public String toSuffix() { return toSuffix(2); }

    /**
     * Show the number with the full name suffix
     * Ex.: 1_500_000_000 → "1.50 Bilhão"
     */
    public String toSuffixFull(int decimals) {
        return formatWithSuffix(decimals, true);
    }

    public String toSuffixFull() { return toSuffixFull(2); }

    // --- compares ----------------------------------------------------------

    @Override
    public int compareTo(BigNumInterger other) {
        if (this.negative != other.negative) return this.negative ? -1 : 1;
        int cmp = compareMagnitudes(this.chunks, other.chunks);
        return this.negative ? -cmp : cmp;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BigNumInterger b)) return false;
        return compareTo(b) == 0;
    }

    @Override
    public int hashCode() { return Objects.hash(chunks, negative); }

    // ---- more utils --------------------------------------------

    /** 
     * Remove leading zeros (unnecessary higher-order chunks)
     */
    private void trim() {
        while (chunks.size() > 1 && chunks.get(chunks.size() - 1) == 0) {
            chunks.remove(chunks.size() - 1);
        }
        if (isZero()) negative = false;
    }

    private static ArrayList<Integer> addMagnitudes(List<Integer> a, List<Integer> b) {
        ArrayList<Integer> result = new ArrayList<>();
        long carry = 0;
        int n = Math.max(a.size(), b.size());
        for (int i = 0; i < n || carry > 0; i++) {
            long sum = carry;
            if (i < a.size()) sum += a.get(i);
            if (i < b.size()) sum += b.get(i);
            result.add((int)(sum % BASE));
            carry = sum / BASE;
        }
        return result;
    }

    /** 
     * Subtract b from a like |a| >= |b| 
     */
    private static ArrayList<Integer> subMagnitudes(List<Integer> a, List<Integer> b) {
        ArrayList<Integer> result = new ArrayList<>();
        long borrow = 0;
        for (int i = 0; i < a.size(); i++) {
            long diff = (long) a.get(i) - borrow - (i < b.size() ? b.get(i) : 0);
            if (diff < 0) { diff += BASE; borrow = 1; }
            else borrow = 0;
            result.add((int) diff);
        }
        return result;
    }

    private static int compareMagnitudes(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) return Integer.compare(a.size(), b.size());
        for (int i = a.size() - 1; i >= 0; i--) {
            int cmp = Integer.compare(a.get(i), b.get(i));
            if (cmp != 0) return cmp;
        }
        return 0;
    }

    private BigNumInterger divideByLong(long divisor, boolean resultNegative) {
        ArrayList<Integer> result = new ArrayList<>();
        long rem = 0;
        for (int i = chunks.size() - 1; i >= 0; i--) {
            long cur = rem * BASE + chunks.get(i);
            result.add(0, (int)(cur / divisor));
            rem = cur % divisor;
        }
        return new BigNumInterger(result, resultNegative);
    }

    /** Big division for large BigNumInterger divisors. */
    private static BigNumInterger divLong(BigNumInterger dividend, BigNumInterger divisor, boolean resultNeg) {
    	BigNumInterger quotient  = ZERO;
    	BigNumInterger remainder = ZERO;
        String digits    = dividend.toString();
        for (char ch : digits.toCharArray()) {
            remainder = remainder.multiply(TEN).add(new BigNumInterger(ch - '0'));
            // remainder (0–9)
            int q = 0;
            BigNumInterger tmp = divisor;
            while (tmp.compareTo(remainder) <= 0) { tmp = tmp.add(divisor); q++; }
            quotient  = quotient.multiply(TEN).add(new BigNumInterger(q));
            remainder = remainder.subtract(divisor.multiply(new BigNumInterger(q)));
        }
        return new BigNumInterger(new ArrayList<>(quotient.chunks), resultNeg);
    }

    /**
     * Formats with a suffix using arithmetic on the decimal string,
     * without depending on double (preserves full precision)
     */
    private String formatWithSuffix(int decimals, boolean useFull) {
        String numStr = toString();
        boolean isNeg = numStr.startsWith("-");
        if (isNeg) numStr = numStr.substring(1);

        int totalDigits = numStr.length();

        // suffix  -- power < totalDigits
        Suffix chosen = null;
        for (int i = SUFFIXES.size() - 1; i >= 0; i--) {
            if (SUFFIXES.get(i).power() < totalDigits) {
                chosen = SUFFIXES.get(i);
                break;
            }
        }

        // less 1.000 -- no suffix
        if (chosen == null) return toString();

        int power = chosen.power();
        String intPart  = numStr.substring(0, totalDigits - power);
        String fracFull = numStr.substring(totalDigits - power);

        // Fill or cut to decimal places.
        String frac = (fracFull + "0".repeat(decimals)).substring(0, decimals);

        String label  = useFull ? chosen.fullName() : chosen.abbr();
        String number = decimals > 0 ? intPart + "." + frac : intPart;
        return (isNeg ? "-" : "") + number + " " + label;
    }
}
