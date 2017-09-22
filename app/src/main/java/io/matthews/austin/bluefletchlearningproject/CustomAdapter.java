package io.matthews.austin.bluefletchlearningproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by amatthews on 9/21/17.
 */

//Custom Adapter class for a list view to show User profile pictures, names, and posts
class CustomAdapter extends ArrayAdapter<Post>{

    String BASE_URL = "https://bfapp-bfsharing.rhcloud.com/";
    CustomAdapter(Context context, List<Post> posts){
        super(context, R.layout.list_view_items, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_view_items, parent, false);

        Post post = getItem(position);

        TextView postText = (TextView) customView.findViewById(R.id.label);
        TextView username = (TextView) customView.findViewById(R.id.name);
        ImageView image = (ImageView) customView.findViewById(R.id.icon);

        //Creates url for the profile picture
        String imgURL = BASE_URL + post.getPostUser().getImageUrl();
        Picasso.with(getContext()).load(imgURL).into(image);

        postText.setText(post.getPostText());
        username.setText(post.getPostUser().getUsername());

        return customView;
    }
}
