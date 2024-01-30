package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.model.BoardDto;
import com.example.simpleboard.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardConverter {

    private final PostConverter postConverter;

    // boardEntity가 들어오면 BoardDto로 변환
    public BoardDto toDto(BoardEntity boardEntity) {

        var postList = boardEntity.getPostList()
                .stream()
                .map(postConverter::toDto)
                .collect(Collectors.toList());

        /*
        - 위 람다식의 원본
        var postList = boardEntity.getPostList()
                .stream()
                .map(postEntity -> {
                    return postConverter.toDto(postEntity);
                }).collect(Collectors.toList());
        */

        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                .postList(postList)
                .build();
    }
}
