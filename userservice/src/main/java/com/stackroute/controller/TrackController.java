package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@PropertySources({
        //@PropertySource(value = "classpath:application-dev.properties"),
        @PropertySource(value = "classpath:application.properties")
        })

@RestController
@RefreshScope
@RequestMapping(value = "/api/v1")
public class TrackController {
    //@Qualifier("userServiceDummyImpl")
    TrackService trackService;
    @Autowired
    public TrackController(  TrackService trackService) {
        this.trackService = trackService;
    }

    /* create new resource -- post*/
    @PostMapping("/track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
       // trackService.saveTrack(track);
        try {
            return new ResponseEntity<Track>(trackService.saveTrack(track), HttpStatus.CREATED);
        }
        catch (TrackAlreadyExistsException e)
        {
            System.out.println("inside Message method");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /*  Show all resources */
    @GetMapping("/track")
    public ResponseEntity<?> getAllTrack() throws Exception{

            return new ResponseEntity<List<Track>>(trackService.getAllTrack(), HttpStatus.OK);

    }
    /* Find given resource by Id */
    @GetMapping("/track/{id}")
    public ResponseEntity<?> getTrackById( @PathVariable int id) throws TrackNotFoundException {

           return new ResponseEntity<Track>(trackService.getByID(id), HttpStatus.OK);

    }
    /* Find given resource by name */
    @GetMapping("track/find/{trackName}")
    public ResponseEntity<?> getTrackByName( @PathVariable String trackName) throws Exception{
//        try {
            return new ResponseEntity<List<Track>>(trackService.getByName(trackName), HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
//        }
    }

    /* update given  response */
    @PutMapping("/track/{id}/{comment}")
    public  ResponseEntity<?> updateTrack(@PathVariable int id, @PathVariable String comment) throws TrackNotFoundException
    {
        return new ResponseEntity<Track>(trackService.updateTrack(id, comment), HttpStatus.OK);

    }
    /* Delete all  resources */
    @DeleteMapping("/track")
    public ResponseEntity<?> deleteAllTrack() throws Exception
    {
            return new ResponseEntity<List<Track>>(trackService.deleteAllTrack(), HttpStatus.OK);
    }
    /* Delete given resources */
    @DeleteMapping("/track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id) throws TrackNotFoundException{

            return new ResponseEntity<Track>(trackService.deleteById(id), HttpStatus.OK);
    }

}
