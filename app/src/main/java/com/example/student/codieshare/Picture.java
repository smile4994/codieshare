package com.example.student.codieshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by student on 2018-01-09.
 */

public class Picture extends Fragment{
    private  final int pictureCode = 1;
    private Button btnGoPicture;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture, container,false);
        btnGoPicture = view.findViewById(R.id.btn_edit_picture);
        btnGoPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"테스트성공",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),PictureActivity.class);
                startActivityForResult(intent,pictureCode);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode, data);
        if (resultCode == pictureCode){
            if (resultCode == 200){
                Toast.makeText(getContext(),"클릭햇지?",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(),"뒤로 버튼을 눌렀군",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
