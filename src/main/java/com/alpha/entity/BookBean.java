package com.alpha.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @ClassName BookBean
 * @Description TODO
 * @Author Tanger
 * @Date 2020/8/18 17:39
 */
@Document(indexName = "book", type = "_doc")
public class BookBean {
    @Id
    private String id;
    private String title;
    private String author;
    private String postDate;

    public BookBean(){}

    public BookBean(String id, String title, String author, String postDate){
        this.id=id;
        this.title=title;
        this.author=author;
        this.postDate=postDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", postDate='" + postDate + '\'' +
                '}';
    }

    public BookBean(String title, String author, String postDate) {
        this.title = title;
        this.author = author;
        this.postDate = postDate;
    }
}
