/**
 * 
 */
package wolf_j.com.github.web.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wolf-J
 *
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List findByLastName(String lastName);

	@Query("SELECT e FROM Employee e WHERE e.salary = :salary")

	public List findBySalary(@Param("salary") int salary);
}
