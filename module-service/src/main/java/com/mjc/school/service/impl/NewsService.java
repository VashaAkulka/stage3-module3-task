package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.error.ErrorCode;
import com.mjc.school.service.error.ValidationException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validate.BaseValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsService implements BaseService<NewsDTO, Long> {

    private BaseValidation<NewsDTO> validation;
    private BaseRepository<NewsModel, Long> repository;

    @Override
    public List<NewsDTO> readAll() {
        return NewsMapper.INSTANCE.newsListToNewsDtoList(repository.readAll());
    }

    @Override
    public NewsDTO readById(Long id) throws ValidationException {
        return NewsMapper.INSTANCE.newsToNewsDto(repository.readById(id).orElseThrow(() -> new ValidationException(ErrorCode.NO_SUCH_NEWS.getErrorData())));
    }

    @Override
    public NewsDTO create(NewsDTO createRequest) throws ValidationException {
        return null;
    }

    @Override
    public NewsDTO update(NewsDTO updateRequest, Long id) throws ValidationException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws ValidationException {
        return false;
    }
}
