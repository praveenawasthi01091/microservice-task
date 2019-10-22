package com.stackroute.repository;
/*  simple junit testing */
import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setUp() {
        track = new Track();
        track.setTrackName("name");
        track.setId(101);
        track.setTrackComments("comment");
    }

    @After
    public void tearDown(){
        trackRepository.deleteAll();
    }


    @Test
    public void testSaveTrack(){
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(101,fetchUser.getId());
    }

    @Test
    public void testSaveTrackFailure(){
        Track testUser = new Track(34,"Harry123","201");
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testUser,track);
    }

    @Test
    public void testGetAllTrack(){
        Track u = new Track(10,"Johny","Jenny");
        Track u1 = new Track(11,"Johny1","Jenny1");
        trackRepository.save(u);
        trackRepository.save(u1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Johny",list.get(0).getTrackName());

    }

    @Test
    public void testGetAllTrackFailure(){
        Track u = new Track(10,"Johny","Jenny");
        Track u1 = new Track(11,"Johny1","Jenny1");
        trackRepository.save(u);
        trackRepository.save(track);

        List<Track> list = trackRepository.findAll();
        Assert.assertNotEquals("Johny",list.get(1).getTrackName());
    }

    @Test
    public void testDeleteTrackSuccess(){
        Track u = new Track(10,"Johny","Jenny");
        Track u1 = new Track(11,"Johny1","Jenny1");
        trackRepository.save(u);
        trackRepository.save(u1);
        trackRepository.delete(u);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals(1,list.size());
    }

    @Test
    public void testDeleteTrackFailure(){
        Track u = new Track(10,"Johny","Jenny");
        Track u1 = new Track(11,"Johny1","Jenny1");
        trackRepository.save(u);
        trackRepository.save(u1);
        trackRepository.delete(track);

        List<Track> list = trackRepository.findAll();
        Assert.assertNotEquals(1,list.size());
    }

}
