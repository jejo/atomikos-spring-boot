package com.test.atomikos.config;

/**
 * Created by jdimayuga on 30/08/2017.
 */

import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "com.test.atomikos.repo.ds2", entityManagerFactoryRef = "entityManagerFactory2", transactionManagerRef = "transactionManager")
public class Db2DataSourceConfig {

    @Value("${spring.datasource.driver-class-name2}")
    String driverClass;
    @Value("${spring.datasource.url2}")
    String url;
    @Value("${spring.datasource.username2}")
    String userName;
    @Value("${spring.datasource.password2}")
    String passWord;


    @Bean(name = "dataSource2", initMethod = "init", destroyMethod = "close")
    public DataSource dataSource2() {
        System.out.println("dataSource2 init");
        PGXADataSource pgxaDataSource = new PGXADataSource();

        pgxaDataSource.setUrl(url);
        pgxaDataSource.setPassword(passWord);
        pgxaDataSource.setUser(userName);
        //pgxaDataSource.setPinGlobalTxToPhysicalConnection(true);
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(pgxaDataSource);
        xaDataSource.setUniqueResourceName("dataSource2");
        xaDataSource.setMinPoolSize(10);
        xaDataSource.setPoolSize(10);
        xaDataSource.setMaxPoolSize(30);
        xaDataSource.setBorrowConnectionTimeout(60);
        xaDataSource.setReapTimeout(20);
        xaDataSource.setMaxIdleTime(60);
        xaDataSource.setMaintenanceInterval(60);
        return xaDataSource;
    }

    @Bean(name = "jpaVendorAdapter2")
    public JpaVendorAdapter jpaVendorAdapter() {
        System.out.println("jpaVendorAdapter2 init");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setDatabase(Database.POSTGRESQL);
        adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Bean(name = "entityManagerFactory2")
    @DependsOn({"atomikosJtaPlatform"})
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        System.out.println("entityManagerFactory2 init");
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJpaVendorAdapter(jpaVendorAdapter());

        entityManager.setPackagesToScan("com.test.atomikos.model.ds2");
        entityManager.setJtaDataSource(dataSource2());
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.current_session_context_class", "jta");
        properties.put("hibernate.transaction.factory_class", "org.hibernate.engine.transaction.internal.jta.CMTTransactionFactory");
        properties.put("hibernate.transaction.jta.platform", "com.test.atomikos.config.AtomikosJtaPlatform");

        entityManager.setJpaProperties(properties);
        return entityManager;
    }

}
