/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.coachmovecustomer.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Map;
import java.util.Base64;


public class MyFcmListenerService extends FirebaseMessagingService {

    String TAG = MyFcmListenerService.class.getSimpleName();
    NotificationManager mNotificationManager;
    String id = "Coach Move Customer";
    public PrefStore store;
    public static Fragment currentFragment = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.e(TAG, "From: " + remoteMessage.getFrom());







        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData());

            Bundle bundle = new Bundle();
            for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }

//            switch (remoteMessage.getData().get(Const.NOTIFICATION_TAG)) {

//                case Const.NOTIFICATION_TAG:

//                    Log.e("responseNotification",remoteMessage.getData().get(Const.SENDER_NAME)/*, remoteMessage.getData().get(NotificationKey.MESSAGE)*/);

//                    if(currentFragment instanceof ChatFragment){
//                        Intent intent = new Intent();
//                        intent.setAction(Globals.ACTION_LOAD_CHAT);
//                        intent.putExtra(BundleKey.NOTIFICATION_DATA,bundle);
//                        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
//
//                    }else if(currentFragment instanceof MessagesFragment){
//                        Intent intent = new Intent();
//                        intent.setAction(Globals.ACTION_LOAD_CHAT);
//                        intent.putExtra(BundleKey.NOTIFICATION_DATA,bundle);
//                        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
//
            sendNotification(remoteMessage.getData().get("title"),
                    remoteMessage.getData().get("tag"),bundle);




         /*   String incript = remoteMessage.getData().get("body");
            Toast.makeText(this, remoteMessage.getData().get("body") + "", Toast.LENGTH_SHORT).show();*/
//
//                    }else {
//                        sendNotification(remoteMessage.getData().get(NotificationKey.SENDER_NAME),
//                                remoteMessage.getData().get(NotificationKey.MESSAGE),
//                                MessagesFragment.class.getSimpleName(),bundle,
//                                Integer.parseInt(remoteMessage.getData().get(NotificationKey.CONNECTION_ID)));
//                    }
//
//                    break;
//
//                case NotificationType.FMN:
//
//                    L.e(remoteMessage.getData().get(NotificationKey.SENDER_NAME), remoteMessage.getData().get(NotificationKey.MESSAGE));
////                    sendNotification(getString(R.string.app_name),
////                            remoteMessage.getData().get(NotificationKey.MESSAGE),
////                            MessagesFragment.class.getSimpleName(),bundle,m);
//
//                    break;
//
//                case NotificationType.ITS_A_MATCH:
//
//                    L.e(TAG, remoteMessage.getData().get(NotificationKey.LABEL));
//                    sendNotification(getString(R.string.app_name),
//                            remoteMessage.getData().get(NotificationKey.MESSAGE),
//                            HomeFragment.class.getSimpleName(),bundle,m);
//
//                    break;
//
//            }
        }

        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }


    private void sendNotification(String title, String messageBody , Bundle response) {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtra("tagNotification", response);
        intent.putExtra("messageBody", true);
     /*   intent.putExtra("senderId", response.getString("senderId"));*/
        intent.putExtras(bundle);
        intent.setAction(Long.toString(System.currentTimeMillis()));
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, intent,
                PendingIntent.FLAG_ONE_SHOT);

     /*   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setAction(Long.toString(System.currentTimeMillis()));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);*/

        String channelId = getString(R.string.app_name);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText("")
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }


        assert notificationManager != null;
        notificationManager.notify(0, notificationBuilder.build());
    }

}


//        Log.e(TAG, "From: " + remoteMessage.getFrom());
//
//        Log.e(TAG, "payload : " + remoteMessage.getData());
////        Log.e(TAG, "payload : " + remoteMessage.getNotification().getBody());
//
////        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        try {
//            createChannel(remoteMessage);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//
//    /**
//     * Create Channel Id for Android version Oreo and above (mandatory) ...
//     */
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void createChannel(RemoteMessage remoteMessage) throws JSONException {
//        CharSequence name = "Coach Move channel";
//        String description = "Coach Move description";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
//            mChannel.setDescription(description);
//            mChannel.enableLights(true);
//            mChannel.enableVibration(true);
//            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//            mNotificationManager.createNotificationChannel(mChannel);
//        }
////        JSONObject json = new JSONObject(remoteMessage.getData().get("notification"));
//        Map data = remoteMessage.getData();
//
//        Bundle bundle = new Bundle();
//        for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {
//            bundle.putString(entry.getKey(), entry.getValue());
//        }
//        showNotification(this, bundle);
//    }
//
//
//    @SuppressLint("NewApi")
//    public void showNotification(Context context, Bundle extra) {
//        String notificationMessage = (String) extra.get("message");
//
//        Intent notificationIntent = new Intent(context, MainActivity.class);
//        Bundle bundle = new Bundle();
//        notificationIntent.putExtra("map", extra);
//        notificationIntent.putExtra("isPush", true);
//        notificationIntent.putExtra("notification_type_id", extra.getString("type"));
//        int notificationID = extra.getInt("id");
//        notificationIntent.putExtras(bundle);
//
//        PendingIntent contentIntent = PendingIntent.getActivity(context,
//                0, notificationIntent,
//                PendingIntent.FLAG_CANCEL_CURRENT);
//
//        mNotificationManager = (NotificationManager) context
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//
//        Notification.Builder builder = new Notification.Builder(context);
//
//        Notification notification = null;
//
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            builder.setContentIntent(contentIntent)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setWhen(System.currentTimeMillis())
//                    .setAutoCancel(true)
//                    .setSound(alarmSound)
////                    .setContentTitle(this.getResources()
////                     /       .getString(R.string.app_name))
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                    .setContentText(notificationMessage)
//                    .setChannelId(id);
//            notification = builder.build();
//            notification.flags |= Notification.FLAG_AUTO_CANCEL;
//
//
//        } else {
//            builder.setContentIntent(contentIntent)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setSound(alarmSound)
//                    .setWhen(System.currentTimeMillis())
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
////                    .setContentTitle(this.getResources().getString(R.string.app_name))
//                    .setContentText(notificationMessage);
//            notification = builder.build();
//            notification.flags |= Notification.FLAG_AUTO_CANCEL;
//
//        }
//        store = new PrefStore(this);
//
//        if (!store.getBoolean(Const.FORGROUND, false))
//            mNotificationManager.notify(notificationID, notification);
//
//    }

