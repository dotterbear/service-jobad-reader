package com.dotterbear.jobad.reader.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

  @Value("${mongodb.host}")
  private String host;

  @Value("${mongodb.port}")
  private int port;

  @Value("${mongodb.auth.username}")
  private String authUserName;

  @Value("${mongodb.auth.password}")
  private String authPassword;

  @Value("${mongodb.auth.db}")
  private String authDb;

  @Value("${mongodb.db}")
  private String db;

  @Override
  protected String getDatabaseName() {
    return db;
  }

  @Override
  @Bean
  public MongoClient mongoClient() {
    return new MongoClient(new ServerAddress(host, port),
        MongoCredential.createCredential(authUserName, authDb, authPassword.toCharArray()),
        new MongoClientOptions.Builder().build());
  }

}
