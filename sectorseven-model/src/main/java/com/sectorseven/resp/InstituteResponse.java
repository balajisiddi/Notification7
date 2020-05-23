package com.sectorseven.resp;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sectorseven.model.common.InstitutionCourses;
import com.sectorseven.model.util.Views.PublicLight;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class InstituteResponse {
	
	@JsonView(PublicLight.class)
    private Long id;
	
	@JsonView(PublicLight.class)
    private String name;
	
	@JsonView(PublicLight.class)
    private List<InstitutionCourses> result;
}
