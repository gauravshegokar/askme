package services.search;

import models.Jsonable;

import java.util.List;

public interface Searchable {
    /**
     * Unique identifier of the instance.
     * Can be used as keys in the search result.
     *
     * @return
     */
    String getIdentifier();

    /**
     * Do the search based on keywords.
     *
     * @param keywords
     * @return
     */
    List<? extends Jsonable> search(List<String> keywords);
}
