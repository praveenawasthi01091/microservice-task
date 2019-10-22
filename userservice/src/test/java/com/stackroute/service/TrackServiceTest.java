package com.stackroute.service;

/* we are going to use trackrepository  instance here so We  have to mock the object for testing of this
* class   */
import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {

    private Track track;
    //Create a mock for UserRepository
    @Mock
    private TrackRepository trackRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private TrackServiceDummyImpl trackService;
    List<Track> list= null;


    @Before
    public void setUp() throws Exception {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setTrackName("trackName");
        track.setId(101);
        track.setTrackComments("trackComment");
        list = new ArrayList<>();
        list.add(track);
    }



    @Test
    public void saveTrackSuccess() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track)any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository,times(1)).save(track);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackFailure() throws TrackAlreadyExistsException{
        when(trackRepository.save((Track)any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository,times(1)).save(track);
    }

    @Test
    public void getAllTrackSuccess() {
        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist = trackService.getAllTrack();
        Assert.assertEquals(list,tracklist);
    }

    @Test
    public void getAllTrackFailure() {
        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(null);
        List<Track> tracklist = trackService.getAllTrack();
        Assert.assertNotEquals(list,tracklist);
    }

    @Test(expected = TrackNotFoundException.class)
    public void getByIDFailure() throws TrackNotFoundException {
        Track track1=new Track(102,"asdf","sdfg");
        trackRepository.save(track1);
        when(trackRepository.findById(102)).thenReturn(java.util.Optional.ofNullable(track));
        Track track2=trackService.getByID(track1.getId());
        assertEquals(track1,track);
    }

    @Test(expected = TrackNotFoundException.class)
    public void updateTrackFailure() throws TrackNotFoundException,TrackAlreadyExistsException{
        when(trackRepository.save((Track)any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        savedTrack.setTrackComments("asdf");
         Track track1=trackService.updateTrack(104,"asdf");
        assertEquals(track,savedTrack);
    }

    @Test
    public void deleteAllTrack() {
       // trackRepository.save(track);
        //stubbing the mock to return specific data
        list.clear();
        List<Track> tracklist = trackService.deleteAllTrack();
        Assert.assertEquals(list,tracklist);
    }

//    @Test
//    public void deleteById() {
//    }

//    @Test
//    public void getByName() {
//    }
}