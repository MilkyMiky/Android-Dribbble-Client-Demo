package com.miky.dev.dribbbleapp.logic.di.modules;



import com.google.gson.GsonBuilder;
import com.miky.dev.dribbbleapp.data.retrofit.DribbbleAPI;

import javax.inject.Singleton;

import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetModule {

    private static final String HEAD_PARAM_AUTH = "Authorization";
    private static final String HEAD_PARAM_AUTH_VALUE = "Bearer ";
    private static final String HEAD_PARAM_CONTENT_TYPE = "Content-Type";
    private static final String HEAD_PARAM_CONTENT_TYPE_VALUE = "application/json";
    private final static String URL = "";

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create(new GsonBuilder().create());
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();

//            requestBuilder.header(HEAD_PARAM_CONTENT_TYPE, HEAD_PARAM_CONTENT_TYPE_VALUE);
            requestBuilder.method(original.method(), original.body());

            HttpUrl.parse(URL).host();
            HttpUrl newUrl = original.url().newBuilder()
                    .scheme(HttpUrl.parse(URL).scheme())
                    .host(HttpUrl.parse(URL).host())
                    .port(HttpUrl.parse(URL).port())
                    .build();

            Request request = requestBuilder.url(newUrl).build();
            return chain.proceed(request);
        });

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        return httpClient.build();
    }


    @Singleton
    @Provides
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Singleton
    @Provides
    DribbbleAPI provideRetrofit(OkHttpClient client, RxJavaCallAdapterFactory adapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
        return retrofit.create(DribbbleAPI.class);
    }
}
