package com.attackt.vlayouthome.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.attackt.vlayouthome.R;
import com.attackt.vlayouthome.bean.Hot;
import com.attackt.vlayouthome.view.Utils;
import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Created by Administrator on 2017/4/3.
 */

public class HotAdapter extends DelegateAdapter.Adapter<HotAdapter.MyViewHolder> {
    private List<Hot.ItemsBean> mHotList;
    private Context mContext;


    public HotAdapter(Context context, List<Hot.ItemsBean> hotList) {
        mHotList = hotList;
        mContext = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        GridLayoutHelper helper = new GridLayoutHelper(2, mHotList.size());
        return helper;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_main_bottom_data_layout, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay()
                .getMetrics(dm);
        // 设置图片宽高
        int screenWidth = ((Activity) mContext).getWindowManager()
                .getDefaultDisplay().getWidth();
        int marginPx = Utils.dip2px(10, dm.density);
        int picSize = (screenWidth - marginPx * 3) / 2;
        RelativeLayout.LayoutParams imagePa = new RelativeLayout.LayoutParams(
                picSize, picSize);
        LinearLayout.LayoutParams linePa = new LinearLayout.LayoutParams(
                picSize, 2);
        holder.bigImageView.setLayoutParams(imagePa);
        holder.line.setLayoutParams(linePa);

        Glide.with(mContext).load(mHotList.get(position).getCover() + "?imageView2/2/w/200/h/200").into(holder.bigImageView);
//        imageLoader.displayImage(list.get(i).getCover()+"?imageView2/2/w/200/h/200", viewHolder.bigImageView);
        holder.titleTv.setText(mHotList.get(position).getName());
        holder.priceTv.setText("￥" + mHotList.get(position).getPrice());
        holder.bigImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了第" + position + "个商品", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mHotList == null ? 0 : mHotList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private View line;
        private ImageView bigImageView;
        private TextView titleTv, priceTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            bigImageView = (ImageView) itemView.findViewById(R.id.main_bottom_data_image);
            line = itemView.findViewById(R.id.main_bottom_data_line);
            titleTv = (TextView) itemView.findViewById(R.id.main_bottom_data_title);
            priceTv = (TextView) itemView.findViewById(R.id.main_bottom_data_price);
        }
    }

}
