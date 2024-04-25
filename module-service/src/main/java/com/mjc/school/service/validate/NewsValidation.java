package com.mjc.school.service.validate;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.impl.TagRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.error.ErrorCode;
import com.mjc.school.service.error.ValidationException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NewsValidation implements BaseValidation<NewsDTO> {

    private BaseRepository<AuthorModel, Long> authorRepository;
    private BaseRepository<TagModel, Long> tagRepository;

    @Override
    public void validate(NewsDTO newsDTO) throws ValidationException {
        if (newsDTO.getTitle().length() < 5 || newsDTO.getTitle().length() > 30) throw new ValidationException(ErrorCode.FIELD_TITLE_INVALID_LENGTH.getErrorData());
        if (newsDTO.getContent().length() < 5 || newsDTO.getContent().length() > 255) throw new ValidationException(ErrorCode.FIELD_CONTENT_INVALID_LENGTH.getErrorData());
        if (!authorRepository.existById(newsDTO.getAuthorId())) throw new ValidationException(ErrorCode.NO_SUCH_AUTHOR.getErrorCode());

        for(Long id : newsDTO.getTagsId()) {
            if (!tagRepository.existById(id)) throw new ValidationException(ErrorCode.NO_SUCH_TAG.getErrorCode());
        }
    }
}
