package duclm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duclm.domain.Field;
import duclm.domain.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

	@Query(value = "SELECT * FROM jobs j WHERE j.user_id = :userid", nativeQuery = true)
	List<Job> findAllJobByUserParam(@Param("userid") Long userid);
}
