package com.qipai.api;

@FunctionalInterface
public interface Processor {
    int getStringLength(String str);

    default int getLengStest(String arg){
        return arg.length();
    }
}
