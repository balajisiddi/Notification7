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
import com.sectorseven.model.Enum.VideoType;
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
public class ActivityLog {
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
    private String message;
    
    @Column
    @JsonView(PublicLight.class)
    private Long user;

    @Column
    @JsonView(PublicLight.class)
    private String authority;

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

    @Column
    @JsonView(PublicLight.class)
    private Long mediaId;
    
    @Column
    @JsonView(PublicLight.class)
    private Integer count;
  
    
    @Column
    @JsonView(PublicLight.class)
    private VideoType videoType;
    
    @Column
    @JsonView(PublicLight.class)
    private String youtubeUrl;
    
    @Column
    @JsonView(PublicLight.class)
    private String mediaType;
   
    
    
    @Transient
    @JsonView(PublicLight.class)
    private double seenPercent;
    
    @Transient
    @JsonView(PublicLight.class)
    private String activityDate;
     
}
