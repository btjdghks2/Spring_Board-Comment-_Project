package com.Page.PageProject.dto;

import com.Page.PageProject.Entity.PageEntity;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class dtoForm {

    private Long id;
    private String title;
    private String content;

    public PageEntity toEntity(dtoForm dtoform) {

        return new PageEntity(id,title,content);
    }
}
