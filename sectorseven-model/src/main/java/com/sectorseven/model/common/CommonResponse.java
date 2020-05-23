package com.sectorseven.model.common;

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
public class CommonResponse {
	
	@JsonView(PublicLight.class)
    private String userName;

    @JsonView(PublicLight.class)
    private String userType;

    @JsonView(PublicLight.class)
    private Long userId;
    
    @JsonView(PublicLight.class)
    private String name;

    @JsonView(PublicLight.class)
    private String email;

}
