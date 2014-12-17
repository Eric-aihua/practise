package com.eric.io;

import com.eric.reflect.ExecuteTimerHandler;

public class StoringAndRecoveringData {
    public static void main(String[] args) throws Exception {
        IReadFileTools tools = (IReadFileTools) ExecuteTimerHandler.newInstance(new ReadFileTools());
        tools.storingAndRecoveringData("C:\\MKS\\PCE-AP\\dev\\corejava\\src\\com\\eric\\io\\test.data");
    }
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
