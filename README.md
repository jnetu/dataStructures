# java data structures
### created by [jneto](https://github.com/jnetu)  
Data Structures in Java  
## Overview
This repository contains a collection of fundamental data structures implemented in Java.   
It's designed for learning about basic data structures and practicing Java.

release 1.0 -> [Download jar file DataStructures-1.0-release.jar](https://github.com/jnetu/dataStructures/blob/master/build/libs/DataStructures-1.0-release.jar)

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
   java -jar .\libs\DataStructures-1.0-release.jar
   ```
   this command will show a short demonstration main


## Running the Project on linux/macOS
1. To build the project, use the following Gradle command:
   ```bash
   gradle build
   ```

2. To run the project, use the following Gradle command:
   ```bash
   java -jar /libs/DataStructures-1.0-release.jar
   ```

## Create javadoc
   ```bash
   .\gradlew javadoc
   ```
   ```bash
   gradle javadoc
   ```