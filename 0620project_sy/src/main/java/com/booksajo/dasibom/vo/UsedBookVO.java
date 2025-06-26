package com.booksajo.dasibom.vo;

import java.util.Date;
import java.util.List;

public class UsedBookVO {

    private int post_Id;
    private Date post_Date;
    private String title;
    private int price;
    private String isbn;
    private String sale_Status;
    private String image_Path;
    private String sale_Type;
    private String user_Id;
    private String content;

    // ?úÖ Ï∂îÍ??êú ?ïÑ?ìú
    private List<String> imagePathList;

    // Í∏∞Î≥∏ ?Éù?Ñ±?ûê
    public UsedBookVO() {}

    // ?Éù?Ñ±?ûê (?ïÑ?öî?ãú ?àò?†ï)
    public UsedBookVO(int post_Id, Date post_Date, String title, int price, String isbn,
                      String sale_Status, String image_Path, String sale_Type,
                      String user_Id, String content) {
        this.post_Id = post_Id;
        this.post_Date = post_Date;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
        this.sale_Status = sale_Status;
        this.image_Path = image_Path;
        this.sale_Type = sale_Type;
        this.user_Id = user_Id;
        this.content = content;
    }

    // ?úÖ Getter & Setter for imagePathList
    public List<String> getImagePathList() {
        return imagePathList;
    }

    public void setImagePathList(List<String> imagePathList) {
        this.imagePathList = imagePathList;
    }

    // Í∏∞Ï°¥ Getter/Setter ?Éù?ûµ ?óÜ?ù¥ Í∑∏Î?Î°? ?ú†Ïß?
    public int getPost_Id() { return post_Id; }
    public void setPost_Id(int post_Id) { this.post_Id = post_Id; }

    public Date getPost_Date() { return post_Date; }
    public void setPost_Date(Date post_Date) { this.post_Date = post_Date; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getSale_Status() { return sale_Status; }
    public void setSale_Status(String sale_Status) { this.sale_Status = sale_Status; }

    public String getImage_Path() { return image_Path; }
    public void setImage_Path(String image_Path) { this.image_Path = image_Path; }

    public String getSale_Type() { return sale_Type; }
    public void setSale_Type(String sale_Type) { this.sale_Type = sale_Type; }

    public String getUser_Id() { return user_Id; }
    public void setUser_Id(String user_Id) { this.user_Id = user_Id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
