package com.example.trip.domain.category.domain;

import com.example.trip.domain.BaseEntity;
import com.example.trip.domain.post.domain.PostCategory;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 *  카테고리 엔티티
 *
 *  지역, 계절 등의 큰 분류를 나누기 위한 엔티티
 */

@Entity
@Getter
public class Category extends BaseEntity {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;    // 식별자

    @Column(nullable = false)
    private String name;    // 카테고리 분류 이름

    @OneToMany(mappedBy = "category")
    private List<PostCategory> postCategoryList = new ArrayList<>();    // 카테고리 모음에 대한 리스트
}
