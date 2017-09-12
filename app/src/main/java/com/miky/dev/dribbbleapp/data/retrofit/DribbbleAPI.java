package com.miky.dev.dribbbleapp.data.retrofit;


import com.miky.dev.dribbbleapp.data.db.entity.Shot;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface DribbbleAPI {

    @GET("shots")
    Observable<List<Shot>> getShots(@Query("per_page") int num);


}
