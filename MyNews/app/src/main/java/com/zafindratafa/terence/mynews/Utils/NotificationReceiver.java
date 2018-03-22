package com.zafindratafa.terence.mynews.Utils;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.zafindratafa.terence.mynews.Controllers.Activities.MainActivity;
import com.zafindratafa.terence.mynews.Controllers.Activities.NotifActivity;
import com.zafindratafa.terence.mynews.Controllers.Activities.ResultActivity;
import com.zafindratafa.terence.mynews.Controllers.Activities.SearchActivity;
import com.zafindratafa.terence.mynews.R;

/**
 * Created by maverick on 22/03/18.
 */

public class NotificationReceiver extends BroadcastReceiver {

    private static final String MYNEWS_CHANNEL_ID = "com.zafindratafa.terence.mynews";
    private static final String MYNEWS_CHANNEL_NAME = "MYNEWS Channel";

    // FOR DATA
    private String QUERY;
    private String FROM_DATE;
    private String TO_DATE;
    private String CHECKBOXES;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(MYNEWS_CHANNEL_ID,
                    MYNEWS_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }

        // Get data from SearchActivity

        Intent resultIntent = new Intent(context, ResultActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, MYNEWS_CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle("Notification title") // title for notification
                .setContentText("Notification text")// message for notification
                .setAutoCancel(true); // clear notification after click

        mNotificationManager.notify(NotifActivity.uniqueID, mBuilder.build());
    }
}
