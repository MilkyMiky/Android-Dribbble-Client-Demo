package com.miky.dev.dribbbleapp.logic.di;


import com.miky.dev.dribbbleapp.data.retrofit.DribbbleAPI;
import com.miky.dev.dribbbleapp.data.retrofit.ShotsResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShotRepository {

    private DribbbleAPI dribbbleAPI;

    public ShotRepository(DribbbleAPI dribbbleAPI) {
        this.dribbbleAPI = dribbbleAPI;
    }

    public Observable<ShotsResponse> getShots(boolean update) {
//        return Observable.just(update).map(aBoolean ->  {
//
//            if (aBoolean) {
//                return dribbbleAPI.getShots()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
//            } else  {
//                return null;
//            }
//        });

        return dribbbleAPI.getShots().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
