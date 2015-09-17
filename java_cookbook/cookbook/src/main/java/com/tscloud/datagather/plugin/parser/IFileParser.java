package com.tscloud.datagather.plugin.parser;

import java.util.ArrayList;

/**
 * Created by Eric on 2015/9/10.
 */
public interface IFileParser {

    public  ArrayList<ArrayList<String>> readExcel(String fileName,String path);

}
