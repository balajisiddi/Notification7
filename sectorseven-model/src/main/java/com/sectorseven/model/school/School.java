package com.sectorseven.model.school;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.util.Views.PublicLight;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author RameshNaidu
 *
 */
@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(of = "id")
public class School {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@Column
    @JsonView(PublicLight.class)
    private String schoolName;
	
	@Column
    @JsonView(PublicLight.class)
    private String email;

    @NotNull
    @Column
    @JsonView(PublicLight.class)
    private Long contactNo;
    
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
    private String pincode;
    
    @Column
    @JsonView(PublicLight.class)
    private String schoolCode;
    
    @Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
    
    @Column
    @JsonView(PublicLight.class)
    private Status status;

	
}
