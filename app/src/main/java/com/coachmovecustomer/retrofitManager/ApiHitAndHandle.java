package com.coachmovecustomer.retrofitManager;

import android.content.Context;
import android.util.Log;

import com.coachmovecustomer.BuildConfig;
import com.coachmovecustomer.activity.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 *
 */
public class ApiHitAndHandle implements Callback<JsonObject> {

    static final String TAG = ApiHitAndHandle.class.getSimpleName();
    private static ApiHitAndHandle apiHitAndHandle;
    private static Context mContext;
    private HashMap<Call, ApiResponse> apiResponseHashMap = new HashMap<>();
    private Object object;
    static Gson gson;
    private String apiMsg;

    public static ApiHitAndHandle getInstance(Context context) {
        if (apiHitAndHandle == null) {
            apiHitAndHandle = new ApiHitAndHandle();
        }
        gson = new Gson();
        mContext = context;
        return apiHitAndHandle;
    }


    public void makeApiCall(Call call, ApiResponse apiResponse) {
        makeApiCall(call, true, apiResponse);
    }

    public void makeApiCall(Call call, boolean showProgress, ApiResponse apiResponse) {
        try {
            apiResponseHashMap.put(call, apiResponse);
            call.enqueue(this);
            if (showProgress) {
                ((BaseActivity) mContext).startProgressDialog();
            }
            //Logs post URL
            log(call.request().url() + "");
            //Logs post params of Multipart request
//            log("Post Params >>>> \n" + bodyToString(call.request().body())
//                    .replace("\r", "")
//                    .replaceAll("--+[a-zA-Z0-9-\\/:=;\\ ]+\\n", "")
//                    .replaceAll("Content+[a-zA-Z0-9-\\/:=;\\ ]+;\\s", "")
//                    .replaceAll("Content+[a-zA-Z0-9-\\/:=;\\ ]+\\n", "")
//                    .replaceAll("charset+[a-zA-Z0-9-\\/:=;\\ ]+\\n", "")
//                    .replace("name=", "")
//                    .replace("\n\n", "--> ")
//            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void log(String s) {
        if (BuildConfig.DEBUG)
            Log.e(TAG, s);
    }

    private String bodyToString(final RequestBody request) {
        try {
            Buffer buffer = new Buffer();
            if (request != null)
                request.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        ((BaseActivity) mContext).stopProgressDialog();

        boolean status = false;
        ApiResponse apiResponse = apiResponseHashMap.get(call);
        String myStringresopnce = gson.toJson(response.body());
        Log.e("Code=", "" + response.code());
        Log.e("response=", "" + myStringresopnce);
        if (response.isSuccessful()) {
            try {
                JSONObject jsonObject = new JSONObject(myStringresopnce);
                apiMsg = jsonObject.optString("message");

                if (response.isSuccessful()) {
                    status = checkApiStatus(myStringresopnce);

                } else {
                    // Handle other responses
                    status = false;
                    apiMsg = jsonObject.optString("message");
                }

                if (status) {
                    apiResponse.onSuccess(call, response.body(),myStringresopnce);
                } else {
                    apiResponse.onError(call, apiMsg, apiResponseHashMap.get(call), response.code(), jsonObject);
                }
                apiResponseHashMap.remove(call);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                String errormsg = jsonObject.optString("message");
//                apiResponse.onError(call, response.message(), apiResponseHashMap.get(call), response.code(), new JSONObject(myStringresopnce));
                apiResponse.onError(call, errormsg, apiResponseHashMap.get(call), response.code());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private boolean checkApiStatus(String myStringresopnce) {
        boolean isStatus = false;
        if (myStringresopnce != null && !myStringresopnce.isEmpty()) {
            try {
                JSONObject object = new JSONObject(myStringresopnce);
                if (object.optString("status").equalsIgnoreCase("OK")) {
                    isStatus = true;
                }
//                isStatus = object.optBoolean("status");/*.equals("1");*/
                apiMsg = object.optString("message");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return isStatus;
    }

    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {
        ((BaseActivity) mContext).stopProgressDialog();
        ApiResponse apiResponse = apiResponseHashMap.get(call);
        ((BaseActivity) mContext).handleError(t);
        apiResponseHashMap.remove(call);
    }



   /* @Override
    public void onResponse(Call call, Response response) {

    }

    @Override
    public void onFailure(Call call, Throwable t) {
        ((BaseActivity) mContext).stopProgressDialog();
        ApiResponse apiResponse = apiResponseHashMap.get(call);
        apiResponse.onError(call, t.getMessage(), apiResponse);
//        apiResponse.onFinish();
        apiResponseHashMap.remove(call);
    }*/


}
