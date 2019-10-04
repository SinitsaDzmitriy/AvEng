package edu.sam.aveng.base.converter;

import edu.sam.aveng.base.model.entity.User;
import edu.sam.aveng.base.model.transfer.user.UserTableItem;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.stream.Stream;

public class UserConverter {

    public Stream<UserTableItem> convertToUserTableItem(Collection<User> users) {
        return users.stream()
                .map(user -> {
                    UserTableItem item = new UserTableItem();

                    boolean admin = user.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .anyMatch(authority -> authority.equals("ROLE_ADMIN"));

                    item.setId(user.getId());
                    item.setEmail(user.getEmail());
                    item.setEnabled(user.isEnabled());
                    item.setAdmin(admin);
                    item.setLastVisit(user.getLastLoggingDate());

                    return item; });
    }

}
