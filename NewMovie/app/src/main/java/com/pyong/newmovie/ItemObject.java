package com.pyong.newmovie;

public class ItemObject {
    private String title;
    private String genre;
    private String img_url;
    private String detail_link;
    private String release;
    private String rating;
    private String reservation;
    private String director;

    public ItemObject(String title, String genre, String img_url, String detail_link, String release, String rating, String reservation, String director) {
        this.title = title;
        this.genre = genre;
        this.img_url = img_url;
        this.detail_link = detail_link;
        this.release = release;
        this.rating = rating;
        this.reservation = reservation;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getDetail_link() {
        return detail_link;
    }

    public String getRelease() {
        return release;
    }

    public String getRating() {
        return rating;
    }

    public String getReservation() {
        return reservation;
    }

    public String getDirector() {
        return director;
    }
}
