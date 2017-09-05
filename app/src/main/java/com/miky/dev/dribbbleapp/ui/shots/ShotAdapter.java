package com.miky.dev.dribbbleapp.ui.shots;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miky.dev.dribbbleapp.R;
import com.miky.dev.dribbbleapp.data.db.entity.Shot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class ShotAdapter extends RecyclerView.Adapter<ShotAdapter.ViewHolder> {

    private List<Shot> shotList = new ArrayList<>();
    private Context context;

    void setContext(Context context) {
        this.context = context;
    }

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
        if (shot.getImages().getHidpi().isEmpty() || shot.getImages().getHidpi() == null) {
            Picasso.with(context).load(shot.getImages().getTeaser()).into(holder.image);
        } else {
            Picasso.with(context).load(shot.getImages().getHidpi()).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return shotList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.image)
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
