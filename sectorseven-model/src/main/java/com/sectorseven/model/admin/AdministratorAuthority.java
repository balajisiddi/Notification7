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
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

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
public class AdministratorAuthority {

	@Id
	@SequenceGenerator(name = "adminAuthorityGen", sequenceName = "adminAuthorityGen_generator")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "adminAuthorityGen")
	@Column(name = "id")
	@JsonView(PublicLight.class)
	private long id;

	/*
	 * @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "administrator")
	 * 
	 * @JsonView(PublicLight.class) private Administrator administrator;
	 */

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "AUTHORITY")
	@JsonView(PublicLight.class)
	private Authority authority;

	@NotNull
	@Column
	@JsonView(PublicLight.class)
	private String userName;

	@NotNull
	@Column
	@JsonView(PublicLight.class)
	private String decryptPassword;

	@NotNull
	@Column
	@JsonView(PublicLight.class)
	private String userType;

	@Column
	@JsonView(PublicLight.class)
	private Long user;

	@Column
	@JsonView(PublicLight.class)
	private Status status;

	@Column
	@JsonView(PublicLight.class)
	private Date dateCreated;

}
