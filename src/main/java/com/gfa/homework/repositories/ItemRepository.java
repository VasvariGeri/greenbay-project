package com.gfa.homework.repositories;

import com.gfa.homework.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT * FROM items ORDER BY id LIMIT (20 * :n) OFFSET 20 * (:n - 1)", nativeQuery = true)
    List<Item> findNthTwentyItems(@Param("n") Integer n);
}
