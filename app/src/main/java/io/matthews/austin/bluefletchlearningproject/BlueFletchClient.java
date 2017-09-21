package io.matthews.austin.bluefletchlearningproject;

import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface BlueFletchClient {

    //Create a new comment
    @POST("/comment")
    Call<Comment> createComment(
            @Field("username") String uname,
            @Field("postId") String postId,
            @Field("commentText") String commentText
    );

    //Update comment text
    @PUT("/comment/{id}")
    Call<Post> updateComment(
            @Field("username") String uname,
            @Path("id") String id,
            @Field("commentText") String commentText
    );

    //Get posts and their comments
    @GET("/feed")
    Call<List<Post>> getPosts();

    @FormUrlEncoded
    //Login User
    @POST("/login")
    //Call object lets us do this asynchronously
    Call<Login> login(
        @Field("username") String uname,
        @Field("password") String password
    );

    //Logout user and clear the session
    @POST("/logout")
    Call<ResponseBody> logout();

    //Create a new post
    @POST("/post")
    Call<Post> createPost(
        @Field("username") String uname,
        @Field("postText") String postText
    );

    //Update post text
    @PUT("/post/{id}")
    Call<Post> updatePost(
            @Path("id") String id,
            @Field("newPostText") String newPostText,
            @Field("username") String uname
    );

    //Test the BlueFletch API
    @GET("/test")
    Call<ResponseBody> testServer();

    //Get the information for the logged in user
    @GET("/user")
    Call<User> getLoggedInUserInfo();

    //Get the information for a specific user
    @GET("/user/username/{username}")
    Call<User> getOtherUserInfo(
            @Path("username") String uname
    );
}