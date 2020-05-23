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
import com.sectorseven.model.Enum.Subscribe;
import com.sectorseven.model.admin.Authority;
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
public class SubscribedCareers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
    
	@Column
	@JsonView(PublicLight.class)
	private Long user;
	   
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authority")
    @JsonView(PublicLight.class)
    private Authority authority;
   

    
    @Column
    @JsonView(PublicLight.class)
    private Date dateCreated;

	@Column
	@JsonView(PublicLight.class)
	private Status status;
        
 
        
    @Column
	@JsonView(PublicLight.class)
	private Subscribe subscribe;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "career_subcat")
    @JsonView(PublicLight.class)
   	private CareerSubcategory careerSubcat;


}
