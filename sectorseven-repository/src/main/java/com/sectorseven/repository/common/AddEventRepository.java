package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.AddEvent;

@Repository
public interface AddEventRepository extends JpaSpecificationExecutor<AddEvent>, JpaRepository<AddEvent, Serializable>{

		@Query(value="select * from add_event where event_date>=:date ORDER BY event_date_time DESC", nativeQuery = true)
        List<AddEvent> findAllUpComingEvents(@Param("date") String date);

		@Query(value="select * from add_event where event_date<:date ORDER BY event_date_time DESC", nativeQuery = true)
		List<AddEvent> findAllPreviousEvents(@Param("date") String strDate);

		@Query(value="select * from add_event ORDER BY event_date_time DESC", nativeQuery = true)
		List<AddEvent> findAllByDate();




}
