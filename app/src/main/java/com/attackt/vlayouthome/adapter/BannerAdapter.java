package com.attackt.vlayouthome.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.attackt.vlayouthome.R;
import com.attackt.vlayouthome.bean.CarouselData;
import com.attackt.vlayouthome.view.AutoScrollViewPager;
import com.attackt.vlayouthome.view.ViewSetUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/4/3.
 */

public class BannerAdapter extends DelegateAdapter.Adapter<BannerAdapter.MyViewHolder> {
    private List<CarouselData.CarouselBean> mBannerList;
    private Context mContext;
//    private LinearLayout ll;

    /**
     * list of banner pointer 轮播指示器列表
     */
    private List<ImageView> list_bannerPointer = new ArrayList<>();

    /**
     * 轮播图个数
     */
    int bannerSize;

    /**
     * position of banner pointer 轮播指示器下标
     */
    private int i_bannerPointerIndex;

    public BannerAdapter(Context context, List<CarouselData.CarouselBean> bannerList) {
        mBannerList = bannerList;
        mContext = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        LinearLayoutHelper helper = new LinearLayoutHelper();
        return helper;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.banner, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ImagePagerAdapter adp_ip = new ImagePagerAdapter(mContext, mBannerList).setInfiniteLoop(true);
        bannerSize = mBannerList.size();
        holder.av.setAdapter(adp_ip);
        holder.av.addOnPageChangeListener(new MyOnPageChangeListener());
        holder.av.setInterval(5000);
        holder.av.startAutoScroll();
        holder.av.setCurrentItem(100 - 100 % bannerSize);
//        setBannerIndex(ll, 0);
    }


    @Override
    public int getItemCount() {
        return 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        AutoScrollViewPager av;


        public MyViewHolder(View itemView) {
            super(itemView);
            av = (AutoScrollViewPager) itemView.findViewById(R.id.label_banner);
//            ll = (LinearLayout) itemView.findViewById(R.id.label_point);
        }
    }

    /**
     * banner's listener 轮播图监听
     *
     * @author Administrator
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            i_bannerPointerIndex = position;
//            setBannerIndex(ll, (i_bannerPointerIndex) % bannerSize);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    /**
     * set banner index 设置banner下标
     *
     * @param viewgroup view group of banner pointer 轮播指示器容器
     * @param position  current position 当前位置
     * @return
     */
    private List<ImageView> setBannerIndex(LinearLayout viewgroup, int position) {
        if (bannerSize == 0)
            return null;

        viewgroup.removeAllViews();

        for (int i = 0; i < bannerSize; i++) {
            ImageView iv_temp = new ImageView(mContext);
            iv_temp.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            iv_temp.setPadding(5, 0, 5, 0);
            iv_temp.setMinimumHeight((int) ViewSetUtils.px2dp(mContext, 15));
            iv_temp.setMaxHeight((int) ViewSetUtils.px2dp(mContext, 15));
            iv_temp.setMinimumWidth((int) ViewSetUtils.px2dp(mContext, 15));
            iv_temp.setMaxWidth((int) ViewSetUtils.px2dp(mContext, 15));

            if (i == position) {
                iv_temp.setImageResource(R.mipmap.zx_carousel);
            } else {
                iv_temp.setImageResource(R.mipmap.zx_carousel_t);
            }

            list_bannerPointer.add(iv_temp);

            viewgroup.addView(iv_temp);
        }

        return list_bannerPointer;
    }

}
