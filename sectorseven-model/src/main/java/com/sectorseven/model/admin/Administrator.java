package com.sectorseven.model.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.AdminRole;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.util.Views.PublicLight;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * @author RameshNaidu
 */
@Getter
@Setter
@ToString
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "AUTHORITY", discriminatorType = DiscriminatorType.STRING)
public class Administrator {

	
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
	    private String firstName;

	    @Column
	    @JsonView(PublicLight.class)
	    private String lastName;

	    @NotNull
	    @Column
	    @JsonView(PublicLight.class)
	    private String username;

	    @NotNull
	    @Column
	    @JsonView(PublicLight.class)
	    private String password;

	    @Column
	    @JsonView(PublicLight.class)
	    private String email;

	    @NotNull
	    @Column
	    @JsonView(PublicLight.class)
	    private long mobile;

	    @Column
	    @JsonView(PublicLight.class)
	    private Date dateOfBirth;

	    @Column
	    @JsonView(PublicLight.class)
	    private Status status;

	    @Column
	    @JsonView(PublicLight.class)
	    private Date dateCreated;

	    @Transient
	    @JsonView(PublicLight.class)
	    private String verifyPassword;

	    @Transient
	    private Set<AdminRole> role;

	    @Column
	    @JsonView(PublicLight.class)
	    private String image;

	    @Transient
	    @JsonView(PublicLight.class)
	    private CommonsMultipartFile userProfilePic;

	    @Transient
	    @JsonView(PublicLight.class)
	    private String birth;

	    public void setBirth(String birth) {
	        this.birth = birth;
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            Date userBirth = sdf.parse(birth);
	            dateOfBirth = userBirth;
	        } catch (ParseException p) {
	            p.getLocalizedMessage();
	        }
	    }
	
}
