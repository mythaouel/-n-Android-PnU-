package com.example.activityandintent_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.model.Product1;
import com.example.model.Product2;

public class ReceiveActivity extends AppCompatActivity {

    TextView txtContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        linkViews();
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
//        int numb = intent.getIntExtra("number",8);
//        double marks = intent.getDoubleExtra("marks",8.9);
//        boolean checked = intent.getBooleanExtra("checked",false);
//        String say = intent.getStringExtra("say");
//        Product2 p2 = intent.getParcelableExtra("product_discription");
//        Product1 p = (Product1) intent.getSerializableExtra("product_info");

        Bundle bundle = intent.getBundleExtra("myBundle");
        int numb = bundle.getInt("numb");
        double marks = bundle.getDouble("marks");
        boolean checked = bundle.getBoolean("checked");
        String say = bundle.getString("say");

        Product2 p2=bundle.getParcelable("info");
        txtContent.append("Numb: "+ numb + "\n");
        txtContent.append("Marks: "+ marks + "\n");
        txtContent.append("Checked: "+ checked + "\n");
        txtContent.append("Say "+ say + "\n");
        //txtContent.append("Product "+ p + "\n");

        txtContent.append("Product"+" "+p2.getProductCode() +": "+p2.getProductName()+ " "+p2.getProductPrice() +"\n");

    }


    private void linkViews() {
        txtContent= findViewById(R.id.txtContent);

    }

}