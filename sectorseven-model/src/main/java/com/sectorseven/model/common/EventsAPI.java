package com.sectorseven.model.common;

import java.util.List;

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
public class EventsAPI {
	
    public EventsAPI(int i, String string, List<AddEvent> events2) {
    		this.id=i;
    		this.title=string;
    		this.data=events2;
	}

	@JsonView(PublicLight.class)
    private int id;
    
    @JsonView(PublicLight.class)
	private String title;
    
    @JsonView(PublicLight.class)
	private List<AddEvent> data;


}
