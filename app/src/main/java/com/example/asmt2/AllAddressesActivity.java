package com.example.asmt2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AllAddressesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AddressAdapter addressAdapter;
    private AddressRepository addressRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_addresses);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addressRepository = new AddressRepository(getApplication());
        loadAddresses();
    }

    private void loadAddresses() {
        List<Address> addressList = addressRepository.getAllAddresses();
        addressAdapter = new AddressAdapter(addressList, this::onAddressClick, this::onAddressDelete);
        recyclerView.setAdapter(addressAdapter);
    }

    private void onAddressClick(Address address) {
        Intent intent = new Intent(AllAddressesActivity.this, EditAddressActivity.class);
        intent.putExtra("address_id", address.id);
        startActivity(intent);
    }

    private void onAddressDelete(Address address) {
        addressRepository.delete(address);
        loadAddresses();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAddresses();
    }
}
