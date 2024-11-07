package com.example.asmt2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView addressInput;
    private Button goButton, addAddressButton, allAddressesButton;
    private AddressRepository addressRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addressInput = findViewById(R.id.address_input);
        goButton = findViewById(R.id.go_button);
        addAddressButton = findViewById(R.id.add_address_button);
        allAddressesButton = findViewById(R.id.all_addresses_button);
        addressRepository = new AddressRepository(getApplication());

        // Populate autocomplete suggestions from the database
        List<Address> addressList = addressRepository.getAllAddresses();
        List<String> addressNames = new ArrayList<>();
        for (Address address : addressList) {
            addressNames.add(address.name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, addressNames);
        addressInput.setAdapter(adapter);

        // OnClickListener for the Go button
        goButton.setOnClickListener(view -> {
            String addressText = addressInput.getText().toString();
            if (!addressText.isEmpty()) {
                Address selectedAddress = getAddressByName(addressText);
                if (selectedAddress != null) {
                    double latitude = selectedAddress.latitude;
                    double longitude = selectedAddress.longitude;
                    Toast.makeText(MainActivity.this, "Lat: " + latitude + " Long: " + longitude, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Address not found in database.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Please enter an address.", Toast.LENGTH_SHORT).show();
            }
        });

        // Navigate to AddAddressActivity
        addAddressButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddAddressActivity.class);
            startActivity(intent);
        });

        // Navigate to AllAddressesActivity
        allAddressesButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AllAddressesActivity.class);
            startActivity(intent);
        });
    }

    // Helper method to find address by name
    private Address getAddressByName(String name) {
        for (Address address : addressRepository.getAllAddresses()) {
            if (address.name.equalsIgnoreCase(name)) {
                return address;
            }
        }
        return null;
    }
}
