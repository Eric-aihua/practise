package com.tscloud.datagather.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Eric on 2015/9/10.
 */
public class TableMetaData implements Serializable {
    private static final long serialVersionUID = -2538589615438366383L;
    private String tableName;
    private List<FileColumn> columnMapping;

    public TableMetaData() {
    }

    public TableMetaData(String tableName, List<FileColumn> columnMapping) {
        this.tableName = tableName;
        this.columnMapping = columnMapping;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<FileColumn> getColumnMapping() {
        return columnMapping;
    }

    public void setColumnMapping(List<FileColumn> columnMapping) {
        this.columnMapping = columnMapping;
    }


    @Override
    public String toString() {
        return "TableMetaData{" +
                "tableName='" + tableName + '\'' +
                ", columnMapping=" + columnMapping +
                '}';
    }
}
