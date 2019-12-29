package com.coachmovecustomer.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coachmovecustomer.BuildConfig;
import com.coachmovecustomer.R;
import com.coachmovecustomer.myInterface.MultiImageCallBack;
import com.coachmovecustomer.retrofitManager.ApiClient;
import com.coachmovecustomer.retrofitManager.ApiHitAndHandle;
import com.coachmovecustomer.retrofitManager.ApiInterface;
import com.coachmovecustomer.retrofitManager.ApiResponse;
import com.coachmovecustomer.utils.Const;
import com.coachmovecustomer.utils.ImageUtils;
import com.coachmovecustomer.utils.NetworkUtil;
import com.coachmovecustomer.utils.PrefStore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.enums.SnackbarType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.HttpException;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse {
    public LayoutInflater inflater;
    public PrefStore store;
    public PermCallback permCallback;
    private Toast toast;
    private Dialog progressDialog;
    private TextView txtMsgTV;
    private int reqCode;
    private NetworksBroadcast networksBroadcast;
    public Picasso picasso;
    private AlertDialog networkAlertDialog;
    private String networkStatus;
    private InputMethodManager inputMethodManager;
    public AlertDialog.Builder networkDialog;
    public ApiInterface apiInterface;
    public ApiHitAndHandle apiHitAndHandle;
    public MultiImageCallBack mImageCallBack;
    Locale myLocale = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inputMethodManager = (InputMethodManager) this.getSystemService(BaseActivity.INPUT_METHOD_SERVICE);
        store = new PrefStore(this);
        initializeNetworkBroadcast();
        createPicassoDownloader();
        strictModeThread();
        toast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        progressDialog();

        networkDialog = new AlertDialog.Builder(this);
        apiHitAndHandle = ApiHitAndHandle.getInstance(this);
//        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface = ApiClient.getClient(store).create(ApiInterface.class);


    }



  /*  public void check() {
        if (store.getProfileData() != null) {
            if (store.getProfileData().profileStatus.equals("1")) {
                goToMainActivity();
            } else {
                startActivity(new Intent(this, LoginSignupActivity.class));
                finish();
            }
        } else {
            startActivity(new Intent(this, LoginSignupActivity.class));
            finish();
        }
    }*/

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    public void initFCM() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        try {
            if (refreshedToken != null)
                refreshedToken = new JSONObject(refreshedToken).getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        store.saveString(Const.FIREBASE_TOKEN, refreshedToken);
        log("Token >>>>>>>  " + refreshedToken);
    }

    private void createPicassoDownloader() {
        OkHttpClient okHttpClient = new OkHttpClient();
        picasso = new Picasso.Builder(this)
                .downloader(new OkHttpDownloader(okHttpClient))
                .build();
    }

    private void initializeNetworkBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        networksBroadcast = new NetworksBroadcast();
        registerReceiver(networksBroadcast, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (networksBroadcast != null) {
                unregisterReceiver(networksBroadcast);
            }
        } catch (Exception e) {
            networksBroadcast = null;
        }
    }


    @Override
    public void onSuccess(Call call, Object object, String resp) {
    }

    @Override
    public void onError(Call call, String errorMessage, ApiResponse apiResponse, int responceCode, JSONObject jsonObject) {
        stopProgressDialog();
        int code = responceCode;
        if (jsonObject != null) {
            if (jsonObject.has("code"))
                code = jsonObject.optInt("code");
            if (errorMessage != null && errorMessage.equals("socketTimeout")) {
                showRetry(call, apiResponse);
            } else {
                handleError(code, errorMessage);
                call.cancel();
            }
        } else {
            handleError(code, errorMessage);
            call.cancel();
        }
    }

    public void handleError(int codee, String msg) {
        if (codee == 401) {
            store.cleanPref();
            startActivity(new Intent(this, LoginSignActivity.class));
            finish();



        } else if (codee > 400 || codee < 499) {
            showToast(msg, false);
        } else if (codee == 500) {
            showAlert(this, getResources().getString(R.string.serverRespond));
        }

        stopProgressDialog();
    }

    @Override
    public void onError(Call call, String errorMessage, ApiResponse apiResponse, int responceCode) {
        stopProgressDialog();
        int code = responceCode;

        if (errorMessage != null && errorMessage.equals("socketTimeout")) {
            showRetry(call, apiResponse);
        } else {
            handleError(code, errorMessage);
            call.cancel();
        }
    }

/*
    @Override
    public void onError(Call call, String errorMessage, ApiResponse apiResponse, int responceCode, JSONObject jsonObject) {
        stopProgressDialog();
        int code = responceCode;
        if (jsonObject.has("code"))
            code = jsonObject.optInt("code");
        if (errorMessage != null && errorMessage.equals("socketTimeout")) {
            showRetry(call, apiResponse);
        } else {
            handleError(code, errorMessage);
            call.cancel();
        }
    }
*/


    public void showRetry(final Call call, final ApiResponse apiResponse) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.alert));
        builder.setMessage(getString(R.string.requestTimeout));
        builder.setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                apiHitAndHandle.makeApiCall(call, apiResponse);
            }
        });
        builder.show();
    }


    public class NetworksBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String status = NetworkUtil.getConnectivityStatusString(context);
            if (status != null)
                showNoNetworkDialog(status);
            else {
                if (networkAlertDialog != null && networkAlertDialog.isShowing())
                    networkAlertDialog.dismiss();
            }
        }
    }

    private void showNoNetworkDialog(String status) {
        networkStatus = status;
        networkDialog.setTitle(getString(R.string.netwrk_status));
        networkDialog.setMessage(status);
        networkDialog.setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!isNetworkAvailable()) {
                    dialog.dismiss();
                    showNoNetworkDialog(networkStatus);
                }
            }
        });
        networkDialog.setCancelable(false);
        networkAlertDialog = networkDialog.create();
        if (!networkAlertDialog.isShowing())
            networkAlertDialog.show();
    }






    public String convertDateFormatLocale(String dateTime, String previousFormat, String destinationFormat) {

        String formattedDateTime = null;

        try {
            DateFormat dateFormat = new SimpleDateFormat(previousFormat, Locale.getDefault());
            Date date = dateFormat.parse(dateTime);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(destinationFormat, Locale.getDefault());
            formattedDateTime = simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDateTime;

    }


    public String convertDateFormat(String dateTime, String previousFormat, String destinationFormat) {

        String formattedDateTime = null;

        try {
            DateFormat dateFormat = new SimpleDateFormat(previousFormat, Locale.ENGLISH);
            Date date = dateFormat.parse(dateTime);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(destinationFormat, Locale.ENGLISH);
            formattedDateTime = simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDateTime;

    }

    public String changeDate(String dateString, String sourceDateFormat/*, String targetDateFormat*/) {
        if (dateString == null || dateString.isEmpty()) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(sourceDateFormat, /*Locale.getDefault()*/  Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        long time = 0;
        try {
            time = sdf.parse(dateString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long now = System.currentTimeMillis();
//        CharSequence ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);
        CharSequence ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.SECOND_IN_MILLIS);
        return String.valueOf(ago);
    }


    public boolean checkPermissions(String[] perms, int requestCode, PermCallback permCallback) {
        this.permCallback = permCallback;
        this.reqCode = requestCode;
        ArrayList<String> permsArray = new ArrayList<>();
        boolean hasPerms = true;
        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                permsArray.add(perm);
                hasPerms = false;
            }
        }
        if (!hasPerms) {
            String[] permsString = new String[permsArray.size()];
            for (int i = 0; i < permsArray.size(); i++) {
                permsString[i] = permsArray.get(i);
            }
            ActivityCompat.requestPermissions(BaseActivity.this, permsString, 99);
            return false;
        } else
            return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean permGrantedBool = false;
        switch (requestCode) {
            case 99:
                for (int grantResult : grantResults) {
                    if (grantResult == PackageManager.PERMISSION_DENIED) {
                        showToast(getString(R.string.not_sufficient_permissions, getString(R.string.app_name)), true);
                        permGrantedBool = false;
                        break;
                    } else {
                        permGrantedBool = true;
                    }
                }
                if (permCallback != null) {
                    if (permGrantedBool) {
                        permCallback.permGranted(reqCode);
                    } else {
                        permCallback.permDenied(reqCode);
                    }
                }
                break;
        }
    }

    public interface PermCallback {
        void permGranted(int resultCode);

        void permDenied(int resultCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageUtils.activityResult(requestCode, resultCode, data);

    }

    public void hideSoftKeyboard() {
        try {
            inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception ignored) {
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null
                && activeNetworkInfo.isConnectedOrConnecting();
    }

    public boolean isValidMail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    public void log(String string) {
        if (BuildConfig.DEBUG)
            Log.e("BaseActivity", string);
    }

    private void progressDialog() {
        progressDialog = new Dialog(this);
        View view = View.inflate(this, R.layout.progress_dialog, null);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(view);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        txtMsgTV = (TextView) view.findViewById(R.id.txtMsgTV);
        progressDialog.setCancelable(false);
    }

    public void startProgressDialog() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            try {
                progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.are_you_sure_want_to_exit))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                }).setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public void showToast(String msg, boolean isError) {
        View view = View.inflate(this, R.layout.layout_toast, null);
        TextView toastTV = view.findViewById(R.id.toastTV);
        if (isError) {
            LinearLayout parentLL = view.findViewById(R.id.parentLL);
            parentLL.setBackgroundColor(ContextCompat.getColor(this, R.color.Black));
        }
        toastTV.setText(msg);
        toast.setView(view);
        toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.show();
    }

    private void strictModeThread() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onClick(View v) {

    }

    public static void showAlert(Context context, String message) {
        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.dismiss();
            }
        });

        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

/*


    public void handleError(int codee, String msg) {

        int code = codee;
        if (code == 403) {
            showToast(msg, false);
//            store.setProfileData(null);
//            startActivity(new Intent(this, LoginSignupActivity.class));
//            finish();

        } else if (!msg.contains("data") && !msg.contains("Data"))
            showToast(msg, false);

        stopProgressDialog();
    }*/

    public void handleError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException response = (HttpException) throwable;
            int code = response.code();
            ResponseBody body = response.response().errorBody();
            Converter<ResponseBody, Error> errorConverter =
                    ApiClient.getClient().responseBodyConverter(Error.class, new Annotation[0]);
            if (code == 403) {
                showToast(throwable.getMessage(), false);
            } else if (code == 500) {
                showAlert(this, getResources().getString(R.string.serverRespond));
            } else if (errorConverter != null && body != null) {
                try {
                    Error error = errorConverter.convert(body);
                    showSnackBar(error.getMessage());
                } catch (IOException e1) {
                    showSnackBar(throwable.getMessage());
                }
            } else
                showSnackBar(throwable.getMessage());
        } else if (throwable instanceof UnknownHostException || throwable instanceof SocketException) {

            showToast(throwable.getMessage(), false);

        } else {
            showSnackBar(throwable.getMessage());
        }
        stopProgressDialog();
    }

    public void showSnackBar(String message) {
        if (message == null)
            return;
        SnackbarManager.show(
                Snackbar.with(getApplicationContext()) // context
                        .text(message) // text to be displayed
                        .type(SnackbarType.MULTI_LINE)
                        .swipeToDismiss(true)
                        .position(Snackbar.SnackbarPosition.BOTTOM)
                        .actionLabel(null)
                        .textColor(Color.WHITE) // change the text color
                        .textTypeface(Typeface.DEFAULT) // change the text font
                        .animation(true)
                        .duration(Snackbar.SnackbarDuration.LENGTH_LONG)
                , this);

        // activity where it is displayed
    }


    public static String setCurrentTimeMillis(Context context) {
        return "?" + SystemClock.currentThreadTimeMillis();
    }


    public InputFilter[] setFiltersET(int length) {
        return new InputFilter[]{new InputFilter.LengthFilter(length), BaseActivity.EMOJI_FILTER};
    }


    public static InputFilter EMOJI_FILTER = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                    return "";
                }
            }
            return null;
        }
    };


    public void changeLang(String lang) {
        if (lang == null) {
//            Log.e("language", store.getLanguage());
//            lang = "en";
            lang = "pt";
        }
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }


   /* public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(lang);//Set Selected Locale
        Locale.setDefault(myLocale);//set new locale as default
        Configuration config = new Configuration();//get Configuration
        config.locale = myLocale;//set config locale as selected locale
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());//Update the config

    }



    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

*/


}
