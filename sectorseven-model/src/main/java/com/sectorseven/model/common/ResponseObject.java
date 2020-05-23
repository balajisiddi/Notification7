package com.sectorseven.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.util.Views.PublicLight;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author MacTec
 *
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ResponseObject {

    @JsonView(PublicLight.class)
    private String statusCode;

    @JsonView(PublicLight.class)
    private Object statusMessage;

    @JsonView(PublicLight.class)
    private Object response;
    
    @JsonView(PublicLight.class)
    private Object file;
}
