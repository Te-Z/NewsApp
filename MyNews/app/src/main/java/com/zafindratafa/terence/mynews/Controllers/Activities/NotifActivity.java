package com.zafindratafa.terence.mynews.Controllers.Activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.zafindratafa.terence.mynews.R;
import com.zafindratafa.terence.mynews.Utils.NotificationReceiver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotifActivity extends AppCompatActivity {

    @BindView(R.id.activity_notif_toolbar)
    Toolbar mToolbar;

    // Query Search
    @BindView(R.id.form_search_query)
    EditText searchQuery;
    // Checkboxes
    @BindView(R.id.form_search_warning_checkboxes)
    TextView mCheckboxesTextview;
    @BindView(R.id.form_search_left_checkBox_arts)
    CheckBox artsCheckBox;
    @BindView(R.id.form_search_left_checkBox_business)
    CheckBox businessCheckBox;
    @BindView(R.id.form_search_left_checkBox_entrepreneurs)
    CheckBox entrepreneursCheckBox;
    @BindView(R.id.form_search_right_checkBox_politics)
    CheckBox politicsCheckBox;
    @BindView(R.id.form_search_right_checkBox_sports)
    CheckBox sportsCheckBox;
    @BindView(R.id.form_search_right_checkBox_travel)
    CheckBox travelCheckBox;
    // Search button
    @BindView(R.id.form_search_button)
    Button searchButton;
    // Notifications switch
    @BindView(R.id.form_search_notif)
    LinearLayout mNotifLayout;
    @BindView(R.id.form_search_switch_notif)
    Switch notifSwitch;

    // For Notification
    public static final int uniqueID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
        ButterKnife.bind(this);

        // Add back button
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Configure the notification switch
        this.configureNotifications();
    }

    // ---------------
    // CONFIGURATION
    // ---------------

    private void configureNotifications(){
        // Set the UI
        mNotifLayout.setVisibility(View.VISIBLE);
        searchButton.setVisibility(View.GONE);


        notifSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    if (TextUtils.isEmpty(searchQuery.getText())) {
                        searchQuery.setError("This field is required !");
                    }else if(checkingCheckboxes() == false){
                        mCheckboxesTextview.setVisibility(View.VISIBLE);
                    } else {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, 12);

                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), uniqueID, configIntent(), PendingIntent.FLAG_UPDATE_CURRENT);

                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                    }
                }
            }
        });
    }

    // ---------------
    // DATA
    // ---------------

    private Intent configIntent(){
        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);

        intent.putExtra("QUERY", searchQuery.getText().toString());
        intent.putExtra("FROM_DATE", "20000101");
        intent.putExtra("TO_DATE", getCurrentDay());
        intent.putExtra("CHECKBOXES", checkBoxString());

        return intent;
    }

    // Create a piece or URI with checked checkboxes
    @NonNull
    private String checkBoxString(){
        String checkString = null;

        List<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(artsCheckBox);
        checkBoxes.add(businessCheckBox);
        checkBoxes.add(entrepreneursCheckBox);
        checkBoxes.add(politicsCheckBox);
        checkBoxes.add(sportsCheckBox);
        checkBoxes.add(travelCheckBox);

        for (CheckBox checkBox: checkBoxes){
            if(checkBox.isChecked() && checkString != null) {
                checkString = checkString+"\" \""+checkBox.getText().toString();
            } else if (checkBox.isChecked() && checkString == null){
                checkString = "news_desk(\""+checkBox.getText().toString();
            }
        }

        return checkString+"\")";
    }

    private String getCurrentDay(){
        String toDate = new String();

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        int month = cal.get(Calendar.MONTH)+1;
        String monthString = Integer.toString(month);
        if (monthString.length() == 1) monthString = 0 +monthString;

        int day = cal.get(Calendar.DAY_OF_MONTH);
        String dayString = Integer.toString(day);
        if (dayString.length() == 1) dayString = 0+dayString;

        return toDate = Integer.toString(year) + monthString + dayString;
    }

    // Check if one checkbox is checked
    private boolean checkingCheckboxes() {
        Boolean checkCheckboxes = false;

        List<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(artsCheckBox);
        checkBoxes.add(businessCheckBox);
        checkBoxes.add(entrepreneursCheckBox);
        checkBoxes.add(politicsCheckBox);
        checkBoxes.add(sportsCheckBox);
        checkBoxes.add(travelCheckBox);

        for (CheckBox checkBox: checkBoxes){
            if (checkBox.isChecked()) checkCheckboxes = true;
        }

        return checkCheckboxes;
    }
}
