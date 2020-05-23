package com.sectorseven.model.admin;

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
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.Gender;
import com.sectorseven.model.Enum.Salutation;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.YesNo;
import com.sectorseven.model.common.CareerCategory;
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
public class Mentor {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@Column
	@JsonView(PublicLight.class)
    private Salutation salutation;
	
	@Column
	@JsonView(PublicLight.class)
    private String firstName;
	
	@Column
	@JsonView(PublicLight.class)
    private String lastName;
	
	@Column
	@JsonView(PublicLight.class)
    private String occupation;
	
	@Column
	@JsonView(PublicLight.class)
    private String expertise;
	
	@Column(name="reason")
	@JsonView(PublicLight.class)
    private String resonForMentor;
	
	@Column
	@JsonView(PublicLight.class)
    private YesNo solutionToStudents;
	
	@Column
	@JsonView(PublicLight.class)
    private YesNo childActivities;
	
	@Column(name="education")
	@JsonView(PublicLight.class)
    private String education;
	
	@Column(name="description")
	@JsonView(PublicLight.class)
    private String description;
	
	
	
	@Column
	@JsonView(PublicLight.class)
    private String contributionTime;
	
	@Column
	@JsonView(PublicLight.class)
    private YesNo mentorTalks;
	
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
    private String email;

    @NotNull
    @Column
    @JsonView(PublicLight.class)
    private Long mobile;

   /* @Column
    @JsonView(PublicLight.class)
    private String dateOfBirth;*/
    
    @Column
    @JsonView(PublicLight.class)
    private String authority;
    
    @Column
	@JsonView(PublicLight.class)
    private Gender gender;
    
    @Column
	@JsonView(PublicLight.class)
    private Status status;
	
	@Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
	
	@Column
    @JsonView(PublicLight.class)
    private String date_of_birth;
	
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
    private String pinCode;
    
    @Column
    @JsonView(PublicLight.class)
    private String diffMentorSectSeven;
    
    @Column
    @JsonView(PublicLight.class)
    private String twitterUrl;
	
    
    @Column
    @JsonView(PublicLight.class)
    private String linkedUrl;
	
    
    @Column
    @JsonView(PublicLight.class)
    private String instagramUrl;
	
    @Column
    @JsonView(PublicLight.class)
    private String imgPath;
	
    
    @Column
    @JsonView(PublicLight.class)
    private String imgName;
	
    
    @Column
    @JsonView(PublicLight.class)
    private String type;
	
    
    @Transient
    @JsonView(PublicLight.class)
    private CommonsMultipartFile mentorImg;

	
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "occupation_category")
    @JsonView(PublicLight.class)
    private CareerCategory occupationCategory;	
    
	 /*public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String str[] = date_of_birth.split("-");
            String year = str[2];
            String month = str[1];
            String date = str[0];
            String s = sdf.format(sdf.parse(year + "-" + month + "-" + date));
            dateOfBirth = sdf.parse(s);
        } catch (Exception p) {
            p.printStackTrace();
        }
	    }*/
	
	
}
