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
@JsonInclude(Include.ALWAYS)
public class MentorsResponse {
	
	
	@JsonView(PublicLight.class)
    private Long id;

	@JsonView(PublicLight.class)
    private String name;

    @JsonView(PublicLight.class)
    private Object imgPath;

    @JsonView(PublicLight.class)
    private String expertize;


    @JsonView(PublicLight.class)
    private String imgName;

    @JsonView(PublicLight.class)
    private Boolean data;


}
