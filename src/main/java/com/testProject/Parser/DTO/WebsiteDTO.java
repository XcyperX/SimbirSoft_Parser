package com.testProject.Parser.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class WebsiteDTO implements Serializable {
    @JsonProperty("website_id")
    private Long id;

    private String url;

    @JsonProperty("data_list")
    private List<DataSiteDTO> dataSiteDTOS;
}
