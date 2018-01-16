
package com.example.student.codieshare;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jypdev.maskcroplibrary.MaskCropView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by student on 2018-01-11.
 */

public class PictureCutActivity extends Activity {
    private MaskCropView view;
    private Button confirmButton;
    private Bitmap bm;

    /**주석테스트**/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_cut);

        view = findViewById(R.id.maskview);

        confirmButton = findViewById(R.id.confirm_button);

        Intent intent = new Intent(this.getIntent());
        String msg = intent.getStringExtra("cutMsg");
        Uri pictureUri = Uri.parse(msg);

        // Uri를 비트맵으로 저장
        try {
            bm = MediaStore.Images.Media.getBitmap(getContentResolver(), pictureUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.setOriginalBitmap(bm);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setBackgroundColor(Color.argb(0, 10, 20, 30));
                saveImage(view.getPicture(), "ttt", "ttt");
                Toast.makeText(PictureCutActivity.this, "사진저장완료", Toast.LENGTH_SHORT).show();
            }
        });
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
