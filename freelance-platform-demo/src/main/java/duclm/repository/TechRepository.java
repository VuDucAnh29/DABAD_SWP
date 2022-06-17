package duclm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duclm.domain.Field;
import duclm.domain.Tech;
import duclm.domain.User;

@Repository
public interface TechRepository extends JpaRepository<Tech, Long> {
	Tech findByName(String name);
	List<Tech> findByNameContaining(String name);
}
