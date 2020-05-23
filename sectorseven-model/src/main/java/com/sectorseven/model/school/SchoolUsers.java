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
import javax.validation.constraints.NotNull;

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
 *
 */
@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(of = "id")
public class SchoolUsers {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private long id;
	
	
	@Column
    @JsonView(PublicLight.class)
    private String firstName;
	
	
	/*@Column
    @JsonView(PublicLight.class)
    private String middleName;
	*/
	@Column
    @JsonView(PublicLight.class)
    private String lastName;
	
	@Column
    @JsonView(PublicLight.class)
    private long phone;
	
	@Column
    @JsonView(PublicLight.class)
    private String email;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "school")
    @JsonView(PublicLight.class)
    private School school;
	
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
    private AdminRole authority;

}
