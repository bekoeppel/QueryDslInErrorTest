package ch.locatee.test.querydslerror.locatee;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.mysema.query.jpa.JPQLTemplates;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;

/**
 * Unit test for simple App.
 */
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class })
@ContextConfiguration(classes = { JPAConfiguration.class })
@Transactional
public class AppTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    EntityManager entityManager;
    
    @Test
	public void testSomething() {
		
		QLocation location = QLocation.location;

		JPAQuery query = new JPAQuery(entityManager, JPQLTemplates.DEFAULT);

		/* fails with:
java.lang.IllegalArgumentException: org.hibernate.hql.internal.ast.QuerySyntaxException: unexpected token: , near line 5, column 53 [select location
from ch.locatee.test.querydslerror.locatee.Location location
where exists (select location_locationAttributes_438ee
from location.locationAttributes as location_locationAttributes_438ee
where location_locationAttributes_438ee.id in :x1_0_, :x1_1_, :x1_2_)]
		 */
		query.from(location)
				.where(location.locationAttributes.any().id.in(1, 2, 3));
		
//		// works:
//		query.from(location)
//				.where(location.locationAttributes.any().id.in(1));
		
		query.list(location);
	}
}
