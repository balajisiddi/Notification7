package com.sectorseven.model.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
public class Parents {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@Column
	@JsonView(PublicLight.class)
    private String motherName;
	
	@Column
	@JsonView(PublicLight.class)
    private String fatherName;
	
	@Column
	@JsonView(PublicLight.class)
    private String motherOccupation;
	
	@Column
	@JsonView(PublicLight.class)
    private String fatherOccupation;
	
	@Column
	@JsonView(PublicLight.class)
    private Long motherMobile;
	
	@Column
	@JsonView(PublicLight.class)
    private Long fatherMobile;
	
	@Column
	@JsonView(PublicLight.class)
    private String motherEmail;
	
	@Column
	@JsonView(PublicLight.class)
    private String fatherEmail;
	
	@Column
	@JsonView(PublicLight.class)
    private Status status;
	
	@Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
	
	@NotNull
    @Column
    @JsonView(PublicLight.class)
    private String userName;

    @NotNull
    @Column
    @JsonView(PublicLight.class)
    private String password;

    @NotNull
    @Column
    @JsonView(PublicLight.class)
    private String decryptPassword;
    
    @Column
    @JsonView(PublicLight.class)
    private String authority;
    
    @Column
    @JsonView(PublicLight.class)
    private String address1;
    
    @Column
    @JsonView(PublicLight.class)
    private String address;
    
    @Column
    @JsonView(PublicLight.class)
    private String city;
    
    @Column
    @JsonView(PublicLight.class)
    private String state;
    
    @Column
    @JsonView(PublicLight.class)
    private String description;
    
    @Column
    @JsonView(PublicLight.class)
    private String pincode;
    
	@Transient
    @JsonView(PublicLight.class)
    private CommonsMultipartFile parentImage;

	@Column
    @JsonView(PublicLight.class)
    private String parentImgName;
	
	@Column
    @JsonView(PublicLight.class)
    private String parentImgPath;
    
	@Column
	@JsonView(PublicLight.class)
	private String type;
	
}
