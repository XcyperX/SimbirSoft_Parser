package com.testProject.Parser.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "data_sites")
@Data
public class DataSite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String word;

    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "site_id", referencedColumnName = "id")
    private Websites websites;
}
