package com.imagedb.imageStorage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imagedb.imageStorage.entity.ImageData;
import com.imagedb.imageStorage.repo.StorageRepository;
import com.imagedb.imageStorage.util.ImageUtils;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    private final StorageRepository repository;

    @Autowired
    public StorageService(StorageRepository repository) {
        this.repository = repository;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(
            new ImageData(file.getOriginalFilename(), file.getContentType(), ImageUtils.compressImage(file.getBytes()))
        );
        if (imageData != null) {
            return "File uploaded successfully: " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        if (dbImageData.isPresent()) {
            return ImageUtils.decompressImage(dbImageData.get().getImageData());
        }
        return null;
    }
}
