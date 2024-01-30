package com.example.simpleboard.board.db;

import com.example.simpleboard.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardName;

    private String status;

    // 1(board) : N(post)의 관계 설정
    @OneToMany(
            mappedBy = "boardEntity"
    )
    @Builder.Default
    @Where(clause = "status = 'REGISTERED'")
    @org.hibernate.annotations.OrderBy(clause = "id desc")
    private List<PostEntity> postList = List.of();

}
