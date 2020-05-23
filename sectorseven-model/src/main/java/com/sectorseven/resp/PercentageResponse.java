package com.sectorseven.resp;

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
public class PercentageResponse {
	
	
	@JsonView(PublicLight.class)
    private Double pdfs;

	@JsonView(PublicLight.class)
    private Double audios;
	
	@JsonView(PublicLight.class)
    private Double videos;
	
	@JsonView(PublicLight.class)
    private Integer noOfVideos;

	@JsonView(PublicLight.class)
    private Integer noOfAudios;
	
	@JsonView(PublicLight.class)
    private Integer noOfPdfs;

}
