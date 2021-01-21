package com.luxoft.rcalculator.controller.rest;

import com.luxoft.rcalculator.model.Video;
import com.luxoft.rcalculator.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VideoRestController {

    private VideoService videoService;

    public VideoRestController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/rest/videos")
    public ResponseEntity<List<Video>> findAll() {
        return new ResponseEntity<>(videoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/rest/videos/titles")
    public ResponseEntity<List<String>> findAllTitles() {
        List<String> titles = videoService.findAllTitles();
        return new ResponseEntity<>(titles, HttpStatus.OK);
    }

    @GetMapping("/rest/videos/{videoTitle}")
    public ResponseEntity<Video> findByTitle(@PathVariable String videoTitle) {
        return new ResponseEntity<>(videoService.findByTitle(videoTitle), HttpStatus.OK);
    }

    @DeleteMapping("/rest/videos/{videoId}")
    public ResponseEntity<Boolean> deleteVideo(@PathVariable int videoId) {
        videoService.deleteById(videoId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/rest/videos")
    public ResponseEntity<Video> addVideo(@Valid @RequestBody Video video) {
        videoService.add(video);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @PutMapping("/rest/videos")
    public ResponseEntity<Video> editVideo(@Valid @RequestBody Video video) {
        videoService.edit(video);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }

}
