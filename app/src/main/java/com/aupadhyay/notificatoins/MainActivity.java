package com.aupadhyay.notificatoins;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button normalNotificationButton, bigStyleNotificationButton, cancelNormalNotificationButton;

    public void showNormalNotification()
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setSmallIcon(android.R.drawable.sym_def_app_icon);
        builder.setContentTitle("Normal Notificatoin");
        builder.setContentText("This is normal type of notification being displayed");

        // to navigate from notification to the SampleActivity
        Intent i = new Intent(this, SampleActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 100, i, 0);
        builder.setContentIntent(pi);

        // create notification from builder
        Notification notification = builder.build();

        // create notification manager
        NotificationManager  notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    public void showBigStyleNotificatoin()
    {
        String message = "This is a big style notification introduced in Android API 16 this can display multiple lines of text inside the notification here is the example infront of you :)";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setSmallIcon(android.R.drawable.alert_dark_frame);
        builder.setContentTitle("Big Style Notificatoin");
        //builder.setContentText("Hello");

        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
        // to navigate from notification to the SampleActivity
        Intent i = new Intent(this, SampleActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 101, i, 0);
        //builder.setContentIntent(pi);
        builder.addAction(android.R.drawable.ic_menu_add, "Add", pi);
        builder.addAction(android.R.drawable.ic_menu_revert, "Add", pi);

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(2, notification);
    }

    // notificationManager.cancel(1);// clear the notification from the status bar. 1 is the id of the notificatoin.

    public void initViews()
    {

        normalNotificationButton = (Button) findViewById(R.id.normalNotificationButton);
        bigStyleNotificationButton = (Button) findViewById(R.id.bigStyleNotificationButton);
        cancelNormalNotificationButton = (Button) findViewById(R.id.cancelNormalNotificationButton);

        normalNotificationButton.setOnClickListener(this);
        bigStyleNotificationButton.setOnClickListener(this);
        cancelNormalNotificationButton.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.normalNotificationButton:
                showNormalNotification();
                break;
            case R.id.bigStyleNotificationButton:
                showBigStyleNotificatoin();
                break;
            case R.id.cancelNormalNotificationButton:
                Toast.makeText(this, "you can use notificationManager.cancel(id number) to clear the notification from status bar", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
