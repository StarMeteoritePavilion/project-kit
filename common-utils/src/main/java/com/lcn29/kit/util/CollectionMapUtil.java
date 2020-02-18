package com.lcn29.kit.util;

import java.util.Collection;
import java.util.Map;

/**
 * <pre>
 *  a util for Java's Collection and Java's Map
 * </pre>
 *
 * @author LCN
 * @date 2020-01-31 13:34
 */
public class CollectionMapUtil {

    /**
     * determine if the map is empty
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * determine if the map isn't empty
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * determine if the collection is empty
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * determine if the collection isn't empty
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
}
