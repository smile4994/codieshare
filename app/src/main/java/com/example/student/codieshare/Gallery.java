package com.example.student.codieshare;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by student on 2018-01-09.
 */

public class Gallery extends Fragment {
    private Context context;
    private Button btn_outer, btn_top, btn_bottom, btn_shoes, btn_acc;
    public int num;
    private GridView gv;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gallery, container, false);

        context = getActivity();
        btn_outer = view.findViewById(R.id.btn_outer);
        btn_top = view.findViewById(R.id.btn_top);
        btn_bottom = view.findViewById(R.id.btn_bottom);
        btn_shoes = view.findViewById(R.id.btn_shoes);
        btn_acc = view.findViewById(R.id.btn_acc);
        gv = (GridView) view.findViewById(R.id.gridview);


        final ImageAdapter ia = new ImageAdapter(context);

        btn_outer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "outer", Toast.LENGTH_SHORT).show();
                ia.setNum(1);
                gv.setAdapter(ia);

            }
        });

        btn_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ia.setNum(2);
                gv.setAdapter(ia);

            }
        });

        btn_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ia.setNum(3);
                gv.setAdapter(ia);

            }
        });

        btn_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ia.setNum(4);
                gv.setAdapter(ia);

            }
        });

        btn_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ia.setNum(5);
                gv.setAdapter(ia);

            }
        });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ia.callImageViewer(position);
            }
        });

        return view;
    }


    /**
     * ==========================================
     * Adapter class
     * ==========================================
     */
    public class ImageAdapter extends BaseAdapter {
        private String imgData;
        private String geoData;
        private ArrayList<String> thumbsDataList;
        private ArrayList<String> thumbsIDList;
        private int num;
        private Cursor imageCursor;

        public void setNum(int num) {
            this.num = num;
        }

        public ImageAdapter(Context c) {
            context = c;
            thumbsDataList = new ArrayList<String>();
            thumbsIDList = new ArrayList<String>();
            getThumbInfo(thumbsIDList, thumbsDataList);
        }

        public final void callImageViewer(int selectedIndex) {
            Intent i = new Intent(context, ImagePopup.class);
            String imgPath = getImageInfo(imgData, geoData, thumbsIDList.get(selectedIndex));
            i.putExtra("filename", imgPath);
            startActivityForResult(i, 1);
        }

        public boolean deleteSelected(int sIndex) {
            return true;
        }

        public int getCount() {
            return thumbsIDList.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(450, 450));
                imageView.setAdjustViewBounds(false);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(2, 2, 2, 2);
            } else {
                imageView = (ImageView) convertView;
            }

            // 리스트뷰에 웹 이미지 띄울때 많이 사용하는 라이브러리
            // Glide.with(context).load(이미지URL).into(이미지뷰)
            Glide.with(context).load(thumbsDataList.get(position)).into(imageView);


            return imageView;
        }

        private void getThumbInfo(ArrayList<String> thumbsIDs, ArrayList<String> thumbsDatas) {
            String[] proj = {MediaStore.Images.Media._ID,
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.SIZE};


            Log.i("gaaaaaanum", ""+num);
            switch (num) {
//            Cursor imageCursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                    proj, , null, null);
                case 1:
                 imageCursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        proj, "bucket_DISPLAY_NAME='아우터'", null, null);
                 Log.i("gaaaaaa", "1");
                break;


                case 2:
                    imageCursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            proj, "bucket_DISPLAY_NAME='상의'", null, null);
                    Log.i("gaaaaaa", "2");
                    break;

                case 3:
                    imageCursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            proj, "bucket_DISPLAY_NAME='하의'", null, null);
                    Log.i("gaaaaaa", "3");
                    break;

                case 4:
                    imageCursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            proj, "bucket_DISPLAY_NAME='신발'", null, null);
                    Log.i("gaaaaaa", "4");
                    break;

                case 5:
                    imageCursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            proj, "bucket_DISPLAY_NAME='악세사리'", null, null);
                    Log.i("gaaaaaa", "5");
                    break;


                default:
                    imageCursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            proj, null, null, null);
                    Log.i("gaaaaaa", "6");
                    break;
            }
            if (imageCursor != null && imageCursor.moveToFirst()) {
                String title;
                String thumbsID;
                String thumbsImageID;
                String thumbsData;
                String data;
                String imgSize;

                int thumbsIDCol = imageCursor.getColumnIndex(MediaStore.Images.Media._ID);
                int thumbsDataCol = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
                int thumbsImageIDCol = imageCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
                int thumbsSizeCol = imageCursor.getColumnIndex(MediaStore.Images.Media.SIZE);
                int num = 0;
                do {
                    thumbsID = imageCursor.getString(thumbsIDCol);
                    thumbsData = imageCursor.getString(thumbsDataCol);
                    thumbsImageID = imageCursor.getString(thumbsImageIDCol);
                    imgSize = imageCursor.getString(thumbsSizeCol);
                    num++;
                    if (thumbsImageID != null) {
                        thumbsIDs.add(thumbsID);
                        thumbsDatas.add(thumbsData);
                    }
                } while (imageCursor.moveToNext());
            }
            imageCursor.close();
            return;
        }

        private String getImageInfo(String ImageData, String Location, String thumbID) {
            String imageDataPath = null;
            String[] proj = {MediaStore.Images.Media._ID,
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.SIZE};
            Cursor imageCursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    proj, "_ID='" + thumbID + "'", null, null);

            if (imageCursor != null && imageCursor.moveToFirst()) {
                if (imageCursor.getCount() > 0) {
                    int imgData = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    imageDataPath = imageCursor.getString(imgData);
                }
            }
            imageCursor.close();
            return imageDataPath;
        }
    }

}
