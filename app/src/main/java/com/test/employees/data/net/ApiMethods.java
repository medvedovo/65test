package com.test.employees.data.net;

import com.test.employees.data.net.dataclass.Response;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiMethods {
    @GET("testTask.json")
    Single<Response> getData();
}
