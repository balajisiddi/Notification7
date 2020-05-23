package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.common.ContributionDocs;

@Repository
public interface ContributionDocsRepository extends JpaSpecificationExecutor<ContributionDocs>, JpaRepository<ContributionDocs, Serializable>{

	@Query(value="SELECT * FROM contribution_docs WHERE authority=?1 AND user=?2 AND type=?3 AND acceptance=1 ORDER BY date_created DESC LIMIT ?4 OFFSET ?5",nativeQuery = true)
	List<ContributionDocs> findLatestContributionDetailsByAuthorityAndUserAndType(Long authority, Long userId,String type,Integer limit,Integer offset);
	
	@Query(value="SELECT * FROM contribution_docs WHERE authority=?1 AND user=?2 AND type=?3 AND acceptance=1 ORDER BY date_created LIMIT ?4 OFFSET ?5",nativeQuery = true)
	List<ContributionDocs> findContributionDetailsByAuthorityAndUserAndType(Long authority, Long userId,String type,Integer limit,Integer offset);

	@Query(value="SELECT * FROM contribution_docs WHERE acceptance=?1  ORDER BY id DESC",nativeQuery = true)
	List<ContributionDocs> findContributionDocsByAcceptance(AdminAcceptance submitted);
	
	@Query(value="select count(user) from contribution_docs where authority=2 and acceptance=1",nativeQuery = true)
	Long findAllStudentContributions();
	
	@Query(value="select count(user) from contribution_docs where authority=8 and acceptance=1",nativeQuery = true)
	Long findAllMentorContributions();
	
	@Query(value="select count(user) from contribution_docs where authority=7 and acceptance=1",nativeQuery = true)
	Long findAllschoolTeacherContributions();
	
	@Query(value="select count(user) from contribution_docs where authority=3 and acceptance=1",nativeQuery = true)
	Long findAllParentContributions();
	
	@Query(value="select * from contribution_docs where acceptance=0",nativeQuery = true)
	List<ContributionDocs> findAllsubmittedContributions();
}
