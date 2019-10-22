package com.stackroute.seeddata;

import com.stackroute.domain.Track;
import com.stackroute.myconfig.MyConfigurationProperty;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties(MyConfigurationProperty.class)
@Component
public class CommandLineRunnerDemo implements CommandLineRunner {
   @Autowired
   private TrackRepository trackRepository;
   @Value("${id1}")
   private int Id;
   @Value("${name1}")
   private String trackName;
   @Value("${comment1}")
   private String comment;

   @Autowired
   private MyConfigurationProperty myConfigurationProperty;

   @Override
   public void run(String... args) throws Exception {
      System.out.println("inside runner");
      /* create track using @Value*/
       trackRepository.save(new Track(Id, trackName, comment));
       /* create track using configuration properties*/
       trackRepository.save(new Track(myConfigurationProperty.getId(),
               myConfigurationProperty.getTrackName(),myConfigurationProperty.getTrackComments()));
   }
}