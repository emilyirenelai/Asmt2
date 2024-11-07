package com.example.asmt2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import java.util.List;

@Dao
public interface AddressDao {
    @Insert
    void insert(Address address);

    @Update
    void update(Address address);

    @Delete
    void delete(Address address);

    @Query("SELECT * FROM address_table")
    List<Address> getAllAddresses();

    // New method to get an address by its ID
    @Query("SELECT * FROM address_table WHERE id = :id LIMIT 1")
    Address getAddressById(int id);
}
