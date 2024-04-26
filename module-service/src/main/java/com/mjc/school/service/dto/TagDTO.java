package com.mjc.school.service.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TagDTO {
    private String name;
    private Set<Long> newsId = new HashSet<>();
}
