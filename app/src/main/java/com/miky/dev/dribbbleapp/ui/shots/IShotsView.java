package com.miky.dev.dribbbleapp.ui.shots;


import com.miky.dev.dribbbleapp.data.db.entity.Shot;

import java.util.List;

interface IShotsView {
    void showData(List<Shot> shotList);

    void showProgress(boolean show);

    void showMessage(String message);
}
