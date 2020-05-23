package com.sectorseven.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sectorseven.model.Enum.VideoType;
import com.sectorseven.model.util.Views.PublicLight;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.ALWAYS)
public class RecVideoResp {
	
	@JsonView(PublicLight.class)
    private Integer id;

	@JsonView(PublicLight.class)
    private Long mediaId;
	
	@JsonView(PublicLight.class)
    private Long subCatId;

    @JsonView(PublicLight.class)
    private VideoType videoType;

    @JsonView(PublicLight.class)
    private String youtubeUrl;
    
    @JsonView(PublicLight.class)
    private String description;

    @JsonView(PublicLight.class)
    private String title;

    @JsonView(PublicLight.class)
    private String thumbnaiUrl;

}
