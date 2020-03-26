package services.search;

import models.Post;

import java.util.List;

/**
 * [Command Pattern]
 */
public class SearchPosts implements Searchable {
    @Override
    public String getIdentifier() {
        return "posts";
    }

    /**
     * Search posts.
     *
     * @param keywords
     * @return
     */
    @Override
    public List<Post> search(List<String> keywords) {
        return Post.searchByKeywords(keywords);
    }
}
