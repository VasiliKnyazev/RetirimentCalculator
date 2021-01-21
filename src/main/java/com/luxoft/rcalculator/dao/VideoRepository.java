package com.luxoft.rcalculator.dao;

import com.luxoft.rcalculator.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Integer> {

    @Query(value = "select title from videos", nativeQuery = true)
    List<String> findAllTitles();

    @Query(value = "select * from videos where genre = :genre", nativeQuery = true)
    List<Video> findByGenre(String genre);

    @Query(value = "select * from videos where format = :format", nativeQuery = true)
    List<Video> findByFormat(String format);

    @Query(value = "select * from videos where title = :title", nativeQuery = true)
    Video findByTitle(String title);

}
