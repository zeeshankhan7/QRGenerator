package com.qrgenerator.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.qrgeneratorapp.max.R;
import com.qrgenerator.models.Attendant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by inmkhan021 on 7/11/2017.
 */

public class QRCodeGeneratorTask extends AsyncTask<Attendant, Integer , Bitmap> {
    public final static int QRcodeWidth = 500 ;
    private Context mContext;
    private OnTaskCompleted listener;
    Bitmap bitmap=null;
    public QRCodeGeneratorTask(Context context, OnTaskCompleted listener){
        mContext = context;
        this.listener=listener;
    }

    @Override
    protected Bitmap doInBackground(Attendant... params) {
        BitMatrix bitMatrix=null;
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("mobile",params[0].getAttendentContactNo());
            jsonObject.put("id",params[0].getPatientId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Irshad", " NORMAL JSON string "+ jsonObject.toString());
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    jsonObject.toString(),
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        mContext.getResources().getColor(R.color.QRCodeBlackColor): mContext.getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        listener.onTaskCompleted(bitmap);

    }
}
