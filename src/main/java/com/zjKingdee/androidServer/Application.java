package com.zjKingdee.androidServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
//@MapperScan("com.kingdee.MES.dao")
@ImportResource("classpath:transaction.xml")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
