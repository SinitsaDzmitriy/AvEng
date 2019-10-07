package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.model.transfer.UserTableItem;
import edu.sam.aveng.temp.service.IPopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private IPopUserService userService;

    @Autowired
    public void setUserService(IPopUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public void register() {

        // ToDo: Additional server-side validation credentials.password = retypedPassword
//        @Valid @RequestBody UserRegCredentials regCredentials
//        UserCredentials credentials = Converter.simplify(regCredentials);
//        long newUserId = userService.persist(credentials);
//        userService.populate(newUserId);
    }

    @GetMapping
    public List<UserTableItem> findAll() {
        return userService.findAll();
    }

    @PatchMapping("/update/{id}/swap-admin")
    public void swapAdmin(@PathVariable long id) {
        userService.swapAdmin(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
            userService.delete(id);
    }
}
