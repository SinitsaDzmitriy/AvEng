package edu.sam.aveng.base.service.user;

import edu.sam.aveng.legacy.contract.dao.IGenericDao;
import edu.sam.aveng.legacy.converter.UserConverter;
import edu.sam.aveng.base.model.entity.SimpleGrantedAuthority;
import edu.sam.aveng.base.model.entity.User;
import edu.sam.aveng.base.model.entity.UserCard;
import edu.sam.aveng.base.model.transfer.user.credentials.UserCredentials;
import edu.sam.aveng.base.model.transfer.user.UserTableItem;
import edu.sam.aveng.temp.dao.IPopGenericHiberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Artificial population free.
 */

@EnableTransactionManagement
@Transactional
public class UserServiceImpl implements IUserService {

    protected IPopGenericHiberDao<User> userDao;
    private IGenericDao<SimpleGrantedAuthority> authorityDao;
    private IGenericDao<UserCard> userCardDao;
    private PasswordEncoder passEncoder;
    private UserConverter userConverter = new UserConverter();

    @Autowired
    public void setUserDao(IPopGenericHiberDao<User> daoToSet) {
        userDao = daoToSet;
        userDao.setClazz(User.class);
    }

    @Autowired
    @Qualifier("genericHiberDao")
    public void setAuthorityDao(IGenericDao<SimpleGrantedAuthority> daoToSet) {
        authorityDao = daoToSet;
        authorityDao.setClazz(SimpleGrantedAuthority.class);
    }

    @Autowired
    @Qualifier("genericHiberDao")
    public void setUserCardDao(IGenericDao<UserCard> daoToSet) {
        userCardDao = daoToSet;
        userCardDao.setClazz(UserCard.class);
    }

    @Autowired
    public void setPassEncoder(PasswordEncoder passEncoder) {
        this.passEncoder = passEncoder;
    }

    @Override
    public long create(UserCredentials userCredentials) {

        User user = new User();

        // Encode user password
        String encodedPassword = passEncoder.encode(userCredentials.getPassword());

        user.setEmail(userCredentials.getEmail());
        user.setPassword(passEncoder.encode(userCredentials.getPassword()));
        user.setLastLoggingDate(new Date());
        user.setEnabled(true);

        // ToDo refactor as obtain(String roleName): fetch or persist
        user.addAuthority(authorityDao.findEagerlyByProperty("role", "ROLE_USER"));

        return userDao.create(user);
    }

    @Override
    public List<UserTableItem> findAll() {
        return userConverter.convertToUserTableItem(userDao.findAll())
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.findByProperty("email", s);
    }

    @Override
    public void swapAdmin(long id) {
        User user = userDao.find(id);

        SimpleGrantedAuthority adminAuthority =
                new SimpleGrantedAuthority("ROLE_ADMIN");

        if (user.getAuthorities().contains(adminAuthority)) {
            user.removeAuthority(adminAuthority);
        } else {
            user.addAuthority(adminAuthority);
        }
    }

    @Override
    public void delete(long id) {
        User userToDelete = userDao.find(id);

        userCardDao.deleteByProperty("owner", userToDelete);
        userDao.delete(userToDelete);
    }
}
