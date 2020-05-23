/**
 * 
 */
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
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.Trending;
import com.sectorseven.model.util.Views.PublicLight;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author user
 *
 */
@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(of = "id")
public class CareerSubcategory {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "career_category")
    @JsonView(PublicLight.class)
    private CareerCategory careerCategory;
	
	
	@Column
    @JsonView(PublicLight.class)
    private String subCategoryName;
	
	@Column
    @JsonView(PublicLight.class)
    private String subcategoryImgName;
	
	@Column
    @JsonView(PublicLight.class)
    private String subcategoryImgPath;

	@Transient
	@JsonView(PublicLight.class)
	private CommonsMultipartFile subCategoryImg;
	
	
    @Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
    
    @Column
    @JsonView(PublicLight.class)
    private Status status;
    
    @Column
    @JsonView(PublicLight.class)
    private Trending trending;
    
    @Transient
    @JsonView(PublicLight.class)
    private String roles;
    
    @Transient
    @JsonView(PublicLight.class)
    private String skills;
    
    @Transient
    @JsonView(PublicLight.class)
    private String abilities;
	
	@Transient
    @JsonView(PublicLight.class)
    private String patterns;

	
	@Column
    @JsonView(PublicLight.class)
    private String amITheOne;


	
	@Column
    @JsonView(PublicLight.class)
    private String howToGetThere;
	
	
	
	@Column
    @JsonView(PublicLight.class)
    private String landingImgName;
	
	@Column
    @JsonView(PublicLight.class)
    private String landingImgPath;
	
	@Transient
	@JsonView(PublicLight.class)
	private CommonsMultipartFile landingImg;
	
	@Column
    @JsonView(PublicLight.class)
    private String amITheOneImgName;
	
	@Column
    @JsonView(PublicLight.class)
    private String amITheOneImgPath;
	
	@Column
	@JsonView(PublicLight.class)
	private String amType;
	
	@Column
	@JsonView(PublicLight.class)
	private String landingType;	
	
	@Transient
	@JsonView(PublicLight.class)
	private CommonsMultipartFile amITheOneImg;

	@Column
    @JsonView(PublicLight.class)
    private String landingDesc;
	
	 @Column
	    @JsonView(PublicLight.class)
	    private String courseCat1;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String courseCat2;
	    @Column
	    @JsonView(PublicLight.class)
	    private String courseCat3;
	    @Column
	    @JsonView(PublicLight.class)
	    private String courseCat4;
	    @Column
	    @JsonView(PublicLight.class)
	    private String courseCat5;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String courseCat6;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String entranceCat1;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String entranceCat2;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String entranceCat3;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String entranceCat4;
	    
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  indiaPay;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  indiaScholorship;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  abroadColleges;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  abroadPay;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  abroadScholorship;
	    
	    
	    
	   /* @Column
	    @JsonView(PublicLight.class)
	    private String  example1;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  example2;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  example3;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  example4;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  example5;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  example1Name;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  example2Name;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  example3Name;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  example4Name;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  example5Name;*/
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  jobRoles;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  landingQuote;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  jobsDescription;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  salaryDescription;


	    @Column
	    @JsonView(PublicLight.class)
	    private String type;
		

}
