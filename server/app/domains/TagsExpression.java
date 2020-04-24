package domains;

import domains.interfaces.Expression;
import models.Tag;

import java.util.List;

public class TagsExpression implements Expression {


    @Override
    public List<Tag> interpret(HashTagsInterpreterContext ic, String tags) {
        return ic.extractTags(tags);
    }
}
