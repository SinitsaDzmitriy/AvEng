package edu.sam.aveng.base.model.transfer.dto;

import edu.sam.aveng.base.model.domain.enumeration.Lang;
import edu.sam.aveng.base.model.domain.enumeration.StatementType;

public class CardTableItem {
    private Long id;
    private Lang lang;
    private String content;
    private StatementType type;
    private String definition;

    public CardTableItem() {}

    public CardTableItem(Long id, Lang lang, String content,
                         StatementType type, String definition) {
        this.id = id;
        this.lang = lang;
        this.content = content;
        this.type = type;
        this.definition = definition;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
