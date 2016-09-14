package com.eric.tachyon;

import java.io.IOException;

import tachyon.TachyonURI;
import tachyon.client.FileOutStream;
import tachyon.client.InStream;
import tachyon.client.ReadType;
import tachyon.client.TachyonFS;
import tachyon.client.TachyonFile;
import tachyon.client.WriteType;
import tachyon.conf.TachyonConf;
 
/*
*
*   将0~9的数字写到tachyon文件/tmp/test，然后将写入的文件内容读出到控制台。
*
*/
public class Test {
    public static final TachyonURI masteruri = new TachyonURI("tachyon://192.168.116.128:19998");
    public static final TachyonURI filepath = new TachyonURI("/123455/test23334");
    public static WriteType writeType = WriteType.CACHE_THROUGH;
    public static ReadType readType = ReadType.CACHE;
 
    public static void writeFile() throws IOException
    {
        TachyonFS tachyonClient = TachyonFS.get(masteruri,new TachyonConf());
        if(tachyonClient.exist(filepath)) {
            tachyonClient.delete(filepath, true);
        }
        tachyonClient.createFile(filepath);
        TachyonFile file = tachyonClient.getFile(filepath);
        FileOutStream os = (FileOutStream) file.getOutStream(writeType);
        for(int i = 0; i < 10; i ++)
        {
            os.write(Integer.toString(i).getBytes());
        }
        os.close();
        tachyonClient.close();
    }
 
    public static void readFile() throws IOException
    {
        TachyonFS tachyonClient = TachyonFS.get(masteruri);
        TachyonFile file = tachyonClient.getFile(filepath);
        InStream in = file.getInStream(readType);
        byte[] bytes = new byte[20];
        in.read(bytes);
        System.out.println(new String(bytes));
        in.close();
        tachyonClient.close();
    }
     
    public static void main(String[] args) throws IOException
    {
        writeFile();
        readFile();
    }
}