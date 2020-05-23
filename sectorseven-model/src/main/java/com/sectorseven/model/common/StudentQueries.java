package com.sectorseven.model.common;

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
public class StudentQueries {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@Column
    @JsonView(PublicLight.class)
    private String question;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mentor")
    @JsonView(PublicLight.class)
    private Mentor mentor;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student")
    @JsonView(PublicLight.class)
    private Student student;

	
	@Column
    @JsonView(PublicLight.class)
    private String attachmentName;
	
	@Column
    @JsonView(PublicLight.class)
    private String attachmentPath;
	
	@Column
    @JsonView(PublicLight.class)
    private String type;
	

	@Transient
    @JsonView(PublicLight.class)
    private String askBase64Img;
	
	@Transient
	@JsonView(PublicLight.class)
	private CommonsMultipartFile questionImage;
	
	@Column
	@JsonView(PublicLight.class)
	private Status status;
	
	
}
