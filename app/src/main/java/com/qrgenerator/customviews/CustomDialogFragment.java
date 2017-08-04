package com.qrgenerator.customviews;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qrgenerator.Events.ErrorEvent;
import com.qrgenerator.Events.ServerEvent;
import com.qrgenerator.fragments.VisitorListFragment;
import com.qrgenerator.models.AddVisitorParams;
import com.qrgenerator.models.AddVisitorResponse;
import com.qrgenerator.models.Visitor;
import com.qrgenerator.retrofit.ApiClient;
import com.qrgenerator.retrofit.ApiInterface;
import com.qrgenerator.retrofit.BusProvider;
import com.qrgenerator.retrofit.Communicator;
import com.qrgenerator.utils.AppSharedPreferenceHelper;
import com.qrgenerator.utils.CommonUtility;
import com.qrgenerator.utils.Constants;
import com.qrgenerator.utils.NetworkCallUtil;
import com.qrgeneratorapp.databases.AppDBHelper;
import com.qrgeneratorapp.databases.ItemTable;
import com.qrgeneratorapp.max.R;
import com.squareup.otto.Subscribe;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by inmkhan021 on 7/17/2017.
 */
public class CustomDialogFragment extends DialogFragment {


    private static final String LOG_TAG= CustomDialogFragment.class.getSimpleName();

    @BindView(R.id.visitor_name_editTxt)
    CustomFontEditText visitorName;
    @BindView(R.id.visitor_mobile_no_editTxt)
    CustomFontEditText visitorMobileNo;
    @BindView(R.id.patient_id_editTxt)
    CustomFontEditText patientId;
    @BindView(R.id.patient_name_editTxt)
    CustomFontEditText patientName;
    private Unbinder unbinder;
    @BindView(R.id.card_view)
    CardView cv;
    @BindView(R.id.activity_main1)
    RelativeLayout activity_main1;
    @BindView(R.id.activity_main)
    LinearLayout  ll;
    @BindView(R.id.cancelBtn)
    CustomFontButton cancelBtn;
    @BindView(R.id.SubmitBtn)
    CustomFontButton SubmitBtn;
    @OnClick(R.id.cancelBtn)
    void cancelButtonClick() {
        getDialog().dismiss();

    }
    @OnClick(R.id.SubmitBtn)
    void submitButtonClick() {
        final Visitor visitor = new Visitor();
        visitor.setPatientId(patientId.getText().toString());
//        visitor.setPatientName(patientName.getText().toString());
        visitor.setVisitorMobileNo(visitorMobileNo.getText().toString());
        visitor.setVisitorName(visitorName.getText().toString());

        if(CommonUtility.isValidVisitorModel(visitor)) {
            Communicator communicator = new Communicator();
            AddVisitorParams params= new AddVisitorParams(visitor.getVisitorName(),visitor.getVisitorMobileNo(),visitor.getPatientId());
            communicator.addVisitorToServer(getActivity(),params,visitor);
        }else{
            CommonUtility.showSnackBar(activity_main1, Constants.REQUEST_MESSAGE);;
        }

    }
    public interface DataPassListener{
        public void passData(Visitor data);
    }
    DataPassListener mCallback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.visitor_form, container,false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        unbinder = ButterKnife.bind(this, rootView);
        String patientIdValue= AppSharedPreferenceHelper.getInstance(getContext()).getPatientIDFromSP();
        patientId.setText(patientIdValue);
        patientId.setEnabled(false);
        // Do something else
        return rootView;
    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try
        {
            mCallback = (DataPassListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()+ " must implement OnImageClickListener");
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG , " onResume called ");
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG , " onPause called ");
        BusProvider.getInstance().unregister(this);
    }
    @Subscribe
    public void onServerEvent(ServerEvent serverEvent){
        String responseMsg="";
        if(serverEvent.getServerResponse() instanceof AddVisitorResponse){
            if(serverEvent!=null && serverEvent.getServerResponse()!=null ){
                String status=((AddVisitorResponse) serverEvent.getServerResponse()).getStatus();
                responseMsg=((AddVisitorResponse) serverEvent.getServerResponse()).getMessage();
                Visitor visitor=serverEvent.getVisitor();
                if(!CommonUtility.isStringEmptyOrNull(status) && status.equalsIgnoreCase(Constants.RESPONSE_SUCCESS)){
                    mCallback.passData(visitor);

                    AppDBHelper appDBHelper = new AppDBHelper(getContext());
                    ItemTable itemTable = new ItemTable(appDBHelper);
                    itemTable.insert(visitor);
//                    String serverURL = "http://demo-ramnath.rhcloud.com/qrLinkSent.do";
//                    new LongOperation2().execute(serverURL,patientId.getText().toString(),visitorMobileNo.getText().toString());
                    CommonUtility.showSnackBar(activity_main1, responseMsg);
                    getDialog().dismiss();
                }else{
                    CommonUtility.showSnackBar(activity_main1, responseMsg);
                }
            }
        }

    }

    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        CommonUtility.showSnackBar(activity_main1, errorEvent.getErrorMsg());
    }

    private class LongOperation2  extends AsyncTask<String, Void, Void>{
        private final HttpClient Client = new DefaultHttpClient();
        private String content;
        private String Error = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                JSONObject JSON_STRING = new JSONObject();

                JSON_STRING.put("patientId", params[1]);
                JSON_STRING.put("contactNo", params[2]);
                Log.d(LOG_TAG , " patientId string "+ params[1]);
                Log.d(LOG_TAG , " contactNumber string "+ params[2]);
                Log.d(LOG_TAG , " JSON string "+ JSON_STRING.toString());
                HttpPost httpPost = new HttpPost(params[0]);
                StringEntity requestEntity = new StringEntity(
                        JSON_STRING.toString());
                httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
                httpPost.setEntity(requestEntity);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                content = Client.execute(httpPost, responseHandler);
                Log.d(LOG_TAG, " content string is not null " + content);
                if (content != null) {
                    JSONObject jsonObject = new JSONObject(content);
                    String status = jsonObject.getString("status");
                    String msg = jsonObject.getString("message");
                    Log.d(LOG_TAG, " status :" + status);
                    Log.d(LOG_TAG, " message :" + msg);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
