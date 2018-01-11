package com.example.student.codieshare;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.student.codieshare.child_fragment.AccFragment;
import com.example.student.codieshare.child_fragment.BottomFragment;
import com.example.student.codieshare.child_fragment.ShoesFragment;
import com.example.student.codieshare.child_fragment.TopFragment;

/**
 * Created by student on 2018-01-09.
 */

public class Gallery extends Fragment implements OnClickListener {

    public static Gallery newInstance() {
        return new Gallery();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fv = inflater.inflate(R.layout.gallery, container, false);
        Button btn_outer, btn_top, btn_bottom, btn_shoes, btn_acc;

        btn_outer = (Button) fv.findViewById(R.id.btn_outer);
        btn_top = (Button) fv.findViewById(R.id.btn_top);
        btn_bottom = (Button) fv.findViewById(R.id.btn_bottom);
        btn_shoes = (Button) fv.findViewById(R.id.btn_shoes);
        btn_acc = (Button) fv.findViewById(R.id.btn_acc);

        return fv;
    }

    @Override
    public void onClick(View v) {
        Fragment fg;
        switch (v.getId()) {
            case R.id.btn_outer:
                fg = TopFragment.newInstance();
                setChildFragment(fg);
                break;

            case R.id.btn_top:
                fg = TopFragment.newInstance();
                setChildFragment(fg);
                break;

            case R.id.btn_bottom:
                fg = BottomFragment.newInstance();
                setChildFragment(fg);
                break;

            case R.id.btn_shoes:
                fg = ShoesFragment.newInstance();
                setChildFragment(fg);
                break;

            case R.id.btn_acc:
                fg = AccFragment.newInstance();
                setChildFragment(fg);
                break;

        }
    }

    private void setChildFragment(Fragment child) {
        FragmentTransaction childFt = getChildFragmentManager().beginTransaction();

        if (!child.isAdded()) {
            childFt.replace(R.id.viewpager_child, child);
            childFt.addToBackStack(null);
            childFt.commit();
        }
    }
}
