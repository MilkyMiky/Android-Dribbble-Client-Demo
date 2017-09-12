package com.miky.dev.dribbbleapp.ui.shots;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miky.dev.dribbbleapp.R;
import com.miky.dev.dribbbleapp.data.db.entity.Shot;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShotsFragment extends Fragment implements IShotsView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private View view;
    private ShotsPresenter presenter;
    private ShotAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ShotsPresenter();
        adapter = new ShotAdapter();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shots, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(R.string.app_name);

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.getData(true));

        adapter.setContext(getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        presenter.setView(this);
        presenter.getData(false);
    }

    @Override
    public void showData(List<Shot> shotList) {
        adapter.setShotList(shotList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress(boolean show) {
        if (swipeRefreshLayout != null) swipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}
