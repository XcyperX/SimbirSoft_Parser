package com.testProject.Parser.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "websites")
@Data
public class Websites implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String url;

    @OneToMany(mappedBy = "websites", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DataSite> dataSites = new ArrayList<>();

    public Websites() {
    }

    public Websites(Long id) {
        this.id = id;
    }
}
