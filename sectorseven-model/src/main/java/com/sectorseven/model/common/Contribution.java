package com.sectorseven.model.common;

import java.util.Date;
import java.util.List;

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
import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.Enum.Status;
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
public class Contribution {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@Column
    @JsonView(PublicLight.class)
    private String message;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subject")
	@JsonView(PublicLight.class)
	private CareerCategory subject;

	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "sub_subject")
	 @JsonView(PublicLight.class)
     private CareerSubcategory subSubject;
	
	@Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
    
    @Column
    @JsonView(PublicLight.class)
    private Status status;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authority")
    @JsonView(PublicLight.class)
    private Authority authority;
    
    @Column
    @JsonView(PublicLight.class)
    private Long user;
    
    @Column
    @JsonView(PublicLight.class)
    private AdminAcceptance acceptance;
    
    @Transient
	@JsonView(PublicLight.class)
	private List<CommonsMultipartFile> contDocs;
    
    @Transient
    @JsonView(PublicLight.class)
    private String userObj;
	
	
}
