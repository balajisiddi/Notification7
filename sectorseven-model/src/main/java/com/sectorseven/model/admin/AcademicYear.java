package com.sectorseven.model.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.util.Views.PublicLight;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author RameshNaidu
 *
 */
@Getter
@Setter
@Entity
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(of = "id")
public class AcademicYear {
	
	@Id
    @SequenceGenerator(name = "adminUser", sequenceName = "adminUserGen_generator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "adminUser")
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;


    @NotNull
    @Size(min = 2)
    @Column
    @JsonView(PublicLight.class)
    private String name;
    
    @Column
    @JsonView(PublicLight.class)
    private Date startingDate;
    
    @Column
    @JsonView(PublicLight.class)
    private Date endingDate;
    
    @Column
    @JsonView(PublicLight.class)
    private Status status;
    
    @Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
    
    
	
}
