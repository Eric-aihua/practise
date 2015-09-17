package com.tscloud.datagather.persistence;

import com.tscloud.common.phoenix.PhoenixUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Eric on 2015/9/10.
 */
public class FileRecordPersistenceMySqlImpl implements IFileRecordPersistence {
    private String connStr;
    private String driverName;
    private String userName;
    private String passwd;
    private Statement statement;

    public FileRecordPersistenceMySqlImpl(String connStr, String driverName, String userName, String passwd) {
        String url="jdbc:mysql://localhost:3306/sample_db?user=root&password=your_password";
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(url);
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        this.connStr = connStr;

    }

    @Override
    public boolean persist(String insertSQL) {
        boolean insertSuccessful=true;
        try {
            statement.executeUpdate(insertSQL);
        } catch (Exception ex) {
            insertSuccessful=false;
           ex.printStackTrace();
        }
        return insertSuccessful;
    }
}
