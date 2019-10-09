package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/errors")
public class ErrorController {
    @RequestMapping
    public String renderErrorPage(Model model, HttpServletRequest httpRequest) {
        Integer httpErrorCode = (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
        model.addAttribute(Constants.Model.HTTP_ERROR_CODE, httpErrorCode);
        return Constants.View.ERROR_PAGE;
    }

    @GetMapping(value = "400Error")
    public void trigger400HttpError() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "403Error")
    public void trigger403HttpError() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "404Error")
    public void trigger404HttpError() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "405Error")
    public void trigger405HttpError() {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @GetMapping(value = "500Error")
    public void trigger500HttpError() {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
