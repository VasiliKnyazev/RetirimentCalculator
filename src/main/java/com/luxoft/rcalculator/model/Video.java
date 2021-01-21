package com.luxoft.rcalculator.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Size(min = 1)
    @Column(name = "url", nullable = false)
    private String url;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "genre", nullable = false)
    private String genre;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "format", nullable = false)
    private String format;

    public Video() {}

    public Video(Integer id, @NotNull @Size(min = 2, max = 100) String title, @NotNull @Size(min = 1) String url, @NotNull @Size(min = 1, max = 30) String genre, @NotNull @Size(min = 1, max = 30) String format) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.genre = genre;
        this.format = format;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
