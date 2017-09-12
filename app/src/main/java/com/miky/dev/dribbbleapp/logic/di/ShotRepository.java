package com.miky.dev.dribbbleapp.logic.di;



import android.content.Context;
import android.util.Log;

import com.miky.dev.dribbbleapp.data.db.DataBaseHelper;
import com.miky.dev.dribbbleapp.data.db.entity.Shot;
import com.miky.dev.dribbbleapp.data.retrofit.DribbbleAPI;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShotRepository {

    private DribbbleAPI dribbbleAPI;
    private DataBaseHelper dataBaseHelper;

    public ShotRepository(Context context, DribbbleAPI dribbbleAPI, DataBaseHelper dataBaseHelper) {
        this.dribbbleAPI = dribbbleAPI;
        this.dataBaseHelper = dataBaseHelper;
    }

    public Observable<List<Shot>> getShots(boolean update) {
        return Observable.just(update)
                .flatMap(aBoolean -> {
                    if (aBoolean) {
                        return loadFromSrv();
                    }

                    List<Shot> shots = dataBaseHelper.getShotDAO().readAll();
                    if(shots == null) {
                        return loadFromSrv();
                    }

                    for(Shot s: shots) {
                        s.setImages(dataBaseHelper.getImageDAO().read(s.getId()));
                    }

                    return Observable.just(shots);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<Shot>> loadFromSrv() {
        Log.i("log", "loading from srv...");
        return dribbbleAPI.getShots()
                .map(shots -> {
                    dataBaseHelper.getShotDAO().save(shots);
                    dataBaseHelper.getImageDAO().save(shots);

                    return shots;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
