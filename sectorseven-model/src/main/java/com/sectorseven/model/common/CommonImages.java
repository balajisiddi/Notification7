package com.sectorseven.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.util.Views.PublicLight;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(of = "id")
public class CommonImages {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@Column
    @JsonView(PublicLight.class)
    private String imgName;
	
	@Column
    @JsonView(PublicLight.class)
    private String imgPath;
	
	@Column
    @JsonView(PublicLight.class)
    private String imgType;
	
	
	@Column
    @JsonView(PublicLight.class)
    private String screen;
	
	
	@Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
    
    @Column
    @JsonView(PublicLight.class)
    private Status status;
    
    @Transient
	@JsonView(PublicLight.class)
	private CommonsMultipartFile cmImage;
	

}
