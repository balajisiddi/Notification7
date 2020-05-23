package com.sectorseven.model.admin;

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

import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.SequenceModule;
import com.sectorseven.model.util.Views.PublicLight;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
public class IdSequence {

    @Id
    @SequenceGenerator(name = "idSequenceTableGen", sequenceName = "idSequenceTableGen_generator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idSequenceTableGen")
    @Column(name = "id")
    @JsonView(PublicLight.class)
    private long id;


    @Column
    @JsonView(PublicLight.class)
    private SequenceModule module;

    @Column
    @JsonView(PublicLight.class)
    private String format;

    @Column
    @JsonView(PublicLight.class)
    private long sequenceNo;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "academicYear")
    @JsonView(PublicLight.class)
    private AcademicYear academicYear;

    @Column
    @JsonView(PublicLight.class)
    private String schoolCode;
    
}