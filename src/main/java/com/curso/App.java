package com.curso;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.curso.config.BeansConfiguration;

/**
 * Spring Core Hello world!
 *
 */
public class App {

  private final static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
          BeansConfiguration.class);
  private static final User user = applicationContext.getBean("genericUser", User.class);
  private static final UserService userService = applicationContext.getBean("userService", UserService.class);
  private static final Utils utils = new Utils(userService);

  public static void main(String[] args) {
    utils.processMenuOption(user);
  }

}
