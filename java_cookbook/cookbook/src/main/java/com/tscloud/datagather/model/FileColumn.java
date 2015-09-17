package com.tscloud.datagather.model;

import java.io.Serializable;

/**
 * Created by Eric on 2015/9/10.
 */
public class FileColumn implements Serializable {
    private static final long serialVersionUID = -5941776465619815662L;
    private String fileFieldName;
    private String dbFieldName;

    public FileColumn(){
        super();
    }

    public FileColumn(String fileFieldName, String dbFieldName) {
        this.fileFieldName = fileFieldName;
        this.dbFieldName = dbFieldName;
    }

    public String getFileFieldName() {
        return fileFieldName;
    }

    public void setFileFieldName(String fileFieldName) {
        this.fileFieldName = fileFieldName;
    }

    public String getDbFieldName() {
        return dbFieldName;
    }

    public void setDbFieldName(String dbFieldName) {
        this.dbFieldName = dbFieldName;
    }

    @Override
    public String toString() {
        return "FileColumn{" +
                "fileFieldName='" + fileFieldName + '\'' +
                ", dbFieldName='" + dbFieldName + '\'' +
                '}';
    }
}
