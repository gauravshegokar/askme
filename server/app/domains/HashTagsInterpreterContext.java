package domains;

import models.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HashTagsInterpreterContext {


    public List<Tag> extractTags(String tags) {

        List<Tag> postTags = new ArrayList<>();

        List<String> tagNames = Arrays.asList(tags.split(","));

        for (String tagName : tagNames) {


            List<Tag> existingTag = Tag.find.query().where().eq("tagName", tagName).findList();

            if (existingTag.size() == 0) {


                Tag newTag = new Tag();
                newTag.setTagName(tagName);
                newTag.save();
                postTags.add(newTag);
            } else {


                postTags.add(existingTag.get(0));


            }

        }
        return postTags;
    }
}
