package com.example.restagram.domain.posts;

import com.example.restagram.domain.BaseTimeEntity;
import com.example.restagram.domain.Users;
import com.example.restagram.domain.comments.Comments;
import com.example.restagram.domain.tags.Tags;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Posts extends BaseTimeEntity {

    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_post_user"))
    private Users author;

    @Column(length = 1024)
    private String content;

    //태그 관계 테이블
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "posts_tags_table",
    joinColumns = @JoinColumn(name = "post_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tags> tags = new ArrayList<Tags>();

    //좋아요 관계 테이블
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "likes",
    joinColumns = @JoinColumn(name = "post_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Users> users = new ArrayList<Users>();

    @OrderBy("createdDate asc ")
    @OneToMany(mappedBy = "post")
    private List<Comments> comments;

    @Builder
    public Posts(Users author, String content){
        this.author = author;
        this.content = content;
    }
}
