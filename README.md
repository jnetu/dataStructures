# java data structures
### created by [jneto](https://github.com/jnetu)  
Data Structures in Java  
## Overview
This repository contains a collection of fundamental data structures implemented in Java.   
It's designed for learning about basic data structures and practicing Java.
## Using the Data Structures

This repository contains a collection of fundamental data structures implemented in Java. Below, you'll find usage instructions and notes for using these data structures.

### Data Structure Interface
All data structures in this repository implement the `DataStructure` interface, which provides common operations for adding, removing, checking emptiness, and obtaining the size of the data structure. The interface is defined as follows:

```java
package net.jneto.dataStructures;

/**
 * This interface represents a generic data structure that can store objects. It
 * defines common operations for adding, removing, checking emptiness, and
 * obtaining the size of the data structure.
 *
 * @param <ITEM> The type of objects to be stored in the data structure.
 * @author joao neto
 */
public interface DataStructure<ITEM> {
    // Methods described below...
}
```


The `DataStructure` interface defines a set of common methods that are available in all data structures implemented in this repository. These methods allow you to interact with the data structures and perform essential operations.

### Available Methods

Here's a brief overview of the methods available in the `DataStructure` interface:

- `void add(ITEM item)`: Adds an object to the data structure.
- `ITEM remove()`: Removes and returns an object from the data structure.
- `boolean isEmpty()`: Checks if the data structure is empty.
- `int size()`: Returns the size (number of objects) in the data structure.
- `ITEM peek()`: Returns the first object that will be removed but does not remove it.
- `String show()`: Returns a string representation of the data structure.
- `String showReverse()`: Returns a string representation of the reversed data structure.
- `String toString()`: Returns a string representation of the Stack size and internal array size.

### Usage Notes

When working with specific data structures such as Stack, Queue, and Bag, it's important to note that method names have been customized to match their intended functionality.  
For example, a Stack does not have `push` and `pop` methods but instead uses `add` and `remove` methods. These method names align with the `DataStructure` interface, making it easier to work with different data structures consistently.



## Custom Comparators

This repository provides the flexibility to use custom comparators for ordering elements in data structures like Priority Queue. You can create your own comparators to define how elements are compared and prioritized.

### Comparator Interface

In the package `net.jneto.dataStructures.Comparator`, you'll find the `Comparator` interface defined as follows:

```java
package net.jneto.dataStructures.Comparator;

public interface Comparator<ITEM>{
    int compare(ITEM t1, ITEM t2);
}
```
This interface allows you to create custom comparators by implementing the `compare` method, which defines the logic for comparing two elements.

### Example Usage
```java

import net.jneto.dataStructures.Comparator.Comparator;
public class Main {
    public static void main(String[] args) {
        Comparator<String> stringComparator = new Comparator<String>() {
            @Override
            public int compare(String t1, String t2) {
                if (t1.length() < t2.length()) { // If bigger - more priority
                    return 1;
                } else if (t1.length() > t2.length()) { // Else - less priority
                    return -1;
                }
                return 0; // Zero if they are equal in length
            }
        };

        PriorityQueue<String> pq = new PriorityQueue<String>(stringComparator);
        pq.add("n1");
    }
}
```
In the above example, we create a custom comparator `stringComparator` that prioritizes `String` elements based on their length.  
You can define your own custom logic within the `compare` method to create comparators tailored to your specific needs.

This flexibility allows you to use custom comparators to control the ordering of elements in data structures, ensuring that they meet your application's requirements.


### Another way with custom comparator Classes
This repository provides the flexibility to use custom comparators for ordering elements in data structures like Priority Queue. You can create your own comparators to define how elements are compared and prioritized.  
For example, in the package `net.jneto.dataStructures.Comparator`, you'll find a class named `BiggerStringLengthComparator` that compares `String` elements based on their length:
```java

import net.jneto.dataStructures.Comparator.BiggerStringLengthComparator;

public class Main {
    public static void main(String[] args) {
        Comparator<String> cp = new BiggerStringLengthComparator(); //another way into Comparator Interface example
        PriorityQueue<String> pq = new PriorityQueue<String>(stringComparator);
        pq.add("n1");
    }
}
```
In the code above, we import the `BiggerStringLengthComparator` class and use it as a custom comparator for prioritizing String elements based on their length.

## Creating Custom Comparator Classes
Additionally, you can create your own custom comparator classes within the `net.jneto.dataStructures.Comparator` package. This allows you to define specific comparison logic for your data types. For example, you can create a class like this:
```java

package net.jneto.dataStructures.Comparator;

/**
 * EXAMPLE USAGE - Custom Comparator for a custom data type
 * Define your custom comparison logic here.
 */
public class MyCustomComparator implements Comparator<CustomDataType> {
    @Override
    public int compare(CustomDataType t1, CustomDataType t2) {
        // Define your comparison logic for CustomDataType objects
    }
}
```
You can then use your custom comparator class in the same way as shown above, providing it to the data structure that requires custom sorting.  



## Releases

Feel free to explore the provided releases to access JAR files for different versions of this library:

- **Release 1.0**:
    - [Download JAR file DataStructures-1.0-release.jar](https://github.com/jnetu/dataStructures/releases/tag/release-1.0)
    - Basic Resizing Array structure Stack, Queue, and Bag created.

- **Release 1.1**:
    - [Download JAR file DataStructures-1.1-release.jar](https://github.com/jnetu/dataStructures/releases/tag/release-1.1)
    - Resizing array List basic methods, Resizing array PriorityQueue, and Deque created.

These releases provide pre-built JAR files for different versions of the library, allowing you to easily incorporate these data structures into your Java projects.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- [Gradle](https://gradle.org/install/) installed. (or use gradle-wrapper)
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) installed.

## Getting Started

make sure you have git installed and PATH configured

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/jnetu/dataStructures.git
   ```
2. Navigate to the directory created:

   ```bash
   cd dataStructures
   ```

## Running the Project on Windows
1. To build the project, use the following Gradle command on project directory:
   ```bash
   .\gradlew build
   ```

2. Will create a .jar file into build/libs/DataStructures-1.0-release.jar
   ```bash
   java -jar .\libs\DataStructures-1.1-release.jar
   ```
   this command will show a short demonstration main


## Running the Project on linux/macOS
1. To build the project, use the following Gradle command:
   ```bash
   gradle build
   ```

2. To run the project, use the following Gradle command:
   ```bash
   java -jar /libs/DataStructures-1.1-release.jar
   ```

## Create javadoc
   ```bash
   .\gradlew javadoc
   ```
   ```bash
   gradle javadoc
   ```