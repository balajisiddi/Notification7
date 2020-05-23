package com.sectorseven.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sectorseven.model.util.Views.PublicLight;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SRAResponse {
	
	public SRAResponse(int i, String name, Object object) {
		this.id=i;
		this.name=name;
		this.result=object;
	}
	
	@JsonView(PublicLight.class)
    private int id;

	@JsonView(PublicLight.class)
    private String name;
	
	@JsonView(PublicLight.class)
    private Object result;




}
