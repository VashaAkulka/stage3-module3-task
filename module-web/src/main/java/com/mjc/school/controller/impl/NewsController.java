package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.error.ValidationException;
import com.mjc.school.service.impl.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@Controller
public class NewsController implements BaseController<NewsDTO, Long> {
    NewsService newsService;

    @Override
    public List<NewsDTO> readAll() {
        List<NewsDTO> newsDTOS = newsService.readAll();
        if (newsDTOS.isEmpty()) System.out.println("empty");
        else System.out.println(newsDTOS);
        return newsDTOS;
    }

    @Override
    public NewsDTO readById(Long id) {
        try {
            NewsDTO newsDTO = newsService.readById(id);
            System.out.println(newsDTO);
            return newsDTO;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public NewsDTO create(NewsDTO createRequest) {
        try {
            NewsDTO newsDTO = newsService.create(createRequest);
            System.out.println("SUCCESS");
            return newsDTO;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public NewsDTO update(NewsDTO updateRequest, Long id) {
        try {
            NewsDTO newsDTO = newsService.update(updateRequest, id);
            System.out.println("SUCCESS");
            return newsDTO;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            newsService.deleteById(id);
            System.out.println("SUCCESS");
            return true;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<NewsDTO> getNewsByParameters(String tagName, Long tagId, String authorName, String title, String content) {
        List<NewsDTO> newsDTOS = newsService.getNewsByParameters(tagName, tagId, authorName, title, content);
        if (newsDTOS.isEmpty()) {
            System.out.println("not find");
            return null;
        }
        System.out.println(newsDTOS);
        return newsDTOS;
    }
}
