package com.dj;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = "com.dj")
@EnableJpaRepositories
@EnableTransactionManagement
public class OAuthServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplicationBuilder app = new SpringApplicationBuilder(OAuthServerApplication.class);
		app.run();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OAuthServerApplication.class);
	}
}
