package com.coachmovecustomer.retrofitManager;

import android.content.Context;

import com.coachmovecustomer.BuildConfig;
import com.coachmovecustomer.utils.Const;
import com.coachmovecustomer.utils.PrefStore;
import com.google.android.gms.dynamic.IFragmentWrapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;

/**
 * Created by naveen sharma on 1/8/17.
 */
public class ApiClient {
    private static Retrofit retrofit = null;
    private static Context mContext;
    private static String appName = "Coach Move Customer";
    private static String appLanuage="";

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = getClient();
        }

        return retrofit;
    }

    public static Retrofit getClient(PrefStore prefStore) {
        appLanuage=prefStore.getLanguage();
        if (appLanuage==null){
            appLanuage = "pt-br";
        }else
        if (appLanuage.equalsIgnoreCase("en")){
            appLanuage = "pt-br";
        }
        else{
            appLanuage = "pt-br";
        }
        return getClient();
    }


//    public static Retrofit getClient(PrefStore prefStore) {
//        appLanuage=prefStore.getLanguage();
//        if (appLanuage==null){
//            appLanuage = "en-US";
//        }else
//        if (appLanuage.equalsIgnoreCase("en")){
//            appLanuage = "en-US";
//        }
//        else{
//            appLanuage = "pt-br";
//        }
//        return getClient();
//    }



    public static Retrofit getClient() {

        /** Creates client for retrofit, here you can configure different settings of retrofit manager
         * like Logging, Cache size, Decoding factories, Convertor factories etc.
         */

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(provideHttpLoggingInterceptor())
                .addInterceptor(interceptor)
                .connectTimeout(80, TimeUnit.SECONDS)
                .writeTimeout(80, TimeUnit.SECONDS)
                .readTimeout(80, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {      // add header in api
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .header("deviceType", "Android")
//                                .header("appVersion", "1.0")
                                .header("appVersion", BuildConfig.VERSION_NAME)
                                .header("Content-Type", "application/json")
//                                .header("timezone", "Asia/Kolkata")
                                .header("timezone", String.valueOf(TimeZone.getDefault().getID()))
//                                .header("locale", appLanuage)
                                .header("locale", "pt-br")
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Const.SERVER_REMOTE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

//    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
//        HttpLoggingInterceptor httpLoggingInterceptor =
//                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                    @Override
//                    public void log(@NonNull String message) {
//                        if (message.contains("Date"))
//                            Log.i("VisionPlan", "Api Cache storage Time: " + getCreatedAt(message.split("Date:")[1]));
//                        else if (message.contains("Expires:"))
//                            Log.i("VisionPlan", "Api Cache Expires Time: " + getCreatedAt(message.split("Expires:")[1]));
//                        else if (message.contains("expires"))
//                            Log.i("VisionPlan", message.split("expires=")[0] + "expires= " + getCreatedAt(message.split("expires=")[1].split("GMT")[0] + "GMT") + " " + message.split("expires=")[1].split("GMT")[1]);
//                        else
//                            Log.i("VisionPlan", message);
//                    }
//                });
//        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? BODY : NONE);
//        return httpLoggingInterceptor;
//    }

    private static String getCreatedAt(String inputText) {
        SimpleDateFormat inputFormat = new SimpleDateFormat
                (" EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.getDefault());
        SimpleDateFormat inputFormat1 = new SimpleDateFormat
                ("EEE, dd-MMM-yyyy HH:mm:ss 'GMT'", Locale.getDefault());
        inputFormat.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        inputFormat1.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        SimpleDateFormat outputFormat =
                new SimpleDateFormat("MMM dd, yyyy h:mm:ss");
        Date date = null;
        try {
            date = inputFormat.parse(inputText);
        } catch (ParseException e) {
            try {
                date = inputFormat1.parse(inputText);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        return outputFormat.format(date);
    }


}
