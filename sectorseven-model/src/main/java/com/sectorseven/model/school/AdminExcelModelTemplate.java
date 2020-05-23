package com.sectorseven.model.school;

import javax.persistence.Column;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.Country;
import com.sectorseven.model.Enum.Trending;
import com.sectorseven.model.Enum.VideoType;
import com.sectorseven.model.common.CareerCategory;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.Institutions;
import com.sectorseven.model.common.SevenSigma;
import com.sectorseven.model.util.Views.PublicLight;

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
public class AdminExcelModelTemplate {

	  	@JsonView(PublicLight.class)
	    private SevenSigma sigma;

	    @JsonView(PublicLight.class)
	    private CommonsMultipartFile document;
	    
	    @JsonView(PublicLight.class)
		private VideoType videoType;

	  	@JsonView(PublicLight.class)
	    private CareerSubcategory subCategoury;

	    @JsonView(PublicLight.class)
		private Country country;
	    
	    @JsonView(PublicLight.class)
		private Institutions institution;

	    @Column
	    @JsonView(PublicLight.class)
	    private String  jobsDescription;
	    
	    @Column
	    @JsonView(PublicLight.class)
	    private String  salaryDescription;
	    
		@JsonView(PublicLight.class)
		private String subCategoryName;

		@JsonView(PublicLight.class)
		private CommonsMultipartFile subCategoryIcon;

		@JsonView(PublicLight.class)
		private CommonsMultipartFile landingImage;

		@JsonView(PublicLight.class)
		private CareerCategory careerCategory;

		@JsonView(PublicLight.class)
		private Trending trendingOrNo;
		
	  	@JsonView(PublicLight.class)
	    private String documnetPath;

	    @JsonView(PublicLight.class)
	    private School school;
	    

}
