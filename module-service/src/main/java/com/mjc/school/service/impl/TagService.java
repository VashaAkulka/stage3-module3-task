package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDTO;
import com.mjc.school.service.error.ErrorCode;
import com.mjc.school.service.error.ValidationException;
import com.mjc.school.service.mapper.TagMapper;
import com.mjc.school.service.validate.BaseValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagService implements BaseService<TagDTO, Long> {

    private BaseValidation<TagDTO> validation;
    private BaseRepository<TagModel, Long> repository;

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
        return null;
    }

    @Override
    public TagDTO update(TagDTO updateRequest, Long id) throws ValidationException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws ValidationException {
        return false;
    }
}
