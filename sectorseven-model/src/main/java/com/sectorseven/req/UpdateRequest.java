package com.sectorseven.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.util.Views.PublicLight;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class UpdateRequest {

	@JsonView(PublicLight.class)
    private String firstName;
	
	@JsonView(PublicLight.class)
    private String lastName;
	
	@JsonView(PublicLight.class)
    private String email;
	
	@JsonView(PublicLight.class)
    private long mobile;
	
	@JsonView(PublicLight.class)
	private String description;
	
	
}
