package com.optimizely.junit5hackday;

import com.optimizely.junit5hackday.service.ExampleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExampleServiceTest {

    ExampleService exampleService = new ExampleService();

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
