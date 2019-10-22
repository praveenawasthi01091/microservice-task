package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

public interface TrackService {
    public Track saveTrack(Track user) throws TrackAlreadyExistsException;

    public List<Track> getAllTrack();

    public Track getByID(int id) throws TrackNotFoundException;

    public Track updateTrack(int id, String comment) throws TrackNotFoundException;

    public List<Track> deleteAllTrack();

    public Track deleteById(int id) throws TrackNotFoundException;

    public List<Track> getByName(String trackName);
}
