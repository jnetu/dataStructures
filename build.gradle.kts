plugins {
    id("java")

}

group = "net.jneto"
version = "1.2-snapshot"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "net.jneto.dataStructures.Main"
    }
}
