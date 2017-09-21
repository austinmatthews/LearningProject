package io.matthews.austin.bluefletchlearningproject;

import java.io.Serializable;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface BlueFletchClient {
//@FormUrlEncoded

/*
    //Create a new comment
    @POST("/comment")
    Call<LoginResult> login(
            @Field("username") String uname,
            @Field("postId") int postId,
            @Field("commentText") String commentText
    );

    //Update comment text
    @PUT("/comment/{id}")
    Call<Test> testServer();
*/
    //Get posts and their comments
    @GET("/feed")
    Call<List<Post>> getPosts();

    @FormUrlEncoded
    //Login User
    @POST("/login")
    //Call object lets us do this asynchronously
    Call<LoginResult> login(
        @Field("username") String uname,
        @Field("password") String password
    );

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

    //Update post text
    @PUT("/post/{id}")
    Call<Test> testServer();

    //Test the BlueFletch API
    @GET("/test")
    Call<Test> testServer();
*/
    //Get the information for the logged in user
    @GET("/user")
    Call<User> getLoggedInUserInfo();
/*
    //Upload a new profile picture
    @PUT("/user/{username}/profilepic")
    Call<Test> testServer();

    //Get the information for a specific user
    @GET("/user/username/{username}")
    Call<Test> testServer();


*/
}