package com.Page.PageProject.service;

import com.Page.PageProject.Entity.Comment;
import com.Page.PageProject.Entity.PageEntity;
import com.Page.PageProject.Repository.CommentRepository;
import com.Page.PageProject.Repository.PageRepository;
import com.Page.PageProject.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PageRepository pageRepository;

    public List<CommentDto> comments(Long pageentityId) {
        // 조회: 댓글 목록
        List<Comment> comments = commentRepository.findByPageentityId(pageentityId);
        // 변환: 엔티티 -> DTO
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }
        // 반환
        return dtos;
    }

    @Transactional
    public CommentDto create(Long pageentityId, CommentDto dto) {
        PageEntity pageentity = pageRepository.findById(pageentityId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패,게시글을 못찾았슴메~"));

        Comment comment = Comment.createComment(dto, pageentity);
        Comment created = commentRepository.save(comment);
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {

        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        // 댓글 수정
        target.patch(dto);
        // DB로 갱신
        Comment updated = commentRepository.save(target);
        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long id) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));
        // 댓글 삭제
        commentRepository.delete(target);
        // 삭제 댓글을 DTO로 반환
        return CommentDto.createCommentDto(target);
    }
}
