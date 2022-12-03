package com.Page.PageProject.Repository;

import com.Page.PageProject.Entity.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PageRepository extends CrudRepository<PageEntity, Long> {
    @Override
    List<PageEntity> findAll();
}
