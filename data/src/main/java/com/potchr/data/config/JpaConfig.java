package com.potchr.data.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.jdbc.datasource.lookup.SingleDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManagerFactory;
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
@EnableJpaRepositories(basePackages = "com.potchr.data.user", entityManagerFactoryRef = "userEntityManager", transactionManagerRef = "userTransactionManager")
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
	public LocalContainerEntityManagerFactoryBean userEntityManager(@Qualifier("userDataSource") DataSource userDataSource)
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//		DefaultPersistenceUnitManager persistenceUnitManager = new DefaultPersistenceUnitManager();
//		entityManagerFactoryBean.setPersistenceUnitManager(persistenceUnitManager);
//		persistenceUnitManager.setDataSourceLookup(new SingleDataSourceLookup(userDataSource));
//		persistenceUnitManager.setDefaultDataSource(userDataSource);
		entityManagerFactoryBean.setDataSource(userDataSource);
//		persistenceUnitManager.setPackagesToScan("com.potchr.data.user");
		entityManagerFactoryBean.setPackagesToScan("com.potchr.data.user");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(jpaProperties.isShowSql());
		vendorAdapter.setGenerateDdl(jpaProperties.isGenerateDdl());
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		HibernateProperties hibernateProperties = new HibernateProperties();
		Map<String,Object> jpaPropertyMap = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings().ddlAuto(() -> "update"));
		entityManagerFactoryBean.setJpaPropertyMap(jpaPropertyMap);
		entityManagerFactoryBean.setPersistenceUnitName("default-user");
		return entityManagerFactoryBean;
	}

	@Bean("userDataSource")
	@Primary
	public DataSource userDataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(jdbcDriverClassName);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		return dataSource;
	}

	@Bean
	@Primary
	public PlatformTransactionManager userTransactionManager(@Qualifier("userEntityManager") LocalContainerEntityManagerFactoryBean userEntityManager)
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(userEntityManager.getObject());
		return transactionManager;
	}
}
