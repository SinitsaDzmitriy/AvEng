package edu.sam.aveng.service;

import edu.sam.aveng.dao.IGenericDao;
import edu.sam.aveng.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class UserDetailsServiceImpl implements UserDetailsService {

    private IGenericDao<User> userDao;

    @Autowired
    public void setDao(IGenericDao<User> daoToSet) {
        userDao = daoToSet;
        userDao.setClazz(User.class);
    }

    public UserDetails loadUserByUsername(String var1) {
        return userDao.findByProperty("email", var1);
    }
}
