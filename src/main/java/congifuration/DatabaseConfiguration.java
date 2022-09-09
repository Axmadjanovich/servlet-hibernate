package congifuration;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


public class DatabaseConfiguration {

    private static DatabaseConfiguration databaseConfiguration;
    private static SessionFactory sessionFactory;
    private static Session session;

    private Properties properties;

    private DatabaseConfiguration(){
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            System.out.println("Error while reading properties file");
        }
    }

    public static DatabaseConfiguration getInstance(){
        if (databaseConfiguration != null){
            return databaseConfiguration;
        }

        databaseConfiguration = new DatabaseConfiguration();
        return databaseConfiguration;
    }

    public DataSource postgres(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getProperty("database.driver.classname")); // org.postgresql.Driver
        dataSource.setPassword(properties.getProperty("database.password"));
        dataSource.setUsername(properties.getProperty("database.username"));
        dataSource.setUrl(properties.getProperty("database.url"));

        return dataSource;
    }

    public static SessionFactory getSessionFactory(){
        if (sessionFactory != null){
            return sessionFactory;
        }

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate/hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(ssr).buildMetadata();

        sessionFactory = metadata.buildSessionFactory();

        return sessionFactory;
    }

    public static Session getSession(){
        if (session != null){
            return session;
        }
        if (sessionFactory == null){
            getSessionFactory();
        }

        session = sessionFactory.openSession();

        return session;
    }
}
