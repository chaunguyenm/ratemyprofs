package com.example.ratemyprofs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ratemyprofs.jpa.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
