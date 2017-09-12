package com.miky.dev.dribbbleapp.data.db.dao;



import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.miky.dev.dribbbleapp.data.db.entity.Shot;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShotDAO extends BaseDaoImpl<Shot, Integer> {
    public ShotDAO(ConnectionSource connectionSource, Class<Shot> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public void save(List<Shot> shots) {
        clearTable();
        for (Shot s : shots) {
            try {
                createOrUpdate(s);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public List<Shot> readAll() {
        List<Shot> shots = new ArrayList<>();
        try {
            shots = this.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shots;
    }

    private void clearTable() {
        try {
            TableUtils.clearTable(connectionSource, Shot.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
