package org.wyttenbach.dale.groovy;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class GroovyBeanFactory {

  private static final Logger LOG = LoggerFactory.getLogger(GroovyBeanFactory.class);

  // this property determines the name of the .properties file to load
  private static final String CLAIMS_ENV = "claims.env";

  // this is the default .properties file to load
  private static final String DEFAULT_ENV = "local";

  private static ApplicationContext ctx;

  public final static Long DDPMN = 2L;

  public final static Long SECUR = 413L;

  public final static Long HORIZ = 47871L;

  public static String user1;

  public static String user2;

  public static Properties properties;

  static {
    try {
      // we are loading properties in Java and not in Spring config, because we want
      // them to be accessible from both
      setPropertyIfNotSet(CLAIMS_ENV, DEFAULT_ENV);
      properties = PropertiesLoaderUtils.loadAllProperties(System.getProperty(CLAIMS_ENV) + ".properties");
      for (Object key : properties.keySet()) {
        setPropertyIfNotSet((String) key, (String) properties.get(key)); 
      }
      
      // init spring
      ctx = new ClassPathXmlApplicationContext("remote-client.xml");
      
      // store a couple of properties in static vars for ease of use by scripts
      user1 = properties.getProperty("user1");
      user2 = properties.getProperty("user2");
    } catch (Exception e) {
      LOG.error(e.getLocalizedMessage(), e);
      System.exit(1);
    }
  }

  private static void setPropertyIfNotSet(String name, String value) {
    String currentValue = System.getProperty(name);
    if (currentValue != null) {
      LOG.warn("Not overriding property '{}={}' previously set", name, currentValue);
    } else {
      System.setProperty(name, value);
    }
  }

  public static Object getBean(String name) {
    Object bean = ctx.getBean(name);
    if (LOG.isDebugEnabled()) {
      LOG.debug(name + " => " + bean);
    }
    return bean;
  }

}
