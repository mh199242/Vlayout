package com.attackt.vlayouthome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.attackt.vlayouthome.adapter.BannerAdapter;
import com.attackt.vlayouthome.adapter.HotAdapter;
import com.attackt.vlayouthome.adapter.ListAdapter;
import com.attackt.vlayouthome.adapter.TextAdapter;
import com.attackt.vlayouthome.bean.CarouselData;
import com.attackt.vlayouthome.bean.Hot;
import com.attackt.vlayouthome.bean.Shop;
import com.attackt.vlayouthome.view.CustomeRecyclerView;
import com.attackt.vlayouthome.view.XScrollView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.MediaType;

public class MainActivity extends AppCompatActivity implements XScrollView.IXScrollViewListener {

    CustomeRecyclerView rv;
    @BindView(R.id.scrollView)
    XScrollView scrollView;

    DelegateAdapter delegateAdapter;
    List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
    List<CarouselData.CarouselBean> bannerList = new ArrayList<>();
    List<Shop.ThemesBean> shopList = new ArrayList<>();
    List<Hot.ItemsBean> hotList = new ArrayList<>();
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;

    private int page = 1;
    private int pageSize = 10;
    private String state = "";

    HotAdapter hotAdapter;
    TextAdapter textAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        getBanner();
    }


    private void init() {
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recycledViewPool.setMaxRecycledViews(0, 20);
        rv.setRecycledViewPool(recycledViewPool);
        delegateAdapter = new DelegateAdapter(layoutManager, true);
        rv.setAdapter(delegateAdapter);
        rv.setNestedScrollingEnabled(false);
        adapters.add(new BannerAdapter(MainActivity.this, bannerList));
        adapters.add(new ListAdapter(MainActivity.this, shopList));
        adapters.add(textAdapter);
        adapters.add(hotAdapter);
        delegateAdapter.setAdapters(adapters);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VirtualLayoutManager layoutManager = new VirtualLayoutManager(MainActivity.this);
                rv.setLayoutManager(layoutManager);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                recycledViewPool.setMaxRecycledViews(0, 20);
                rv.setRecycledViewPool(recycledViewPool);
                delegateAdapter = new DelegateAdapter(layoutManager, true);
                rv.setAdapter(delegateAdapter);
                rv.setNestedScrollingEnabled(false);
                adapters.clear();
                adapters.add(new ListAdapter(MainActivity.this, shopList));
                adapters.add(hotAdapter);
                delegateAdapter.setAdapters(adapters);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VirtualLayoutManager layoutManager = new VirtualLayoutManager(MainActivity.this);
                rv.setLayoutManager(layoutManager);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                recycledViewPool.setMaxRecycledViews(0, 20);
                rv.setRecycledViewPool(recycledViewPool);
                delegateAdapter = new DelegateAdapter(layoutManager, true);
                rv.setAdapter(delegateAdapter);
                rv.setNestedScrollingEnabled(false);
                adapters.clear();
                adapters.add(hotAdapter);
                delegateAdapter.setAdapters(adapters);
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VirtualLayoutManager layoutManager = new VirtualLayoutManager(MainActivity.this);
                rv.setLayoutManager(layoutManager);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                recycledViewPool.setMaxRecycledViews(0, 20);
                rv.setRecycledViewPool(recycledViewPool);
                delegateAdapter = new DelegateAdapter(layoutManager, true);
                rv.setAdapter(delegateAdapter);
                rv.setNestedScrollingEnabled(false);
                adapters.clear();
                adapters.add(new BannerAdapter(MainActivity.this, bannerList));
                adapters.add(new ListAdapter(MainActivity.this, shopList));
                adapters.add(textAdapter);
                adapters.add(hotAdapter);
                delegateAdapter.setAdapters(adapters);
            }
        });
    }

    private void initView() {
        scrollView.setPullRefreshEnable(true);
        scrollView.setPullLoadEnable(true);
        scrollView.setAutoLoadEnable(false);
        scrollView.setIXScrollViewListener(this);
        scrollView.setRefreshTime(getTime());

        View content = LayoutInflater.from(this).inflate(R.layout.content, null);
        if (null != content) {
            rv = (CustomeRecyclerView) content.findViewById(R.id.rv);
        }
        scrollView.setView(content);
    }

    private void onEnd() {
        scrollView.stopRefresh();
        scrollView.stopLoadMore();
        scrollView.setRefreshTime(getTime());
    }

    private String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    private void getBanner() {
        String bannerUrl = "https://www.dopstore.cn/api/v1/home/carousel";
        Map<String, String> map = new HashMap<>();
        map.put("project_type", "1");
        OkHttpUtils.postString()
                .url(bannerUrl)
                .mediaType(MediaType.parse("application/json"))
                .content(new Gson().toJson(map))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("轮播图", "onResponse: " + response);
                        CarouselData data = new Gson().fromJson(response, CarouselData.class);
                        bannerList = data.getCarousel();
                        getList();
                    }
                });
    }

    private void getList() {
        String listUrl = "https://www.dopstore.cn/api/v1/home/theme";
        Map<String, String> map = new HashMap<>();
        map.put("category_id", "1");
        OkHttpUtils.postString()
                .url(listUrl)
                .mediaType(MediaType.parse("application/json"))
                .content(new Gson().toJson(map))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("商品列表", "onResponse: " + response);
                        Shop data = new Gson().fromJson(response, Shop.class);
                        shopList = data.getThemes();
                        state = "request";
                        getHot();
                    }
                });
    }

    public void getHot() {
        final String hotUrl = "https://www.dopstore.cn/api/v1/goods";
        Map<String, String> map = new HashMap<>();
        map.put("page", String.valueOf(page));
        map.put("pageSize", String.valueOf(pageSize));
        map.put("is_recommended", "1");
        OkHttpUtils.postString()
                .url(hotUrl)
                .mediaType(MediaType.parse("application/json"))
                .content(new Gson().toJson(map))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("热卖列表", "onResponse: " + response);
                        List<Hot.ItemsBean> mHotList = new ArrayList<>();
                        hotAdapter = new HotAdapter(MainActivity.this, hotList);
                        textAdapter = new TextAdapter();
                        Hot data = new Gson().fromJson(response, Hot.class);
                        mHotList = data.getItems();
                        page += 1;
                        scrollView.setVisibility(View.VISIBLE);
                        if (mHotList.size() > 0) {
                            scrollView.setPullLoadEnable(true);
                        } else {
                            scrollView.setPullLoadEnable(false);
                        }

                        if (state.equals("request")) {
                            hotList.clear();
                            hotList.addAll(mHotList);
                            hotAdapter.notifyDataSetChanged();
                            init();
                        } else if (state.equals("refresh")) {
                            onEnd();
                            hotList.clear();
                            hotList.addAll(mHotList);
                            hotAdapter.notifyDataSetChanged();
                            delegateAdapter.setAdapters(adapters);
                        } else if (state.equals("load")) {
                            onEnd();
                            hotList.addAll(mHotList);
                            hotAdapter.notifyDataSetChanged();
                            delegateAdapter.setAdapters(adapters);
                        }

                    }
                });
    }

    @Override
    public void onRefresh() {
        state = "refresh";
        page = 1;
        getHot();
    }

    @Override
    public void onLoadMore() {
        state = "load";
        getHot();
    }


}
