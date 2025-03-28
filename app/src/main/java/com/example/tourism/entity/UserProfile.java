package com.example.tourism.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_profile")
public class UserProfile {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String about;
    public String profileImageUri;

    public UserProfile(String name, String about, String profileImageUri) {
        this.name = name;
        this.about = about;
        this.profileImageUri = profileImageUri;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public String getAbout() { return about; }
    public String getProfileImageUri() { return profileImageUri; }

    public void setName(String name) { this.name = name; }
    public void setAbout(String about) { this.about = about; }
    public void setProfileImageUri(String profileImageUri) { this.profileImageUri = profileImageUri; }

}
