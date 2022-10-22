package com.example.sendsms;



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

public class MainActivity extends AppCompatActivity {
    EditText etPhoneNum,etMsg;
    String msg,phoneNum;
    Button btnSend;
    private static final  int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if the permission is not granted
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            //if permission is not granted then check ifn the user has denied the permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS))
            {

            }
            else
            {
                //a pop up will appear asking for required permission i.e allow or deny

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        etMsg = findViewById(R.id.etMsg);
        etPhoneNum = findViewById(R.id.etPhoneNum);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTextMessage();
            }
        });
    }
    //onCreate
    //after getting result of permission request,the result will be passed through this method
    public void onRequestPermissionResult(int requestCode, String permissions[], int[]grantResults)
    {
        //will chdck the requestCode
        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_SEND_SMS:
            {
                //Chdck whether the length of grantResult is greater than 0 and  = PERMISSION_GRANTED
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,"Thanks ", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this,"AYou denied permission ",Toast.LENGTH_LONG).show();
                }
            }
        }//swithc
    }//method

    protected void sendTextMessage()
    {
        msg = etMsg.getText().toString();
        phoneNum = etPhoneNum.getText().toString();

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNum, null,msg, null, null );
        Toast.makeText(this,"Send!", Toast.LENGTH_LONG).show();
    }

}
