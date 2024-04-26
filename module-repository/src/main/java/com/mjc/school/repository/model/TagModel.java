package com.mjc.school.repository.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "tags")
@Entity
public class TagModel implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<NewsModel> news = new HashSet<>();
}
