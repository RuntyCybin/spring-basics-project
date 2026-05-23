package com.curso;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.curso.config.BeansConfiguration;

/**
 * Spring Core Hello world!
 *
 */
public class App {

    private static final Utils utils = new Utils();
    private final static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
            BeansConfiguration.class);

    static RolAdmin rolAdmin = applicationContext.getBean(RolAdmin.class);
    static RolNormal rolNormal = applicationContext.getBean(RolNormal.class);
    static Users auxuser = applicationContext.getBean("users", Users.class);

    public static void main(String[] args) {

        utils.processMenuOption(auxuser);
    }

}
