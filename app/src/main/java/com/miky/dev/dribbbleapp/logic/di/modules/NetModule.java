package com.miky.dev.dribbbleapp.logic.di.modules;


import com.miky.dev.dribbbleapp.data.retrofit.DribbbleAPI;
import com.miky.dev.dribbbleapp.logic.di.ShotRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private final static String OAUTH_TOKEN = "Bearer 9cdc9a5a6ad0a7264933c735b9175f7f9e6c9392be25a708f4a5f327c0039432";
    private final static String HEAD_PARAM_AUTH = "Authorization";
    private final static String URL = "https://api.dribbble.com/v1/";

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
            requestBuilder.header(HEAD_PARAM_AUTH, OAUTH_TOKEN);
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

    @Singleton
    @Provides
    ShotRepository provideShotsRepository(DribbbleAPI dribbbleAPI) {
        return new ShotRepository(dribbbleAPI);
    }
}
