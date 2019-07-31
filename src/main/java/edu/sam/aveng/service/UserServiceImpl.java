package edu.sam.aveng.service;

import edu.sam.aveng.dao.IGenericDao;
import edu.sam.aveng.domain.SimpleGrantedAuthority;
import edu.sam.aveng.domain.User;
import edu.sam.aveng.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        //User newUser = Converter.convertToEntity(userDto);
        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder()
                .encode(userDto.getPassword()));
        newUser.setLastLoggingDate(new Date());
        newUser.setEnabled(true);
        newUser.addAuthority(new SimpleGrantedAuthority("ROLE_USER"));
        userDAO.create(newUser);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.findByProperty("email", s);
    }
}
