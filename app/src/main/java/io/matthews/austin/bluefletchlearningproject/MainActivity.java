package io.matthews.austin.bluefletchlearningproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private Button b1;
    private EditText ed1,ed2;
    private String loginJSON;
    private String postJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://bfapp-bfsharing.rhcloud.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);

        Retrofit retrofit = builder.build();


        final BlueFletchClient client = retrofit.create(BlueFletchClient.class);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login(v, client);
            }
        });

    }

    //Incomplete
    private void createComment(final BlueFletchClient client){

        String uname = "";
        String postId = "";
        String commentText= "";

        Call<Comment> call = client.createComment(uname, postId, commentText);

        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
               Comment comment = response.body();
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Incomplete
    private void createPost(BlueFletchClient client){
        String uname = "";
        String postText= "";

        Call<Post> call = client.createPost(uname, postText);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post = response.body();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Incomplete
    private void getLoggedInUserInfo(BlueFletchClient client){

        Call<User> call = client.getLoggedInUserInfo();
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Incomplete
    private void getOtherUserInfo(BlueFletchClient client){
        String uname = "";

        Call<User> call = client.getOtherUserInfo(uname);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getPosts(final View v, final BlueFletchClient client){
        Call<List<Post>> call = client.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.code() == 200) {
                    List<Post> post = response.body();
                    Gson gson = new Gson();
                    postJSON = gson.toJson(post);
                    sendIntent(v);
                }

            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void login(final View v, final BlueFletchClient client){
        String username = ed1.getText().toString();
        String password = ed2.getText().toString();

        Call<Login> call = client.login(username, password);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                Login result = response.body();
                Gson gson = new Gson();
                loginJSON = gson.toJson(result);

                if (response.code() == 200){
                    getPosts(v, client);
                }
                else{
                    Toast.makeText(MainActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void logout(BlueFletchClient client){
        Call<ResponseBody> call = client.logout();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(response.code());
                if (response.code() == 200){
                }
                else{
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Sends the loginResult and post JSON strings to the DisplayUserInfoActivity
    private void sendIntent(View v) {
        Intent intent = new Intent(v.getContext(), DisplayUserFeedActivity.class);
        intent.putExtra("login", loginJSON);
        intent.putExtra("posts", postJSON);
        startActivity(intent);
    }

    private void testServer(BlueFletchClient client){
        Call<ResponseBody> call = client.testServer();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(response.code());
                if (response.code() == 200) {
                    System.out.println(response.body());
                }
                else{
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Incomplete
    private void updateComment(BlueFletchClient client){
        String uname = "";
        String postId = ""
;        String commentText= "";

        Call<Comment> call = client.createComment(uname, postId, commentText);

        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                Comment comment = response.body();
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Incomplete
    private void updatePost(BlueFletchClient client){
        String uname = "";
        String id = "";
        String newPostText= "";

        Call<Post> call = client.updatePost(id, newPostText, uname);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post = response.body();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
