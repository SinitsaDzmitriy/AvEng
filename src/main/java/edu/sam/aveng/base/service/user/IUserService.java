package edu.sam.aveng.base.service.user;

import edu.sam.aveng.base.model.transfer.user.AbstractUserCredentials;
import edu.sam.aveng.base.model.transfer.user.UserTableItem;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    long create(AbstractUserCredentials credentials);

    List<UserTableItem> findAll();

    void swapAdmin(long id);

    void delete(long id);

}
