package com.attackt.vlayouthome;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * Created by rongchengtianxia on 2016/6/21.
 */
public class MyApplication extends Application {
    public final static String CACHE_IMAGE_DIR = "FengTui/ImgCache";
    private static MyApplication instance;


    public synchronized static MyApplication getInstance() {
        if (instance == null) {
            synchronized (MyApplication.class) {
                if (instance == null) {
                    instance = new MyApplication();
                }
            }
        }
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
        initImageLoader();
    }

    private void initImageLoader() {

        File cacheDir = StorageUtils.getOwnCacheDirectory(
                getApplicationContext(), CACHE_IMAGE_DIR);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).memoryCacheExtraOptions(480, 800)
                // 即保存的每个内存缓存文件的最大长宽
                .threadPoolSize(5)
                // 线程池内加载的数量
                .diskCacheFileCount(100)
                // 设置硬盘缓存的文件的最多个数
                .threadPriority(Thread.NORM_PRIORITY - 2)
                // 配置线程优先级
                .denyCacheImageMultipleSizesInMemory()//.内存缓存

                .diskCache(new UnlimitedDiskCache(cacheDir)).build();//硬盘缓存路径

        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);
    }

}
