package com.Page.PageProject.dto;


import com.Page.PageProject.Entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentDto {
    private Long id;
    @JsonProperty("pageentity_id")
    private Long pageentityId;
    private String nickname;
    private String body;


    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getPageentity().getId(),
                comment.getNickname(),
                comment.getBody());


    }
}
