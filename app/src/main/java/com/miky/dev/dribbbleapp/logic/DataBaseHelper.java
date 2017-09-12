package com.miky.dev.dribbbleapp.logic;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.miky.dev.dribbbleapp.data.db.dao.ImageDAO;
import com.miky.dev.dribbbleapp.data.db.dao.ShotDAO;
import com.miky.dev.dribbbleapp.data.db.entity.Image;
import com.miky.dev.dribbbleapp.data.db.entity.Shot;

import java.sql.SQLException;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "dribbbleapp.db";
    private static final int DATABASE_VERSION = 5;

    private ShotDAO shotDAO = null;
    private ImageDAO imageDAO = null;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.d("log", "DB onCreate");
        try {
            TableUtils.createTable(connectionSource, Image.class);
            TableUtils.createTable(connectionSource, Shot.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.d("log", "DB onUpgrade");
        try {
            TableUtils.dropTable(connectionSource, Image.class, true);
            TableUtils.dropTable(connectionSource, Shot.class, true);
            onCreate(database, connectionSource);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


    public ShotDAO getShotDAO() {
        if (shotDAO == null) {
            try {
                shotDAO = new ShotDAO(getConnectionSource(), Shot.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return shotDAO;
    }

    public ImageDAO getImageDAO() {
        if (imageDAO == null) {
            try {
                imageDAO = new ImageDAO(getConnectionSource(), Image.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return imageDAO;
    }

    @Override
    public void close() {
        super.close();
        imageDAO = null;
        shotDAO = null;
    }
}
