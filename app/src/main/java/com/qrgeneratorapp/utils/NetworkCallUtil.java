package com.qrgeneratorapp.utils;

import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**************
 * this class contains all the functions for network related utilities
 *
 * @author inmkhan021
 ******************/

public class NetworkCallUtil {


    /*****************************************************
     * method to make request to the websphere server securely
     *
     * @param urlString
     * @param webRequestDataObject
     * <p>
     * in case of provisioning on admin apn pass httpsurlconnection
     * object else for other cases pass url string
     ******************************************************/
    private static String LOG_TAG = NetworkCallUtil.class.getSimpleName();

    public static String secureHttpRequest(String urlString, JSONObject webRequestDataObject)
            throws Exception {
        Log.v("#### URL being hit ###", urlString);
        String response = "";
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(Constants.DEFAULT_HTTP_CONNECT_TIMEOUT);
        urlConnection.setReadTimeout(Constants.DEFAULT_HTTP_READ_TIMEOUT);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-type", "application/json");
        urlConnection.setRequestProperty("charset", "utf-8");
        DataOutputStream wr = new DataOutputStream(
                urlConnection.getOutputStream());
        wr.writeBytes(webRequestDataObject.toString());
        wr.flush();
        wr.close();

        try {
            int  responseCode= urlConnection.getResponseCode();
            Log.v(LOG_TAG, "  connection network response code: " + responseCode + "");

            if (responseCode == 200) {
                response = readStream(urlConnection.getInputStream());
            }

        } catch (Exception e) {
            Log.v(LOG_TAG, " Exception occurred in NetworkCallUtil " );
            e.printStackTrace();
        } finally {
            Log.v(LOG_TAG, " inside finally block in NetworkCallUtil " );
            urlConnection.disconnect();
        }

        return response;

    }

    private static String readStream(InputStream is) {
        final char[] buffer = new char[1024];
        final StringBuilder out = new StringBuilder();
        try (Reader in = new InputStreamReader(is, "UTF-8")) {
            for (; ; ) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0)
                    break;
                out.append(buffer, 0, rsz);
            }
            Log.v(LOG_TAG, "  server response: " + out.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return out.toString();
    }
   
}
