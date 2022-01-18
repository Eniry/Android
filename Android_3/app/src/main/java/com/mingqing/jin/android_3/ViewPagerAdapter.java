package com.mingqing.jin.android_3;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {


    private List<UpInfoFragment> list;
    private List<Long> fragmentIds = new ArrayList<>();//用于存储更新fragment的特定标识
    private HashSet<Long> creatIds = new HashSet<>();//得用hashset防重，用于存储adapter内的顺序

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity,List<UpInfoFragment> list) {
        super(fragmentActivity);
        this.list=list;
        update(list);
    }


    public void update(List<UpInfoFragment> fragmentLists) {
        fragmentIds.clear();
        for (int i = 0; i< fragmentLists.size(); i++){
            fragmentIds.add(((UpInfoFragment)list.get(i)).getTypeId());
        }
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Long ids = fragmentIds.get(position);
        creatIds.add(ids);//创建的时候将未添加的fragment添加进来，每次刷新都会调用这里，其次调用containsItem
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    /**
     * 这两个方法必须重写，作为数据改变刷新检测的工具
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return fragmentIds.get(position);
    }

    @Override
    public boolean containsItem(long itemId) {
        return creatIds.contains(itemId);
    }

}

