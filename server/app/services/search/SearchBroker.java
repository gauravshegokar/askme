package services.search;

import models.Jsonable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * [Command Pattern]
 */
public class SearchBroker {
    private List<Searchable> searchables = new ArrayList<>();

    /**
     * Append a searchable command.
     *
     * @param searchable
     * @return
     */
    public SearchBroker addSearchable(Searchable searchable) {
        this.searchables.add(searchable);

        return this;
    }

    /**
     * Execute searchable commands.
     *
     * @return
     */
    public HashMap<String, List<? extends Jsonable>> execute(List<String> keywords) {
        HashMap<String, List<? extends Jsonable>> hashMap = new HashMap<>();

        searchables.forEach(searchable ->
                hashMap.put(
                        searchable.getIdentifier(),
                        searchable.search(keywords)
                )
        );

        return hashMap;
    }
}
