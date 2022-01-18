package com.mingqing.jin.android_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mingqing.jin.android_3.databinding.ActivityDetailBinding;

import java.util.Random;

public class DetailActivity extends AppCompatActivity {

    private UpInfoBean upInfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent()!=null&&getIntent().getExtras()!=null){
            Bundle extras=getIntent().getExtras();
            upInfoBean= (UpInfoBean) extras.getSerializable("upInfo");
        }
        ActivityDetailBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_detail);
        binding.upName.setText(upInfoBean.getUpName());
        binding.upImage.setImageResource(upInfoBean.getUpImage());
        binding.fansNumber.setText(String.valueOf(upInfoBean.getFans()));
        binding.cancel.setOnClickListener(view -> {
            Toast.makeText(DetailActivity.this,"取关成功",Toast.LENGTH_SHORT).show();
            upInfoBean.setFocus(false);
            Intent intent=new Intent();
            Bundle bundle=new Bundle();
            bundle.putSerializable("upInfo",upInfoBean);
            intent.putExtras(bundle);
            setResult(RESULT_OK,intent);
            finish();
        });
    }


}