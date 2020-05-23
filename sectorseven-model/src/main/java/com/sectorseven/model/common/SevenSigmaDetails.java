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
public class SevenSigmaDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seven_sigma")
    @JsonView(PublicLight.class)
    private SevenSigma sevenSigma;
	
	
	@Column
	@JsonView(PublicLight.class)
	private String name;
	
	
	@Column
	@JsonView(PublicLight.class)
	private VideoType videoType;
	
	
	@Column(name="type", length=100, nullable=false)
	private String type;
	
	@Transient
	@JsonView(PublicLight.class)
	private List<CommonsMultipartFile> sigmaDocument;

	@Column
    @JsonView(PublicLight.class)
    private String authorName;
	
	@Column
    @JsonView(PublicLight.class)
    private String datePublished;
	
	@Column
    @JsonView(PublicLight.class)
    private String description;
	

	@Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
    
    @Column
    @JsonView(PublicLight.class)
    private Status status;

	@Column
    @JsonView(PublicLight.class)
    private String sigmaDocumentName;
	
	@Column
    @JsonView(PublicLight.class)
    private String sigmaDocumentPath;
	
	@Column
    @JsonView(PublicLight.class)
    private String title;
	
	@Column
	@JsonView(PublicLight.class)
	private String youtubeUrl;
}
