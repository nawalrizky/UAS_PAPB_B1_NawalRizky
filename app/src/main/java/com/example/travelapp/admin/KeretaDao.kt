package com.example.travelapp.admin

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface KeretaDao {
    @Query("SELECT * FROM Kereta")
    fun getAll(): List<Kereta>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(kereta: Kereta)

    @Update
    fun update(kereta: Kereta)

    @Delete
    fun delete(kereta: Kereta)
}