package com.sectorseven.service.common;

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
    public static final String CONTRIBUTION_DOCUMENTS = "/contributionDocs" ;
    public static final String SUB_CATEGORY_IMAGES = "/subCategoryImages" ;
    public static final String SUB_CATEGORY_ARTICLES = "/subCategoryArticles" ;
    public static final String SUB_CATEGORY_EBOOKS = "/subCategoryEbooks" ;
    public static final String SIGMA_IMAGES = "/sigmaImages" ;
    public static final String SIGMA_PDFS = "/sigmaPDFs" ;
    public static final String SIGMA_PPTS= "/sigmaPPTs" ;
    public static final String STUDEMT_IMAGES = "/studentImages" ;
    public static final String MENTOR_IMAGES= "/mentorImages" ;
    public static final String SCHOOL_TEACHER_IMAGES= "/teacherImages" ;
    public static final String PARENT_IMAGES= "/parentImages" ;
    public static final String commonImages= "/commonImages" ;
}
