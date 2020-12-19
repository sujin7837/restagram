package com.example.restagram.web.dto;

import com.example.restagram.domain.Users;
import com.example.restagram.domain.posts.Posts;
import com.example.restagram.domain.tags.Tags;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostsSaveRequestDto {
    private Users author;
    private String content;
    private String tagName;

    @Builder
    public PostsSaveRequestDto(Users author, String content, String tagName){
        this.author = author;
        this.content = content;
        this.tagName = tagName;
    }
    public Posts toPostsEntity(){
        return Posts.builder()
                .author(author)
                .content(content)
                .build();
    }

    public Tags toTagsEntity(){
        return Tags.builder()
                .tagName(tagName)
                .build();
    }
}
