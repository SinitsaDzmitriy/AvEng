package edu.sam.spittr.controller;

import edu.sam.spittr.validator.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {
    // File validator injection.
    @Autowired
    private FileValidator fileValidator;

    // to bind FileValidator onto the controller.
    @InitBinder
    protected void initFileValidatorBinder(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String uploadFile(Model model) {
        return "uploadFile";
    }

    // The controller method which handles the file upload.

    /*
            Attempt to validate MultipartFile without wrapping
            @Valid, Errors errors
            if (errors.hasErrors()) {
                return "uploadFile";
            }
    */

    @RequestMapping(method = RequestMethod.POST)
    public String uploadFile(@RequestPart("file")  MultipartFile file) throws IOException {
        file.transferTo(new File("C:/TestStorage/Permanent/" + file.getOriginalFilename()));
        return "successfulUseCaseCompletion";
    }
}
// ToDo: get more about difference between redirect and forward.