package com.mjc.school.repository.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@Table(name = "news")
@Entity
public class NewsModel implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authorId")
    private AuthorModel author;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tag_news",
    joinColumns = @JoinColumn(name = "newId"),
    inverseJoinColumns = @JoinColumn(name = "tagId"))
    private Set<TagModel> tags = new HashSet<>();
}
