package com.example.service;


import com.example.dto.SizeScreen;
import com.example.entity.Container;
import com.example.repository.ContainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Slf4j
public class ContainerService {

    @Value("/path")
    String videoDir;

    @Autowired
    ContainerRepository containerRepository;

    public UUID addVideoContainer(MultipartFile videoContainer) throws IOException {
        Path filePath = Path.of(videoDir, "hello" + "." + StringUtils.getFilenameExtension(videoContainer.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream in = videoContainer.getInputStream();
             OutputStream out = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(in, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(out, 1024)) {
            bis.transferTo(bos);
        }
        byte[] data = videoContainer.getBytes();
        Container container = new Container();
        container.setData(data);
        container.setFilePath(filePath.toString());
        container.setFileSize(data.length);
        container.setMediaType(videoContainer.getContentType());
        containerRepository.save(container);
        return container.getId();
    }

    public String deleteVideoContainer(UUID id) throws IOException {
        Path filePath = Path.of(videoDir, id + ".");
        Files.delete(filePath);
        return "success";
    }

    public String editVideoContainer(UUID id, SizeScreen sizeScreen) {
        return "success";
    }

    public Container getInfo(UUID id) {
        Path filePath = Path.of(videoDir, id + ".");
        return new Container();
    }
}
