package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.SubCategoryDetails;

@Repository
public interface SubCategoryDetailsRepository extends JpaSpecificationExecutor<SubCategoryDetails>, JpaRepository<SubCategoryDetails, Serializable> {

	
	@Query(value="select * from sub_category_details  where career_subcat=?1 AND type=?2 LIMIT ?3 OFFSET ?4", nativeQuery = true)
	List<SubCategoryDetails> findByCareerSubcatAndType(Long subCatDetails,String type,Integer limit,Integer offset);

	List<SubCategoryDetails> findByStatus(Status active);

	List<SubCategoryDetails> findByCareerSubcat(CareerSubcategory careerSubCat);

	
	@Query(value="select * from sub_category_details s where s.tags IN (:interests1) AND type like '%video%' ", nativeQuery = true)
	List<SubCategoryDetails> findAllByInterests(@Param("interests1") List<String> interests1);

	
	
	@Query(value="select * from sub_category_details m where m.career_subcat IN (:subcats) AND m.id NOT IN (:mediaIds) AND m.type like '%video%' ORDER BY FIELD(:subcats,career_subcat) DESC  LIMIT :limit OFFSET :offset", nativeQuery = true)
	List<SubCategoryDetails> findAllRecommendationsByCareerSubcat(@Param("subcats") List<Long> subcats,@Param("mediaIds") List<Long> mediaIds,@Param("limit") Integer limit,@Param("offset") Integer offset);
	
	

	@Query(value="select * from sub_category_details m where m.career_subcat IN (:subcats) AND m.type like '%video%' ORDER BY date_created DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
	List<SubCategoryDetails> findAllRecommendationsByCareerSubcatNoAct(@Param("subcats") List<Long> subcats,@Param("limit") Integer limit,@Param("offset") Integer offset);

	@Query(value="select count(id) from sub_category_details where type='video/mp4'", nativeQuery = true)
	Long findAllSubcareerVideos();
	
	@Query(value="select count(id) from sub_category_details where type='application/pdf'", nativeQuery = true)
	Long findAllSubcareerPDFs();
}
