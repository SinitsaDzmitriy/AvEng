package edu.sam.aveng.base.service.user;

import edu.sam.aveng.base.contract.dao.IGenericDao;
import edu.sam.aveng.base.converter.UserConverter;
import edu.sam.aveng.base.model.domain.SimpleGrantedAuthority;
import edu.sam.aveng.base.model.domain.User;
import edu.sam.aveng.base.model.domain.UserCard;
import edu.sam.aveng.base.model.transfer.user.AbstractUserCredentials;
import edu.sam.aveng.base.model.transfer.user.UserTableItem;
import edu.sam.aveng.base.util.Converter;
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
    public long create(AbstractUserCredentials credentials) {
        // Encode user password
        credentials.setPassword(passEncoder.encode(credentials.getPassword()));

        String test = passEncoder.encode("admin");
        System.out.println(test);

        User user = Converter.convertToEntity(credentials);

        user.setLastLoggingDate(new Date());
        user.setEnabled(true);
        // ToDo refactor as obtain(String roleName): fetch or create
        user.addAuthority(authorityDao.findOneEagerlyByProperty("role", "ROLE_USER"));

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
        User user = userDao.findOne(id);

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
        User userToDelete = userDao.findOne(id);

        userCardDao.deleteByProperty("owner", userToDelete);
        userDao.delete(userToDelete);
    }
}
