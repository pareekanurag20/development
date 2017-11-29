package spring.test.rest.configuration;
import static org.hibernate.cfg.Environment.C3P0_ACQUIRE_INCREMENT;
import static org.hibernate.cfg.Environment.C3P0_MAX_SIZE;
import static org.hibernate.cfg.Environment.C3P0_MAX_STATEMENTS;
import static org.hibernate.cfg.Environment.C3P0_MIN_SIZE;
import static org.hibernate.cfg.Environment.C3P0_TIMEOUT;
import static org.hibernate.cfg.Environment.DRIVER;
import static org.hibernate.cfg.Environment.HBM2DDL_AUTO;
import static org.hibernate.cfg.Environment.PASS;
import static org.hibernate.cfg.Environment.SHOW_SQL;
import static org.hibernate.cfg.Environment.URL;
import static org.hibernate.cfg.Environment.USER;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("spring.test.dao"),
      @ComponentScan("spring.test.rest"),
      @ComponentScan("spring.test.mapper")})
public class HibernateAppConfig {

	 @Autowired
	   private Environment env;

	   @Bean
	   public LocalSessionFactoryBean getSessionFactory() {
	      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

	      Properties props = new Properties();
	      // Setting JDBC properties
	      props.put(DRIVER, env.getProperty("mysql.driver"));
	      props.put(URL, env.getProperty("mysql.url"));
	      props.put(USER, env.getProperty("mysql.user"));
	      props.put(PASS, env.getProperty("mysql.password"));

	      // Setting Hibernate properties
	      props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
	      props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

	      // Setting C3P0 properties
	      props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
	      props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
	      props.put(C3P0_ACQUIRE_INCREMENT, 
	            env.getProperty("hibernate.c3p0.acquire_increment"));
	      props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
	      props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));

	      factoryBean.setHibernateProperties(props);
	      factoryBean.setPackagesToScan("spring.test");

	      return factoryBean;
	   }
	   
	   
	  /* @Bean
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory() 
	      throws NamingException {
	        LocalContainerEntityManagerFactoryBean em 
	          = new LocalContainerEntityManagerFactoryBean();
	        em.setDataSource(dataSource());
	         
	        // rest of entity manager configuration
	        return em;
	    }
	 
	    @Bean
	    public DataSource dataSource() throws NamingException {
	        return (DataSource) new JndiTemplate().lookup(env.getProperty("jdbc.url"));
	    }*/
	 

	   @Bean
	   public HibernateTransactionManager getTransactionManager() {
	      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	      transactionManager.setSessionFactory(getSessionFactory().getObject());
	      return transactionManager;
	   }
}
