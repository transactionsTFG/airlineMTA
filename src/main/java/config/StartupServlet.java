package config;

import integration.entitymanagerfactory.SingletonEntityManagerFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class StartupServlet implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SingletonEntityManagerFactory.getInstance(); //Inicializamos la BD
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        SingletonEntityManagerFactory.remove();
    }
}
