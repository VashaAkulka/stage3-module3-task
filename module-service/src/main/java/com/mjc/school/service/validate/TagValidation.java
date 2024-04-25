package com.mjc.school.service.validate;

import com.mjc.school.service.dto.TagDTO;
import com.mjc.school.service.error.ErrorCode;
import com.mjc.school.service.error.ValidationException;

public class TagValidation implements BaseValidation<TagDTO> {
    @Override
    public void validate(TagDTO tagDTO) throws ValidationException {
        if (tagDTO.getName().length() < 3 || tagDTO.getName().length() > 15) throw new ValidationException(ErrorCode.FIELD_NAME_INVALID_LENGTH.getErrorData());
    }
}
