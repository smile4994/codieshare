package com.example.student.codieshare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by student on 2018-01-15.
 */

public class ImagePopup extends Activity implements View.OnClickListener {
    private Context mContext = null;
    private final int imgWidth = 740;
    private final int imgHeight = 740;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_popup);
        mContext = this;

        /** 전송메시지 */
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String imgPath = extras.getString("filename");

        /** 완성된 이미지 보여주기  */
        BitmapFactory.Options bfo = new BitmapFactory.Options();
        bfo.inSampleSize = 2;
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        Bitmap bm = BitmapFactory.decodeFile(imgPath, bfo);
        Bitmap resized = Bitmap.createScaledBitmap(bm, imgWidth, imgHeight, true);
        iv.setImageBitmap(resized);

        /** 리스트로 가기 버튼 */
        Button btn = (Button) findViewById(R.id.btn_back);
        btn.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_back:
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
