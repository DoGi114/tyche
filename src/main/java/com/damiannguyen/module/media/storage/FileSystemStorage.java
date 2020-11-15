package com.damiannguyen.module.media.storage;

import com.damiannguyen.module.media.repository.MediaEntity;
import com.damiannguyen.module.media.repository.MediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class FileSystemStorage {
    @Value("${mediaRootLocation}")
    private String path;
    @Autowired
    private MediaRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemStorage.class);

    public void store(MultipartFile file){
        try{
            LOGGER.info("File {} uploading", file.getOriginalFilename());
            Path destFile = Paths.get(path)
                    .resolve(Paths.get(Objects.requireNonNull(file.getOriginalFilename())))
                    .normalize()
                    .toAbsolutePath();
            try(InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destFile, StandardCopyOption.REPLACE_EXISTING);
                destFile.toFile().mkdirs();
            }

            LOGGER.info("File {} saved on disc", file.getOriginalFilename());
            repository.saveAndFlush(
                    new MediaEntity(file.getOriginalFilename())
            );
            LOGGER.info("File {} metadata saved to database", file.getOriginalFilename());
        }catch (IOException e){
            LOGGER.error("File {} saving failed", file.getOriginalFilename());
            throw new StorageException("Failed to store the file", e);
        }
    }

    public List<MediaEntity> getList(){
        return repository.findAll();
    }
}
