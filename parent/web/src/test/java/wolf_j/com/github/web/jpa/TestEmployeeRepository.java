/**
 * 
 */
package wolf_j.com.github.web.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author wolf-J
 *
 */

@ExtendWith(value = { SpringExtension.class })
@ContextConfiguration(locations = { "classpath:spring/spring_jpa.xml" })
class TestEmployeeRepository {

	@Autowired
	EmployeeRepository repository;

	@Test
	void findBySalarySucessWhenUseJpa() {
		Optional<Employee> employee = repository.findById(1l);
		assertEquals(null, employee);
	}

}
