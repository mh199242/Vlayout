package com.attackt.vlayouthome.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Field;

/**
 * 控件设置工具类
 *
 * @author Administrator
 */
public class ViewSetUtils {
    public static int i_popHeightLeft, i_popHeightRight;

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 首页 分类宽高
     * <p/>
     * 以最小图片为1倍
     *
     * @param context
     * @param view
     * @param w       宽比
     * @param h       高比
     */
    public static void setHomeView(Context context, View view, int w, int h) {

        int spacePer = dip2px(context, 2);// 单倍间距
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = ((Activity) context).getWindowManager();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels - spacePer * 3;

        int widthPer = width / 4;// 单倍宽

        LayoutParams params = (LayoutParams) view.getLayoutParams();
        params.width = (int) (widthPer * w + spacePer * (w - 1));
        params.height = (int) (widthPer * h + spacePer * (h - 1));
        view.setLayoutParams(params);

    }

    /**
     * 根据屏幕宽度与宽高比设置控件高度
     *
     * @param context
     * @param view    控件
     * @param w       宽比
     * @param h       高比
     */
    public static void setViewHeigh(Context context, View view, float w, float h) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = ((Activity) context).getWindowManager();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        LayoutParams params = view.getLayoutParams();
        params.height = (int) (width * h / w);
        view.setLayoutParams(params);

    }

    /**
     * 根据屏幕宽度设置控件宽度，并根据宽高比设置控件高度
     *
     * @param context
     * @param view
     * @param w       屏幕宽度的百分百
     * @param wi      宽比
     * @param hi      高比
     */
    public static void setViewWhithAndHeigh(Context context, View view, int w,
                                            int wi, int hi) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = ((Activity) context).getWindowManager();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        LayoutParams params = (LayoutParams) view.getLayoutParams();
        params.width = (int) (width * w / 100);
        params.height = (int) ((width * w / 100) * hi / wi);
        view.setLayoutParams(params);

    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScrenWhitch(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = ((Activity) context).getWindowManager();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        return width;

    }

    /**
     * 隐藏软键盘
     *
     * @param context
     */
    public static void hideKey(Context context) {
        InputMethodManager imm = ((InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE));
        if (imm != null)
            imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 计算listview高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;

        View listItem = listAdapter.getView(0, null, listView);
        listItem.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        listItem.measure(0, 0);
        LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) +
                listItem.getMeasuredHeight() * (listAdapter.getCount());
        listView.setLayoutParams(params);
    }

    /**
     * 计算recyclerview高度
     *
     * @param recyclerView
     */
    public static void setRecyclverViewHeightBasedOnChildren(RecyclerView recyclerView) {
        // 获取RecyclerView对应的Adapter
        RecyclerView.Adapter recyclerViewAdapter = recyclerView.getAdapter();
        if (recyclerViewAdapter == null) {
            return;
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.height = recyclerView.getMeasuredHeight();
        recyclerView.setLayoutParams(params);
    }




    /**
     * 计算listview高度并把高度设置给别的控件
     *
     * @param listView
     */
    public static void CountListAndSet(Context context, ListView listView, View view) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        View listItem = listAdapter.getView(0, null, listView);
        listItem.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        listItem.measure(0, 0);
        LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) +
                listItem.getMeasuredHeight() * (listAdapter.getCount()) + dp2px(context, 10);
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(image.getLayoutParams());
//        params.setMargins(0, dp2px(context, 20), 0, 0);
        view.setLayoutParams(params);
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
    /**
     * 计算GridView高度
     *
     * @param gridView
     */
    public static int setGridViewHeightBasedOnChildren(GridView gridView) {
        // 获取GridView对应的Adapter
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return 0;
        }
        int rows;
        int columns = 0;
        int horizontalBorderHeight = 0;
        Class<?> clazz = gridView.getClass();
        try {
            // 利用反射，取得每行显示的个数
            Field column = clazz.getDeclaredField("mRequestedNumColumns");
            column.setAccessible(true);
            columns = (Integer) column.get(gridView);
            // 利用反射，取得横向分割线高度
            Field horizontalSpacing = clazz
                    .getDeclaredField("mRequestedHorizontalSpacing");
            horizontalSpacing.setAccessible(true);
            horizontalBorderHeight = (Integer) horizontalSpacing.get(gridView);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // 判断数据总数除以每行个数是否整除。不能整除代表有多余，需要加一行
        columns = columns == 0 ? 1 : columns;
        if (listAdapter.getCount() % columns > 0) {
            rows = listAdapter.getCount() / columns + 1;
        } else {
            rows = listAdapter.getCount() / columns;
        }
        int totalHeight = 0;
//		for (int i = 0; i < rows; i++) { // 只计算每项高度*行数
//			View listItem = listAdapter.getView(i, null, gridView);
//			listItem.measure(0, 0); // 计算子项View 的宽高
//			totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
//		}

        for (int i = 0; i < rows; i++) {
            int rowHight = 0;
            for (int j = 0; j < columns; j++) {

                int p = columns * i + j;
                if (p < listAdapter.getCount()) {

                    View listItem = listAdapter.getView(p, null, gridView);
                    listItem.measure(0, 0); // 计算子项View 的宽高
                    int itemHight = listItem.getMeasuredHeight(); // 子项的总高度
                    rowHight = rowHight > itemHight ? rowHight : itemHight;

                }

            }
            totalHeight += rowHight;
        }

        int h = totalHeight + horizontalBorderHeight * (rows - 1);// 最后加上分割线总高度
        LayoutParams params = gridView.getLayoutParams();
        params.height = h;
        gridView.setLayoutParams(params);

        return h;
    }

    public static void getGridViewHeightBasedOnChildren() {

    }

}
