package com.imagedb.imageStorage.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.imagedb.imageStorage.entity.ImageData;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {


    Optional<ImageData> findByName(String fileName);
}
