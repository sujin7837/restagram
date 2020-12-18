package com.example.restagram.domain.tags;

import com.example.restagram.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Tags {

    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String tagName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private List<Posts> posts;
}
