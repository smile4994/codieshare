package com.example.student.codieshare.gallery_tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.student.codieshare.R;

/**
 * Created by student on 2018-01-10.
 */

public class Outer extends Fragment{
    private GridView gridView;
    public static Outer newInstance(){
        return new Outer();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.outer, container, false);

        gridView = fv.findViewById(R.id.gridview_outer);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
