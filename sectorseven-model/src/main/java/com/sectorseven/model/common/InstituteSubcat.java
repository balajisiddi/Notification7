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
import com.sectorseven.model.Enum.Country;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.util.Views.PublicLight;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Entity
public class InstituteSubcat {
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
    @JoinColumn(name = "subcat_id")
    @JsonView(PublicLight.class)
    private CareerSubcategory subcatId;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "institution_id")
    @JsonView(PublicLight.class)
    private Institutions institutions;
    
    @Column
    @JsonView(PublicLight.class)
    private Country country;
    
}
