package com.example.tourism.entity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserProfileDao {
    @Query("SELECT * FROM user_profile LIMIT 1")
    UserProfile getProfile();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProfile(UserProfile profile);

    @Query("UPDATE user_profile SET name = :name, about = :about, profileImageUri = :imageUri WHERE id = :id")
    void update(int id, String name, String about, String imageUri);

    @Query("DELETE FROM user_profile")
    void deleteAllProfiles();
}

