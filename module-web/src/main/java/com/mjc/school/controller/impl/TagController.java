package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.TagDTO;
import com.mjc.school.service.error.ValidationException;
import com.mjc.school.service.impl.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@Controller
public class TagController implements BaseController<TagDTO, Long> {
    TagService tagService;

    @Override
    public List<TagDTO> readAll() {
        List<TagDTO> tagDTOS = tagService.readAll();
        if (tagDTOS.isEmpty()) System.out.println("empty");
        else System.out.println(tagDTOS);
        return tagDTOS;
    }

    @Override
    public TagDTO readById(Long id) {
        try {
            TagDTO tagDTO= tagService.readById(id);
            System.out.println(tagDTO);
            return tagDTO;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TagDTO create(TagDTO createRequest) {
        try {
            TagDTO tagDTO = tagService.create(createRequest);
            System.out.println("SUCCESS");
            return tagDTO;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TagDTO update(TagDTO updateRequest, Long id) {
        try {
            TagDTO tagDTO = tagService.update(updateRequest, id);
            System.out.println("SUCCESS");
            return tagDTO;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            tagService.deleteById(id);
            return true;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<TagDTO> getTagsByNewId(Long newsId) {
        List<TagDTO> tagDTOS = tagService.getTagsByNewId(newsId);
        if (tagDTOS.isEmpty()) {
            System.out.println("not find");
            return null;
        }
        System.out.println(tagDTOS);
        return tagDTOS;
    }
}
