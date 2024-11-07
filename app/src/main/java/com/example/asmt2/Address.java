package com.example.asmt2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


@Entity(tableName = "address_table")
public class Address {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public double longitude;
    public double latitude;

    // No-argument constructor (Room needs this too)
    public Address() {}

    // Parameterized constructor
    public Address(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
