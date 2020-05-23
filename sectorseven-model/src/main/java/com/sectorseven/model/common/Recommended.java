package com.sectorseven.model.common;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

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
public class Recommended {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	
    @Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
    
    @Column
    @JsonView(PublicLight.class)
    private Status status;

    
    @Column
    @JsonView(PublicLight.class)
    private Long user;

    @Column
    @JsonView(PublicLight.class)
    private String authority;

    
    
    @Transient
    @JsonView(PublicLight.class)
    private String catPercent;
   
    
    @Column
    @JsonView(PublicLight.class)
    private Integer count;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "career_subcat")
    @JsonView(PublicLight.class)
    private CareerSubcategory careerSubcat;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "career_cat")
    @JsonView(PublicLight.class)
    private CareerCategory careerCat;
    
     
}
