package com.example.restagram.web.dto;

import com.example.restagram.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostsApiController {
    private final PostsService postsService;

//    @PostMapping("")
//    public Long save(PostsSaveRequestDto requestDto){
//        return postsService.save(requestDto);
//    }

}
