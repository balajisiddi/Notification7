package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.common.StudentQueries;

@Repository
public interface AskMentorRepository extends JpaSpecificationExecutor<StudentQueries>, JpaRepository<StudentQueries, Serializable>{

	List<StudentQueries> findByStatus(Status inactive);
	
	List<StudentQueries> findByMentor(Mentor mentor);

	StudentQueries findById(long id);

    @Query(value="SELECT * FROM student_queries  WHERE  mentor=?1 AND  student=?2 ORDER BY date_created DESC",nativeQuery=true)
	List<StudentQueries> findAllByMentorAndStudent(Long mentor, Long student);




}
