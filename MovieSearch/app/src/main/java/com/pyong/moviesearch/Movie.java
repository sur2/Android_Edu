package com.pyong.moviesearch;

public class Movie {

    private String title;
    private String directer;
    private String pubDate;
    private String actor;
    private String image;
    private String userRating;

    public Movie(String title, String directer, String pubDate, String actor, String image, String userRating) {

        this.title = title;
        this.directer = directer;
        this.actor = actor;
        this.image = image;
        this.pubDate = pubDate;
        this.userRating = userRating;
    }

    public String getTitle() {
        return title;
    }

    public String getDirecter() {
        return directer;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getActor() {
        return actor;
    }

    public String getImage() {
        return image;
    }

    public String getUserRating() {
        return userRating;
    }
}
