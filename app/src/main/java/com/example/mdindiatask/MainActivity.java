package com.example.mdindiatask;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MarshMallowPermission marshMallowPermission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        marshMallowPermission = new MarshMallowPermission(MainActivity.this);
        if (Build.VERSION.SDK_INT >= 23 && !marshMallowPermission.hasStartupPermission()) {
            marshMallowPermission.checkPermissionForstartUp();
            //Log.d(TAG, "onCreate: inside if");
        } else {
            //Log.d(TAG, "onCreate: in else part");
            startAnimating();
        }

    }
    private void startAnimating() {
        // Load animations for all views within the LinearLayout
        TextView logo = (TextView) findViewById(R.id.textview1);
        Animation spin = AnimationUtils.loadAnimation(this, R.anim.custom_anim);
        logo.startAnimation(spin);

        spin.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationEnd(Animation animation) {
                // The animation has ended, transition to the Main Menu screen
                gotoNextActivity();
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {

            }
        });
    }
    private void gotoNextActivity() {
        startActivity(new Intent(MainActivity.this, HospitalActivity.class));
        MainActivity.this.finish();
    }

}