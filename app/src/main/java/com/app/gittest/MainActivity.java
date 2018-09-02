package com.app.gittest;

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
                 Toast.makeText(getApplicationContext(),"确认了",Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onCancel() {
                 Toast.makeText(getApplicationContext(),"取消了",Toast.LENGTH_SHORT).show();
             }
         }).show(getSupportFragmentManager(),"");
    }
}
