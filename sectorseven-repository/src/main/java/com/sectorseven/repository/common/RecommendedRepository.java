package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.Recommended;

@Repository
public interface RecommendedRepository extends JpaSpecificationExecutor<Recommended>, JpaRepository<Recommended, Serializable>{


	@Query(value="SELECT * FROM recommended WHERE user=?1 AND authority=?2 ORDER BY count DESC LIMIT 5",nativeQuery=true)
	List<Recommended> findAllRecommendationsByUserAndAuthority(Long userId, String userType);
	
	@Query(value="SELECT * FROM recommended WHERE user=?1 AND authority=?2 ORDER BY date_created LIMIT 5",nativeQuery=true)
	List<Recommended> findAllRecommendationByUserAndAuthority(Long userId, String userType);
	
	@Query(value="SELECT * FROM recommended WHERE user=?1 AND authority=?2 AND career_cat=?3 ORDER BY date_created LIMIT 5",nativeQuery=true)
	List<Recommended> findAllRecommendationByUserAndAuthorityAndCareerCat(Long userId, String userType,Long careerId);
	

	@Query(value="SELECT SUM(count) FROM recommended WHERE user=?1 AND authority=?2 ORDER BY count DESC LIMIT 5",nativeQuery=true)
	Integer getTotalSumOfCount(Long userId, String userType);

	@Query(value="SELECT * FROM recommended WHERE career_subcat=?1 AND user=?2 AND authority=?3",nativeQuery=true)
	Recommended findByCareerSubcatAndUserAndAuthority(Long careerSubcat, Long user, String authority);

	@Query(value="SELECT * FROM recommended WHERE user=?1 AND authority=?2 ORDER BY count DESC LIMIT 1",nativeQuery=true)
	Recommended findByUserAndAuthority(Long userId, String authority);


	


}
