package com.qrgenerator.retrofit;

import com.qrgenerator.models.AddVisitorParams;
import com.qrgenerator.models.AddVisitorResponse;
import com.qrgenerator.models.VisitorListResponse;
import com.qrgenerator.models.GetVisitorParams;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by inmkhan021 on 7/24/2017.
 */

public interface ApiInterface {

    @POST("addVisitors.do")
    Call<AddVisitorResponse> addVisitor(@Header("Content-Type") String content_type, @Body AddVisitorParams params);
    @POST("getVisitors.do")
    Call<VisitorListResponse> getVisitorList(@Header("Content-Type") String content_type, @Body GetVisitorParams params);
}
