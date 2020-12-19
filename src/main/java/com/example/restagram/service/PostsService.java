package com.example.restagram.service;

import com.example.restagram.domain.posts.PostsRepository;
import com.example.restagram.domain.tags.TagsRepository;
import com.example.restagram.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final TagsRepository tagsRepository;

    public Long save(PostsSaveRequestDto requestDto){
        Long postId = postsRepository.save(requestDto.toPostsEntity()).getId();
        return postId;
    }
}
