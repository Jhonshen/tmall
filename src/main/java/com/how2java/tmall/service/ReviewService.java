package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Review;

import java.util.List;

public interface ReviewService {
    void add(Review r);
    void delete(int id);
    void update(Review r);
    List<Review> list(int pid);
    int getCount(int pid);
}
