package com.example.shankaratalk.modelclass;

public class Create_user {
    private String name;
    private String email;
    private String uid;
    private String gender;
    private String identity;
    private String postcount;
    private String photolink;
    private String followers;
    private  String following;
    private  String bio;



    public Create_user() {
    }

    public Create_user(String name, String email, String uid, String gender, String identity, String postcount, String photolink, String followers, String following, String bio) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.gender = gender;
        this.identity = identity;
        this.postcount = postcount;
        this.photolink = photolink;
        this.followers = followers;
        this.following = following;
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPostcount() {
        return postcount;
    }

    public void setPostcount(String postcount) {
        this.postcount = postcount;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getPhotolink() {
        return photolink;
    }

    public void setPhotolink(String photolink) {
        this.photolink = photolink;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
