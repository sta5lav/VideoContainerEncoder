package com.example.controller;

import com.example.dto.SizeScreen;
import com.example.entity.Container;
import com.example.service.ContainerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@Slf4j
public class ContainerController {

    ContainerService containerService;

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Container getInfo(@PathVariable UUID id) {
        return containerService.getInfo(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UUID postFile(@RequestParam MultipartFile videoContainer) throws IOException {
        return containerService.addVideoContainer(videoContainer);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String patchFile(@PathVariable UUID id,
                            @Valid @RequestBody SizeScreen sizeScreen) {
        return containerService.editVideoContainer(id, sizeScreen);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteFile(@PathVariable UUID id) throws IOException {
        return containerService.deleteVideoContainer(id);
    }
}
