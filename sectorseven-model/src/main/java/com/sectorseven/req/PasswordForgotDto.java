package com.sectorseven.req;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.util.Views.PublicLight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordForgotDto {
	 
	    /** The username for customer login */
	  
	    
	    @NotNull(message = "'Username' must be specified")
	    @Size(min = 1, message = "'Username' must be specified")
	    @JsonView(PublicLight.class)
	    private String username;

}
