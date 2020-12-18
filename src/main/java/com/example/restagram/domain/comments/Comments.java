package com.example.restagram.domain.comments;

import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.Users;
import com.example.restagram.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comments extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    @Id
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_comments_posts"))
    private Posts post;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_comments_users"))
    private Users author;
}
