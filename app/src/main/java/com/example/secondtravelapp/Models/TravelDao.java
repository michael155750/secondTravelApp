package com.example.secondtravelapp.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TravelDao {
    @Query("select * from travels")
    LiveData<List<Travel>> getAll() throws Exception;

    @Query("select * from travels where travelId=:id")
    LiveData<Travel> get(String id) throws Exception;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Travel travel) throws Exception;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Travel> travels) throws Exception;

    @Update
    void update(Travel travel) throws Exception;

    @Delete
    void delete(Travel... travels) throws Exception;

    @Query("delete from travels")
    void clear() throws Exception;
}
