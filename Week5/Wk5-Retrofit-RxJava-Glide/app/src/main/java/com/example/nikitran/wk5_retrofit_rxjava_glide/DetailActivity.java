package com.example.nikitran.wk5_retrofit_rxjava_glide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    public static final String DETAIL_EXTRA = "com.example.nikitran.wk5_rxretrofit2.DETAIL_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView pic = (ImageView) findViewById(R.id.pic);
        TextView name = (TextView) findViewById(R.id.tv_name);
        TextView location = (TextView) findViewById(R.id.tv_location);
        TextView email = (TextView) findViewById(R.id.tv_email);
        TextView phone = (TextView) findViewById(R.id.tv_phone);


        Intent intent = getIntent();
        Result result = (Result) intent.getSerializableExtra(DETAIL_EXTRA);

        String picture = result.getPicture().getLarge();
        //Loading image from url into imageView
        Glide.with(this)
                .load(picture)
                .override(200, 200)
                .into(pic);

        name.setText(result.getName().toString());
        location.setText(result.getLocation().toString());
        email.setText("Email: " + result.getEmail());
        phone.setText("Phone: " + result.getPhone());

    }
}
