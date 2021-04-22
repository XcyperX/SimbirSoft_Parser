package com.testProject.Parser.service;


import com.testProject.Parser.DTO.DataSiteDTO;
import com.testProject.Parser.DTO.WebsiteDTO;
import com.testProject.Parser.base.CRUDService;

import java.io.IOException;
import java.util.List;

public interface WebsiteService extends CRUDService<WebsiteDTO, Long> {
    List<WebsiteDTO> findAll();
    WebsiteDTO saveSite(String url) throws IOException;
}
