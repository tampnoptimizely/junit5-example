## Hack day - JUnit5 - Tam Pham

## Table of contents:
<ul style="list-style: circle;">
    <li><a href="#title_1">1. Overview about UnitTest</a></li>
    <li><a href="#title_2">2. Difference between JUnit5 and JUnit4</a></li>
    <li><a href="#title_3">3. UTD, UTS, PICT, CSV source, Stream Source</a></li>
    <li><a href="#title_4">4. Mockito in JUnit5, BeforeAll, AfterAll, BeforeEach, AfterEach</a></li>
</ul>

<h2 id="title_1">1. Overview about UnitTest</h2>
### About testing flow:
![](https://github.com/tampnoptimizely/junit5-hackday/blob/master/docs/images/testing_flow.png?raw=true)
<br/>

<p><i><b>Note: </b> This document will focus on JUnit5 's function and other concepts about testing, coverage, etc. would be the most basic </i></p>

<b>Acronyms may appear in the article: </b>
<ul>
    <li>UT - Unit Testing</li>
    <li>IT - Integration Testing</li>
    <li>ST - System Testing</li>
    <li>AT - Acceptance Testing</li>
    <li>UTD - Unit Testing Design</li>
    <li>UTS - Unit Testing Spec</li>
</ul>

### What's Unit Testing?
Unit testing involves the testing of each unit or an individual component of the software application. It is the first level of functional testing. The aim behind unit testing is to validate unit components with its performance.

A unit is a single testable part of a software system and tested during the development phase of the application software.

The purpose of UT is to test the correctness of isolated code. A unit component is an individual function or code of the application. White box testing approach used for unit testing and usually done by the developers.

Whenever the application is ready and given to the Test engineer, he/she will start checking every component of the module or module of the application independently or one by one, and this process is known as Unit testing or components testing.

### Why do we need Unit Testing?
In a testing level hierarchy, UT is the first level of testing done before integration and other remaining levels of the testing.
It uses modules for the testing process which reduces the dependency of waiting for UT frameworks, stubs, drivers and mock objects are
used for assistance in UT.

Generally, the software goes under four level of testing: UT, IT, ST, and AT but sometimes
due to time consumption software engineers does minimal UT but skipping of UT may lead to higher defects during IT, ST, AT 
or even during Beta testing which takes place after the completion of software application.


Some crucial reasons are listed below:
<ul>
    <li>Unit testing helps tester and developers to understand the base of code that makes them able to change defect causing code quickly.</li>
    <li>Unit testing helps in the documentation.</li>
    <li>Unit testing fixes defects very early in the development phase that's why there is a possibility to occur a smaller number of defects in upcoming testing levels.</li>
    <li>It helps with code re-usability by migrating code and test cases.</li>
</ul>

### Types of UnitTesting
In UT, each unit of code is tested independently to ensure that is behaves as expected, without any dependency on other units. Unit tests typically focus on a small piece of 
functionality and are automated, allowing for efficient and repeatable testing. There are several types of unit testing, each with its own advantages 
and use cases. But we usually know about 3 types of UT: white-box testing, black-box testing, and gray-box testing.
<a href="https://www.javatpoint.com/types-of-unit-testing" target="_blank">Detailed for UT types</a>
### How to Unit Testing?
Ok, let's go!

<h2 id="title_2">2. Difference between JUnit5 and JUnit4</h2>
JUnit 5 aims to adapt the Java8 style of coding and to be more robust and flexible than JUnit 4. 
### Some differences in Annotations:
<table><tbody><tr><th>Feature</th><th>JUnit 4</th><th>Junit 5</th></tr><tr><td>Declare a test method</td><td><code>@Test</code></td><td><code>@Test</code></td></tr><tr><td>Execute before all test methods in the current class</td><td><code>@BeforeClass</code></td><td><code><a href="https://howtodoinjava.com/junit5/before-all-annotation-example/">@BeforeAll</a></code></td></tr><tr><td>Execute after all test methods in the current class</td><td><code>@AfterClass</code></td><td><code><a href="https://howtodoinjava.com/junit5/after-all-annotation-example/">@AfterAll</a></code></td></tr><tr><td>Execute before each test method</td><td><code>@Before</code></td><td><code><a href="https://howtodoinjava.com/junit5/before-each-annotation-example/">@BeforeEach</a></code></td></tr><tr><td>Execute after each test method</td><td><code>@After</code></td><td><code><a href="https://howtodoinjava.com/junit5/after-each-annotation-example/">@AfterEach</a></code></td></tr><tr><td>Disable a test method/class</td><td><code>@Ignore</code></td><td><code><a href="https://howtodoinjava.com/junit5/junit-5-disabled-test-example/">@Disabled</a></code></td></tr><tr><td>Test factory for dynamic tests</td><td>NA</td><td><code>@TestFactory</code></td></tr><tr><td>Nested tests</td><td>NA</td><td><code>@Nested</code></td></tr><tr><td>Tagging and filtering</td><td><code>@Category</code></td><td><code><a href="https://howtodoinjava.com/junit5/junit-5-tag-annotation-example/">@Tag</a></code></td></tr><tr><td>Register custom extensions</td><td>NA</td><td><code>@ExtendWith</code></td></tr></tbody></table>

Other differences in architecture, required, assertions, assumptions, etc. Following <a href="https://howtodoinjava.com/junit5/junit-5-vs-junit-4/" target="_blank">More differences between JUnit 5 and JUnit 4</a>

<h2 id="title_3">3. UTD, UTS, PICT, CSV source, Stream Source</h2>
In JUnit 4, foreach test case need to create a test method. However, with JUnit 5, we can execute so many test case with only test method by using @ParameterizedTest instead of @Test. 
With Parameterized test, this test method will be executed so many times depend on testing resource input. Each test param will be run as an independent test case. 

For example:
We have a method want to test:
```java
    public String showHttpStatus(Integer statusCode) {
        if (Objects.isNull(statusCode)) {
            throw new NullPointerException();
        }
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        return httpStatus.name();
    }
```
We can define some test case for this method:
```text
1. If statusCode = null -> Throw NullPointerException()
2. If statusCode = 200  -> return "OK"
3. If statusCode = 100  -> return "CONTINUE"
4. If statusCode = 302  -> return "FOUND"
5. If statusCode = 400  -> return "BAD_REQUEST"
6. If statusCode = 500  -> return "INTERNAL_SERVER_ERROR"
7. If statusCode = 600  -> Throw IllegalArgumentException()
```

Let's create test method with JUnit 4:
```java
    @Test(expected = NullPointerException.class)
    public void testShowHttpStatus_inputNull() {
        exampleService.showHttpStatus(null);
    }

    @Test
    public void testShowHttpStatus_OK() {
        String status = exampleService.showHttpStatus(200);
        Assert.assertEquals("OK", status);
    }

    @Test
    public void testShowHttpStatus_CONTINUE() {
        String status = exampleService.showHttpStatus(100);
        Assert.assertEquals("CONTINUE", status);
    }

    @Test
    public void testShowHttpStatus_FOUND() {
        String status = exampleService.showHttpStatus(302);
        Assert.assertEquals("FOUND", status);
    }

    @Test
    public void testShowHttpStatus_BAD_REQUEST() {
        String status = exampleService.showHttpStatus(400);
        Assert.assertEquals("BAD_REQUEST", status);
    }

    @Test
    public void testShowHttpStatus_INTERNAL_SERVER_ERROR() {
        String status = exampleService.showHttpStatus(500);
        Assert.assertEquals("INTERNAL_SERVER_ERROR", status);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShowHttpStatus_inputInvalid() {
        exampleService.showHttpStatus(600);
    }
```
We have 7 cases in definition and need to create 7 test method for them. And then, let's try JUnit5 and @ParameterizedTest.
For detailed, you can see in `src/test/java/com/optimizely/junit5hackday/ExampleServiceTest.java`
```java
    @ParameterizedTest
    @CsvSource({
            ", NullPointerException",
            "200, OK",
            "100, CONTINUE",
            "302, FOUND",
            "400, BAD_REQUEST",
            "500, INTERNAL_SERVER_ERROR",
            "600, IllegalArgumentException"
    })
    void showHttpStatusTest(Integer statusCode, String expectedResult) {
        try {
            String status = exampleService.showHttpStatus(statusCode);
            Assertions.assertEquals(expectedResult, status);
        } catch (Exception ex) {
            Assertions.assertEquals(expectedResult, ex.getClass().getSimpleName());
        }
    }
```
OK! It is look like more convenience than using JUnit4, right?
However, we need to create parameter source for test. Param source can be csv source, stream source or etc. But we need create a test resource before.
With csv source, we need to create UTD (Unit testing design) file and use PICT tool 
(<a href="https://github.com/microsoft/pict/releases/" target="_blank">Download PICT</a>) to generate to UTS (Unit testing specification) file
For example:


<h2 id="title_4">4. Mockito in JUnit5, BeforeAll, AfterAll, BeforeEach, AfterEach</h2>
