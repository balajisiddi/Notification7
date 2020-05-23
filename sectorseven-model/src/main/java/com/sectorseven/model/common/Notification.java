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
import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.VideoType;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.school.Student;
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
public class Notification {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	
    @Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
    
    @Transient
    @JsonView(PublicLight.class)
    private String displayDate;
    
    
    @Column
    @JsonView(PublicLight.class)
    private Status status;
    
    @Column
    @JsonView(PublicLight.class)
    private String message;

    @Column
    @JsonView(PublicLight.class)
    private String screen;

    @Column
    @JsonView(PublicLight.class)
    private String action;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_cat")
    @JsonView(PublicLight.class)
    private CareerSubcategory subcategory;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mentor")
    @JsonView(PublicLight.class)
    private Mentor mentor;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student")
    @JsonView(PublicLight.class)
    private Student student;

    @Column
    @JsonView(PublicLight.class)
    private Long mediaId;
    
    @Column
    @JsonView(PublicLight.class)
    private VideoType videoType;
    
    @Column
	@JsonView(PublicLight.class)
	private AdminAcceptance acceptance;
    
    @Column
	@JsonView(PublicLight.class)
	private String youtubeUrl;
    

    @Transient
	@JsonView(PublicLight.class)
    private Long subCatId;

  
    @Column
 	@JsonView(PublicLight.class)
    private Long user;

    @Column
 	@JsonView(PublicLight.class)
    private String authority;

}
