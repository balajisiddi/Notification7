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
import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.VideoType;
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
public class ContributionDocs {
	
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
	    private String contDocName;
		
		@Column
	    @JsonView(PublicLight.class)
	    private String contDocPath;
		
		@Column
	    @JsonView(PublicLight.class)
	    private String type;
		
		@Column
	    @JsonView(PublicLight.class)
	    private VideoType videoType;
		
		
		@Column
	    @JsonView(PublicLight.class)
	    private String description;
		
		@Column
	    @JsonView(PublicLight.class)
	    private String title;
		
		
		 @ManyToOne(cascade = CascadeType.ALL)
		 @JoinColumn(name = "authority")
		 @JsonView(PublicLight.class)
		 private Authority authority;
		   
		 @ManyToOne(cascade = CascadeType.ALL)
		 @JoinColumn(name = "contribution")
		 @JsonView(PublicLight.class)
		 private Contribution contribution;
		
	    @Column
	    @JsonView(PublicLight.class)
	    private AdminAcceptance acceptance; 
		
}
