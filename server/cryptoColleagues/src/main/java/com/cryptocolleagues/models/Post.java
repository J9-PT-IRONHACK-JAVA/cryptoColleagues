package com.cryptocolleagues.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(length = 500)
    private String description;

    @Column(length = 2500)
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    public Post(String title, String description, String content, User author) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.author = author;
    }

    public Post() {

    }
}
