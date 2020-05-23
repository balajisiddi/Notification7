package com.sectorseven.repository.admin;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.SequenceModule;
import com.sectorseven.model.admin.AcademicYear;
import com.sectorseven.model.admin.IdSequence;

@Repository
public interface IdSequenceRepository extends JpaRepository<IdSequence, Serializable>, JpaSpecificationExecutor<IdSequence>{

	IdSequence findByModule(SequenceModule studentModule);

	IdSequence findByModuleAndSchoolCodeAndAcademicYear(SequenceModule studentModule, String schoolCode,
			AcademicYear academicYear);


	
	
	
}
