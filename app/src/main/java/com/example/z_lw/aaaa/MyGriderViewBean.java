package com.example.z_lw.aaaa;

/**
 * Created by Z-LW on 2016/10/19.
 */
public class MyGriderViewBean {
    private int imageId;
    private String name;

    public MyGriderViewBean(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
