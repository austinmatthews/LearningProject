package io.matthews.austin.bluefletchlearningproject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface BlueFletchClient {
@FormUrlEncoded

    //POST REQUESTS

    //Login User
    @POST("/login")
    //Call object lets us do this asynchronously
    Call<LoginResult> login(
        @Field("username") String uname,
        @Field("password") String password
    );
/*
    //Create a new comment
    @POST("/comment")
    Call<LoginResult> login(
        @Field("username") String uname,
        @Field("postId") int postId,
        @Field("commentText") String commentText
    );
*/
    //Logout user and clear the session
    @POST("/logout")
    Call<ResponseBody> logout();
/*
    //Create a new post (thread)
    @POST("/post")
    Call<PostResult> post(
        @Field("username") String uname,
        @Field("postText") String postText
    );

    //GET REQUESTS

    //Get posts and their comments
    @GET("/feed")
    Call<Test> testServer();

    //Test the BlueFletch API
    @GET("/test")
    Call<Test> testServer();

    //Get the information for the logged in user
    @GET("/user")
    Call<Test> testServer();

    //Get the information for a specific user
    @GET("/user/username/{username}")
    Call<Test> testServer();


    //PUT REQUESTS

    //Update comment text
    @PUT("/comment/{id}")
    Call<Test> testServer();

    //Update post text
    @PUT("/post/{id}")
    Call<Test> testServer();

    //Upload a new profile picture
    @PUT("/user/{username}/profilepic")
    Call<Test> testServer();

*/
}