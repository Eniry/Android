package com.mingqing.jin.android_3;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UpInfoAdapter extends RecyclerView.Adapter<UpInfoAdapter.ViewHolder> {

    private Context context;
    private List<UpInfoBean> list;

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public UpInfoAdapter(Context context, List<UpInfoBean> list) {
        this.context = context;
        this.list = list;
    }

    public void refresh(List<UpInfoBean> list){
        this.list=list;
        notifyItemRangeChanged(0,list.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.adapter_upinfo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(list.get(position).isFocus()){
            holder.upImage.setImageResource(list.get(position).getUpImage());
            holder.upName.setText(list.get(position).getUpName());
        }
        holder.itemView.setOnClickListener(view -> {
            if(onItemClickListener!=null){
                onItemClickListener.onItemClick(view,holder.getLayoutPosition());
            }
        });

        holder.itemView.setOnLongClickListener(view -> {
            if(onItemLongClickListener!=null){
                onItemLongClickListener.onItemLongClick(view,holder.getLayoutPosition());
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView upImage;
        private TextView upName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            upImage=itemView.findViewById(R.id.upImage);
            upName=itemView.findViewById(R.id.upName);
        }
    }

    interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

}
