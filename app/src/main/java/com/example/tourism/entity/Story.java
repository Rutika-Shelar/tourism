package com.example.tourism.entity;

public class Story {
    private String id;
    private String title;
    private String description;
    private String userName;
    private long timestamp;
    private String status;
    private String imageUrl;


    public Story() {
    }

    // Parameterized constructor
    public Story(String id, String title, String description, String userName, long timestamp, String status, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userName = userName;
        this.timestamp = timestamp;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
