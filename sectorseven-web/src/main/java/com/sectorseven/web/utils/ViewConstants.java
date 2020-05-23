package com.sectorseven.web.utils;

import org.springframework.http.MediaType;


/**
 * @author RameshNaidu
 *
 */
public class ViewConstants {

    private ViewConstants() {
    }

    public static final String OUTPUT = MediaType.APPLICATION_JSON_VALUE;
    public static final String INPUT = MediaType.APPLICATION_JSON_VALUE;

    public static final String SAVE_LOCATION = System.getProperty("catalina.base") + "/sector-seven";
    public static final String STUDENT_DATAUPLOAD_LOCATION = "/uploadedFiles" ; 
    public static final String CATEGORY_IMAGES = "/categoryImages" ;
    public static final String INTERESTS_IMAGES = "/interestsImages" ;
    public static final String LANDING_IMAGES = "/landingImages" ;
    public static final String AM_I_THE_ONE_IMAGES = "/amITheOneImages" ;
    public static final String SUB_CATEGORY_IMAGES = "/subCategoryImages" ;
    public static final String SUB_CATEGORY_INSTITUES = "/subCategoryInstitutes" ;
    public static final String SUB_CATEGORY_DOCUMENTS= "/subCategoryDocuments" ;
    public static final String SUB_CATEGORY_LANDING= "/subCategoryLanding" ;
    public static final String SUB_CATEGORY_SIDE= "/subCategorySide" ;
    public static final String SIGMA_IMAGES = "/sigmaImages" ;
    public static final String SIGMA_SUB_IMAEGES = "/subSigmaImages" ;
    public static final String SIGMA_SUB_DOCUMENTS = "/subSigmaDocuments" ;
    public static final String CONTRIBUTION_DOCUMENTS= "/contributionDocs" ;
    public static final String ASKMENTOR_IMAGES= "/askMentorImages" ;
    public static final String COMMON_IMAGES= "/commonImages" ;
}
