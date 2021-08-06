package com.example.mvpdemopractice.utils;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 网络框架工具类
 * @author zhangkaiwen
 */
public class NetUtils {

    // static变量全局唯一
    private static Retrofit retrofit;
    private static OkHttpClient mOkHttpClient;
    private static String mBaseUrl = "https://www.wanandroid.com";


    public static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (NetUtils.class) {
                try {
                    // TODO: 优化Logging
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Log.d("OkHttp", message));
                    mOkHttpClient = new OkHttpClient.Builder()
                            .addNetworkInterceptor(logging)
                            .retryOnConnectionFailure(true)
                            .build();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return mOkHttpClient;
    }
    public static Retrofit getRetrofit() {
        // 饿汉线程安全单例模式
        if (retrofit == null) {
            // 类锁
            synchronized (NetUtils.class) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(mBaseUrl)
                        //增加返回值为String的支持
                        // https://stackoverflow.com/questions/42349022/how-to-get-string-in-response-using-retrofit-2
                        .addConverterFactory(ScalarsConverterFactory.create())
                        //增加返回值为Gson的支持(以实体类返回)
                        .addConverterFactory(GsonConverterFactory.create())
                        //增加返回值为Observable<T>的支持
                        // https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(getOkHttpClient())
                        .build();
            }

        }

        return retrofit;
    }

    public static Retrofit getRetrofit(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
        return retrofit;
    }
}
