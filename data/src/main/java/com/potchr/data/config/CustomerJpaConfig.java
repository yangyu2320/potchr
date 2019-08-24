//package com.potchr.data.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
///**
// * <p>标题：</p>
// * <p>功能：</p>
// * <p>
// * 其他说明：
// * </p>
// * <p>作者：yangy</p>
// * <p>审核：</p>
// * <p>重构：</p>
// * <p>创建日期：2019/8/20 11:50</p>
// */
//@Configuration
//@EnableJpaRepositories(basePackages = "com.potchr.data.ccode", entityManagerFactoryRef = "customerEntityManager", transactionManagerRef = "customerTransactionManager")
//public class CustomerJpaConfig
//{
//	@Value("${spring.datasource.user.driver-class-name}")
//	private String        jdbcDriverClassName;
//	@Value("${spring.datasource.bcode.url}")
//	private String        jdbcUrl;
//	@Value("${spring.datasource.user.username}")
//	private String        jdbcUsername;
//	@Value("${spring.datasource.user.password}")
//	private String        jdbcPassword;
//	@Autowired
//	private JpaProperties jpaProperties;
//
//	@Bean("customerEntityManager")
//	public LocalContainerEntityManagerFactoryBean entityManager()
//	{
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(userDataSource());
//		em.setPackagesToScan(new String[] { "com.potchr.data.ccode" });
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		em.setJpaVendorAdapter(vendorAdapter);
//		HibernateProperties hibernateProperties = new HibernateProperties();
//		Map<String,Object> jpaPropertyMap = new HashMap<>();
//		jpaPropertyMap.putAll(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings().ddlAuto(() -> "update")));
//		jpaPropertyMap.put("show-sql", true);
//		em.setJpaPropertyMap(jpaPropertyMap);
//		em.setPersistenceUnitName("default-bcode");
//		return em;
//	}
//
//	@Bean("customerDataSource")
//	public DataSource userDataSource()
//	{
//		HikariDataSource dataSource = new HikariDataSource();
//		dataSource.setDriverClassName(jdbcDriverClassName);
//		dataSource.setJdbcUrl(jdbcUrl);
//		dataSource.setUsername(jdbcUsername);
//		dataSource.setPassword(jdbcPassword);
//		dataSource.setMaximumPoolSize(50);
//		return dataSource;
//	}
//
//	@Bean("customerTransactionManager")
//	public PlatformTransactionManager userTransactionManager()
//	{
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(entityManager().getObject());
//		return transactionManager;
//	}
//}
