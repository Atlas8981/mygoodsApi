package com.atlas.mygoods.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table
public class ImageEntity {
//    @Id
//    @GeneratedValue
    private Long imageId;
    private String imageURL;
    private String imageName;

    public ImageEntity() {
    }

    public ImageEntity(Long imageId, String imageURL, String imageName) {
        this.imageId = imageId;
        this.imageURL = imageURL;
        this.imageName = imageName;
    }

    public ImageEntity(String imageURL, String imageName) {
        this.imageURL = imageURL;
        this.imageName = imageName;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "ImageEntity{" +
                "imageId='" + imageId + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
