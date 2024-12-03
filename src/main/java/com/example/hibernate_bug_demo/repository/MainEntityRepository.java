package com.example.hibernate_bug_demo.repository;

import com.example.hibernate_bug_demo.entity.MainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainEntityRepository extends JpaRepository<MainEntity, Long> {
}
