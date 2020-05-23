package com.sectorseven.repository.admin;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.Mentor;

@Repository
public interface MentorRepository extends JpaSpecificationExecutor<Mentor>, JpaRepository<Mentor, Serializable> {

	List<Mentor> findByEmailOrMobile(String email, Long mobile);

	@Query(value="select * from mentor where id not in (?3) and (email = ?1 or mobile= ?2)", nativeQuery = true)
	List<Mentor> findByEmailOrMobileAndIdNotIn(String email, long mobile, Long id);

	List<Mentor> findByStatus(Status active);
	

	@Query(value="select * from mentor m where m.occupation_category IN (:interests1) LIMIT :limit OFFSET :offset ", nativeQuery = true)
	List<Mentor> findAllByInterests(@Param("interests1") List<Long> interests1,@Param("limit") Integer limit,@Param("offset") Integer offset);

	Mentor findByUserName(String username);
}
