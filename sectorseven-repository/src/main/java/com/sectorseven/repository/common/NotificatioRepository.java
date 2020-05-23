package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.common.Notification;
import com.sectorseven.model.school.Student;

@Repository
public interface NotificatioRepository extends JpaSpecificationExecutor<Notification>, JpaRepository<Notification, Serializable>{

	List<Notification> findAllNotificationsByStudentAndAcceptance(Student student, AdminAcceptance acceptance);

	List<Notification> findAllNotificationsByMentorAndAcceptance(Mentor mentor, AdminAcceptance acceptance);

	@Query(value="SELECT * FROM notification where date_created > ?1 AND (media_id!='' or sub_cat!='') ORDER BY date_created DESC",nativeQuery=true)
	List<Notification> findAllCommonNotificationsByDate(Date loginDate);
	
	@Query(value="SELECT * FROM notification where date_created > ?1 AND (media_id!='' or sub_cat!='' or (user=?4 AND authority=?5)) ORDER BY id DESC LIMIT ?2 OFFSET ?3",nativeQuery=true)
	List<Notification> findAllCommonNotificationsByDateCreated(Date loginDate,Integer limit,Long offset,Long userId,String authority);


	@Query(value="SELECT * FROM notification where date_created > ?3 AND ( media_id!='' or sub_cat!='' or student=?1 or mentor=?2 )  ORDER BY date_created DESC",nativeQuery=true)
	List<Notification> findAllNotificationsByStudentOrMentorOrDate(Long studentId,Long mentorId,Date loginDate);
	
	@Query(value="SELECT * FROM notification where date_created > ?3 AND ( media_id!='' or sub_cat!='' or student=?1 or ( mentor=?2 AND action='Request') or (user=?6 AND authority=?7)) ORDER BY id DESC LIMIT ?4 OFFSET ?5",
            nativeQuery=true)
	List<Notification> findAllNotificationsByLimit(Long studentId,Long mentorId,Date loginDate,Integer limit,Long offset,Long userId,String authority);


	@Query(value="SELECT * FROM notification where student=?1 AND mentor=?2",nativeQuery=true)
	Notification findByStudentAndMentor(Student student, Mentor mentor);



}
