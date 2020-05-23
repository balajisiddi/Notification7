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
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.Gender;
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
public class SchoolTeacher {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "school")
    @JsonView(PublicLight.class)
    private School school;
	
	@Column
	@JsonView(PublicLight.class)
    private String firstName;
	
	@Column
	@JsonView(PublicLight.class)
    private String lastName;
	
	@Column
	@JsonView(PublicLight.class)
    private String expertise;
	
	@Column
	@JsonView(PublicLight.class)
    private String hobbies;
	
	@Column
	@JsonView(PublicLight.class)
    private String interests;
	

	@Column
    @JsonView(PublicLight.class)
    private String email;

    @NotNull
    @Column
    @JsonView(PublicLight.class)
    private Long mobile;
    
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

   /* @Column
    @JsonView(PublicLight.class)
    private Date dateOfBirth;*/
    
    @Column
    @JsonView(PublicLight.class)
    private Gender gender;
    
    @Column
	@JsonView(PublicLight.class)
    private boolean teachersTalkContribution;
    
	@Column
	@JsonView(PublicLight.class)
    private Status status;
	
	@Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
	
	@Column
    @JsonView(PublicLight.class)
    private String date_of_birth;
	
	
	@Transient
    @JsonView(PublicLight.class)
    private CommonsMultipartFile teacherImage;

	@Column
    @JsonView(PublicLight.class)
    private String teacherImgName;
	
	@Column
    @JsonView(PublicLight.class)
    private String teacherImgPath;
	
	
	@Column
    @JsonView(PublicLight.class)
    private String description;
	
	@Transient
    @JsonView(PublicLight.class)
    private String userName;
	
	@Column
	@JsonView(PublicLight.class)
	private String type;
	
	/* public void setDate_of_birth(String date_of_birth) {
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
