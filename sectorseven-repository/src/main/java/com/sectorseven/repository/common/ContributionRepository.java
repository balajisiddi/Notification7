package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.common.Contribution;
import com.sectorseven.model.common.ContributionDocs;

@Repository
public interface ContributionRepository extends JpaSpecificationExecutor<Contribution>, JpaRepository<Contribution, Serializable>{

	@Query(value="SELECT * FROM contribution c where c.acceptance=?1",nativeQuery=true)
	List<Contribution> findContributionByAcceptance(Integer submitted);

	Contribution findContributionById(Long id);

	@Query(value="SELECT * FROM contribution c WHERE c.authority=?2 AND c.acceptance=?1  GROUP BY c.user  ORDER BY c.date_created DESC",nativeQuery = true)
	List<Contribution> findContributionByAcceptanceAndAuthority(Integer accepted,Long authority);

	Contribution findAllContributionByAuthorityAndUser(Authority authority, Long userId);
	
	@Query(value="select count(distinct user) from contribution where acceptance=1",nativeQuery = true)
	Long findAllContributors();
	
	@Query(value="select count(distinct user) from contribution where authority=2 and acceptance=1",nativeQuery = true)
	Long findAllStudentContributors();
	
	@Query(value="select count(distinct user) from contribution where authority=8 and acceptance=1",nativeQuery = true)
	Long findAllMentorContributors();

	@Query(value="select count(distinct user) from contribution where authority=7 and acceptance=1",nativeQuery = true)
	Long findAllschoolTeacherContributors();
	
	@Query(value="select count(distinct user) from contribution where authority=3 and acceptance=1",nativeQuery = true)
	Long findAllParentContributors();
}
