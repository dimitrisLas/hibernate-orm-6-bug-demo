package com.example.hibernate_bug_demo.service;

import com.example.hibernate_bug_demo.dto.AddressInformationDTO;
import com.example.hibernate_bug_demo.dto.FinancialInformationDTO;
import com.example.hibernate_bug_demo.dto.MainDTO;
import com.example.hibernate_bug_demo.entity.MainEntity;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @PersistenceContext
    private EntityManager entityManager;

    private CriteriaBuilder cb;

    @PostConstruct
    private void init() {
        this.cb = entityManager.getCriteriaBuilder();
    }

    // With nested cb.construct
    public List<MainDTO> fetchMainDTOSortedBy(String value) {
        CriteriaQuery<MainDTO> query = cb.createQuery(MainDTO.class);
        Root<MainEntity> root = query.from(MainEntity.class);

        query
                .select(
                        cb.construct(
                                MainDTO.class,
                                root.get("id").alias("id"),
                                cb.construct(
                                        FinancialInformationDTO.class,
                                        root.get("paid").alias("paid"),
                                        root.get("received").alias("received")
                                ),
                                cb.construct(
                                        AddressInformationDTO.class,
                                        root.get("address").alias("address"),
                                        root.get("number").alias("number")
                                ),
                                root.get("alphaNumber").alias("alphaNumber"),
                                root.get("betaNumber").alias("betaNumber"),
                                root.get("gammaNumber").alias("gammaNumber")
                        )
                )
                .orderBy(
                        cb.asc(
                                root.get(value)
                        )
                );

        return entityManager
                .createQuery(query)
                .getResultList();
    }

    // Without nested cb.construct
    public List<MainEntity> fetchMainEntitySortedBy(String value) {
        CriteriaQuery<MainEntity> query = cb.createQuery(MainEntity.class);
        Root<MainEntity> root = query.from(MainEntity.class);

        query
                .select(
                        cb.construct(
                                MainEntity.class,
                                root.get("id").alias("id"),
                                root.get("paid").alias("paid"),
                                root.get("received").alias("received"),
                                root.get("address").alias("address"),
                                root.get("number").alias("number"),
                                root.get("alphaNumber").alias("alphaNumber"),
                                root.get("betaNumber").alias("betaNumber"),
                                root.get("gammaNumber").alias("gammaNumber")
                        )
                )
                .orderBy(
                        cb.asc(
                                root.get(value)
                        )
                );

        return entityManager
                .createQuery(query)
                .getResultList();
    }
}
