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
public class InstitutionCourses {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@Column
    @JsonView(PublicLight.class)
    private String courseUrl;
    
    @Column
    @JsonView(PublicLight.class)
    private String courseName;
    
    @Column
    @JsonView(PublicLight.class)
    private Status status;
    
    @Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
 
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "institution")
//    @JsonView(PublicLight.class)
    private Institutions institution;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subcat_id")
//    @JsonView(PublicLight.class)
    private CareerSubcategory subCategory;
    
}
