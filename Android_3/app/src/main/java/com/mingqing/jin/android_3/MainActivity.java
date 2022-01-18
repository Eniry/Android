package com.mingqing.jin.android_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.mingqing.jin.android_3.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UpInfoAdapter adapter;
    private List<UpInfoBean> list;

    private List<UpInfoFragment> fragmentList;

    private int choosePosition;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        list=new ArrayList<>();
        fragmentList=new ArrayList<>();
        list.add(new UpInfoBean("陆仁甲",R.drawable.image1,R.drawable.image1_1));
        list.add(new UpInfoBean("陆仁乙",R.drawable.image2,R.drawable.image2_1));
        list.add(new UpInfoBean("陆仁丙",R.drawable.image3,R.drawable.image3_1));
        list.add(new UpInfoBean("陆仁丁",R.drawable.image4,R.drawable.image4_1));
        list.add(new UpInfoBean("陆仁戊",R.drawable.image5,R.drawable.image5_1));
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.recyclerView.setLayoutManager(layoutManager);
        adapter=new UpInfoAdapter(this,list);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position) -> binding.viewPager.setCurrentItem(position));
        adapter.setOnItemLongClickListener((view, position) -> {
            choosePosition=position;
            Intent intent=new Intent(MainActivity.this,DetailActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("upInfo",list.get(position));
            intent.putExtras(bundle);
            startActivityForResult(intent,1);
        });
        UpInfoFragment fragment1=new UpInfoFragment(1l).newInstance(1l,list.get(0));
        UpInfoFragment fragment2=new UpInfoFragment(2l).newInstance(2l,list.get(1));
        UpInfoFragment fragment3=new UpInfoFragment(3l).newInstance(3l,list.get(2));
        UpInfoFragment fragment4=new UpInfoFragment(4l).newInstance(4l,list.get(3));
        UpInfoFragment fragment5=new UpInfoFragment(5l).newInstance(5l,list.get(4));
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);
        viewPagerAdapter=new ViewPagerAdapter(this,fragmentList);
        binding.viewPager.setAdapter(viewPagerAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK){
            if(data!=null){
                Bundle bundle=data.getExtras();
                UpInfoBean upInfoBean = (UpInfoBean) bundle.getSerializable("upInfo");
                if(!upInfoBean.isFocus()){
                    list.remove(choosePosition);
                    adapter.notifyItemRemoved(choosePosition);
                    fragmentList.remove(choosePosition);
                    viewPagerAdapter.update(fragmentList);
                    viewPagerAdapter.notifyDataSetChanged();
                }
                adapter.refresh(list);
            }
        }
    }
}