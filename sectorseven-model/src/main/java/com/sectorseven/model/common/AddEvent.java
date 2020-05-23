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
public class AddEvent {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@Column
	@JsonView(PublicLight.class)
	private String eventName;
	   
	
	@Column
    @JsonView(PublicLight.class)
    private String eventTime;
    
    @Transient
    @JsonView(PublicLight.class)
    private String getDate;
   
    
    @Column
    @JsonView(PublicLight.class)
    private Date eventDateTime;
  
    
   	@Column   	
    @JsonView(PublicLight.class)
    private String description;
   	
    @Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
    
    @Column
    @JsonView(PublicLight.class)
    private Status status;

   
     @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "hub_zone")
	 @JsonView(PublicLight.class)
	 private CoCreationHub hubZone;
     
     @Column
     @JsonView(PublicLight.class)
     private String event_date;
 	
 	
     @Transient
     @JsonView(PublicLight.class)
     private String event_month;
 	
     
     @Transient
     @JsonView(PublicLight.class)
     private String event_day;
 	
   
}
