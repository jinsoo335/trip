package com.example.trip.domain.post.service;

import com.example.trip.domain.category.CategoryRepository;
import com.example.trip.domain.image.ImageRepository;
import com.example.trip.domain.image.domain.Image;
import com.example.trip.domain.member.MemberRepository;
import com.example.trip.domain.member.domain.Member;
import com.example.trip.domain.member.location.LocationRepository;
import com.example.trip.domain.member.location.domain.Location;
import com.example.trip.domain.post.PostRepository;
import com.example.trip.domain.post.domain.CreatePostRequest;
import com.example.trip.domain.post.domain.Post;
import com.example.trip.domain.post.domain.PostCategory;
import com.example.trip.domain.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final ImageRepository imageRepository;

    public Long createPost(Long userId, CreatePostRequest request) {
        Member member = memberRepository.findById(userId).orElseThrow(RuntimeException::new);
        List<Location> locationList = locationRepository.findAllById(request.getLocationList());
        List<Image> imageList = imageRepository.findAllById(request.getImageList());
        List<PostCategory> postCategoryList = categoryRepository.findAllById(request.getCategoryList()).stream()
                .map(PostCategory::new)
                .toList();
        List<Tag> tagList = request.getTagList().stream()
                .map(Tag::new)
                .toList();

        Post post = Post.builder()
                .member(member)
                .title(request.getTitle())
                .content(request.getContent())
                .postCategoryList(postCategoryList)
                .locationList(locationList)
                .imageList(imageList)
                .tagList(tagList)
                .build();

        postRepository.save(post);

        return post.getId();
    }

}