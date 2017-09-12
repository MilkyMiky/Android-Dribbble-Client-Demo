package com.miky.dev.dribbbleapp.data.db.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "shot_image")
public class Image {
    private static class Column {
        private final static String ID = "id";
        private final static String SHOT_ID = "shot_id";
        private final static String HIDPI = "hidpi";
        private final static String NORMAL = "normal";
        private final static String TEASER = "teaser";
    }

    @DatabaseField(id = true, dataType = DataType.STRING, columnName = Column.ID)
    private String id;

    @DatabaseField(dataType = DataType.LONG, columnName = Column.SHOT_ID)
    private long shotId;

    @DatabaseField(dataType = DataType.STRING, columnName = Column.HIDPI)
    private String hidpi;

    @DatabaseField(dataType = DataType.STRING, columnName = Column.NORMAL)
    private String normal;

    @DatabaseField(dataType = DataType.STRING, columnName = Column.TEASER)
    private String teaser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setShotId(long shotId) {
        this.shotId = shotId;
    }

    public String getHidpi() {
        return hidpi;
    }

    public String getTeaser() {
        return teaser;
    }
}