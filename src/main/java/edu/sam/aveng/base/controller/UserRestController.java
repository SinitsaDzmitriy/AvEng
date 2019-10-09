package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.model.transfer.UserTableItem;
import edu.sam.aveng.base.service.user.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
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
