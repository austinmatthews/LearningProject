package io.matthews.austin.bluefletchlearningproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private List<Post> post;
    private LoginResult result;
    private String loginJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        final BlueFletchClient client = ServiceGenerator.createService(BlueFletchClient.class);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login(v, client);
            }
        });
    }

    private void login(final View v, final BlueFletchClient client){
        String username = ed1.getText().toString();
        String password = ed2.getText().toString();
        Call<LoginResult> call = client.login(username, password);

        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                result = response.body();
                Gson gson = new Gson();
                String loginJSON = gson.toJson(result);

                if (response.code() == 200){
                    getPosts(v, client);

                }
                else{
                    Toast.makeText(MainActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

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
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getOtherUserInfo(BlueFletchClient client){

    }

    private void getPosts(final View v, final BlueFletchClient client){
        Call<List<Post>> call = client.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.code() == 200) {
                    post = response.body();

                    Gson gson = new Gson();
                    String postJSON = gson.toJson(post);
                    Intent intent;
                    intent = new Intent(v.getContext(), DisplayUserInfoActivity.class);
                    intent.putExtra("loginResult", loginJSON);
                    intent.putExtra("posts", postJSON);
                    startActivity(intent);
                }

            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
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

    private void testAPI(BlueFletchClient client){

    }

    private void updateComment(BlueFletchClient client){

    }

    private void uploadProfilePicture(BlueFletchClient client){

    }

    private void updatePost(BlueFletchClient client){

    }
}
