package com.miky.dev.dribbbleapp.ui.shots;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miky.dev.dribbbleapp.R;
import com.miky.dev.dribbbleapp.data.db.entity.Shot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class ShotAdapter extends RecyclerView.Adapter<ShotAdapter.ViewHolder> {

    private List<Shot> shotList = new ArrayList<>();

    void setShotList(List<Shot> shotList) {
        this.shotList = shotList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shot, parent, false);
        return new ShotAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Shot shot = shotList.get(position);
        holder.title.setText(shot.getTitle());
    }

    @Override
    public int getItemCount() {
        return shotList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
