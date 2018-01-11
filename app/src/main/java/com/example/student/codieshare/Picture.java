package com.example.student.codieshare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.net.URI;

/**
 * Created by student on 2018-01-09.
 */

public class Picture extends Fragment {
    private Button btnGoPicture;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture, container, false);

        btnGoPicture = view.findViewById(R.id.btn_edit_picture);
        btnGoPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crop.pickImage(getContext(), Picture.this);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == Activity.RESULT_OK) {
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getContext().getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(getContext(), Picture.this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == Activity.RESULT_OK) {
            Intent intent = new Intent(getActivity(), PictureActivity.class);
            String pictuerUri = Crop.getOutput(result).toString();
            intent.putExtra("pictuerUri",pictuerUri);
            startActivity(intent);
        } else if (resultCode == Crop.RESULT_ERROR) {
        }
    }
}
