package com.example.hibernate_bug_demo;

import com.example.hibernate_bug_demo.dto.MainDTO;
import com.example.hibernate_bug_demo.entity.MainEntity;
import com.example.hibernate_bug_demo.service.MainService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HibernateORM6BugDemoApplicationTests {

    @Autowired
    private MainService mainEntityService;

    // This test case should succeed because there is no nested cb.construct used for the entity.
    @Test
    void fetch_mainEntity_should_be_sorted_by_paid() {
        List<MainEntity> result = mainEntityService.fetchMainEntitySortedBy("paid");

        assertOrdered(result, MainEntity::getPaid);
    }

    // This test case should succeed because there is no cb.construct in front of the paid.
    @Test
    void fetch_mainDTO_should_be_sorted_by_paid() {
        List<MainDTO> result = mainEntityService.fetchMainDTOSortedBy("paid");

        assertOrdered(result, MainDTO::getPaid);
    }

    // This test case should succeed because there is no nested cb.construct used for the entity.
    @Test
    void fetch_mainEntity_should_be_sorted_by_number() {
        List<MainEntity> result = mainEntityService.fetchMainEntitySortedBy("number");

        assertUnordered(result, MainEntity::getAlphaNumber);

        assertOrdered(result, MainEntity::getNumber);
    }

    // This test case should fail because there is a single cb.construct in front of the number.
    // It will sort on the alphaNumber instead of the number. (1 select lower)
    @Test
    void fetch_mainDTO_should_be_sorted_by_number() {
        List<MainDTO> result = mainEntityService.fetchMainDTOSortedBy("number");

        // This will now succeed because it is what is being sorted on
        assertOrdered(result, MainDTO::getAlphaNumber);

        // This will fail because of the bug
        assertOrdered(result, MainDTO::getNumber);
    }

    // This test case should succeed because there is no nested cb.construct used for the entity.
    @Test
    void fetch_mainEntity_should_be_sorted_by_alphaNumber() {
        List<MainEntity> result = mainEntityService.fetchMainEntitySortedBy("alphaNumber");

        assertOrdered(result, MainEntity::getAlphaNumber);
        assertUnordered(result, MainEntity::getGammaNumber);
    }

    // This test case should fail because there are two cb.construct in front of the alphaNumber.
    // It will sort on the gammaNumber instead of the number. (2 selects lower)
    @Test
    void fetch_mainDTO_should_be_sorted_by_alphaNumber() {
        List<MainDTO> result = mainEntityService.fetchMainDTOSortedBy("alphaNumber");

        // This will now succeed because it is what is being sorted on
        assertOrdered(result, MainDTO::getGammaNumber);

        // This will fail because of the bug
        assertOrdered(result, MainDTO::getAlphaNumber);
    }

    private <T, R extends Comparable<R>> void assertUnordered(List<T> list, Function<T, R> keyExtractor) {
        var isOrdered = isOrdered(list, keyExtractor);
        assertFalse(isOrdered, "List was fully ordered: " + list.stream().map(keyExtractor).toList());
    }

    private <T, R extends Comparable<R>> void assertOrdered(List<T> list, Function<T, R> keyExtractor) {
        var isOrdered = isOrdered(list, keyExtractor);
        assertTrue(isOrdered, "List is not fully ordered: " + list.stream().map(keyExtractor).toList());
    }

    private <T, R extends Comparable<R>> Boolean isOrdered(List<T> list, Function<T, R> keyExtractor) {
        return IntStream.range(1, list.size())
                .allMatch(index -> {
                    R previous = keyExtractor.apply(list.get(index - 1));
                    R current = keyExtractor.apply(list.get(index));
                    return previous.compareTo(current) <= 0;
                });
    }
}
