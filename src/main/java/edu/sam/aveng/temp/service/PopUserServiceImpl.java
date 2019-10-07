package edu.sam.aveng.temp.service;

import edu.sam.aveng.base.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;

@EnableTransactionManagement
@Service
@Transactional
public class PopUserServiceImpl extends UserService implements IPopUserService {

    @Override
    public int populate(long userId) {
        return userDao.populate(userId);
    }

}
