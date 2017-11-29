package spring.test.rest.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScans(value = { @ComponentScan("spring.test.rest"),
		@ComponentScan("spring.test.dao"),
		@ComponentScan("spring.test.mapper")})
public class SpringRestConfiguration {
	

}