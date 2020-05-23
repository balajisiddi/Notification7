package com.sectorseven.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
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
public class Authority {

    @Id
    @SequenceGenerator(name = "authorityGen", sequenceName = "authority_generator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "authorityGen")
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private long id;

    @Column
    @JsonView(PublicLight.class)
    private String authorityCode;

}
