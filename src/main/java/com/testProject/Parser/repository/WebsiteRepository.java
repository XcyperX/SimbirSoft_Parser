package com.testProject.Parser.repository;

import com.testProject.Parser.model.Websites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebsiteRepository extends JpaRepository<Websites, Long> {
}
