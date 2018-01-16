package com.example.student.codieshare;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yalantis.ucrop.UCrop;

public class MainActivity extends AppCompatActivity{
    private Button[] tabs = new Button[4];
    private ViewPager viewPager;
    private Fragment[] frags = new Fragment[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        tabs[0] = findViewById(R.id.btn_camera);
        tabs[1] = findViewById(R.id.btn_gallery);
        tabs[2] = findViewById(R.id.btn_board);
        tabs[3] = findViewById(R.id.btn_settings);
        viewPager = findViewById(R.id.viewpager);

        for (int i = 0; i < tabs.length; i++) {
            final int finalI = i;
            tabs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(finalI);
                    tabs[0].setBackgroundColor(0x5588ff00);
                    tabs[1].setBackgroundColor(0x55888800);
                    tabs[2].setBackgroundColor(0x55886600);
                    tabs[3].setBackgroundColor(0x55884400);
                    v.setBackgroundColor(0xffffff);
                }
            });
        }
        frags[0] = new Picture();
        frags[1] = new Gallery();
        frags[2] = new Board();
        frags[3] = new Settings();

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabs[0].setBackgroundColor(0x5588ff00);
                tabs[1].setBackgroundColor(0x55888800);
                tabs[2].setBackgroundColor(0x55886600);
                tabs[3].setBackgroundColor(0x55884400);
                tabs[position].setBackgroundColor(0xffffff);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            // listview 어댑터 작성할 때 getView 메소드와 비슷
            return frags[position];
        }
        @Override
        public int getCount() {
            return frags.length;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == UCrop.REQUEST_CROP) {     // Picture 에서 받은 request코드를 강제로 다시 인텐트시킨다
            Log.d("jys", requestCode + "/" + resultCode);
            frags[0].onActivityResult(requestCode, resultCode, data);
        }
    }
}
