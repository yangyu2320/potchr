package com.potchr.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/7/11 16:05</p>
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.potchr.data.user" }, entityManagerFactoryRef = "userEntityManager", transactionManagerRef = "userTransactionManager")
public class JpaConfig
{
	@Value("${spring.datasource.user.driver-class-name}")
	private String        jdbcDriverClassName;
	@Value("${spring.datasource.user.url}")
	private String        jdbcUrl;
	@Value("${spring.datasource.user.username}")
	private String        jdbcUsername;
	@Value("${spring.datasource.user.password}")
	private String        jdbcPassword;
	@Autowired
	private JpaProperties jpaProperties;

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean userEntityManager()
	{
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(userDataSource());
		em.setPackagesToScan(new String[] { "com.potchr.data.user.entity" });
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HibernateProperties hibernateProperties = new HibernateProperties();
		Map<String,Object> jpaPropertyMap = new HashMap<>();
		jpaPropertyMap.putAll(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings().ddlAuto(() -> "update")));
		jpaPropertyMap.put("show-sql", "true");
		em.setJpaPropertyMap(jpaPropertyMap);
		return em;
	}

	@Primary
	@Bean
	public DataSource userDataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(jdbcDriverClassName);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		return dataSource;
	}

	@Primary
	@Bean
	public PlatformTransactionManager userTransactionManager()
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(userEntityManager().getObject());
		return transactionManager;
	}
}
