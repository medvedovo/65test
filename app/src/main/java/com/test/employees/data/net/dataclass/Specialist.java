package com.test.employees.data.net.dataclass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Specialist {
    @SerializedName("f_name")
    public String fName;
    @SerializedName("l_name")
    public String lName;
    @SerializedName("birthday")
    public String birthday;
    @SerializedName("avatr_url")
    public String avatrUrl;
    @SerializedName("specialty")
    public List<Speciality> speciality;
}
