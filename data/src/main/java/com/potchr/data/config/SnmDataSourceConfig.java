package com.potchr.data.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/8/22 14:23</p>
 */
@Configuration
public class SnmDataSourceConfig
{
	@Value("${spring.datasource.snm.driver-class-name}")
	private String        jdbcDriverClassName;
	@Value("${spring.datasource.snm.url}")
	private String        jdbcUrl;
	@Value("${spring.datasource.snm.username}")
	private String        jdbcUsername;
	@Value("${spring.datasource.snm.password}")
	private String        jdbcPassword;

	@Bean("snmDataSource")
	public DataSource getDataSource()
	{
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(jdbcDriverClassName);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		dataSource.setMaximumPoolSize(50);
		return dataSource;
	}

	@Bean("snmJdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("snmDataSource") DataSource dataSource)
	{
		return new JdbcTemplate(dataSource);
	}

	@Bean("snmrNamedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("snmDataSource") DataSource dataSource)
	{
		return new NamedParameterJdbcTemplate(dataSource);
	}
}
