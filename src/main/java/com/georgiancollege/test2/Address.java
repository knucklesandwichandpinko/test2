package com.georgiancollege.test2;

import com.google.gson.annotations.SerializedName;

public  class Address {
    @SerializedName("address")
    private String address;

    @SerializedName("city")
    private String city;

    @SerializedName("postalCode")
    private String postalCode;

    @SerializedName("state")
    private String state;

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getState() {
        return state;
    }
}
