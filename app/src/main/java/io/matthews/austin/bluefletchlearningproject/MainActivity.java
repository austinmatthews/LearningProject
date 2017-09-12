package io.matthews.austin.bluefletchlearningproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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

        textView = (TextView)findViewById(R.id.blueFletchTextView);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://bfapp-bfsharing.rhcloud.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        BlueFletchClient client = retrofit.create(BlueFletchClient.class);
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
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
