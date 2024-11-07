package com.example.asmt2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditAddressActivity extends AppCompatActivity {
    private EditText nameInput, longitudeInput, latitudeInput;
    private Button updateButton, deleteButton;
    private AddressRepository addressRepository;
    private Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        nameInput = findViewById(R.id.name_input);
        longitudeInput = findViewById(R.id.longitude_input);
        latitudeInput = findViewById(R.id.latitude_input);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);

        addressRepository = new AddressRepository(getApplication());

        int addressId = getIntent().getIntExtra("address_id", -1);
        address = addressRepository.getAddressById(addressId);

        if (address != null) {
            nameInput.setText(address.name);
            longitudeInput.setText(String.valueOf(address.longitude));
            latitudeInput.setText(String.valueOf(address.latitude));
        } else {
            Toast.makeText(this, "Address not found", Toast.LENGTH_SHORT).show();
            finish();
        }

        updateButton.setOnClickListener(view -> {
            address.name = nameInput.getText().toString();
            address.longitude = Double.parseDouble(longitudeInput.getText().toString());
            address.latitude = Double.parseDouble(latitudeInput.getText().toString());
            addressRepository.update(address);
            Toast.makeText(this, "Address updated", Toast.LENGTH_SHORT).show();
            finish();
        });

        deleteButton.setOnClickListener(view -> {
            addressRepository.delete(address);
            Toast.makeText(this, "Address deleted", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
