package com.testProject.Parser.service;


import com.testProject.Parser.base.CRUDService;
import com.testProject.Parser.model.DataSite;

import java.util.List;

public interface DataSiteService extends CRUDService<DataSite, Long> {
    List<DataSite> findAll();
}
