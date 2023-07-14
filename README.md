# Hack day - JUnit5 - Tam Pham

### Table of contents:
<ul style="list-style: none">
    <li><a href="#title_1">1. Overview about Unit Testing</a></li>
    <li><a href="#title_2">2. Difference between JUnit5 and JUnit4</a></li>
    <li><a href="#title_3">3. UTD, UTS, PICT, CSV Source, Method Source</a></li>
    <li><a href="#title_4">4. Update existing project to JUnit5</a></li>
    <li><a href="#title_5">5. Reference documents</a></li>
</ul>

<h2 id="title_1">1. Overview about Unit Testing</h2>

### About testing flow:

<div style="width: 100%;display: flex;justify-content: center;">
    <img src="https://github.com/tampnoptimizely/junit5-hackday/blob/master/docs/images/testing_flow.png?raw=true" alt="tam pham hack day">
</div>
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
Unit testing involves the testing of each unit or an individual component of the software application. It is the first level of functional testing. The aim behind unit testing is to validate unit components with their performance.

A unit is a single testable part of a software system and is tested during the development phase of the application software.

The purpose of UT is to test the correctness of isolated code. A unit component is an individual function or code of the application. The white box testing approach is used for unit testing and is usually done by the developers.

Whenever the application is ready and given to the Test engineer, he/she will start checking every component of the module or module of the application independently or one by one, and this process is known as Unit testing or components testing.

### Why do we need Unit Testing?
In a testing level hierarchy, UT is the first level of testing done before integration and the other remaining levels of the testing.
It uses modules for the testing process which reduces the dependency on waiting for UT frameworks, stubs, drivers and mock objects are
used for assistance in UT.

Generally, the software goes under four levels of testing: UT, IT, ST, and AT but sometimes
due to time consumption, software engineers do minimal UT but skipping UT may lead to higher defects during IT, ST, AT 
or even during Beta testing which takes place after the completion of the software application.

Some crucial reasons are listed below:
<ul>
    <li>Unit testing helps testers and developers to understand the base of code that makes them able to change defect-causing code quickly.</li>
    <li>Unit testing helps in the documentation.</li>
    <li>Unit testing fixes defects very early in the development phase that's why there is a possibility to occur a smaller number of defects in upcoming testing levels.</li>
    <li>It helps with code re-usability by migrating code and test cases.</li>
</ul>

### Types of Unit Testing
In UT, each unit of code is tested independently to ensure that it behaves as expected, without any dependency on other units. Unit tests typically focus on a small piece of 
functionality and are automated, allowing for efficient and repeatable testing. There are several types of unit testing, each with its own advantages 
and use cases. But we usually know about 3 types of UT: white-box testing, black-box testing, and gray-box testing.
<a href="https://www.javatpoint.com/types-of-unit-testing" target="_blank">Detailed for UT types</a>
### How to Unit Testing?
Ok, let's go!

<h2 id="title_2">2. Difference between JUnit5 and JUnit4</h2>
JUnit 5 aims to adapt the Java8 style of coding and to be more robust and flexible than JUnit 4. 
### Some differences in Annotations:
<table><tbody><tr><th>Feature</th><th>JUnit 4</th><th>Junit 5</th></tr><tr><td>Declare a test method</td><td><code>@Test</code></td><td><code>@Test</code></td></tr><tr><td>Execute before all test methods in the current class</td><td><code>@BeforeClass</code></td><td><code><a href="https://howtodoinjava.com/junit5/before-all-annotation-example/">@BeforeAll</a></code></td></tr><tr><td>Execute after all test methods in the current class</td><td><code>@AfterClass</code></td><td><code><a href="https://howtodoinjava.com/junit5/after-all-annotation-example/">@AfterAll</a></code></td></tr><tr><td>Execute before each test method</td><td><code>@Before</code></td><td><code><a href="https://howtodoinjava.com/junit5/before-each-annotation-example/">@BeforeEach</a></code></td></tr><tr><td>Execute after each test method</td><td><code>@After</code></td><td><code><a href="https://howtodoinjava.com/junit5/after-each-annotation-example/">@AfterEach</a></code></td></tr><tr><td>Disable a test method/class</td><td><code>@Ignore</code></td><td><code><a href="https://howtodoinjava.com/junit5/junit-5-disabled-test-example/">@Disabled</a></code></td></tr><tr><td>Test factory for dynamic tests</td><td>NA</td><td><code>@TestFactory</code></td></tr><tr><td>Nested tests</td><td>NA</td><td><code>@Nested</code></td></tr><tr><td>Tagging and filtering</td><td><code>@Category</code></td><td><code><a href="https://howtodoinjava.com/junit5/junit-5-tag-annotation-example/">@Tag</a></code></td></tr><tr><td>Register custom extensions</td><td>NA</td><td><code>@ExtendWith</code></td></tr></tbody></table>

Other differences in architecture, requirements, assertions, assumptions, etc. Following <a href="https://howtodoinjava.com/junit5/junit-5-vs-junit-4/" target="_blank">More differences between JUnit 5 and JUnit 4</a>

<h2 id="title_3">3. UTD, UTS, PICT, CSV Source, Method Source</h2>
In JUnit 4, each test case needs to create a test method. However, with JUnit 5, we can execute so many test cases with only a test method by using @ParameterizedTest instead of @Test. 
With the Parameterized test, this test method will be executed so many times depending on testing resource input. Each test param will be run as an independent test case. 

For example:
We have a method want to test:
```java
@Service
public class ExampleService {
    public String showHttpStatus(Integer statusCode) {
        if (Objects.isNull(statusCode)) {
            throw new NullPointerException();
        }
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        return httpStatus.name();
    }
}
```
We can define some test cases for this method:
```text
1. If statusCode = null -> Throw NullPointerException()
2. If statusCode = 200  -> return "OK"
3. If statusCode = 100  -> return "CONTINUE"
4. If statusCode = 302  -> return "FOUND"
5. If statusCode = 400  -> return "BAD_REQUEST"
6. If statusCode = 500  -> return "INTERNAL_SERVER_ERROR"
7. If statusCode = 600  -> Throw IllegalArgumentException()
```

Let's create a test method with JUnit 4:
```java
@RunWith(JUnit4.class)
public class ExampleServiceTestJUnit4 {
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
}
```
We have 7 cases in definition and need to create 7 test methods for them. And then, let's try JUnit5 and @ParameterizedTest.
For details, see `src/test/java/com/optimizely/junit5hackday/ExampleServiceTest.java`
```java
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExampleServiceTest {

    @Autowired
    ExampleService exampleService;

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
}
```
OK! It looks more convenient than using JUnit4, right?
However, we need to create a parameter source for the test. Param source can be csv source, method source or etc. But we need to create a test resource before.
With csv source, we need to create UTD (Unit testing design) file and then use the PICT tool to generate to UTS (Unit testing specification) file.

_Download PICT tool for Window <a href="https://github.com/microsoft/pict/releases/" target="_blank">PICT.exe</a> or execute this cmd to download for MacOS:_
```shell
brew install pict
```

For this test spec, we can create UTD file similar:
```textmate
# Input
CODE: null, 100, 200, 302, 400, 500, 600

# Output
STATUS:  NullPointerException, OK, CONTINUE, FOUND, BAD_REQUEST, INTERNAL_SERVER_ERROR, IllegalArgumentException

IF [CODE] = "null" THEN [STATUS] = "NullPointerException";
IF [CODE] = "100" THEN [STATUS] = "CONTINUE";
IF [CODE] = "200" THEN [STATUS] = "OK";
IF [CODE] = "302" THEN [STATUS] = "FOUND";
IF [CODE] = "400" THEN [STATUS] = "BAD_REQUEST";
IF [CODE] = "500" THEN [STATUS] = "INTERNAL_SERVER_ERROR";
IF [CODE] = "600" THEN [STATUS] = "IllegalArgumentException";
```
UTD(Unit testing design) file will encompass all cases for this test, and then we use PICT to generate to UTS file (<a href="https://github.com/microsoft/pict/blob/main/doc/pict.md" target="_blank">PICT syntax</a>)

Example on Mac:
```shell
pict UTD/ExampleServiceUTD.txt > UTS/ExampleServiceUTS.txt
```

Example on Window:
```shell
pict.exe UTD/ExampleServiceUTD.txt > UTS/ExampleServiceUTS.txt
```
Here is UTS output file:
```textmate
CODE	STATUS
null	NullPointerException
400	BAD_REQUEST
200	OK
100	CONTINUE
500	INTERNAL_SERVER_ERROR
600	IllegalArgumentException
302	FOUND
```
And now, we can customize this UTS file to have a CSV source for the UT method in JUnit5. If we don't want to create UTD, UTS, run PICT, etc. 
Because it has so many steps and inconveniences, we are able to use another way, @MethodSource. 

```java
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExampleServiceTest {

    @Autowired
    ExampleService exampleService;

    @ParameterizedTest
    @MethodSource(value = "showHttpStatusTestData")
    void showHttpStatusTest_methodSource(Integer statusCode, String expectedResult) {
        try {
            String status = exampleService.showHttpStatus(statusCode);
            Assertions.assertEquals(expectedResult, status);
        } catch (Exception ex) {
            Assertions.assertEquals(expectedResult, ex.getClass().getSimpleName());
        }
    }
    private Stream<Arguments> showHttpStatusTestData() {
        return Stream.of(
                Arguments.of(null, "NullPointerException"),
                Arguments.of(200, "OK"),
                Arguments.of(100, "CONTINUE"),
                Arguments.of(302, "FOUND"),
                Arguments.of(400, "BAD_REQUEST"),
                Arguments.of(500, "INTERNAL_SERVER_ERROR"),
                Arguments.of(600, "IllegalArgumentException")
        );
    }
}
```
Done, it looks like easier than using CSV Source (^-^). Besides, we have some other ways as CsvFileSource, ArgumentsSource, 
but I think MethodSource is the most convenience than others.

<h2 id="title_4">4. Update existing project to JUnit5</h2>
Currently, we have a JUnit4 testing class:
```java
@RunWith(JUnit4.class)
public class TestParserJ4 {

    @Test
    public void testMoneyDollar()
    {
        final CompiledExpression e = Parser.parse("$425.80");
        final Literal l = (Literal) e;
        assertEquals(425.80d, l.getDoubleValue());
        assertEquals("$", l.getCurrency());
        assertEquals(LiteralType.MONEY, l.getType());
    }

    @Test
    public void testMoneyPounds()
    {
        final Literal l = (Literal) Parser.parse("£425.80");
        assertEquals(425.80d, l.getDoubleValue());
        assertEquals("£", l.getCurrency());
        assertEquals(LiteralType.MONEY, l.getType());
    }

    @Test
    public void testMoneyPoundsWithDblQuotes()
    {
        final Literal l = (Literal) Parser.parse("\"£425.80\"");
        assertNotNull(l);
        assertEquals("£", l.getCurrency());
        assertEquals(425.80d, l.getDoubleValue());
        assertEquals(LiteralType.MONEY, l.getType());
    }

    @Test
    public void testMoneyEuro()
    {
        final Literal l = (Literal) Parser.parse("€425.80");
        assertEquals(425.80d, l.getDoubleValue());
        assertEquals("€", l.getCurrency());
        assertEquals(LiteralType.MONEY, l.getType());
    }
}
```
Let's install JUnit 5 in this project and try with it. Maven dependencies (for more dependencies <a href="https://mvnrepository.com/" target="_blank">mvnrepository</a>):
```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.9.3</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>5.3.1</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-junit-jupiter</artifactId>
        <version>5.3.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
```java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestParserJ5 {

    @ParameterizedTest
    @MethodSource(value = "testParseMoneyData")
    void testParseMoney(String moneyStr, double expectedMoney, String expectedCur) {
        final Literal literal = (Literal) Parser.parse(moneyStr);
        assertNotNull(literal);
        assertEquals(expectedMoney, literal.getDoubleValue());
        assertEquals(expectedCur, literal.getCurrency());
        assertEquals(LiteralType.MONEY, literal.getType());
    }

    private Stream<Arguments> testParseMoneyData() {
        return Stream.of(
                Arguments.of("$425.80", 425.80d, "$"),
                Arguments.of("€425.80", 425.80d, "€"),
                Arguments.of("£425.80", 425.80d, "£"),
                Arguments.of("\"£425.80\"", 425.80d, "£")
        );
    }
}
```
An additional, we can completely use both JUnit4 and JUnit5 in the same project.

<h2 id="title_5">5. Reference documents</h2>
<ul>
    <li><a href="https://www.javatpoint.com/unit-testing" target="_blank">What is Unit Testing?</a></li>
    <li><a href="https://howtodoinjava.com/junit5/junit-5-vs-junit-4/" target="_blank">Difference between JUnit 5 an JUnit 4</a></li>
    <li><a href="https://www.javatpoint.com/types-of-unit-testing" target="_blank">Types of Unit Testing</a></li>
    <li><a href="https://github.com/microsoft/pict/blob/main/doc/pict.md" target="_blank">Pairwise Independent Combinatorial Testing tool</a></li>
</ul>
