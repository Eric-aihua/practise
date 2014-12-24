package com.eric.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class FormattedMemoryInput {
    public static void main(String[] args) throws Exception {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(new ReadFileTools().readByBufferReader(
                "E:\\sourcecode\\corejava\\src\\com\\eric\\io\\test.avi").getBytes()));
        while(dis.available()>0){
            System.out.println((char)dis.read());
        }
        
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
