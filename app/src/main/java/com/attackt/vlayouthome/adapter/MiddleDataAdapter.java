package com.attackt.vlayouthome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.attackt.vlayouthome.R;
import com.attackt.vlayouthome.bean.Shop;
import com.bumptech.glide.Glide;

import java.util.List;


public class MiddleDataAdapter extends BaseAdapter {
    private Context context;
    private List<Shop.ThemesBean.RelatedGoodsBean> list;

    public MiddleDataAdapter(Context context, List<Shop.ThemesBean.RelatedGoodsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_main_middle_data_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.bigImageView = (ImageView) view.findViewById(R.id.main_middle_data_image);
            viewHolder.oneLayout = (LinearLayout) view.findViewById(R.id.main_middle_data_last_one);
            viewHolder.allTxt = (TextView) view.findViewById(R.id.main_middle_data_last_two);
            viewHolder.titleTv = (TextView) view.findViewById(R.id.main_middle_data_title);
            viewHolder.priceTv = (TextView) view.findViewById(R.id.main_middle_data_price);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (list.size() > 0) {
            String idStr = list.get(i).getId();
            if ("-1".equals(idStr)) {
                viewHolder.oneLayout.setVisibility(View.GONE);
                viewHolder.allTxt.setVisibility(View.VISIBLE);
            } else {
                viewHolder.oneLayout.setVisibility(View.VISIBLE);
                viewHolder.allTxt.setVisibility(View.GONE);
                Glide.with(context).load(list.get(i).getCover() + "?imageView2/2/w/200/h/200").into(viewHolder.bigImageView);
                viewHolder.titleTv.setText(list.get(i).getName());
                viewHolder.priceTv.setText("￥ " + list.get(i).getPrice());
            }
        }
        return view;
    }


    static class ViewHolder {
        private LinearLayout oneLayout;
        private TextView allTxt;
        private ImageView bigImageView;
        private TextView titleTv, priceTv;
    }
}
