package ru.danileyko.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.danileyko.dao.HelloDAO;
import ru.danileyko.dao.HelloDAOImpl;
import ru.danileyko.model.Hello;
import ru.danileyko.service.HelloService;
import ru.danileyko.service.HelloServiceImpl;

import javax.activation.DataSource;
import javax.persistence.Column;
import java.util.Properties;


/**
 * Created by danil on 27.10.2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.danileyko")
public class WebMVCConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/views/**").addResourceLocations("/views/");
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

// Подключение к базе данных.

    @Bean(name = "dataSource")
    public BasicDataSource getDataSource()
    {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://109.123.152.119:3306/contactmanager");
        dataSource.setUsername("contactmanager");
        dataSource.setPassword("contactmanager");
        return dataSource;
    }
//Подключение Hibernate. Создание SessionFactory.
    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean getLocalSessionFactorybean()
    {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(getDataSource());
        localSessionFactoryBean.setPackagesToScan(new String[] {"ru.danileyko.model"});
        Properties properties = new Properties();
        properties.setProperty("annotatedClasses","ru.danileyko.model");
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql","true");
        return localSessionFactoryBean;
    }

    @Bean
    @Autowired
    public  HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
    {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Autowired
    @Bean(name = "helloDAO")
    public HelloDAO helloDAO(SessionFactory sessionFactory)
    {
        HelloDAOImpl helloDAO = new HelloDAOImpl();
        helloDAO.setSessionFactory(sessionFactory);
        return helloDAO;
    }

    @Autowired
    @Bean(name = "helloService")
    public HelloService helloService(HelloDAO helloDAO)
    {
        HelloServiceImpl helloService = new HelloServiceImpl();
        helloService.setHelloDAO(helloDAO);
        return helloService;
    }

}

