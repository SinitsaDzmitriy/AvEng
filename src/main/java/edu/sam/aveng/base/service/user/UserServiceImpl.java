package edu.sam.aveng.base.service.user;

import edu.sam.aveng.base.model.entity.VerificationToken;

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

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

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

    private int EXPIRATION_PERIOD_IN_MILLISECONDS = 24 * 60 * 60 * 1000;

    protected IPopGenericHiberDao<User> userDao;
    private IGenericDao<SimpleGrantedAuthority> authorityDao;
    private IGenericDao<UserCard> userCardDao;
    private IGenericDao<VerificationToken> verificationTokenDao;

    private PasswordEncoder passEncoder;
    private UserConverter userConverter = new UserConverter();

    private MailSender mailSender;

    @Autowired
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

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
    @Qualifier("genericHiberDao")
    public void setVerificationTokenDao(IGenericDao<VerificationToken> daoToSet) {
        verificationTokenDao = daoToSet;
        verificationTokenDao.setClazz(VerificationToken.class);
    }

    @Autowired
    public void setPassEncoder(PasswordEncoder passEncoder) {
        this.passEncoder = passEncoder;
    }

    @Override
    public void create(UserCredentials userCredentials) {

        // Encode user password
        String encodedPassword = passEncoder.encode(userCredentials.getPassword());

        // Create new User
        User user = new User();
        user.setEmail(userCredentials.getEmail());
        user.setPassword(passEncoder.encode(userCredentials.getPassword()));
        user.setEnabled(false);

        // ToDo refactor as obtain(String roleName): fetch or persist
        user.addAuthority(authorityDao.findEagerlyByProperty("role", "ROLE_USER"));

        // Creat their verification token
        VerificationToken verificationToken = new VerificationToken(user);
        verificationTokenDao.create(verificationToken);

        // Prepare email hardcode
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("AvEng Registration");
        msg.setTo(user.getEmail());
        msg.setText("To activate your account just click the following link "
                + "http://localhost:8888/users/activation?token=" + verificationToken.getToken());

        // Send message
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            // Simply log it and go on.
            ex.printStackTrace();
        }

    }

    @Override
    public List<UserTableItem> findAll() {
        return userConverter.convertToUserTableItem(userDao.findAll())
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userDao.findByProperty("email", s);

        if(user != null && !user.isEnabled()){
            throw new UsernameNotFoundException("User is disabled.");
        }
        return user;

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

    @Override
    public String activate(String token) {

        String userEmail = null;
        VerificationToken verificationToken = verificationTokenDao.findByProperty("token", token);

        if(verificationToken != null) {

            User userToActivate = verificationToken.getUser();
            verificationTokenDao.delete(verificationToken);

            userToActivate.setEnabled(true);
            userEmail = userToActivate.getEmail();
        }

        return userEmail;

    }

    @Override
    public boolean isUserRegistered(String email) {

        boolean isRegistered = false;
        User user = userDao.findByProperty("email", email);

        if(user != null) {

            if(user.isEnabled()){
                isRegistered = true;
            } else {

                VerificationToken verificationToken = verificationTokenDao.findByProperty("user", user);

                if(verificationToken == null){
                    userDao.delete(user);
                } else {

                    if(new Date().getTime() - (verificationToken.getExpiryDate().getTime())
                            < EXPIRATION_PERIOD_IN_MILLISECONDS) {
                        isRegistered = true;
                    } else {

                        verificationTokenDao.delete(verificationToken);
                        userDao.delete(user);

                    }

                }

            }

        }

        return isRegistered;

    }

}
