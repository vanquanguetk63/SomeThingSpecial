package com.thptcualo.main.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thptcualo.main.databinding.ItemContainerBinding;
import com.thptcualo.main.models.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder>{

    private final List<User> users;

    public UsersAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerBinding itemContainerBinding = ItemContainerBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new UserViewHolder(itemContainerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.setUserData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        ItemContainerBinding binding;
        UserViewHolder(ItemContainerBinding itemContainerBinding){
            super(itemContainerBinding.getRoot());
            binding = itemContainerBinding;
        }

        void setUserData(User user){
            binding.TextName.setText(user.fullname);
            binding.Textemail.setText(user.email);
        }

    }
}
