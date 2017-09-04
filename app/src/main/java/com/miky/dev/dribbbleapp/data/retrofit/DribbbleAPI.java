package com.miky.dev.dribbbleapp.data.retrofit;


import retrofit2.http.GET;
import rx.Observable;

public interface DribbbleAPI {

    @GET("shots")
    Observable<ShotsResponse> getShots();

}
