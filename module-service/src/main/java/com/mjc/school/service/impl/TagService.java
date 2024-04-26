package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.TagRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDTO;
import com.mjc.school.service.error.ErrorCode;
import com.mjc.school.service.error.ValidationException;
import com.mjc.school.service.mapper.TagMapper;
import com.mjc.school.service.validate.BaseValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TagService implements BaseService<TagDTO, Long> {

    private BaseValidation<TagDTO> validation;
    private TagRepository repository;
    private BaseRepository<NewsModel, Long> newsRepository;

    @Override
    public List<TagDTO> readAll() {
        return TagMapper.INSTANCE.tagListToTagDTOList(repository.readAll());
    }

    @Override
    public TagDTO readById(Long id) throws ValidationException {
        return TagMapper.INSTANCE.tagToTagDTO(repository.readById(id).orElseThrow(() -> new ValidationException(ErrorCode.NO_SUCH_TAG.getErrorData())));
    }

    @Override
    public TagDTO create(TagDTO createRequest) throws ValidationException {
        validation.validate(createRequest);

        TagModel tagModel = new TagModel();
        tagModel.setName(createRequest.getName());

        Set<NewsModel> newsModelSet = new HashSet<>();
        for (Long newsId : createRequest.getNewsId()) {
            newsModelSet.add(newsRepository.readById(newsId).orElseThrow(RuntimeException::new));
        }
        tagModel.setNews(newsModelSet);

        return TagMapper.INSTANCE.tagToTagDTO(repository.create(tagModel));
    }

    @Override
    public TagDTO update(TagDTO updateRequest, Long id) throws ValidationException {
        validation.validate(updateRequest);

        TagModel tagModel = new TagModel();
        tagModel.setName(updateRequest.getName());

        Set<NewsModel> newsModelSet = new HashSet<>();
        for (Long newsId : updateRequest.getNewsId()) {
            newsModelSet.add(newsRepository.readById(newsId).orElseThrow(RuntimeException::new));
        }
        tagModel.setNews(newsModelSet);

        return TagMapper.INSTANCE.tagToTagDTO(repository.update(tagModel, id).orElseThrow(() -> new ValidationException(ErrorCode.NO_SUCH_TAG.getErrorData())));
    }

    @Override
    public boolean deleteById(Long id) throws ValidationException {
        if (repository.deleteById(id)) return true;
        else throw new ValidationException(ErrorCode.NO_SUCH_TAG.getErrorData());
    }

    public List<TagDTO> getTagsByNewId(Long newsId) {
        return TagMapper.INSTANCE.tagListToTagDTOList(repository.getTagsByNewsId(newsId));
    }
}
