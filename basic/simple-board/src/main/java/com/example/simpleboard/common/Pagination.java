package com.example.simpleboard.common;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {

    // 현재 페이지
    private Integer page;

    // 총 사이즈가 몇개가 들어있는지
    private Integer size;

    // 현재 가지고 있는 엘리먼트가 몇개가 있는지
    private Integer currentElements;

    // 토탈 페이지
    private Integer totalPage;

    // 전체 엘리먼트가 몇 개가 있는지
    private Long totalElements;
}
