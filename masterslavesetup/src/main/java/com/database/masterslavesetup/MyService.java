package com.database.masterslavesetup;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class MyService {

    private final JdbcTemplate masterJdbcTemplate;
    private final JdbcTemplate slaveJdbcTemplate;

    public MyService(@Qualifier("masterJdbcTemplate") JdbcTemplate masterJdbcTemplate,
                     @Qualifier("slaveJdbcTemplate") JdbcTemplate slaveJdbcTemplate) {
        this.masterJdbcTemplate = masterJdbcTemplate;
        this.slaveJdbcTemplate = slaveJdbcTemplate;
    }

    @Transactional(readOnly = true) //
    public List<String> getSomeDataFromSlave() {
        return slaveJdbcTemplate.queryForList("SELECT some_column FROM TABLE some_table", String.class);
    }

    @Transactional
    public void insertDataIntoMaster(String data) {
        masterJdbcTemplate.update("INSERT INTO some_table (some_column) VALUES (?)", data);
    }
}
