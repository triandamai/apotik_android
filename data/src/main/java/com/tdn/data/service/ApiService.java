package com.tdn.data.service;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {

    String BASE = "https://apipengaduan.000webhostapp.com/";
    String BASE_URL = BASE + "api/v1/";
    String BASE_URL_IMAGE = BASE + "assets/pengaduan/";
    String USER_KEY = "";

    String accept_urlencoded = "Content-Type: application/x-www-form-urlencoded";
    String accept_json = "Accept: application/json;charset=utf-8";
    String content_type = "Content-Type: application/json;charset=utf-8";
    String api_key = "X-API-KEY: your api key";


    @Headers({accept_json, content_type, api_key})
    @GET("user/users")
    Call<Response> getUserById(@Query("id") String id);

    @Headers({accept_json, content_type, api_key})
    @POST("user/users")
    Call<Response> postUser(@Body Response userModel);

    @Headers({accept_json, content_type, api_key})
    @PUT("user/users")
    Call<Response> putUser(@Body UserModel userModel);

    @Headers({accept_json, content_type, api_key})
    @DELETE("user/users")
    Call<Response> deleteUser(@Body UserModel userModel);



    class Factory {
        public static ApiService create() {
            return ServiceFactory.createService(ApiService.class);
        }
    }
}
