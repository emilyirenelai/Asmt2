package com.example.asmt2;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


@Database(entities = {Address.class}, version = 1)
public abstract class AddressDatabase extends RoomDatabase {
    public abstract AddressDao addressDao();
}
