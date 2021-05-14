package com.dedsec.machinetask.Model;

public class Data {

    private String author, download_url;

    public Data(String author, String download_url) {
        this.author = author;
        this.download_url = download_url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return download_url;
    }

    public void setUrl(String download_url) {
        this.download_url = download_url;
    }
}
