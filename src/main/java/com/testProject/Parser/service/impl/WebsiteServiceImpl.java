package com.testProject.Parser.service.impl;

import com.testProject.Parser.DTO.DataSiteDTO;
import com.testProject.Parser.DTO.WebsiteDTO;
import com.testProject.Parser.model.Websites;
import com.testProject.Parser.repository.WebsiteRepository;
import com.testProject.Parser.service.WebsiteService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WebsiteServiceImpl implements WebsiteService {
    private final WebsiteRepository formRepository;
    private final MapperFacade mapperFacade;

    @Override
    public WebsiteDTO getById(Long id) {
        if (formRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такого сайта!");
        }
        return mapperFacade.map(formRepository.findById(id).get(), WebsiteDTO.class);
    }

    @Override
    public WebsiteDTO save(WebsiteDTO websiteDTO) {
        return null;
    }

    @Override
    public WebsiteDTO update(WebsiteDTO websiteDTO) {
        if (formRepository.findById(websiteDTO.getId()).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такой анкеты!");
        }
        Websites websites = formRepository.save(mapperFacade.map(websiteDTO, Websites.class));
        return mapperFacade.map(websites, WebsiteDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (formRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такого сайта!");
        }
        formRepository.deleteById(id);
    }

    @Override
    public List<WebsiteDTO> findAll() {
        return mapperFacade.mapAsList(formRepository.findAll(), WebsiteDTO.class);
    }


    @Override
    public WebsiteDTO saveSite(String url) throws IOException {
        WebsiteDTO websiteDTO = new WebsiteDTO();
        websiteDTO.setUrl(url);
        if (!url.regionMatches(true,0, "http", 0, 4)) {
            url = "https://" + url;
        }
        Document document = Jsoup.connect(url).get();
        String word = document.body().text();
        List<DataSiteDTO> dataSiteDTOS = new ArrayList<>();
        List<String> list = Arrays.asList(word.replaceAll("\\pP", "").replaceAll("\n", " ").strip().split(" "));
        System.out.println(list);
        Set<String> setWord = new HashSet<>(list);
        setWord.remove("");
        for (String uniqWord : setWord) {
            DataSiteDTO dataSiteDTO = new DataSiteDTO();
            int i = 0;
            for (String allWord : list) {
                if (uniqWord.equals(allWord)) {
                    i++;
                }
            }
            dataSiteDTO.setWord(uniqWord);
            dataSiteDTO.setAmount(i);
            dataSiteDTOS.add(dataSiteDTO);
        }
        websiteDTO.setDataSiteDTOS(dataSiteDTOS);
        formRepository.save(mapperFacade.map(websiteDTO, Websites.class));
        return websiteDTO;
    }
}
