package edu.sam.aveng.base.model.transfer;

import edu.sam.aveng.base.model.domain.enumeration.Lang;
import edu.sam.aveng.base.model.domain.enumeration.StatementType;
import edu.sam.aveng.base.model.domain.enumeration.Status;

public class UserCardTableItem {

    Long userCardId;
    Status status;
    Lang lang;
    String content;
    StatementType type;
    String definition;


    public Long getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(Long userCardId) {
        this.userCardId = userCardId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StatementType getType() {
        return type;
    }

    public void setType(StatementType type) {
        this.type = type;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
