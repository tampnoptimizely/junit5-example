package com.optimizely.junit5hackday;

import com.optimizely.junit5hackday.service.ExampleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

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
