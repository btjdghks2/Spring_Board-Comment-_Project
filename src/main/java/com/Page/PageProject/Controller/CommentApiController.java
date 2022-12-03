package com.Page.PageProject.Controller;

import com.Page.PageProject.dto.CommentDto;
import com.Page.PageProject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/api/detail/{pageentityId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long pageentityId) {
        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(pageentityId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/api/post/{pageentityId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long pageentityId,
                                             @RequestBody CommentDto dto) {
        // 서비스에게 위임
        CommentDto createdDto = commentService.create(pageentityId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto) {
        // 서비스에게 위임
        CommentDto updatedDto = commentService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        // 서비스에게 위임
        CommentDto deletedDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }



}
