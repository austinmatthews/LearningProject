package io.matthews.austin.bluefletchlearningproject;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("postText")
    @Expose
    private String postText;
    @SerializedName("postUser")
    @Expose
    private PostUser postUser;
    @SerializedName("numUpdates")
    @Expose
    private Integer numUpdates;
    @SerializedName("comments")
    @Expose
    private List<Comment> comments = null;
    @SerializedName("lastUpdatedDate")
    @Expose
    private String lastUpdatedDate;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("newPostForUser")
    @Expose
    private Boolean newPostForUser;

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public PostUser getPostUser() {
        return postUser;
    }

    public void setPostUser(PostUser postUser) {
        this.postUser = postUser;
    }

    public Integer getNumUpdates() {
        return numUpdates;
    }

    public void setNumUpdates(Integer numUpdates) {
        this.numUpdates = numUpdates;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getNewPostForUser() {
        return newPostForUser;
    }

    public void setNewPostForUser(Boolean newPostForUser) {
        this.newPostForUser = newPostForUser;
    }

}