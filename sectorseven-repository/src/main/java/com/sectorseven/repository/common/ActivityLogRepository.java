package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.ActivityLog;
import com.sectorseven.model.common.CareerSubcategory;

@Repository

public interface ActivityLogRepository extends JpaSpecificationExecutor<ActivityLog>, JpaRepository<ActivityLog, Serializable>{

	List<ActivityLog> findAllByUserAndAuthority(Long userId, String authority);
	
	@Query(value="SELECT * FROM activity_log WHERE authority=?2 AND user=?1 AND screen='Subscribed'",nativeQuery = true)
	ActivityLog findAllByUserAndAuthorit(Long userId, String authority);


	@Query(value="SELECT * FROM activity_log WHERE authority=?2 AND user=?1  ORDER BY date_created DESC",nativeQuery = true)
	List<ActivityLog> findLastRecordsByUserAndAuthority(Long user,String authority);

	@Query(value="SELECT * FROM activity_log WHERE authority=?2 AND user=?1 ORDER BY date_created DESC LIMIT ?3 OFFSET ?4",nativeQuery = true)
	List<ActivityLog> findLastRecordsByUserAndAuthority(Long user,String authority,Integer limit,Integer offset);

	
	ActivityLog findByMediaId(Long mediaId);

	List<ActivityLog> findAllByUserAndAuthorityAndSubcategory(Long userId, String userType,
			CareerSubcategory careerSub);
	
	@Query(value="select * from activity_log  where authority=?2 AND user=?1 AND sub_cat=?3 AND action like '%Video%' ", nativeQuery = true)
	List<ActivityLog> findAllByUserAndAuthorityAndSubcategoryByVideos(Long user,String authority,Long subcat);

	@Query(value="select * from activity_log  where authority=?2 AND user=?1 AND sub_cat=?3 AND action like '%Audio%' ", nativeQuery = true)
	List<ActivityLog> findAllByUserAndAuthorityAndSubcategoryByAudios(Long user,String authority,Long subcat);

	@Query(value="select * from activity_log  where authority=?2 AND user=?1 AND sub_cat=?3 AND action like '%Pdf%' ", nativeQuery = true)
	List<ActivityLog> findAllByUserAndAuthorityAndSubcategoryByPdfs(Long user,String authority,Long subcat);

	@Query(value="select * from activity_log  where authority=?2 AND user=?1 AND sub_cat=?3 AND media_type=?4 LIMIT ?5 OFFSET ?6", nativeQuery = true)
	List<ActivityLog> findAllByUserAndAuthorityAndSubcategoryByType(Long userId, String userType, Long mediaId,String type,Integer limit,Integer offset);

	@Query(value="SELECT * FROM activity_log WHERE authority=?2 AND user=?1 AND sub_cat=?3 AND action=?4",nativeQuery = true)
	ActivityLog findAllByUserAndTypeAndSubCatAndScreen(Long userId, String authority, Long subCatId, String ordinal);

	@Query(value="SELECT * FROM activity_log WHERE media_id=?1 AND user=?2 AND authority=?3",nativeQuery = true)
	ActivityLog findByMediaIdAndUserAndAuthority(Long mediaId, Long user, String authority);



}
