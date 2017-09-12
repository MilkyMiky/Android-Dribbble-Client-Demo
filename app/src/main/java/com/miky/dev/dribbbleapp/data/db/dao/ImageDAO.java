package com.miky.dev.dribbbleapp.data.db.dao;


import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.miky.dev.dribbbleapp.data.db.entity.Image;
import com.miky.dev.dribbbleapp.data.db.entity.Shot;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ImageDAO extends BaseDaoImpl<Image, Integer> {
    private final static String SHOT_ID = "shot_id";

    public ImageDAO(ConnectionSource connectionSource, Class<Image> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public void save(List<Shot> shots) {
        clearTable();
        for (Shot s : shots) {
            try {
                Image image = s.getImages();
                image.setId(UUID.randomUUID().toString());
                image.setShotId(s.getId());
                createOrUpdate(image);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Image read(long shotId) {
        List<Image> images = new ArrayList<>();
        try {
            images = this.queryForEq(SHOT_ID, shotId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images.get(0);
    }

    private void clearTable() {
        try {
            TableUtils.clearTable(connectionSource, Image.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
