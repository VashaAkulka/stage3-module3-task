package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.error.ErrorCode;
import com.mjc.school.service.error.ValidationException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validate.BaseValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class NewsService implements BaseService<NewsDTO, Long> {

    private BaseValidation<NewsDTO> validation;
    private NewsRepository repository;
    private BaseRepository<AuthorModel, Long> authorRepository;
    private BaseRepository<TagModel, Long> tagRepository;

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
        validation.validate(createRequest);

        NewsModel newsModel = new NewsModel();
        newsModel.setContent(createRequest.getContent());
        newsModel.setTitle(createRequest.getTitle());

        Set<TagModel> tagModelSet = new HashSet<>();
        for (Long TagId : createRequest.getTagsId()) {
            tagModelSet.add(tagRepository.readById(TagId).orElseThrow(RuntimeException::new));
        }
        newsModel.setTags(tagModelSet);

        newsModel.setAuthor(authorRepository.readById(createRequest.getAuthorId()).orElseThrow(RuntimeException::new));
        return NewsMapper.INSTANCE.newsToNewsDto(repository.create(newsModel));
    }

    @Override
    public NewsDTO update(NewsDTO updateRequest, Long id) throws ValidationException {
        validation.validate(updateRequest);

        NewsModel newsModel = new NewsModel();
        newsModel.setContent(updateRequest.getContent());
        newsModel.setTitle(updateRequest.getTitle());

        Set<TagModel> tagModelSet = new HashSet<>();
        for (Long TagId : updateRequest.getTagsId()) {
            tagModelSet.add(tagRepository.readById(TagId).orElseThrow(RuntimeException::new));
        }
        newsModel.setTags(tagModelSet);

        newsModel.setAuthor(authorRepository.readById(updateRequest.getAuthorId()).orElseThrow(RuntimeException::new));
        return NewsMapper.INSTANCE.newsToNewsDto(repository.update(newsModel, id).orElseThrow(() -> new ValidationException(ErrorCode.NO_SUCH_NEWS.getErrorData())));
    }

    @Override
    public boolean deleteById(Long id) throws ValidationException {
        if (repository.deleteById(id)) return true;
        else throw new ValidationException(ErrorCode.NO_SUCH_NEWS.getErrorData());
    }

    public List<NewsDTO> getNewsByParameters(String tagName, Long tagId, String authorName, String title, String content) {
        Set<NewsModel> newsModelSet = new HashSet<>();

        if (tagName != null) {
            newsModelSet.addAll(repository.getNewsByTagName(tagName));
        }
        if (tagId != null) {
            newsModelSet.addAll(repository.getNewsByTagIds(tagId));
        }
        if (authorName != null) {
            newsModelSet.addAll(repository.getNewsByAuthorName(authorName));
        }
        if (title != null) {
            newsModelSet.addAll(repository.getNewsByTitle(title));
        }
        if (content != null) {
            newsModelSet.addAll(repository.getNewsByContent(content));
        }

        return NewsMapper.INSTANCE.newsListToNewsDtoList(new ArrayList<>(newsModelSet));
    }
}
