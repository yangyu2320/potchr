package com.potchr.data.snm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CcodeDAO {

    @Autowired
    @Qualifier("snmJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAll() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM ccode WHERE cname IS NOT NULL AND isimport IS NULL ORDER BY cname");
        return maps;
    }
}
