package com.example.student.codieshare;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by student on 2018-01-10.
 */

public class PictureActivity extends Activity {
    private Button apBtnSave;
    private ImageView apImageview;

    private  Button apBtnTest;

    private Bitmap bm;
    private String savePictureName;

    private String testMsg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        apBtnSave = findViewById(R.id.ap_btn_save);
        apImageview = findViewById(R.id.ap_imageview);
        apBtnTest = findViewById(R.id.ap_btn_test);

        // fragment에서 사진 가져오는 작업
        Intent intent = new Intent(this.getIntent());
        String msg = intent.getStringExtra("pictuerUri");

        testMsg = msg;

        Uri pictureUri = Uri.parse(msg);
        apImageview.setImageDrawable(null);
        apImageview.setImageURI(pictureUri);

        // Uri를 비트맵으로 저장
        try {
            bm = MediaStore.Images.Media.getBitmap(getContentResolver(), pictureUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        apBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               makeDialogSaveName().show();
            }
        });

        apBtnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(PictureActivity.this,"testMsg = "+testMsg,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PictureActivity.this, TestActivity.class);
                intent.putExtra("testMsg",testMsg);
                startActivity(intent);

            }
        });
    }

    private Dialog makeDialogSave() {
        final Dialog custom = new Dialog(this);
        custom.setContentView(R.layout.dialog_save);
        Button outerwear = custom.findViewById(R.id.btn_outerwear);
        Button shirt = custom.findViewById(R.id.btn_shirt);
        Button pants = custom.findViewById(R.id.btn_pants);
        Button shoes = custom.findViewById(R.id.btn_shoes);
        Button accessory = custom.findViewById(R.id.btn_accessory);

        outerwear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(bm, "아우터", savePictureName);
                custom.cancel();
                Toast.makeText(getApplicationContext(), "아우터 폴터에 저장됨", Toast.LENGTH_SHORT).show();
            }
        });
        shirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(bm, "상의", savePictureName);
                custom.cancel();
                Toast.makeText(getApplicationContext(), "상의 폴터에 저장됨", Toast.LENGTH_SHORT).show();
            }
        });
        pants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(bm, "하의", savePictureName);
                custom.cancel();
                Toast.makeText(getApplicationContext(), "하의 폴터에 저장됨", Toast.LENGTH_SHORT).show();
            }
        });
        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(bm, "신발", savePictureName);
                custom.cancel();
                Toast.makeText(getApplicationContext(), "신발 폴터에 저장됨", Toast.LENGTH_SHORT).show();
            }
        });
        accessory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(bm, "악세사리", savePictureName);
                custom.cancel();
                Toast.makeText(getApplicationContext(), "악세사리 폴터에 저장됨", Toast.LENGTH_SHORT).show();
            }
        });
        return custom;
    }

    private Dialog makeDialogSaveName() {
        final Dialog custom = new Dialog(this);
        custom.setContentView(R.layout.dialog_save_name);
        final EditText name = custom.findViewById(R.id.et_save);
        Button saveName = custom.findViewById(R.id.btn_save);

        saveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                String randum = r.nextInt(100)+"";
                savePictureName = "";
                savePictureName = name.getText()+"_"+randum;
                custom.cancel();
                makeDialogSave().show();
            }
        });
        return custom;
    }

    private void saveImage(Bitmap bitmap, String folder, String name) {
        String ex_storage = Environment.getExternalStorageDirectory().getAbsolutePath();
        // String folder_name = "/DCIM/Camera/"+folder+"/"; -> 기존 Camera에서 저장되는경우 화면에 보이질 않는다
        String folder_name = "/Pictures/" + folder + "/"; // 새로운 앨범에 추가
        String file_name = name + ".jpg";
        String folder_path = ex_storage + folder_name;

        try {
            // 폴더 만들기
            File savedFolder = new File(folder_path);
            if (savedFolder != null && !savedFolder.exists()) {
                Log.d("jys", "make folder / " + savedFolder.exists());
                savedFolder.mkdirs();
            } else {
                Log.d("jys", "exists folder" + savedFolder.exists());
            }
            Log.d("jys", folder_path + file_name);

            FileOutputStream out = new FileOutputStream(folder_path + file_name);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();

            //미디어 스캐닝을 실시한다(갤러리 바로 보여지게)
            Intent intent = new Intent();
            sendBroadcast(new Intent(intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + folder_path + file_name)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
