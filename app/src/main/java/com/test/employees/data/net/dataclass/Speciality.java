package com.test.employees.data.net.dataclass;

import com.google.gson.annotations.SerializedName;

public class Speciality {
    @SerializedName("specialty_id")
    public int specialtyId;

    @SerializedName("name")
    public String name;
}
