package com.testProject.Parser.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DataSiteDTO implements Serializable {
    @JsonProperty("data_id")
    private Long id;

    private String word;

    private Integer amount;

    @JsonProperty("website_id")
    private Long websiteId;
}
