package com.example.shankaratalk;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.shankaratalk.fragment.Home;
import com.example.shankaratalk.mainactivity.HomeActivity;
import com.example.shankaratalk.mainactivity.Registration_page;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView2 = findViewById(R.id.imageView2);

        imageView2 = findViewById(R.id.imageView2);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationforbutton);
        imageView2.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();

                } else {
                    startActivity(new Intent(MainActivity.this, Registration_page.class));
                    finish();
                }


            }
        }, 3000);

    }

}