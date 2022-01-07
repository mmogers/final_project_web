package lv.kidspuzzle.web.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//model just creates the table in the database, for manipulations with data will be created interface (repository) for every model
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //every time new id automatically generated
    private Long id;

    private String full_text, author;


    //CONSTRUCTORS


    public Post() {
    }

    public Post(String full_text, String author) {
        this.full_text = full_text;
        this.author = author;
    }

    //GETTERS and SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}//end of class
