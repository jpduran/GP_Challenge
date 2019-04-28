package com.example.gpandroidchallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class UsersAdapter extends ListAdapter<UserModel, UsersAdapter.UsersViewHolder> {

    private Context context;

    public UsersAdapter(Context context) {
        super(UserModel.DIFF_CALLBACK);
        this.context = context;

    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        UsersViewHolder usersViewHolder = new UsersViewHolder(view);
        return usersViewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        holder.bindTo(getItem(position));
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView avatar;

        private UsersViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.userNameTextView);
            avatar = itemView.findViewById(R.id.avatarImageView);
        }

        private void bindTo(UserModel user){
            name.setText(user.getFirstName() + " " + user.getLastName());

            String image_url = user.getAvatarURL();

            Picasso.get()
                    .load(image_url)
                    .placeholder(android.R.drawable.sym_def_app_icon)
                    .error(android.R.drawable.sym_def_app_icon)
                    .into(avatar);
        }
    }
}
