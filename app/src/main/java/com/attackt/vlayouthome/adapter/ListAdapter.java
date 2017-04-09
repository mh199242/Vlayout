package com.attackt.vlayouthome.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.attackt.vlayouthome.R;
import com.attackt.vlayouthome.bean.Shop;
import com.attackt.vlayouthome.view.EScrollView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/4/3.
 */

public class ListAdapter extends DelegateAdapter.Adapter<ListAdapter.MyViewHolder> {
    private List<Shop.ThemesBean> mShopList;
    private Context mContext;


    public ListAdapter(Context context, List<Shop.ThemesBean> shopList) {
        mShopList = shopList;
        mContext = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        LinearLayoutHelper helper = new LinearLayoutHelper();
        return helper;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.item_main_middle_layout, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(mContext).load(mShopList.get(position).getPicture() + "?imageView2/2/w/480/h/240").into(holder.bigImageView);
        holder.bigImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了第" + position + "条的大图", Toast.LENGTH_SHORT).show();
            }
        });

        List<Shop.ThemesBean.RelatedGoodsBean> datas = mShopList.get(position).getRelated_goods();
        List<Shop.ThemesBean.RelatedGoodsBean> datas1;
        if (datas.size() > 3) {
            datas1 = new ArrayList<>();
            for (int j = 0; j < 3; ++j) {
                datas1.add(datas.get(j));
            }
            Shop.ThemesBean.RelatedGoodsBean shopData = new Shop.ThemesBean.RelatedGoodsBean();
            shopData.setId("-1");
            datas1.add(datas1.size(), shopData);
        } else {
            datas1 = new ArrayList<>();
            datas1 = datas;
            Shop.ThemesBean.RelatedGoodsBean shopData = new Shop.ThemesBean.RelatedGoodsBean();
            shopData.setId("-1");
            datas1.add(datas1.size(), shopData);
        }
        MiddleDataAdapter middleDataAdapter = new MiddleDataAdapter(mContext, datas1);
        holder.eScrollView.setAdapter(middleDataAdapter);
        holder.eScrollView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(mContext, "点击了第" + position + "项的第" + i + "个小图", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mShopList == null ? 0 : mShopList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView bigImageView;
        private View line;
        private EScrollView eScrollView;

        public MyViewHolder(View itemView) {
            super(itemView);
            bigImageView = (ImageView) itemView.findViewById(R.id.main_middle_bigimage);
            line = itemView.findViewById(R.id.main_middle_line);
            eScrollView = (EScrollView) itemView.findViewById(R.id.main_middle_escrollview);
        }
    }

}
