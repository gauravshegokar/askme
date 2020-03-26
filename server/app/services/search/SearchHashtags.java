package services.search;

import models.Jsonable;
import models.Tag;

import java.util.List;

/**
 * [Command Pattern]
 */
public class SearchHashtags  implements Searchable {
    @Override
    public String getIdentifier() {
        return "tags";
    }

    /**
     * Search hashtags.
     *
     * @param keywords
     * @return
     */
    @Override
    public List<? extends Jsonable> search(List<String> keywords) {
        return Tag.searchByKeywords(keywords);
    }
}
