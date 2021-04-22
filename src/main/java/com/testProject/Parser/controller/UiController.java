package com.testProject.Parser.controller;

import com.testProject.Parser.report.PDFGenerator;
import com.testProject.Parser.service.WebsiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UiController {
    private final WebsiteService websiteService;

    @GetMapping("/")
    public String listForms(Model model) throws IOException {
        model.addAttribute("websites", websiteService.findAll());
        return "mainPage";
    }

    @GetMapping(value = "/pdf/request/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> chequeRequestReport(@PathVariable("id") Long id){
        PDFGenerator pdfGenerator = new PDFGenerator();
        ByteArrayInputStream bis = pdfGenerator.PDFReport(websiteService.getById(id));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Отчет.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
