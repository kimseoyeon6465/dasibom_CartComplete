package com.booksajo.dasibom.vo;

import java.util.Date;

public class BookVO {
	
	private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int price;
    private int discount_price;
    private String genre;
	private String summary;
	private String imgurl;
	private String image_Path;
	private int sales;
    private Date pub_date;
    private String link;
    
    
	public String getImage_Path() {
		return image_Path;
	}
	public void setImage_Path(String image_Path) {
		this.image_Path = image_Path;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
    public int getDiscount_price() {
  		return discount_price;
  	}
  	public void setDiscount_price(int discount_price) {
  		this.discount_price = discount_price;
  	}
    public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Date getPub_date() {
		return pub_date;
	}
	public void setPub_date(Date pub_date) {
		this.pub_date = pub_date;
	}
	private String image_path;
    
    public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
    
    // getters and setters
}


