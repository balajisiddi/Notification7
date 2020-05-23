package com.sectorseven.model.school;

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

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.Gender;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.YesNo;
import com.sectorseven.model.admin.AcademicYear;
import com.sectorseven.model.admin.Parents;
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
public class Student {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@Column
	@JsonView(PublicLight.class)
    private String firstName;
	
	@Column
	@JsonView(PublicLight.class)
    private String lastName;
	
    @Column
    @JsonView(PublicLight.class)
    private String userName;

    @Column
    @JsonView(PublicLight.class)
    private String password;

    @Column
    @JsonView(PublicLight.class)
    private String decryptPassword;

    
    @Column
    @JsonView(PublicLight.class)
    private String email;

    @Column
    @JsonView(PublicLight.class)
    private long mobile;

    @Column
    @JsonView(PublicLight.class)
    private String date_of_birth;
    
    @Column
    @JsonView(PublicLight.class)
    private String authority;
    
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "school")
    @JsonView(PublicLight.class)
    private School school;
	
	@Column
	@JsonView(PublicLight.class)
    private Gender gender;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent")
    @JsonView(PublicLight.class)
    private Parents parents;
	
	 @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 @JoinColumn(name = "school_teacher")
	 @JsonView(PublicLight.class)
	 private SchoolTeacher schoolTeacher;
		
	
    
	@Column
	@JsonView(PublicLight.class)
    private String section;
	
	@Column(name="class")
	@JsonView(PublicLight.class)
    private String studentClass;
	
	@Column(name="studentStream")
	@JsonView(PublicLight.class)
    private YesNo studentStream;
	
	
	@Column
	@JsonView(PublicLight.class)
    private Status status;
	
	@Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
	
	@Column
    @JsonView(PublicLight.class)
    private String hobbies;
	
	@Column
    @JsonView(PublicLight.class)
    private String description;

	@Transient
    @JsonView(PublicLight.class)
    private CommonsMultipartFile studentImage;

	@Column
    @JsonView(PublicLight.class)
    private String studentImgName;
	
	@Column
    @JsonView(PublicLight.class)
    private String studentImgPath;

	 @Column
	 @JsonView(PublicLight.class)
	 private String type;
		
	 
	 @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 @JoinColumn(name = "academicYear")
	 private AcademicYear acedemicYear;
	 
     @Transient
	 @Column
	 @JsonView(PublicLight.class)
	 private String academicYear;
     
	
}
