package com.qipai.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MyIntegerCalculatorTest {
    @Test
    public void calcIt() throws Exception {
        MyIntegerCalculator myIntegerCalculator = (x)->x*2;
        log.debug("第一个lambda表达式- Result x2 : "+myIntegerCalculator.calcIt(3).toString());
    }
    @Test
    public void test() throws Exception {
        Processor stringProcessor = (str) -> str.length();
        String name = "Java Lambda";
        int length = stringProcessor.getStringLength(name);
        log.debug(String.valueOf(length));
    }
}