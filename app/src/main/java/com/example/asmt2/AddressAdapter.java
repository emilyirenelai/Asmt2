// AddressAdapter.java
package com.example.asmt2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private List<Address> addressList;
    private OnAddressClickListener clickListener;
    private OnAddressDeleteListener deleteListener;

    public AddressAdapter(List<Address> addressList, OnAddressClickListener clickListener, OnAddressDeleteListener deleteListener) {
        this.addressList = addressList;
        this.clickListener = clickListener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Address address = addressList.get(position);
        holder.nameTextView.setText(address.name);

        holder.editButton.setOnClickListener(view -> clickListener.onAddressClick(address));
        holder.deleteButton.setOnClickListener(view -> deleteListener.onAddressDelete(address));
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public interface OnAddressClickListener {
        void onAddressClick(Address address);
    }

    public interface OnAddressDeleteListener {
        void onAddressDelete(Address address);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        Button editButton;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            editButton = itemView.findViewById(R.id.edit_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}
