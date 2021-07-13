package com.potchr.data.config;

import com.google.common.base.Preconditions;
import org.apache.shardingsphere.core.yaml.swapper.MasterSlaveRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.core.yaml.swapper.ShardingRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.encrypt.yaml.swapper.EncryptRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.shardingjdbc.api.EncryptDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.spring.boot.common.SpringBootPropertiesConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.encrypt.SpringBootEncryptRuleConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.masterslave.SpringBootMasterSlaveRuleConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.sharding.SpringBootShardingRuleConfigurationProperties;
import org.apache.shardingsphere.spring.boot.util.DataSourceUtil;
import org.apache.shardingsphere.spring.boot.util.PropertyUtil;
import org.apache.shardingsphere.underlying.common.config.inline.InlineExpressionParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.ConstructorProperties;
import java.sql.SQLException;
import java.util.*;

@Configuration
@EnableConfigurationProperties({SpringBootShardingRuleConfigurationProperties.class, SpringBootMasterSlaveRuleConfigurationProperties.class, SpringBootEncryptRuleConfigurationProperties.class,
        SpringBootPropertiesConfigurationProperties.class})
@ConditionalOnProperty(prefix = "spring.shardingsphere", name = {"enabled"}, havingValue = "true", matchIfMissing = true)
@EnableJpaRepositories(basePackages = "com.potchr.data.ccode", entityManagerFactoryRef = "customerEntityManager", transactionManagerRef = "customerTransactionManager")
public class CustomerShardingConfig implements EnvironmentAware {
    private final SpringBootShardingRuleConfigurationProperties shardingProperties;
    private final SpringBootMasterSlaveRuleConfigurationProperties masterSlaveProperties;
    private final SpringBootEncryptRuleConfigurationProperties encryptProperties;
    private final SpringBootPropertiesConfigurationProperties propMapProperties;
    private final Map<String, DataSource> dataSourceMap = new LinkedHashMap();
    private final ShardingRuleConfigurationYamlSwapper shardingSwapper = new ShardingRuleConfigurationYamlSwapper();
    private final MasterSlaveRuleConfigurationYamlSwapper masterSlaveSwapper = new MasterSlaveRuleConfigurationYamlSwapper();
    private final EncryptRuleConfigurationYamlSwapper encryptSwapper = new EncryptRuleConfigurationYamlSwapper();
    @Autowired
    private JpaProperties jpaProperties;

    @ConstructorProperties({"shardingProperties", "masterSlaveProperties", "encryptProperties", "propMapProperties"})
    public CustomerShardingConfig(SpringBootShardingRuleConfigurationProperties shardingProperties, SpringBootMasterSlaveRuleConfigurationProperties masterSlaveProperties,
                                  SpringBootEncryptRuleConfigurationProperties encryptProperties, SpringBootPropertiesConfigurationProperties propMapProperties) {
        this.shardingProperties = shardingProperties;
        this.masterSlaveProperties = masterSlaveProperties;
        this.encryptProperties = encryptProperties;
        this.propMapProperties = propMapProperties;
    }

    @Bean("customerEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManager(@Qualifier("customerDataSource") DataSource dataSource) throws SQLException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[]{"com.potchr.data.ccode"});
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HibernateProperties hibernateProperties = new HibernateProperties();
        Map<String, Object> jpaPropertyMap = new HashMap<>();
        jpaPropertyMap.putAll(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings().ddlAuto(() -> "update")));
        jpaPropertyMap.put("show-sql", true);
        em.setJpaPropertyMap(jpaPropertyMap);
        em.setPersistenceUnitName("default-bcode");
        return em;
    }

    @Bean("customerDataSource")
    public DataSource dataSource() throws SQLException {
        if (null != this.masterSlaveProperties.getMasterDataSourceName()) {
            return MasterSlaveDataSourceFactory.createDataSource(this.dataSourceMap, this.masterSlaveSwapper.swap(this.masterSlaveProperties), this.propMapProperties.getProps());
        } else {
            return !this.encryptProperties.getEncryptors().isEmpty() ?
                    EncryptDataSourceFactory.createDataSource((DataSource) this.dataSourceMap.values().iterator().next(), this.encryptSwapper.swap(this.encryptProperties), this.propMapProperties.getProps()) :
                    ShardingDataSourceFactory.createDataSource(this.dataSourceMap, this.shardingSwapper.swap(this.shardingProperties), this.propMapProperties.getProps());
        }
    }

    @Bean("customerNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("customerDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean("customerJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("customerDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    public final void setEnvironment(Environment environment) {
        String prefix = "spring.shardingsphere.datasource.";
        Iterator var3 = this.getDataSourceNames(environment, prefix).iterator();
        while (var3.hasNext()) {
            String each = (String) var3.next();
            try {
                this.dataSourceMap.put(each, this.getDataSource(environment, prefix, each));
            } catch (ReflectiveOperationException var6) {
                throw new RuntimeException("Can't find datasource type!", var6);
            }
        }
    }

    private List<String> getDataSourceNames(Environment environment, String prefix) {
        StandardEnvironment standardEnv = (StandardEnvironment) environment;
        standardEnv.setIgnoreUnresolvableNestedPlaceholders(true);
        return null == standardEnv.getProperty(prefix + "name") ?
                (new InlineExpressionParser(standardEnv.getProperty(prefix + "names"))).splitAndEvaluate() :
                Collections.singletonList(standardEnv.getProperty(prefix + "name"));
    }

    private DataSource getDataSource(Environment environment, String prefix, String dataSourceName) throws ReflectiveOperationException {
        Map<String, Object> dataSourceProps = (Map) PropertyUtil.handle(environment, prefix + dataSourceName.trim(), Map.class);
        Preconditions.checkState(!dataSourceProps.isEmpty(), "Wrong datasource properties!");
        return DataSourceUtil.getDataSource(dataSourceProps.get("type").toString(), dataSourceProps);
    }

    @Bean("customerTransactionManager")
    public PlatformTransactionManager userTransactionManager(@Qualifier("customerEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactory) throws SQLException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }
}
