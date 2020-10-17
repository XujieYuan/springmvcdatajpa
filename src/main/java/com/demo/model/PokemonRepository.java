package com.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import static org.springframework.data.repository.query.parser.Part.Type.LIKE;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    /*
    As with Spring Data JPA, you don't have to write any DAO code.
    Just declare an interface that extends the CrudRepository interface
    which defines CRUD methods like save(), findAll(), findById(),deleteById(), etc.
    At runtime, Spring Data JPA automatically generates the implementation code.

    NOTE: that we must specify the type of the model class and type of the primary key field when extending the JPA/CrudRepository interface
    EX: CrudRepository<Pokemon, Long>
    */

    //the search()method is just an abstract method annotated with the @Query annotation. The search query is JPA query.
    @Query(value = "SELECT p FROM Pokemon p WHERE " +
            "lower(p.pokename) LIKE lower(CONCAT('%', :keyword, '%')) OR " +
            "lower(p.poketype) LIKE lower(CONCAT('%', :keyword, '%')) OR " +
            "lower(p.pokespeed) LIKE lower(CONCAT('%', :keyword, '%'))")
    public List<Pokemon> search(@Param("keyword") String keyword);
}