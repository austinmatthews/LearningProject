package io.matthews.austin.bluefletchlearningproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;


import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.blueFletchTextView);

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
                logout(client);
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void logout(BlueFletchClient client){
        Call<ResponseBody> call1 = client.logout();

        call1.enqueue(new Callback<ResponseBody>() {
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
}
