package com.qrgenerator.retrofit;

import com.qrgenerator.models.AddVisitorParams;
import com.qrgenerator.models.AddVisitorResponse;
import com.qrgenerator.models.VisitorListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by inmkhan021 on 7/24/2017.
 */

public interface ApiInterface {

    @POST("addVisitors.do")
    Call<AddVisitorResponse> addVisitor(@Header("Content-Type") String content_type, @Body AddVisitorParams params);
    @POST("getVisitors.do")
    Call<VisitorListResponse> getVisitorList(@Header("Content-Type") String content_type, @Body String params);
}
