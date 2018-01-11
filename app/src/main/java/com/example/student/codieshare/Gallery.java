package com.example.student.codieshare;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.student.codieshare.gallery_tab.Acc;
import com.example.student.codieshare.gallery_tab.Bottom;
import com.example.student.codieshare.gallery_tab.Outer;
import com.example.student.codieshare.gallery_tab.Shoes;
import com.example.student.codieshare.gallery_tab.Top;
import com.soundcloud.android.crop.Crop;

/**
 * Created by student on 2018-01-09.
 */

public class Gallery extends Fragment{
    private Context context;
    private Button btn_outer, btn_top, btn_bottom, btn_shoes, btn_acc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gallery, container, false);

        btn_outer = view.findViewById(R.id.btn_outer);
        btn_top = view.findViewById(R.id.btn_top);
        btn_bottom = view.findViewById(R.id.btn_bottom);
        btn_shoes = view.findViewById(R.id.btn_shoes);
        btn_acc = view.findViewById(R.id.btn_acc);

        btn_outer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Crop.pickImage(getContext(), Gallery.this);
            }
        });

        return view;
    }
}
