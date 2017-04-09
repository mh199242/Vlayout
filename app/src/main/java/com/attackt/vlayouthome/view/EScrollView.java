package com.attackt.vlayouthome.view;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;


/**
 * 横向滑动的多item布局
 */
public class EScrollView extends HorizontalScrollView implements
        View.OnClickListener, View.OnLongClickListener {

    public static int WIDTH = 0;
    public static int HEIGHT = 0;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    private AdapterView.OnItemLongClickListener mOnItemLongClickListener;
    private ListAdapter mListAdapter;
    private LinearLayout mContainer;
    private MDataSetObserver mDataSetObserver;
    private int leftMargin = 0;
    private int rightMargin = 0;

    public EScrollView(Context context) {
        super(context);
        init(context);
    }

    public EScrollView(Context context, int lsft, int right) {
        super(context);
        init(context);
    }

    public EScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        // 设备屏幕分辨率
        getResolution(context);
        int result_resolution = WIDTH * HEIGHT;// 分辨率

        switch (result_resolution) {
            case 480 * 800: {
                leftMargin = 5;
                rightMargin = 6;
            }
            break;
            case 480 * 854: {
                leftMargin = 5;
                rightMargin = 6;
            }
            break;
            case 540 * 960: {
                leftMargin = 5;
                rightMargin = 8;
            }
            break;
            case 1280 * 800: {
                leftMargin = 16;
                rightMargin = 16;
            }
            break;
            case 1920 * 1080: {
                leftMargin = 16;
                rightMargin = 12;
            }
            break;
            default:
                leftMargin = 10;
                rightMargin = 9;
                break;
        }

        mDataSetObserver = new MDataSetObserver();
        setHorizontalScrollBarEnabled(false);
        mContainer = new LinearLayout(context);
        LayoutParams l = new LayoutParams(-1, -2);
        l.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        mContainer.setLayoutParams(l);
        mContainer.setOrientation(LinearLayout.HORIZONTAL);
        addView(mContainer);
    }

    public void setAdapter(ListAdapter adapter) {
        if (null != mListAdapter) {
            mListAdapter.unregisterDataSetObserver(mDataSetObserver);
        }
        mListAdapter = adapter;
        mListAdapter.registerDataSetObserver(mDataSetObserver);
        notifyDataChange();
    }

    public ListAdapter getAdapter() {

        return mListAdapter;
    }

    public void setSelection(int position) {
        ;
    }

    public void deleteItem(int position) {
        int count = getChildCount();
        int id = -1;
        for (int i = 0; i < count; ++i) {
            View v = getChildAt(i);
            id = v.getId();
            if (id == position) {
                mContainer.removeView(v);
            }
        }
    }

    public void removeAllItem() {
        removeAllViews();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener lis) {
        mOnItemClickListener = lis;
    }

    public void setOnItemLongClickListener(
            AdapterView.OnItemLongClickListener longLis) {
        mOnItemLongClickListener = longLis;
    }

    @Override
    public void onClick(View v) {
        if (null != mOnItemClickListener) {
            mOnItemClickListener.onItemClick(null, v, v.getId(), v.getId());
        }
    }

    private void notifyDataChange() {
        mContainer.removeAllViews();
        int len = mListAdapter.getCount();
        for (int i = 0; i < len; ++i) {
            View v = mListAdapter.getView(i, null, null);
            LinearLayout.LayoutParams pl = (LinearLayout.LayoutParams) v
                    .getLayoutParams();
            if (null == pl) {
                pl = new LinearLayout.LayoutParams(-2, -2);
            }
            if (0 != i) {
                pl.leftMargin = leftMargin;
            }
            pl.rightMargin = rightMargin;
            v.setLayoutParams(pl);
            v.setId(i);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
            mContainer.addView(v);
        }
    }

    class MDataSetObserver extends DataSetObserver {

        @Override
        public void onChanged() {
            notifyDataChange();
        }

        @Override
        public void onInvalidated() {
            notifyDataChange();
        }

    }

    @Override
    public boolean onLongClick(View v) {
        if (null != mOnItemLongClickListener) {
            mOnItemLongClickListener.onItemLongClick(null, v, v.getId(),
                    v.getId());
        }
        return true;
    }


    public static void getResolution(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        WIDTH = metrics.widthPixels;
        HEIGHT = metrics.heightPixels;

    }
}
