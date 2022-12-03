package com.Page.PageProject.service;


import com.Page.PageProject.Entity.PageEntity;
import com.Page.PageProject.Repository.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestApiService {

    private final PageRepository pageRepository;

    @Transactional
    public PageEntity saved(PageEntity pageEntity) {
        return pageRepository.save(pageEntity);
    }

    @Transactional(readOnly = true)
    public PageEntity DetailPage(Long id) {
        return pageRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id를 확인하세요"));
    }

    @Transactional(readOnly = true)
    public List<PageEntity> homePage() {
        return  pageRepository.findAll();
    }
    @Transactional
    public PageEntity update(Long id, PageEntity pageEntity) {
        PageEntity pageentity = pageRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("id를 확인해주세요"));
        pageentity.setTitle(pageEntity.getTitle());
        pageentity.setContent(pageEntity.getContent());

        return null;
    }
    @Transactional
    public String delete(Long id) {
        pageRepository.deleteById(id);
        return "ok";
    }

}
