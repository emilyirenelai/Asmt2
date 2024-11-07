package com.example.asmt2;

import android.app.Application;
import androidx.room.Room;
import java.util.List;

public class AddressRepository {
    private AddressDao addressDao;

    public AddressRepository(Application application) {
        AddressDatabase db = Room.databaseBuilder(application, AddressDatabase.class, "address-database")
                .allowMainThreadQueries()
                .build();
        addressDao = db.addressDao();
    }

    public void insert(Address address) { addressDao.insert(address); }
    public void update(Address address) { addressDao.update(address); }
    public void delete(Address address) { addressDao.delete(address); }
    public Address getAddressById(int id) { return addressDao.getAddressById(id); }
    public List<Address> getAllAddresses() { return addressDao.getAllAddresses(); }
}
