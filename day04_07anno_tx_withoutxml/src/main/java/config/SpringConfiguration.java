package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * spring配置类,相当于bean.xml
 */
@Configuration
@ComponentScan("es.kiwi")
@Import({JdbcConfig.class, TransactionManager.class})
@PropertySource("classpath:jdbcConfig.properties")
@EnableTransactionManagement
public class SpringConfiguration {
}
