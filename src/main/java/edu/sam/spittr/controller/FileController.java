package edu.sam.spittr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {
    @RequestMapping(method = RequestMethod.GET)
    public String uploadFile(Model model) {
        return "uploadFile";
    }

    @RequestMapping(method = RequestMethod.POST)
//    public String uploadFile(@RequestPart("file") byte[] fileInBytes) {
    public String uploadFile(@RequestPart("file") MultipartFile fileInBytes) {
        // ToDo: get more about difference between redirect and forward.
        return "redirect:/file";
    }
}
