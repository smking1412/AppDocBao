package com.example.appdocbao;

public class ItemTinTuc {
    private String title;
    private String img;
    private String cmt;
    private String pubDate;

    public ItemTinTuc(String title, String img, String cmt, String pubDate) {
        this.title = title;
        this.img = img;
        this.cmt = cmt;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
