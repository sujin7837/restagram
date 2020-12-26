package com.example.restagram.domain.tags;

import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.tables.TagsTables;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Tags {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String tagName;

    @OneToMany(mappedBy = "tag")
    private List<TagsTables> posts = new ArrayList<>();

    @Builder
    public Tags(String tagName){
        this.tagName = tagName;
    }


}
