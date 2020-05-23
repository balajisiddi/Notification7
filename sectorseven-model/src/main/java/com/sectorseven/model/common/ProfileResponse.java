package com.sectorseven.model.common;

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
public class ProfileResponse {
	
	public ProfileResponse(int i, String string, Object object) {
		this.id=i;
		this.title=string;
		this.result=object;
	}

	@JsonView(PublicLight.class)
    private int id;
    
    @JsonView(PublicLight.class)
	private String title;
    
    @JsonView(PublicLight.class)
	private Object result;

}
