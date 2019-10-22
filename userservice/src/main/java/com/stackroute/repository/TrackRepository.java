package com.stackroute.repository;

import com.stackroute.domain.Track;
/*
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
*/
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {
    /* the implementation will be provided to us in run time*/
    @Query("{ 'trackName' : ?0}")
    public List<Track> findByName(String trackName);

}
