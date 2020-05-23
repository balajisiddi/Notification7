package com.sectorseven.model.common;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.Country;
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
public class Institutions {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@Column
    @JsonView(PublicLight.class)
    private String name;
	
	@Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
    
    @Column
    @JsonView(PublicLight.class)
    private Status status;
    
    @Column
    @JsonView(PublicLight.class)
    private String imgName;
	
    @Column
    @JsonView(PublicLight.class)
    private String instituteUrl;
	
    
	@Column
    @JsonView(PublicLight.class)
    private String imgPath;
	
	@Column
    @JsonView(PublicLight.class)
    private Country country;

    
	@Column
    @JsonView(PublicLight.class)
    private String type;

    @Transient
    @JsonView(PublicLight.class)
    private  CommonsMultipartFile instImage;
    
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subcat_id")
    @JsonView(PublicLight.class)
    private CareerSubcategory careerSubCat;
  


}
