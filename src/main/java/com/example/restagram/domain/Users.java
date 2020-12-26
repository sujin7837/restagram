package com.example.restagram.domain;

import com.example.restagram.domain.comments.Comments;
import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.tables.LikesTables;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String userName;

    public Users(String userName){
        this.userName = userName;
    }
}
