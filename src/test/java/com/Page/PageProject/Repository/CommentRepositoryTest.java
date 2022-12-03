package com.Page.PageProject.Repository;

import com.Page.PageProject.Entity.Comment;
import com.Page.PageProject.Entity.PageEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;


    @Test
    void findByPageentityId() {

        Long pageentityId =4L;

        List<Comment> comments = commentRepository.findByPageentityId(pageentityId);
        PageEntity pageentity = new PageEntity(4L, "4번게시글", "댓글 확인");
        Comment a = new Comment(1L, pageentity, "운영자1", "운영자1댓글");
        Comment b = new Comment(2L, pageentity, "운영자2", "운영자2댓글");
        Comment c = new Comment(3L, pageentity, "운영자3", "운영자3댓글");
        List<Comment> expected = Arrays.asList(a, b, c);


        assertEquals(expected.toString(), comments.toString(), "4번 게시글의 댓글을 출력");
    }
}