package com.mjc.school.service.validate;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.TagDTO;
import com.mjc.school.service.error.ErrorCode;
import com.mjc.school.service.error.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TagValidation implements BaseValidation<TagDTO> {
    private BaseRepository<NewsModel, Long> repository;

    @Override
    public void validate(TagDTO tagDTO) throws ValidationException {
        if (tagDTO.getName().length() < 3 || tagDTO.getName().length() > 15) throw new ValidationException(ErrorCode.FIELD_NAME_INVALID_LENGTH.getErrorData());

        for (Long id : tagDTO.getNewsId()) {
            if (!repository.existById(id)) throw new ValidationException(ErrorCode.NO_SUCH_NEWS.getErrorData());
        }
    }
}
