package com.example.demoapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.databinding.ActivitySendSmsBinding;

public class SendSmsActivity extends AppCompatActivity {

    private ActivitySendSmsBinding activitySendSmsBinding;
    EditText editTextPhone, editTextMsg;
    Button btnSent;
    private SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySendSmsBinding = ActivitySendSmsBinding.inflate(getLayoutInflater());
        setContentView(activitySendSmsBinding.getRoot());

        editTextPhone = activitySendSmsBinding.editTextPhone;
        editTextMsg = activitySendSmsBinding.editTextMessage;
        btnSent = activitySendSmsBinding.btnSent;
        sharedPreferenceHelper = new SharedPreferenceHelper(this);
        editTextMsg.setText(sharedPreferenceHelper.getDefaultSms());


        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(SendSmsActivity.this, Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED) {
                    //when permission is granted
                    //create a method
                    sendSMS();
                } else {
                    //when permission is not granted
                    //request for permission
                    ActivityCompat.requestPermissions(SendSmsActivity.this, new String[]{Manifest.permission.SEND_SMS}, 100);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //check condition
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //permission is granted
            //call method
            sendSMS();
        } else {
            //when permission is denied
            //display toast msg
            Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSMS() {
        //get value form editText
        String phone = editTextPhone.getText().toString();
        String message = editTextMsg.getText().toString();

        //check condition if string is empty or not
        if (!phone.isEmpty() && !message.isEmpty()) {
            //initialize SMS Manager
            SmsManager smsManager = SmsManager.getDefault();
            //send message
            smsManager.sendTextMessage(phone, null, message, null, null);
            //display Toast msg
            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter phone and message", Toast.LENGTH_SHORT).show();
        }
    }
}