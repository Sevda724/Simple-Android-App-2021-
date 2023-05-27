package com.example.simple_app;

import com.google.gson.annotations.SerializedName;

public class PersonInfoClass {
    @SerializedName("char_id")
    private Integer char_id;
    @SerializedName("name")
    private String name;
    @SerializedName("img")
    private String img;
    @SerializedName("status")
    private String status;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("occupation")
    private String[] occupation;

    public PersonInfoClass(int char_id, String name, String birthday, String img, String status, String[] occupation){
        this.char_id = char_id;
        this.name = name;
        this.img = img;
        this.status = status;
        this.birthday = birthday;
        this.occupation = occupation;
    }

    public String getBirthday() {
        return birthday;
    }

    public String[] getOccupation() {
        return occupation;
    }

    public String getStatus() {
        return status;
    }

    public Integer getChar_id() {
        return char_id;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }
}
