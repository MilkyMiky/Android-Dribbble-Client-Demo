package com.miky.dev.dribbbleapp.logic.di;



import com.miky.dev.dribbbleapp.data.db.entity.Shot;
import com.miky.dev.dribbbleapp.data.retrofit.DribbbleAPI;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShotRepository {

    private DribbbleAPI dribbbleAPI;

    public ShotRepository(DribbbleAPI dribbbleAPI) {
        this.dribbbleAPI = dribbbleAPI;
    }

    public Observable<List<Shot>> getShots(boolean update) {
        return Observable.just(update)
                .flatMap(aBoolean -> {
                    if (aBoolean) {
                        return loadFromSrv();
                    }
//                    List<AnalysisType> analysisTypes = dataBaseHelper.getAnalysisTypeDAO().read(patientId);
//                    if (analysisTypes == null) {
//                        return loadFromSrv();
//                    }
                    return Observable.just(null);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<Shot>> loadFromSrv() {
        return dribbbleAPI.getShots()
                .map(shots -> {
//                    dataBaseHelper.getAnalysisTypeDAO().save(response.getTypeList());
//                    return response.getTypeList();
                    return shots;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
