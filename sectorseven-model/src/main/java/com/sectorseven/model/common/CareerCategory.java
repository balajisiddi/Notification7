/**
 * 
 */
package com.sectorseven.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.util.Views.PublicLight;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * @author user
 *
 */
@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(of = "id")
public class CareerCategory {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@Column
    @JsonView(PublicLight.class)
    private String categoryName;
	
	@Column
    @JsonView(PublicLight.class)
    private String categoryImagName;
	
	@Column
    @JsonView(PublicLight.class)
    private String categoryImagPath;
	
	@Transient
	@JsonView(PublicLight.class)
	private CommonsMultipartFile categoryImg;
	
	@Column
    @JsonView(PublicLight.class)
    private String intrstImagName;
	
	@Column
    @JsonView(PublicLight.class)
    private String intrstImagPath;
	
	@Transient
	@JsonView(PublicLight.class)
	private CommonsMultipartFile intrstImg;
    
    
    @Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
    
    @Column
    @JsonView(PublicLight.class)
    private Status status;

    @Column
    @JsonView(PublicLight.class)
    private String type;
	
    @Column
    @JsonView(PublicLight.class)
    private String intrstType;
	

}
