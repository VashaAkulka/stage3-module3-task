package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDTO;
import com.mjc.school.service.error.ValidationException;
import com.mjc.school.service.impl.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@Controller
public class AuthorController implements BaseController<AuthorDTO, Long> {
    AuthorService authorService;

    @Override
    public List<AuthorDTO> readAll() {
        List<AuthorDTO> authorDTOS = authorService.readAll();
        if (authorDTOS.isEmpty()) System.out.println("empty");
        else System.out.println(authorDTOS);
        return authorDTOS;
    }

    @Override
    public AuthorDTO readById(Long id) {
        try {
            AuthorDTO authorDTO = authorService.readById(id);
            System.out.println(authorDTO);
            return authorDTO;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public AuthorDTO create(AuthorDTO createRequest) {
        try {
            AuthorDTO authorDTO = authorService.create(createRequest);
            System.out.println("SUCCESS");
            return authorDTO;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public AuthorDTO update(AuthorDTO updateRequest, Long id) {
        try {
            AuthorDTO authorDTO = authorService.update(updateRequest, id);
            System.out.println("SUCCESS");
            return authorDTO;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            authorService.deleteById(id);
            System.out.println("SUCCESS");
            return true;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public AuthorDTO getAuthorByNewsId(Long newsId) {
        AuthorDTO authorDTO = authorService.getAuthorByNewsId(newsId);
        if (authorDTO != null) {
            System.out.println(authorDTO);
            return authorDTO;
        }
        System.out.println("Not find");
        return null;
    }
}
