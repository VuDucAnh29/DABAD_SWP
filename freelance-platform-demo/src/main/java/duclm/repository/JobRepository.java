package duclm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duclm.domain.Field;
import duclm.domain.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
	
	//Tim job khong phai status 0 cua 1 userid
	@Query(value = "SELECT * FROM jobs j WHERE j.user_id = :userid AND j.status!=0", nativeQuery = true)
	List<Job> findAllJobByUserParam(@Param("userid") Long userid);
	
	//Tim tat ca job theo status
	List<Job> findAllByStatus(int status);
	
	//Tim tat ca job co status 1 va containing title: alljobs
	@Query(value = "SELECT * FROM jobs j WHERE j.title LIKE %:title% AND j.status=1",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.title LIKE %:title% AND j.status=1",
			nativeQuery = true)
	Page<Job> findAllJobByTitleHiring(@Param("title") String title, Pageable pageable);
	
	//Tim tat ca job co status 1 theo field va containing title: alljobs
	@Query(value = "SELECT * FROM jobs j WHERE j.title LIKE %:title% AND j.status=1 AND j.field_id=:fieldid",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.title LIKE %:title% AND j.status=1 AND j.field_id=:fieldid",
			nativeQuery = true)
	Page<Job> findAllJobByTitleAndFieldHiring(@Param("title") String title,@Param("fieldid") Long fieldid, Pageable pageable);
	
	//Tim tat ca job co status 1: alljobs
	@Query(value = "SELECT * FROM jobs j WHERE j.status=1",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.status = 1",
			nativeQuery = true)
	Page<Job> findAllJobHiring(Pageable pageable);
	
	//Tim tat ca job status khac 0 cua 1 userid containing title:myjobs
	@Query(value = "SELECT * FROM jobs j WHERE j.title LIKE %:title% AND j.status!=0 AND j.user_id = :userid",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.title LIKE %:title% AND j.status!=0 AND j.user_id = :userid",
			nativeQuery = true)
	Page<Job> findAllJobByTitleUserParam(@Param("userid") Long userid, @Param("title") String title, Pageable pageable);
	
	//Tim tat ca job status khac 0 cua 1 userid containing title:myjobs
	@Query(value = "SELECT * FROM jobs j WHERE j.title LIKE %:title% AND j.status!=0 AND j.user_id = :userid AND j.field_id=:fieldid",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.title LIKE %:title% AND j.status!=0 AND j.user_id = :userid AND j.field_id=:fieldid",
			nativeQuery = true)
	Page<Job> findAllJobByTitleAndFieldUserParam(@Param("userid") Long userid, @Param("title") String title,@Param("fieldid") Long fieldid, Pageable pageable);
	
	//Tim tat ca job status khac 0 cua 1 userid
	@Query(value = "SELECT * FROM jobs j WHERE j.user_id = :userid AND j.status!=0",
			countQuery = "SELECT count(*) FROM jobs j WHERE j.user_id = :userid AND j.status!=0",
			nativeQuery = true)
	Page<Job> findAllJobUserParam(@Param("userid") Long userid,Pageable pageable);
}
