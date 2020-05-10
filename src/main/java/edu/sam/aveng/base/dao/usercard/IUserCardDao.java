package edu.sam.aveng.base.dao.usercard;

import edu.sam.aveng.base.model.entity.User;
import edu.sam.aveng.base.model.entity.UserCard;
import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.model.enumeration.StatementType;
import edu.sam.aveng.base.model.enumeration.Status;

import java.util.List;

public interface IUserCardDao {
    List<UserCard> search(User user, Lang lang, String input, List<Status> statusList,
                          List<StatementType> typeList);
}