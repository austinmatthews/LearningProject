package io.matthews.austin.bluefletchlearningproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;


import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.blueFletchTextView);
        //listView = (ListView) findViewById(R.id.listView);

        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://bfapp-bfsharing.rhcloud.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);

        Retrofit retrofit = builder.build();

        BlueFletchClient client = retrofit.create(BlueFletchClient.class);


        login(client);

    }

    private void createComment(BlueFletchClient client){

    }

    private void createPost(BlueFletchClient client){

    }

    private void getLoggedInUserInfo(BlueFletchClient client){
        Call<User> call = client.getLoggedInUserInfo();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                String resultString = "ID: " + user.getId() + System.getProperty("line.separator")
                        + "LastActionDate: " + user.getLastActionDate() + System.getProperty("line.separator")
                        + "Username: " + user.getUsername() + System.getProperty("line.separator")
                        + "Created Date: " + user.getCreatedDate() + System.getProperty("line.separator")
                        + "ImageURL: " + user.getImageUrl();

                textView.setText(resultString);
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getOtherUserInfo(BlueFletchClient client){

    }

    private void getPosts(BlueFletchClient client){
        Call<List<Post>> call = client.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                Post p = posts.get(0);

                String resultString = "Post Text: " + p.getPostText();

                textView.setText(resultString);

            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void login(final BlueFletchClient client){
        Call<LoginResult> call = client.login("test1", "pass1");

        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                LoginResult result = response.body();
                String resultString = "ID: " + result.getId() + System.getProperty("line.separator")
                        + "Password: " + result.getPassword() + System.getProperty("line.separator")
                        + "Username: " + result.getUsername() + System.getProperty("line.separator")
                        + "ImageURL: " + result.getImageUrl();

                textView.setText(resultString);
                //logout(client);
                //getLoggedInUserInfo(client);
                getPosts(client);
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
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
                    textView.setText("Logout Successful");
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


    private void testAPI(BlueFletchClient client){

    }

    private void updateComment(BlueFletchClient client){

    }

    private void uploadProfilePicture(BlueFletchClient client){

    }

    private void updatePost(BlueFletchClient client){

    }


}
