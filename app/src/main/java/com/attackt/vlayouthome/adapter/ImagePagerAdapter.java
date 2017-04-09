/*
 * Copyright 2014 trinea.cn All right reserved. This software is the confidential and proprietary information of
 * trinea.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with trinea.cn.
 */
package com.attackt.vlayouthome.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.attackt.vlayouthome.bean.CarouselData;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.List;

/**
 * 轮播图适配器
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-23
 */
public class ImagePagerAdapter extends RecyclingPagerAdapter {

    private Context context;
    //index data
    private List<CarouselData.CarouselBean> mAdEntity;

    private int size;
    private boolean isInfiniteLoop;
    private DisplayImageOptions dis_ImgOptions;


    public ImagePagerAdapter(Context context, List<CarouselData.CarouselBean> valueEntity) {
        this.context = context;
        mAdEntity = valueEntity;
        this.size = mAdEntity.size();
        isInfiniteLoop = false;
        this.dis_ImgOptions = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(false)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new SimpleBitmapDisplayer())
                .handler(new Handler())
                .build();
    }

    @Override
    public int getCount() {
        // Infinite loop
        if (size == 0) {
            return 0;
        } else {
            return isInfiniteLoop ? Integer.MAX_VALUE : size;
        }
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    private int getPosition(int position) {

        if (size == 0)
            return 0;
        else
            return isInfiniteLoop ? position % size : position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            holder.imageView = new ImageView(context);
            view = holder.imageView;
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.imageView.setScaleType(ScaleType.FIT_XY);

//        Glide.with(context).load(mAdEntity.get(getPosition(position)).getPicture()).into(holder.imageView);
//        ImageLoader.getInstance().displayImage(mAdEntity.get(getPosition(position)).getImageUrl(),
//                holder.imageView, dis_ImgOptions);
        ImageLoader.getInstance().displayImage(mAdEntity.get(getPosition(position)).getPicture(),
                holder.imageView, dis_ImgOptions);
        return view;
    }

    private class ViewHolder {

        ImageView imageView;
    }

    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }

}
