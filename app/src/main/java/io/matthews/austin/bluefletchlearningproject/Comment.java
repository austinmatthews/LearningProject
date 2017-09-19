package io.matthews.austin.bluefletchlearningproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("commentText")
    @Expose
    private String commentText;
    @SerializedName("commentUser")
    @Expose
    private CommentUser commentUser;
    @SerializedName("numCommentUpdates")
    @Expose
    private Integer numCommentUpdates;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public CommentUser getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(CommentUser commentUser) {
        this.commentUser = commentUser;
    }

    public Integer getNumCommentUpdates() {
        return numCommentUpdates;
    }

    public void setNumCommentUpdates(Integer numCommentUpdates) {
        this.numCommentUpdates = numCommentUpdates;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}
