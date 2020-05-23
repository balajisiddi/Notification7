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
public class DemandResp {
	
	
	@JsonView(PublicLight.class)
    private Integer id;
	
	@JsonView(PublicLight.class)
	private String longTitle;

	@JsonView(PublicLight.class)
    private Object countries;

    @JsonView(PublicLight.class)
    private  Object year1Data;

    @JsonView(PublicLight.class)
    private  Object year2Data;


}
