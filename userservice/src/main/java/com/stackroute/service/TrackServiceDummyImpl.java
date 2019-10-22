package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Profile("dummy")
// mvn clean
// mvn spring-boot:run -Dspring.profiles.active=dummy

public class TrackServiceDummyImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceDummyImpl(TrackRepository trackRepository) {
        System.out.println("Inside dummy impl");
        this.trackRepository = trackRepository;
    }


    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getId()))
            throw new TrackAlreadyExistsException("Track id is already exists");

        Track savedTrack= trackRepository.save(track);
        if(savedTrack == null)
            throw new TrackAlreadyExistsException("Track id is  already exists");
        return savedTrack;
    }

    @Override
    public List<Track> getAllTrack() {

        System.out.println("Inside dummy impl");
        return trackRepository.findAll();
    }

    @Override
    public Track getByID(int id) throws TrackNotFoundException
    {
        if(!trackRepository.existsById(id))
            throw new TrackNotFoundException("Track not found!");
        return (trackRepository.findById(id).get());
    }

    @Override
    public Track updateTrack(int id, String comment) throws TrackNotFoundException
    {
        if(!trackRepository.existsById(id))
            throw new TrackNotFoundException("Track not found");
        Track updatedUser=getByID(id);
        updatedUser.setTrackComments(comment);
        trackRepository.save(updatedUser);
        if(updatedUser == null)
            throw new TrackNotFoundException("Track not found");
        return updatedUser;
    }

    @Override
    public List<Track> deleteAllTrack()
    {
        List<Track>list=trackRepository.findAll();
        trackRepository.deleteAll();
        return list;
    }

    @Override
    public Track deleteById(int id) throws TrackNotFoundException
    {
        if(!trackRepository.existsById(id))
            throw new TrackNotFoundException("Track not found");
        Track track= trackRepository.findById(id).get();
        trackRepository.delete(trackRepository.findById(id).get());
        return track;
    }

    @Override
    public List<Track> getByName(String trackName)
    {
        List<Track>list= trackRepository.findByName(trackName);
        return list;
    }
}