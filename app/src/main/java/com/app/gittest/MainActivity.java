package com.app.gittest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_PERMISSION_CAMERA = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestPermission(true);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        requestPermission(false);
    }

    private void requestPermission(boolean showFragment) {
        // thate have no camera permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //request camera permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSION_CAMERA);
        } else {
            if (showFragment) {
               enterPermissionFragment();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // request camera permission successful
        } else {
            // request camera permission failure
            enterPermissionFragment();
        }
    }

    private void enterPermissionFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container,new RequestPermissionFragment(),RequestPermissionFragment.class.getSimpleName())
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
