package com.booksajo.dasibom.vo;

public class PhotosVO {
    private int photo_Id;
    private String category_type;
    private int post_Id;
    private String img_path;

    // Getter & Setter

    public int getPhoto_Id() {
        return photo_Id;
    }

    public void setPhoto_id(int photo_Id) {
        this.photo_Id = photo_Id;
    }

    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }

    public int getPost_Id() {
        return post_Id;
    }

    public void setPost_id(int post_Id) {
        this.post_Id = post_Id;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
