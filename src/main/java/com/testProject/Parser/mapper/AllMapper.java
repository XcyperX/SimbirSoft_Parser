package com.testProject.Parser.mapper;

import com.testProject.Parser.DTO.DataSiteDTO;
import com.testProject.Parser.DTO.WebsiteDTO;
import com.testProject.Parser.model.DataSite;
import com.testProject.Parser.model.Websites;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllMapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory) {
        factory.classMap(Websites.class, WebsiteDTO.class)
                .byDefault()
                .customize(new CustomMapper<Websites, WebsiteDTO>() {
                    @Override
                    public void mapAtoB(Websites websites, WebsiteDTO websiteDTO, MappingContext context) {
                        if (websites.getDataSites() != null) {
                            List<DataSite> dataSites = new ArrayList<>();
                            websites.getDataSites().forEach(dataSite -> {
                                dataSite.setWebsites(new Websites(websites.getId()));
                                dataSites.add(dataSite);
                            });
                            websiteDTO.setDataSiteDTOS(mapperFacade.mapAsList(dataSites, DataSiteDTO.class));
                        }
                        super.mapAtoB(websites, websiteDTO, context);
                    }

                    @Override
                    public void mapBtoA(WebsiteDTO websiteDTO, Websites websites, MappingContext context) {
                        if (websiteDTO.getDataSiteDTOS() != null) {
                            List<DataSite> dataSites = new ArrayList<>();
                            websiteDTO.getDataSiteDTOS().forEach(dataSiteDTO -> {
                                DataSite dataSite = new DataSite();
                                dataSite.setWord(dataSiteDTO.getWord());
                                dataSite.setAmount(dataSiteDTO.getAmount());
                                dataSite.setWebsites(websites);
                                dataSites.add(dataSite);
                            });
                            websites.setDataSites(dataSites);
                        }
                        super.mapBtoA(websiteDTO, websites, context);
                    }
                })
                .register();

        factory.classMap(DataSite.class, DataSiteDTO.class)
                .field("websites.id", "websiteId")
                .byDefault()
                .register();
    }
}