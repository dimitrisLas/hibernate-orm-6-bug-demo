package com.example.hibernate_bug_demo.service;

import com.example.hibernate_bug_demo.dto.AddressInformation;
import com.example.hibernate_bug_demo.dto.FinancialInformation;
import com.example.hibernate_bug_demo.dto.ViewDTO;
import com.example.hibernate_bug_demo.entity.MainEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainEntityService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ViewDTO> fetchMainEntityDataSortedBy(String value){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ViewDTO> query = cb.createQuery(ViewDTO.class);

        Root<MainEntity> root = query.from(MainEntity.class);

        query.distinct(true);

        query.select(
                cb.construct(
                        ViewDTO.class,
                        root.get("id"),
                        cb.construct(
                                FinancialInformation.class,
                                root.get("paid"),
                                root.get("received")
                        ),
                        cb.construct(
                                AddressInformation.class,
                                root.get("address"),
                                root.get("number")
                        ),
                        root.get("value1"),
                        root.get("value2"),
                        root.get("value3")
                )
        );

        query.orderBy(cb.asc(root.get(value)));

        return entityManager.createQuery(query).getResultList();
    }
}
