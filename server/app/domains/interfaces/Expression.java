package domains.interfaces;

import domains.HashTagsInterpreterContext;
import models.Tag;

import java.util.List;

public interface Expression {

    public List<Tag> interpret(HashTagsInterpreterContext ic, String tags);
}
