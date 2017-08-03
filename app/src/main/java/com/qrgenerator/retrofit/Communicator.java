package com.qrgenerator.retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.qrgenerator.Events.ErrorEvent;
import com.qrgenerator.Events.ServerEvent;
import com.qrgenerator.models.AddVisitorParams;
import com.qrgenerator.models.AddVisitorResponse;
import com.qrgenerator.models.Attendant;
import com.qrgenerator.models.GetVisitorParams;
import com.qrgenerator.models.Visitor;
import com.qrgenerator.models.VisitorListResponse;
import com.qrgenerator.utils.CommonUtility;
import com.qrgenerator.utils.Constants;
import com.squareup.otto.Produce;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by inmkhan021 on 7/24/2017.
 */

public class Communicator {
    private static  final String LOG_TAG = Communicator.class.getSimpleName();

    public void addVisitorToServer(AddVisitorParams addVisitorParams, final Visitor visitor){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AddVisitorResponse> call = apiService.addVisitor(Constants.CONTENT_TYPE, addVisitorParams);

        call.enqueue(new Callback<AddVisitorResponse>() {
            @Override
            public void onResponse(Call<AddVisitorResponse> call, Response<AddVisitorResponse> response) {
                // response.isSuccessful() is true if the response code is 2xx
                //produceServerEvent(response.body(),visitor
                if(response.isSuccessful()){
                    BusProvider.getInstance().post(produceServerEvent(response.body(),visitor));
                }

            }

            @Override
            public void onFailure(Call<AddVisitorResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(produceErrorEvent(-2,t.getMessage()));
            }
        });
    }

    public void loginAttendant(Context ctx, final Attendant attendant){

        final ProgressDialog progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Login....");
        progressDialog.setCancelable(false);
        // show it
        progressDialog.show();
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<AddVisitorResponse> call= apiService.loginAttendant(Constants.CONTENT_TYPE,attendant);
        call.enqueue(new Callback<AddVisitorResponse>() {
            @Override
            public void onResponse(Call<AddVisitorResponse> call, Response<AddVisitorResponse> response) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if(response.isSuccessful()){
                    BusProvider.getInstance().post(produceServerEvent(response.body()));
                }

            }

            @Override
            public void onFailure(Call<AddVisitorResponse> call, Throwable t) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                BusProvider.getInstance().post(produceErrorEvent(-2,t.getMessage()));

            }
        });
    }
    public void getVisitorListFromServer(GetVisitorParams patientId){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<VisitorListResponse> call = apiService.getVisitorList(Constants.CONTENT_TYPE, patientId);

        call.enqueue(new Callback<VisitorListResponse>() {
            @Override
            public void onResponse(Call<VisitorListResponse> call, Response<VisitorListResponse> response) {
                // response.isSuccessful() is true if the response code is 2xx
                if(response.isSuccessful()){
                    String status=response.body().getStatus();
                    String msg=response.body().getMessage();
                    if(!CommonUtility.isStringEmptyOrNull(status) && status.equals(Constants.RESPONSE_FAILURE)){
                        AddVisitorResponse addVisitorResponse= new AddVisitorResponse(status,msg);
                        BusProvider.getInstance().post(produceServerEvent(addVisitorResponse));

                    }else{
                        BusProvider.getInstance().post(produceServerEvent(response.body()));

                    }
                }

            }

            @Override
            public void onFailure(Call<VisitorListResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(produceErrorEvent(-2,t.getMessage()));
            }
        });
    }


    @Produce
    public <T> ServerEvent produceServerEvent(T serverResponse,Visitor visitor) {

        return new ServerEvent(serverResponse,visitor);
    }
    @Produce
    public <T> ServerEvent produceServerEvent(T serverResponse) {
        return new ServerEvent(serverResponse);
    }

    @Produce
    public ErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new ErrorEvent(errorCode, errorMsg);
    }
}
