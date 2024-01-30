package com.example.simpleboard.board.model;

import com.example.simpleboard.post.PostDto;
import com.example.simpleboard.post.db.PostEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardDto {

    private Long id;

    private String boardName;

    private String status;

    // 1(board) : N(post)의 관계 설정
    @Builder.Default
    private List<PostDto> postList = List.of();
}
