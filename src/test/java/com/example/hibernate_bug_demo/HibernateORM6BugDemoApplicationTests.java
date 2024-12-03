package com.example.hibernate_bug_demo;

import com.example.hibernate_bug_demo.dto.ViewDTO;
import com.example.hibernate_bug_demo.service.MainEntityService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class HibernateORM6BugDemoApplicationTests {

	@Autowired
	private MainEntityService mainEntityService;

	@Test
	void TestFetchMainEntityDataIsSortedByValue1() {
		List<ViewDTO> result = mainEntityService.fetchMainEntityDataSortedBy("value1");

		assertNotNull(result, "Result should not be null");

		for (int i=1; i<result.size();i++){
			Integer previousValue1 = result.get(i-1).getValue1();
			Integer currentValue1 = result.get(i).getValue1();
			assertTrue(previousValue1 <= currentValue1);
		}
	}
}
