package com.example.asmt2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddAddressActivity extends AppCompatActivity {
    private EditText nameInput, longitudeInput, latitudeInput;
    private Button addButton;
    private AddressRepository addressRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        nameInput = findViewById(R.id.name_input);
        longitudeInput = findViewById(R.id.longitude_input);
        latitudeInput = findViewById(R.id.latitude_input);
        addButton = findViewById(R.id.add_button);

        addressRepository = new AddressRepository(getApplication());

        addButton.setOnClickListener(view -> {
            String name = nameInput.getText().toString();
            String longitude = longitudeInput.getText().toString();
            String latitude = latitudeInput.getText().toString();

            if (!name.isEmpty() && !longitude.isEmpty() && !latitude.isEmpty()) {
                Address address = new Address(name, Double.parseDouble(longitude), Double.parseDouble(latitude));
                addressRepository.insert(address);
                Toast.makeText(this, "Address added successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
