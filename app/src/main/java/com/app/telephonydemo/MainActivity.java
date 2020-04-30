package com.app.telephonydemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4, ed5;
Button btn1, btn2, btn3, btn4;
Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        ed3=(EditText)findViewById(R.id.ed3);
        ed4=(EditText)findViewById(R.id.ed4);
        ed5=(EditText)findViewById(R.id.ed5);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
    }

    public void call(View view) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" +ed1.getText().toString()));
        startActivity(intent);
    }

    public void sendSms(View view) {
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(ed1.getText().toString(),null,ed2.getText().toString(),null,null);
    }

    public void attach(View view) {
        Intent i=new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        //all files of all formats
        i.setType("*/*");
        startActivityForResult(i,123);
    }

    public void send(View view) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ed3.getText().toString()} );
        intent.putExtra(Intent.EXTRA_SUBJECT, ed4.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, ed5.getText().toString());
        // get the attachment file
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        //get MIME data
        intent.setType("message/rfc822");
        startActivity(intent.createChooser(intent,"please add sender email"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //using 3rd parmenter object can get the data of attachment
        uri=data.getData();
    }
}
