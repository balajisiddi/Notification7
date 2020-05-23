package com.sectorseven.resp;

import java.util.List;

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
public class Graphresponse {
	
	@JsonView(PublicLight.class)
    private List<String> labels;

    @JsonView(PublicLight.class)
    private List<String> legend;
    
    @JsonView(PublicLight.class)
    private List<List<Integer>> data;
    
    @JsonView(PublicLight.class)
    private List<String> barColors;


}
