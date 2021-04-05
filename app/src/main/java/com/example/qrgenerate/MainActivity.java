package com.example.qrgenerate;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private EditText fullname, address, contactnumber, emailadress;
    private Button button;
    private CheckBox checkBox;
    private TextView warn_name,warn_address, warn_contactnumber;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewS();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    if(checkBox.isChecked()){
                        initQRCodes();
                    }else{
                        Toast.makeText(MainActivity.this, "Check the agreement", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }

    private boolean validate() {
        if(fullname.getText().toString().equals("")){
            warn_name.setVisibility(View.VISIBLE);
            warn_name.setText("Enter a Full Name");
            return false;
        }

        if(address.getText().toString().equals("")){
            warn_address.setVisibility(View.VISIBLE);
            warn_address.setText("Enter an Address");
            return false;
        }

        if (contactnumber.getText().toString().equals("")){
            warn_contactnumber.setVisibility(View.VISIBLE);
            warn_contactnumber.setText("Enter a Contact Number");
            return false;
        }

        return true;

    }

    private void initQRCodes() {
        warn_name.setVisibility(View.GONE);
        warn_address.setVisibility(View.GONE);
        warn_contactnumber.setVisibility(View.GONE);


        String name= fullname.getText().toString();
        String location=address.getText().toString();
        String contact=contactnumber.getText().toString();
        String email=emailadress.getText().toString();

        StringBuilder textToSend = new StringBuilder();
        textToSend.append( " Name: " + name + " | Contact Number: " + contact +" | Address: " + location + " | Email: " + email);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(textToSend.toString(), BarcodeFormat.QR_CODE, 600, 600);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
            imageView.setVisibility(View.VISIBLE);

        }catch (WriterException e){
            e.printStackTrace();
        }

    }



    private void initViewS() {
        fullname=findViewById(R.id.EditTextName);
        address=findViewById(R.id.EditTextAddress);
        contactnumber=findViewById(R.id.EditTextContactNumber);
        emailadress=findViewById(R.id.EditTextEmailAddress);

        button=findViewById(R.id.button);
        checkBox=findViewById(R.id.checkBox);
        warn_address=findViewById(R.id.warnAddress);
        warn_name=findViewById(R.id.warnName);
        warn_contactnumber=findViewById(R.id.warnContactNumber);
        imageView=findViewById(R.id.imageView);


    }
}