package com.example;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// JUnit Advanced - Exercise 2: Test Suites
@Suite
@SelectClasses({
    CalculatorTest.class,
    AssertionsTest.class,
    AAAPatternTest.class,
    EvenCheckerTest.class,
    OrderedTests.class,
    ExceptionThrowerTest.class,
    PerformanceTesterTest.class
})
public class AllTests {
    // Suite runner - groups all test classes
}
