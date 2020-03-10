package com.coachmovecustomer.retrofitManager;

import android.view.textclassifier.TextClassification;

import com.coachmovecustomer.data.SearchChatResponse;
import com.coachmovecustomer.utils.Const;
import com.google.firebase.annotations.PublicApi;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 *
 */
public interface ApiInterface {


//    @POST
//    Call<JsonObject> postAPI(@Header("Authorization") String header ,@Url String url, @Body HashMap<String, String> hashMap);


    @GET
    Call<JsonObject> getComments(@Header("Authorization") String header, @Url String url);

    @Multipart
    @POST
    Call<JsonObject> uploadSurvey(@Url String url,
                                  @Part MultipartBody.Part[] surveyImage,
                                  @PartMap HashMap<String, RequestBody> map);
    @GET
    Call<JsonObject> getUserForSearchApi(@Header("Authorization") String auth,
                                         @Url String url);


    @PUT
    Call<JsonObject> putAPI(@Header("Authorization") String header, @Url String url, @Body HashMap<String, String> hashMap);


    @GET
    Call<JsonObject> getAPI(@Header("Authorization") String header, @Url String url);

    @GET
    Call<SearchChatResponse> getUserForChatAPI(@Header("Authorization") String header, @Url String url);

    @GET
    Call<JsonObject> blockUser(@Header("Authorization") String header, @Url String url);


    @POST
    Call<JsonObject> postAPI(@Url String url, @Body HashMap<String, String> hashMap);


    @POST
    Call<JsonObject> postAPI(@Header("Authorization") String header, @Url String url, @Body HashMap<String, String> hashMap);

    @PUT
    Call<JsonObject> updateProfile(@Header("Authorization") String header, @Url String url, @Body RequestBody hashMap);

    @Multipart
    @POST
    Call<JsonObject> multipartRequestAPI(@Header("Authorization") String header, @Url String url, @PartMap HashMap<String, RequestBody> map);

//    Call<JsonObject> multipartRequestAPI(@Header("Authorization") String header ,@Url String url, @Part ("file") RequestBody map);


    @DELETE("CoachMove/api/cards/{id}")
    Call<JsonObject> getDelete(@Header("Authorization") String header, @Path("id") int itemId);


    @GET("CoachMove/api/customers")
    Call<JsonObject> getSearchQuery(@Header("Authorization") String header, @Query("search") String search);


    @GET("CoachMove/api/coaches/workouts")
    Call<JsonObject> getSearchWorkoutCoaches(@Header("Authorization") String header,
                                             @Query("neigbourhood") String neigbourhood,
                                             @Query("modalityId") String modalityId,
                                             @Query("search") String search,
                                             @Query("time") String time,
                                             @Query("date") String date,
                                             @Query("gender") String gender);
 @GET(Const.APPLY_PROMO_CODE)
    Call<JsonObject> applyPromoCode(@Header("Authorization") String header,
                                             @Query("couponId") String couponId,
                                             @Query("userId") String userId,
                                             @Query("amount") String amount);


    @POST
    Call<JsonObject> postApiObject(@Header("Authorization") String auth,
                                   @Url String url,
                                   @Body HashMap<String, Object> hashMap);

    @POST
    Call<JsonObject> postApiJSONObject(@Header("Authorization") String auth,
                                       @Url String url,
                                       @Body JSONObject hashMap);


    @GET("CoachMove/api/coaches/diets/")
    Call<JsonObject> searchNutritionist(@Header("Authorization") String header, @Query("search") String search);


    @POST
    Call<JsonObject> postApiPaymentCoach(@Header("Authorization") String auth,
                                         @Url String url,
                                         @Body HashMap<String, Object> hashMap);


    @GET("CoachMove/api/customers/")
    Call<JsonObject> scheduleWorkoutApi(@Header("Authorization") String header, @Query("term") String term);

    @GET("CoachMove/api/customers/")
    Call<JsonObject> searchForUSerForChat(@Header("Authorization") String header, @Query("term") String term);


    @FormUrlEncoded
    @PUT
    Call<JsonObject> updateWorkoutStatus(@Header("Authorization") String header, @Url String url, @FieldMap HashMap<String, Object> hashMap);


    @PATCH("CoachMove/api/customers/{customerid}/workouts/{id}/confirmCancelation")
    Call<JsonObject> cancellationAPI(@Header("Authorization") String header, @Path("customerid") String customerid, @Path("id") String id, @Body RequestBody hashMap);


    @PUT("CoachMove/api/workouts/{id}/status")
    Call<JsonObject> cancelWorkout(@Header("Authorization") String header, @Path("id") String id, @Body RequestBody hashMap);


}


