package com.example.taurus.utils;

import cn.hutool.core.lang.Assert;
import com.example.taurus.logging.Logger;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ServiceUtils {
    private final static Logger LOGGER = Logger.getLogger(ServiceUtils.class);

    private ServiceUtils() {
    }

    /**
     * @param datas           data collection
     * @param mappingFunction calculate the id in data list
     * @param <ID>            id type
     * @param <T>             data type
     * @return a set of id
     */
    public static <ID, T> Set<ID> fetchProperty(final Collection<T> datas, Function<T, ID> mappingFunction) {
        return CollectionUtils.isEmpty(datas) ?
                Collections.emptySet() :
                datas.stream().map(mappingFunction).collect(Collectors.toSet());
    }

    public static <ID, D> Map<ID, List<D>> converToListMAp(Collection<ID> ids, Collection<D> list, Function<D, ID> mappingFunction) {
        Assert.notNull(mappingFunction, "mapping function must not be null");
        if (CollectionUtils.isEmpty(ids) || CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<ID, List<D>> resultMap = new HashMap<>();
        list.forEach(data -> resultMap.computeIfAbsent(mappingFunction.apply(data), id -> new LinkedList<>()).add(data));
        ids.forEach(id -> resultMap.putIfAbsent(id, Collections.emptyList()));
        return resultMap;
    }

    public static <ID, D> Map<ID, D> convertToMap(Collection<D> list, Function<D, ID> mappingFunction) {
        Assert.notNull(mappingFunction, "mapping function must not be null");

        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }

        Map<ID, D> resultMap = new HashMap<>();

        list.forEach(data -> resultMap.putIfAbsent(mappingFunction.apply(data), data));

        return resultMap;
    }

    public static <ID, D, V> Map<ID, V> convertToMap(Collection<D> list, Function<D, ID> keyFunction, Function<D, V> valueFunction) {
        Assert.notNull(keyFunction, "mapping function must not be null");

        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }

        Map<ID, V> resultMap = new HashMap<>();

        list.forEach(data -> resultMap.putIfAbsent(keyFunction.apply(data), valueFunction.apply(data)));

        return resultMap;
    }

}
