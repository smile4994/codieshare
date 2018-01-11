package com.example.student.codieshare.child_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.soundcloud.android.crop.Crop;

/**
 * Created by student on 2018-01-10.
 */

public class TopFragment extends Fragment{
    public static TopFragment newInstance(){
        return new TopFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crop.pickImage((Activity) this.getContext());
    }
}
