package com.mjc.school.service.dto;

import lombok.Data;

import java.util.Set;

@Data
public class NewsDTO {
    Long authorId;
    String title;
    String content;
    Set<Long> tagsId;
}
