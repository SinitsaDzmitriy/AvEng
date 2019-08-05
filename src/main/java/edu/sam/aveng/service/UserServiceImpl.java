package edu.sam.aveng.service;

import edu.sam.aveng.dao.IGenericDao;
import edu.sam.aveng.domain.SimpleGrantedAuthority;
import edu.sam.aveng.domain.User;
import edu.sam.aveng.dto.UserDTO;
import edu.sam.aveng.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.Date;

@EnableTransactionManagement
@Service
@Transactional
public class UserServiceImpl implements IUserService, UserDetailsService {

    private IGenericDao<User> userDAO;

    @Autowired
    public void setDao(IGenericDao<User> daoToSet) {
        userDAO = daoToSet;
        userDAO.setClazz(User.class);
    }

    @Override
    public void register(UserDTO userDto) {
        User user = Converter.convertToEntity(userDto);
        user.setLastLoggingDate(new Date());
        user.setEnabled(true);
        user.addAuthority(new SimpleGrantedAuthority("ROLE_USER"));
        userDAO.create(user);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.findByProperty("email", s);
    }
}
