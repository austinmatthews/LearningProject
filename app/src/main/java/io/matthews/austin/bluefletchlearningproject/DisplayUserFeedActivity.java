package io.matthews.austin.bluefletchlearningproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import android.widget.ListView;

public class DisplayUserFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_feed);

        Bundle bundle = getIntent().getExtras();

        String postJSON = bundle.getString("posts");
        String loginJSON = bundle.getString("login");

        System.out.println(postJSON);

        Gson gson = new Gson();

        Post[] post = gson.fromJson(postJSON, Post[].class);
        Login result = gson.fromJson(loginJSON, Login.class);

        List<Post> postList = Arrays.asList(post);

        ListAdapter adapter = new CustomAdapter(this, postList);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

    }

}