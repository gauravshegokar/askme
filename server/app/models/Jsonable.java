package models;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Indicates that an instance can be converted into JsonNode.
 */
public interface Jsonable {
    /**
     * Convert a list of objects into JsonNode.
     *
     * @param list
     * @return
     */
    static JsonNode toJson(List<? extends Jsonable> list) {
        return Json.toJson(
                list.stream()
                        .map(Jsonable::toJson)
                        .collect(Collectors.toList()));
    }

    /**
     * Convert an instance into JsonNode.
     *
     * @return
     */
    JsonNode toJson();
}
