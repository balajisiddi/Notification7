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

import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.Authority;
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
public class SchoolAdministratorAuthority {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "schoolUser")
    @JsonView(PublicLight.class)
    private SchoolUsers schoolUser;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "authority")
    @JsonView(PublicLight.class)
    private Authority authority;
	
	@Column
	@JsonView(PublicLight.class)
    private Status status;
	
	@Column
    @JsonView(PublicLight.class)
    private Date dateCreated;
	
	
}
