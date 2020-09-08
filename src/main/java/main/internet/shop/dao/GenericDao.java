package main.internet.shop.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, K> {
    T create(T t);

    Optional<T> get(K k);

    List<T> getAll();

    T update(T k);

    boolean deleteById(K k);

    boolean delete(T t);
}
