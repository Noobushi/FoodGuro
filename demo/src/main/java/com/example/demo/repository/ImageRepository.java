package com.example.demo.repository;

import com.example.demo.entity.ImageDataBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageDataBase, Integer> {
    ImageDataBase findImageByName(String imageName);
}
