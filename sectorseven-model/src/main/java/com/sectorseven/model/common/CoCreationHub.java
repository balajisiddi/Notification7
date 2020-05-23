package com.sectorseven.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.Hub;
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
public class CoCreationHub {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@Column
    @JsonView(PublicLight.class)
    private String hubName;
	
	@Column
    @JsonView(PublicLight.class)
    private String hubZone;
	
	
	@Column
    @JsonView(PublicLight.class)
    private String address1;
	
	@Column
    @JsonView(PublicLight.class)
    private String address2;
	
	@Column
    @JsonView(PublicLight.class)
    private String city;
	
	@Column
    @JsonView(PublicLight.class)
    private String state;
	
	@Column
    @JsonView(PublicLight.class)
    private String longitude;

	@Column
    @JsonView(PublicLight.class)
    private String latitude;

	@Transient
    @JsonView(PublicLight.class)
    private String url;


	@Column
    @JsonView(PublicLight.class)
    private String pincode;

    
    @Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
    
    @Column
    @JsonView(PublicLight.class)
    private Status status;

    @Column
    @JsonView(PublicLight.class)
    private Hub hub;


}
