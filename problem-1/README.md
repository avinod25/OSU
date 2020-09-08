<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)

<!-- ABOUT THE PROJECT -->
## About The Project

 * [Problem 1] 
 Given 2 strings, merge them in an alternate way, i.e. the final string’s first character is the first character of the longer string, the second character of the final string is the first character of the shorter string and so on. And if once you reach end of the shorter string while if the longer string is still remaining then append the remaining of that longer string to final string. If the length of both strings are equal, then the final string’s first character is the first character of the first string.
 
```Examples:
 
Input: string 1: "lobster"
       string 2: "like"
Output: "lloibkseter"
Explanation : The answer contains characters from alternate strings, and once 
the second shorter string ends the remaining of the first longer string is added to the final string
 
Input: string 1: "hello"
       string 2: "lobster"
Output: lhoeblsltoer
 
Input: string 1: "gourmet"
       string 2: "lobster"
Output: glooubrsmteetr
```

### Built With

* [Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

<!-- GETTING STARTED -->
## Getting Started

To get a local copy of the project up and running, follow these simple steps:

1. Clone the repo

2. Import problem-1 project into eclipse workspace

3. Build project with maven

```sh
mvn clean package
```

4. Run AlternateMergeTest test case

```sh
com.osu.demo.problem1.AlternateMergeTest
```
5. Test case will invoke alternateMergeStrings method from AlternateMerge class

```sh
com.osu.demo.problem1.AlternateMerge
```
6. Update input data and re-run test

