package com.cryptocolleagues.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    public Post(String title, String description, String content, User author) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.author = author;
    }
}
