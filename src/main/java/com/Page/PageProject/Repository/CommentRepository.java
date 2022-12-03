package com.Page.PageProject.Repository;

import com.Page.PageProject.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value =
            "SELECT * " +
                    "FROM comment " +
                    "WHERE pageentity_id = :pageentityId",
            nativeQuery = true)
    List<Comment> findByPageentityId(@Param("pageentityId") Long pageentityId);

}