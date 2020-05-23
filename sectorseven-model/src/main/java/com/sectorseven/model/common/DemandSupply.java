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
public class DemandSupply {
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
    
   
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "label")
    @JsonView(PublicLight.class)
    private DemandLabels label;
    
    
    @Column
    @JsonView(PublicLight.class)
    private Double salary;
	
    @Column
    @JsonView(PublicLight.class)
    private Double manpower;
    @Column
    @JsonView(PublicLight.class)
    private String backgroundColor;
    
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "year")
    @JsonView(PublicLight.class)
    private DemandYears year;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "color")
    @JsonView(PublicLight.class)
    private DemandColors color;
	
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subcat_id")
    @JsonView(PublicLight.class)
    private CareerSubcategory careerSubCat;
   
}
