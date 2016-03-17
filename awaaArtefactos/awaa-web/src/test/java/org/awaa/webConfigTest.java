package org.awaa;

import java.io.IOException;
import java.util.Properties;

import org.awaa.spring.config.WebConfig;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("org.awaa.*")
public class webConfigTest extends WebConfig {
	@Override
	@Bean
	public SessionFactory sessionFactory() throws IOException {
		LocalSessionFactoryBean sessionFactory = Mockito.mock(LocalSessionFactoryBean.class);
		sessionFactory.setPackagesToScan("org.awaa.orm", "org.awaa.orm.administracion");
		sessionFactory.setHibernateProperties(this.getHibernateProperties());
		sessionFactory.afterPropertiesSet();
		return sessionFactory.getObject();
	}

	@Override
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) throws IOException {
		return null;
	}

	@Override
	public Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.hbm2ddl.auto", "update");
		prop.put("hibernate.format_sql", "false");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		prop.put("hibernate.default_schema", "public");
		prop.put("hibernate.default_catalog", "AWAA");
		prop.put("hibernate.max_fetch_depth", "1");
		// prop.put("hibernate.connection.datasource", "java:/ds_awaa");
		prop.put("hibernate.connection.release_mode", "AFTER_TRANSACTION");
		prop.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");
		prop.put("hibernate.transaction.manager_lookup_class",
				"org.hibernate.transaction.JBossTransactionManagerLookup");
		return prop;
	}

}
