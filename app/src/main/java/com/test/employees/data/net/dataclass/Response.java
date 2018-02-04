package com.test.employees.data.net.dataclass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("response")
    public List<Specialist> response;
}
