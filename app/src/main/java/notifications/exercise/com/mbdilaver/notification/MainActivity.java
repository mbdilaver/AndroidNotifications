package notifications.exercise.com.mbdilaver.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText mNotificationTitle;
    EditText mNotificationText;
    Button mNotificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationTitle = (EditText) findViewById(R.id.notificationTitleEditText);
        mNotificationText = (EditText) findViewById(R.id.notificationTextEditText);
        mNotificationButton = (Button) findViewById(R.id.notificationButton);

        mNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = String.valueOf(mNotificationTitle.getText());
                String text = String.valueOf(mNotificationText.getText());
                mNotificationTitle.setText(null);
                mNotificationText.setText(null);
                issueNotification(title, text);
            }
        });
    }

    public void issueNotification(String title, String text) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setAutoCancel(true);

        Intent resultIntent = new Intent(this, MainActivity.class);


        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        // Sets an ID for the notification
        int mNotificationId = 001;
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.

        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
