package io.matthews.austin.bluefletchlearningproject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface BlueFletchClient {
@FormUrlEncoded
    @POST("/login")
    Call<LoginResult> login(
        @Field("username") String uname,
        @Field("password") String password
    );
}