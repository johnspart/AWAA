/**
 * 
 */
package org.awaa.spring.config;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author john.lopez
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.awaa.*")
public class WebConfig {

	@Bean
	public SessionFactory sessionFactory() throws IOException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setPackagesToScan("org.awaa.orm", "org.awaa.orm.administracion");
		sessionFactory.setHibernateProperties(this.getHibernateProperties());
		sessionFactory.afterPropertiesSet();
		return sessionFactory.getObject();
	}

	@Bean
	public HibernateTransactionManager transactionManager() throws IOException {
		return new HibernateTransactionManager(this.sessionFactory());
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	public Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.hbm2ddl.auto", "update");
		prop.put("hibernate.format_sql", "false");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		prop.put("hibernate.default_schema", "public");
		prop.put("hibernate.default_catalog", "AWAA");
		prop.put("hibernate.max_fetch_depth", "1");
		prop.put("hibernate.connection.datasource", "java:/ds_awaa");
		prop.put("hibernate.connection.release_mode", "AFTER_TRANSACTION");
		prop.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JTATransactionFactory");
		prop.put("hibernate.transaction.manager_lookup_class",
				"org.hibernate.transaction.JBossTransactionManagerLookup");
		return prop;
	}
}
