package com.miky.dev.dribbbleapp.data.retrofit;


import com.google.gson.annotations.SerializedName;
import com.miky.dev.dribbbleapp.data.db.Shot;

import java.util.List;

public class ShotsResponse {

    @SerializedName("data")
    private List<Shot> shotList;

    public List<Shot> getShotList() {
        return shotList;
    }
}
