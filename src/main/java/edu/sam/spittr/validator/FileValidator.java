package edu.sam.spittr.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

// ToDo get more about @Component
@Component
// ToDo get more about Validator interface and custom validation in Spring
public class FileValidator implements Validator {

    // ToDo get more about the method goal
    @Override
    public boolean supports(Class<?> clazz) {
        return MultipartFile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MultipartFile file = (MultipartFile) target;

        if (file.isEmpty()){
            /*
                    Errors
                    void reject(String errorCode);

                    Register a global error for the entire target object,
                using the given error description.
            */
            errors.reject("validation.file.empty");
        }

        // ToDo: Bear this value in a property file
        final long MAX_SIZE = 2097152;

        if (file.getSize() > MAX_SIZE) {
            errors.reject("validation.file.maxsize");
        }
    }
}

/*
    Template
*/