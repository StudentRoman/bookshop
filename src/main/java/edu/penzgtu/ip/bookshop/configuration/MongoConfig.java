package edu.penzgtu.ip.bookshop.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "edu.penzgtu.ip.bookshop.repositories")
public class MongoConfig {

}