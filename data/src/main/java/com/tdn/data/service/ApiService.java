package com.tdn.data.service;


import com.tdn.domain.model.PenjualanTempModel;
import com.tdn.domain.model.UserModel;
import com.tdn.domain.serialize.req.ReqAuth;
import com.tdn.domain.serialize.req.ReqPenjualan;
import com.tdn.domain.serialize.res.ResponseAction;
import com.tdn.domain.serialize.res.ResponseAuth;
import com.tdn.domain.serialize.res.ResponseGetObat;
import com.tdn.domain.serialize.res.ResponseGetPenjualanTemp;

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

    String BASE = "http://192.168.100.5/apotekk/";
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
    @POST("user/auth")
    Call<ResponseAuth> auth(@Body ReqAuth reqAuth);

    @Headers({accept_json, content_type, api_key})
    @PUT("user/users")
    Call<Response> putUser(@Body UserModel userModel);

    @Headers({accept_json, content_type, api_key})
    @DELETE("user/users")
    Call<Response> deleteUser(@Body UserModel userModel);

    @Headers({accept_json, content_type, api_key})
    @GET("obat/obats")
    Call<ResponseGetObat> getObat();

    @Headers({accept_json, content_type, api_key})
    @GET("obat/obats")
    Call<ResponseGetObat> getObatById(@Query("id") String id);

    @Headers({accept_json, content_type, api_key})
    @POST("penjualan/penjualan_temp")
    Call<ResponseAction> postTempPenjualan(@Body PenjualanTempModel penjualanTempModel);

    @Headers({accept_json, content_type, api_key})
    @GET("penjualan/penjualan_temp")
    Call<ResponseGetPenjualanTemp> getPenjualanTemp();

    @Headers({accept_json, content_type, api_key})
    @POST("penjualan/penjualan")
    Call<ResponseAction> postPenjualan(@Body ReqPenjualan reqPenjualan);

    class Factory {
        public static ApiService create() {
            return ServiceFactory.createService(ApiService.class);
        }
    }
}
