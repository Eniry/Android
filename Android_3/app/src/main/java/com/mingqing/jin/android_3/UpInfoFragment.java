package com.mingqing.jin.android_3;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.mingqing.jin.android_3.databinding.FragmentUpInfoBinding;


public class UpInfoFragment extends Fragment {

    private FragmentUpInfoBinding binding;
    private UpInfoBean upInfoBean;

    private  long key;

    public UpInfoFragment(long l) {
        this.key=l;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(binding==null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_up_info, container, false);

        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!=null){
            upInfoBean= (UpInfoBean) getArguments().getSerializable("upInfo");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        binding.upName.setText(upInfoBean.getUpName());
        binding.bodyImage.setBackgroundDrawable(new BitmapDrawable(decodeBitMap(this.getContext(),upInfoBean.getBodyImage())));
    }

    private Bitmap decodeBitMap(Context context, int id){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=2;
        return BitmapFactory.decodeResource(context.getResources(),id,options);
    }

    public  long getTypeId() {
        return key;
    }

    public UpInfoFragment newInstance(long l,UpInfoBean upInfoBean) {
        UpInfoFragment upInfoFragment = new UpInfoFragment(l);
        Bundle bundle = new Bundle();
        bundle.putSerializable("upInfo", upInfoBean);
        upInfoFragment.setArguments(bundle);
        return upInfoFragment;
    }
}