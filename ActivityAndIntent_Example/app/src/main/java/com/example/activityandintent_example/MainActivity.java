package com.example.activityandintent_example;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Process;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;

import com.example.model.Product1;
import com.example.model.Product2;

import java.util.BitSet;

public class MainActivity extends AppCompatActivity {
    Button btnOpenActivity2,btnOpenDialog,btnSendData,btnSend,btnDial,btnCall,btnOpenCamera;
    EditText edtNumber, edtPhoneNumber;
    TextView txtResult;
    ImageView imvPhoto;



    //public static final int REQUEST_CODE =1;

    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity","onCreate");
        linkViews();
        addEvents();
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
            if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                int number = result.getData().getIntExtra("power",-1);
                if(number != -1)
                    txtResult.setText(String.valueOf(number));
                Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                if (bitmap!=null)
                    imvPhoto.setImageBitmap(bitmap);
            }
        });
    }

    private void addEvents() {
        btnOpenActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Activity 2
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent);
            }
        });
        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ReceiveActivity.class);
//                //Attach data - Cách 1
//                intent.putExtra("number",9);
//                intent.putExtra("marks",8.9);
//                intent.putExtra("checked",true);
//                intent.putExtra("say","Hello");
//
//                Product1 p = new Product1(1,"Heineken",190000);
//                intent.putExtra("product_info",p);
//
//                Product2 p2 = new Product2();
//                p2.setProductCode(2);
//                p2.setProductName("Tiger");
//                p2.setProductPrice(18000);
//
//                intent.putExtra("product_decription",p2);

                //Attach Intent - Cách 2

                Bundle bundle = new Bundle();
                bundle.putInt("numb",8);
                bundle.putDouble("marks",8.8);
                bundle.putBoolean("checked",true);
                bundle.putString("say","Welcome");

                Product2 p2 = new Product2();
                p2.setProductCode(3);
                p2.setProductName("Sapporo");
                p2.setProductPrice(20000);

                bundle.putParcelable("info", p2);
                intent.putExtra("myBundle",bundle);
                //startActivity(intent);

                startActivityForResult(intent,1);
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProcessActivity.class);
                //Attach data
                intent.putExtra("number",edtNumber.getText().toString());

                activityResultLauncher.launch(intent);

                //startActivityForResult(intent,REQUEST_CODE);
            }
        });

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:" + edtPhoneNumber.getText().toString());
                intent.setData(uri);
                startActivity(intent);
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:" + edtPhoneNumber.getText().toString());
                intent.setData(uri);
                startActivity(intent);
            }
        });

        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher.launch(intent);
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
//            txtResult.setText(String.valueOf(data.getIntExtra("power",0)));
//        }
//    }

    private void linkViews() {
        btnOpenActivity2 = findViewById(R.id.btnOpenActivity2);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);
        btnSendData = findViewById(R.id.btnSendData);
        btnSend = findViewById(R.id.btnSend);
        btnCall=findViewById(R.id.btnCall);
        btnDial = findViewById(R.id.btnDial);
        btnOpenCamera = findViewById(R.id.btnOpenCamera);

        edtNumber = findViewById(R.id.edtNumber);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);

        txtResult = findViewById(R.id.txtResult);

        imvPhoto = findViewById(R.id.imvPhoto);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity","onDestroy");
    }

}