package com.sectorseven.repository.admin;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.common.MentorFollowers;
import com.sectorseven.model.school.Student;

@Repository
public interface MentorFollowersRepository extends JpaSpecificationExecutor<MentorFollowers>, JpaRepository<MentorFollowers, Serializable>{

	MentorFollowers findById(long id);
	
	@Query(value="SELECT * FROM mentor_followers WHERE acceptance=?1 AND student=?2 ORDER BY date_created DESC LIMIT ?3 OFFSET ?4",nativeQuery = true)
	List<MentorFollowers> getAllFollowedMentorsByAcceptanceAndStudent(Integer accepted,Long student,Integer limit,Integer offset);
	
	List<MentorFollowers> getAllFollowedStudentsByAcceptance(AdminAcceptance accepted);

	@Query(value="SELECT * FROM mentor_followers WHERE acceptance=?1 AND mentor=?2 ORDER BY date_created DESC LIMIT ?3 OFFSET ?4",nativeQuery = true)
	List<MentorFollowers> getAllFollowedStudentsByAcceptanceAndMentor(Integer accepted, Long mentor,Integer limit,Integer offset);

	List<MentorFollowers> findMentorFollowersByMentorAndStudent(Mentor mentor,Student student);



}
