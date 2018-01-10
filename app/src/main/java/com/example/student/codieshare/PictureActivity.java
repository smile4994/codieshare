package com.example.student.codieshare;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.io.File;

/**
 * Created by student on 2018-01-10.
 */

public class PictureActivity extends Activity {
    private Button apBtnEdit;
    private ImageView apImageview;
    private int pressBackCount = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        apBtnEdit = findViewById(R.id.ap_btn_edit);
        apImageview = findViewById(R.id.ap_imageview);

        apBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apImageview.setImageDrawable(null);
                Crop.pickImage(PictureActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == Activity.RESULT_OK) {
            Log.d("onActivityResult", "request pick");
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            Log.d("onActivityResult", "request crop");
            handleCrop(resultCode, result);
        }
    }

    @Override
    public void onBackPressed() {
        if (pressBackCount == 0) {
            Toast.makeText(this, "진짜 종료하고 싶으면 한번 더눌러", Toast.LENGTH_SHORT).show();
            pressBackCount++;
        } else {
            super.onBackPressed();
        }
    }

    private void beginCrop(Uri source) {
        Log.d("beginCrop", "start");
        Uri destination = Uri.fromFile(new File(PictureActivity.this.getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(PictureActivity.this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == Activity.RESULT_OK) {
            Log.d("handleCrop", "Result_OK");
            apImageview.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {
            Log.d("handleCrop", "RESULT_ERROR");
            Toast.makeText(PictureActivity.this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
