package com.csquare.sampleapp.data.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.csquare.sampleapp.model.Datum;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM UserInfo")
    List<Datum> getAllUser();

    @Insert
    (onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Datum> datumList);

}
