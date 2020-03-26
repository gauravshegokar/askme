package services.search;

import models.Jsonable;

import java.util.HashMap;
import java.util.List;

public class SearchService {
    /**
     * 'all'|'posts'|'hashtags'
     */
    private final String SEARCH_TYPE;

    /**
     * @param searchType 'all'|'posts'|'hashtags'
     */
    public SearchService(String searchType) {
        this.SEARCH_TYPE = searchType;
    }

    /**
     * [Command Pattern]
     * <p>
     * Perform search based on keywords.
     *
     * @param keywords
     * @return
     */
    public HashMap<String, List<? extends Jsonable>> search(
            List<String> keywords
    ) {
        SearchBroker broker = new SearchBroker();

        if (this.SEARCH_TYPE.equals("all") || this.SEARCH_TYPE.equals("posts")) {
            broker.addSearchable(new SearchPosts());
        }

        if (this.SEARCH_TYPE.equals("all") || this.SEARCH_TYPE.equals("hashtags")) {
            broker.addSearchable(new SearchHashtags());
        }

        return broker.execute(keywords);
    }
}
