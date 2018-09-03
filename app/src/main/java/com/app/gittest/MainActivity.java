package com.app.gittest;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         CommonDialogFragment.newInstance(0, R.style.anim_dialog, new CommonDialogFragment.OnDialogCallBack() {
             @Override
             public void onConfirm() {
                 startActivity( getAppDetailSettingIntent());
                ;
                 Toast.makeText(getApplicationContext(),"确认了",Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onCancel() {
                 Toast.makeText(getApplicationContext(),"取消了",Toast.LENGTH_SHORT).show();
             }
         }).show(getSupportFragmentManager(),"");
    }
    private Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        return localIntent;
    }
}
