package com.miky.dev.dribbbleapp.data.db.entity;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "shot")
public class Shot {
    private static class Column {
        private final static String ID = "id";
        private final static String TITLE = "title";
        private final static String DESCRIPTION = "description";
        private final static String WIDTH = "width";
        private final static String HEIGHT = "height";
    }

    @DatabaseField(id = true, dataType = DataType.LONG, columnName = Column.ID)
    private long id;
    @DatabaseField(dataType = DataType.STRING, columnName = Column.TITLE)
    private String title;
    @DatabaseField(dataType = DataType.STRING, columnName = Column.DESCRIPTION)
    private String description;
    @DatabaseField(dataType = DataType.INTEGER, columnName = Column.WIDTH)
    private int width;
    @DatabaseField(dataType = DataType.INTEGER, columnName = Column.HEIGHT)
    private int height;

    private Image images;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Image getImages() {
        return images;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImages(Image images) {
        this.images = images;
    }
}
