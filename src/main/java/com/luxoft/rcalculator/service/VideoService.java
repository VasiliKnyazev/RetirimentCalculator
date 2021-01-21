package com.luxoft.rcalculator.service;

import com.luxoft.rcalculator.model.Video;

import java.util.List;

public interface VideoService {

    List<Video> findAll();

    List<String> findAllTitles();

    List<Video> findByGenre(String genre);

    List<Video> findByFormat(String format);

    Video findById(int id);

    Video findByTitle(String title);

    void add(Video video);

    void deleteById(int videoId);

    void edit(Video video);

}
