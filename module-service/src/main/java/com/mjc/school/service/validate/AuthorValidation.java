package com.mjc.school.service.validate;

import com.mjc.school.service.dto.AuthorDTO;
import com.mjc.school.service.error.ErrorCode;
import com.mjc.school.service.error.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class AuthorValidation implements BaseValidation<AuthorDTO> {
    @Override
    public void validate(AuthorDTO authorDTO) throws ValidationException {
        if (authorDTO.getName().length() < 3 || authorDTO.getName().length() > 15) throw new ValidationException(ErrorCode.FIELD_NAME_INVALID_LENGTH.getErrorData());
    }
}
