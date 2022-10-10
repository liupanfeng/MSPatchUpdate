package com.meishe.patchupdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.meishe.patchupdate.MSPatchUtils;
import com.meishe.patchupdate.databinding.ActivityMainBinding;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    public void doPatch(View view) {
        File newFile=new File(getExternalFilesDir("apk"),"app.apk");
        File patchFile=new File(getExternalFilesDir("apk"),"patch.apk");
        String oldApkPath=getApplicationInfo().sourceDir;
        String newApkPath= newFile.getAbsolutePath();
        String patchPath=patchFile.getAbsolutePath();

        Log.d("lpf","oldApkPath="+oldApkPath);
        Log.d("lpf","newApkPath="+newApkPath);
        Log.d("lpf","patchPath="+patchPath);
        int result = MSPatchUtils.doPatch(oldApkPath,newApkPath, patchPath);
        if (result==0){
            install(newFile);
        }

    }

    private void install(File newFile) {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            Uri apkUri= FileProvider.getUriForFile(this,getPackageName()+".fileProvider",newFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        }else{
            intent.setDataAndType(Uri.fromFile(newFile), "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }
}