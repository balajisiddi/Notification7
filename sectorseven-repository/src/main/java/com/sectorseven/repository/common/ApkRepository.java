package com.sectorseven.repository.common;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.Apk;

@Repository
public interface ApkRepository extends JpaSpecificationExecutor<Apk>, JpaRepository<Apk, Serializable> {

	@Query(value="SELECT * FROM apk  ORDER BY date_created DESC",nativeQuery=true)
	Apk findApkUpdateAPI();

	
}
