package com.example.youtube;

public class MediaObject {
    private String title;
    private String media_url;
    private String thumbnail;

    public MediaObject(String title, String media_url, String thumbnail) {  //class for videos on the list
        this.title = title;
        this.media_url = media_url;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getMedia_url() {
        return media_url;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
