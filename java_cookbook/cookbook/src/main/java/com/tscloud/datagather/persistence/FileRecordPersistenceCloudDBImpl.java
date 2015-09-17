package com.tscloud.datagather.persistence;

import com.tscloud.common.phoenix.PhoenixUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Eric on 2015/9/10.
 */
public class FileRecordPersistenceCloudDBImpl implements IFileRecordPersistence {
    private String connStr;
    private JdbcTemplate jdbcTemplate;

    public FileRecordPersistenceCloudDBImpl(String connStr) {
        this.connStr = connStr;
        jdbcTemplate = PhoenixUtils.getInstance().getPhoenixJdbcTemplate(connStr, 30);
    }

    @Override
    public boolean persist(String insertSQL) {
        boolean insertSuccessful=true;
        try {
            jdbcTemplate.update(insertSQL);
        } catch (Exception ex) {
            insertSuccessful=false;
           ex.printStackTrace();
        }
        return insertSuccessful;
    }
}
