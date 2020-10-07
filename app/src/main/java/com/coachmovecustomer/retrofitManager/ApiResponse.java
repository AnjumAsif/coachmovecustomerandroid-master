package com.coachmovecustomer.retrofitManager;

import org.json.JSONObject;

import retrofit2.Call;

/**

 */
public interface ApiResponse {
    void onSuccess(Call call, Object object, String data);

    void onError(Call call, String errorMessage, ApiResponse apiResponse, int responceCode);

    void onError(Call call, String errorMessage, ApiResponse apiResponse, int responceCode, JSONObject jsonObject);

/** Uncomment below method and its occurenece in {@link ApiHitAndHandle}
 *  if you want a method which is sure to fire even request completes or fails
 */

//    void onFinish();

}
