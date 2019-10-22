package com.stackroute.myconfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("my-values")
public class MyConfigurationProperty {
   private int id;
   private String trackName;
   private String trackComments;

}