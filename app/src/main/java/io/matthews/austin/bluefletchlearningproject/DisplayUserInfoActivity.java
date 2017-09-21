package io.matthews.austin.bluefletchlearningproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayUserInfoActivity extends AppCompatActivity {

    private final String BASE_URL = "https://bfapp-bfsharing.rhcloud.com/";
    private LoginResult result;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_info);

        Bundle bundle = getIntent().getExtras();

        String postJSON = bundle.getString("posts");

        Gson gson = new Gson();

        Post[] post = gson.fromJson(postJSON, Post[].class);

        List<Post> postList = Arrays.asList(post);

        String postString = postList.get(0).getPostText();

        System.out.println(postString);

        //GET POST STRINGS and display them on this activity view

        /*
        String imgURL = BASE_URL + result.getImageUrl();
        imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load(imgURL).into(imageView);
        */

    }
}
