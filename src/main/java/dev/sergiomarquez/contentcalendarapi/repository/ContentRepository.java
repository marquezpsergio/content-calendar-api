package dev.sergiomarquez.contentcalendarapi.repository;

import dev.sergiomarquez.contentcalendarapi.model.Content;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends ListCrudRepository<Content, Integer> {


    List<Content> findAllByTitleContains(String title);

    @Query("""
            SELECT * FROM content
            WHERE status = :status
            """)
    List<Content> findAllByStatus(@Param("status") String status);
}
