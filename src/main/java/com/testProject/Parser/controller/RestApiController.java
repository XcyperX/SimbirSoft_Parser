package com.testProject.Parser.controller;

import com.testProject.Parser.DTO.WebsiteDTO;
import com.testProject.Parser.model.Websites;
import com.testProject.Parser.service.WebsiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestApiController {
    private final WebsiteService websiteService;

    @PostMapping("/parser/")
    public WebsiteDTO createWebsite(@RequestBody @Valid WebsiteDTO websiteDTO) throws IOException {
        return websiteService.saveSite(websiteDTO.getUrl());
    }
}
