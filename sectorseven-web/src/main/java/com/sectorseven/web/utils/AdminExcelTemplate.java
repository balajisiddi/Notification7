package com.sectorseven.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.util.Log;

import com.ibm.icu.text.SimpleDateFormat;
import com.sectorseven.model.Enum.Country;
import com.sectorseven.model.Enum.Gender;
import com.sectorseven.model.Enum.Salutation;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.Trending;
import com.sectorseven.model.Enum.VideoType;
import com.sectorseven.model.Enum.YesNo;
import com.sectorseven.model.admin.AcademicYear;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.admin.Parents;
import com.sectorseven.model.common.AddEvent;
import com.sectorseven.model.common.CareerCategory;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.CoCreationHub;
import com.sectorseven.model.common.DemandColors;
import com.sectorseven.model.common.DemandLabels;
import com.sectorseven.model.common.DemandSupply;
import com.sectorseven.model.common.DemandYears;
import com.sectorseven.model.common.InstituteSubcat;
import com.sectorseven.model.common.InstitutionCourses;
import com.sectorseven.model.common.Institutions;
import com.sectorseven.model.common.Notification;
import com.sectorseven.model.common.SevenSigma;
import com.sectorseven.model.common.SevenSigmaDetails;
import com.sectorseven.model.common.SubCategoryDetails;
import com.sectorseven.model.common.SuccessPersons;
import com.sectorseven.model.school.School;
import com.sectorseven.model.school.SchoolTeacher;
import com.sectorseven.model.school.SchoolUsers;
import com.sectorseven.model.school.Student;
import com.sectorseven.model.util.MailEngine;
import com.sectorseven.service.School.SchoolTeacherService;
import com.sectorseven.service.admin.CareerService;
import com.sectorseven.service.admin.MentorService;
import com.sectorseven.service.admin.ParentsService;
import com.sectorseven.service.student.CommonService;
import com.sectorseven.service.student.StudentService;

public class AdminExcelTemplate {

	// for sigmaDetails
	public static Map<String, Object> readExcelFile(File file, SevenSigma sigma, VideoType videoType) {
		Map<String, Object> studentAndErrors = new HashMap<String, Object>();
		List<SevenSigmaDetails> sigmaDetailList = new ArrayList<>();
		List<Notification> notificationList = new ArrayList<Notification>();
		List<String> errorsList = new ArrayList<>();
		try {
			FileInputStream selectedFile = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(selectedFile);
			Sheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() != 0) {
					SevenSigmaDetails sigmaDetails = new SevenSigmaDetails();
					SimpleDateFormat sdf= new SimpleDateFormat("dd-mm-yyyy");
					Notification notification = new Notification();
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						boolean error = false;
						Cell cell = cellIterator.next();
						if (cell.getColumnIndex() == 0) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String title = cell.getStringCellValue().toString();
								if(title.length()<=200) {
									sigmaDetails.setTitle(title);
								}
								else {
									error=true;
									errorsList.add("Data too long for title at cell"+cell.getColumnIndex());
								}
							}
							
						} else if (cell.getColumnIndex() == 1) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String youtubeUrl = cell.getStringCellValue().toString();
								String[] val = youtubeUrl.split("=", 2);
								sigmaDetails.setYoutubeUrl(val[1]);
								sigmaDetails.setType("video/mp4");
								sigmaDetails.setDatePublished(sdf.format(new Date()));
								sigmaDetails.setAuthorName("SectorSeven");
								sigmaDetails.setVideoType(VideoType.Youtube);
								notification.setStatus(Status.Active);
								notification.setYoutubeUrl(val[1]);
								notification.setAction("Video");
								notification.setScreen("sigma");
								notification.setMessage("New Video Has been Added");
							}
						}
						if (!error) {
							sigmaDetails.setSevenSigma(sigma);
							sigmaDetails.setVideoType(videoType);
							sigmaDetails.setStatus(Status.Active);
						}
					}
					sigmaDetailList.add(sigmaDetails);
					notificationList.add(notification);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.info(sigmaDetailList.size());
		studentAndErrors.put("sigmaDetails", sigmaDetailList);
		studentAndErrors.put("errors", errorsList);
		studentAndErrors.put("notifications", notificationList);
		return studentAndErrors;
	}

	// for institution upload
	public static Map<String, Object> readExcelFile(File file, CareerSubcategory careerSubcategory, Country country,
			CareerService careerService) {
		Map<String, Object> studentAndErrors = new HashMap<String, Object>();
		List<Institutions> InstitutionsList = new ArrayList<>();
		List<InstituteSubcat> instituteSubcatList = new ArrayList<InstituteSubcat>();
		List<String> errorsList = new ArrayList<>();
		try {
			FileInputStream selectedFile = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(selectedFile);
			Sheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() != 0) {
					Institutions institution = new Institutions();
					InstituteSubcat instituteSubcat = new InstituteSubcat();
					Institutions institute = null;
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						boolean error = false;
						Cell cell = cellIterator.next();
						if (cell.getColumnIndex() == 0) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String instituteName = cell.getStringCellValue().toString();
								//institution.setName(instituteName);
								institution.setName(instituteName.trim());
							}
						} else if (cell.getColumnIndex() == 1) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String instituteUrl = cell.getStringCellValue().toString();
								institute = careerService.getInstituteByUrl(instituteUrl);
								if (institute == null) {
									institution.setInstituteUrl(instituteUrl);
									institution.setCountry(country);
									institution.setCareerSubCat(careerSubcategory);
									institution.setStatus(Status.Active);
									instituteSubcat.setInstitutions(institution);
									instituteSubcat.setSubcatId(careerSubcategory);
									instituteSubcat.setCountry(country);
									instituteSubcat.setStatus(Status.Active);
								} else {
									if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
										instituteSubcat.setInstitutions(institute);
										instituteSubcat.setSubcatId(careerSubcategory);
										instituteSubcat.setCountry(country);
										instituteSubcat.setStatus(institute.getStatus());
									}
								}
							}
						}
						if (!error) {
						}
					}
					if (institution.getCareerSubCat() != null) {
						InstitutionsList.add(institution);
					}
					if (instituteSubcat.getSubcatId() != null) {
						instituteSubcatList.add(instituteSubcat);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.info(InstitutionsList.size());
		studentAndErrors.put("institutes", InstitutionsList);
		studentAndErrors.put("errors", errorsList);
		studentAndErrors.put("instituteSubcats", instituteSubcatList);
		return studentAndErrors;
	}

	// for institution courses
	public static Map<String, Object> readExcelFile(File file, CareerSubcategory careerSubcategory,
			Institutions institution) {
		Map<String, Object> studentAndErrors = new HashMap<String, Object>();
		List<InstitutionCourses> InstitutionCoursesList = new ArrayList<>();
		List<String> errorsList = new ArrayList<>();
		try {
			FileInputStream selectedFile = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(selectedFile);
			Sheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() != 0) {
					InstitutionCourses institutionCourse = new InstitutionCourses();
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						boolean error = false;
						Cell cell = cellIterator.next();
						if (cell.getColumnIndex() == 0) {
							String courseName = cell.getStringCellValue().toString();
							institutionCourse.setCourseName(courseName);
						} else if (cell.getColumnIndex() == 1) {
							String courseUrl = cell.getStringCellValue().toString();
							institutionCourse.setCourseUrl(courseUrl);
						}
						if (!error) {
							institutionCourse.setStatus(Status.Active);
							institutionCourse.setInstitution(institution);
							institutionCourse.setSubCategory(careerSubcategory);
						}
					}

					InstitutionCoursesList.add(institutionCourse);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.info(InstitutionCoursesList.size());
		studentAndErrors.put("instituteCourses", InstitutionCoursesList);
		studentAndErrors.put("errors", errorsList);
		return studentAndErrors;
	}

	// for institutions and courese both
	public static Map<String, Object> readExcelFile(File file, CareerSubcategory careerSubcategory, Country country,
			Institutions courseInstitutions, CareerService careerService) {
		Map<String, Object> institutesAndCourses = new HashMap<String, Object>();
		List<Institutions> InstitutionsList = new ArrayList<>();
		List<InstituteSubcat> instituteSubcatList = new ArrayList<InstituteSubcat>();
		List<InstitutionCourses> institutionCoursesList = new ArrayList<>();
		List<String> errorsList = new ArrayList<>();
		try {
			FileInputStream selectedFile = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(selectedFile);
			Sheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() != 0) {
					Institutions institution = new Institutions();
					InstituteSubcat instituteSubcat = new InstituteSubcat();
					InstitutionCourses institutionCourse = new InstitutionCourses();
					Institutions institute = null;
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if (cell.getColumnIndex() == 0) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								Iterator<Cell> cellIterator1 = row.cellIterator();
								while (cellIterator1.hasNext()) {
									Cell cell1 = cellIterator1.next();
									if (cell1.getColumnIndex() == 0) {
										if (cell1.getCellType() != Cell.CELL_TYPE_BLANK) {
											String instituteName = cell1.getStringCellValue().toString();
											institution.setName(instituteName);
										}
									} else if (cell1.getColumnIndex() == 1) {
										if (cell1.getCellType() != Cell.CELL_TYPE_BLANK) {
											String instituteUrl = cell1.getStringCellValue().toString();
											institute = careerService.getInstituteByUrl(instituteUrl);
											if (institute == null) {
												institution.setInstituteUrl(instituteUrl);
												institution.setCountry(country);
												institution.setCareerSubCat(careerSubcategory);
												institution.setStatus(Status.Active);
												instituteSubcat.setInstitutions(institution);
												instituteSubcat.setSubcatId(careerSubcategory);
												instituteSubcat.setCountry(country);
												instituteSubcat.setStatus(Status.Active);
											} else {
												if (cell1.getCellType() != Cell.CELL_TYPE_BLANK) {
													instituteSubcat.setInstitutions(institute);
													instituteSubcat.setSubcatId(careerSubcategory);
													instituteSubcat.setCountry(country);
													instituteSubcat.setStatus(institute.getStatus());
												}
											}
										}
									} else if (cell1.getColumnIndex() == 2) {
										if (cell1.getCellType() != Cell.CELL_TYPE_BLANK) {
											String courseUrl = cell1.getStringCellValue().toString();
											institutionCourse.setCourseUrl(courseUrl);
											institutionCourse.setStatus(Status.Active);
											if(institution.getCareerSubCat()!=null) {
												institutionCourse.setInstitution(institution);
											}
											else if(institution.getCareerSubCat()==null) {
												institutionCourse.setInstitution(institute);
											}
											institutionCourse.setSubCategory(careerSubcategory);
										}
										
									} else if (cell1.getColumnIndex() == 3) {
										if (cell1.getCellType() != Cell.CELL_TYPE_BLANK) {
											String courseName = cell1.getStringCellValue().toString();
											institutionCourse.setCourseName(courseName);
										}
									}
								}
								if (institution.getCareerSubCat() != null) {
//									InstitutionsList.add(institution);
									careerService.saveInstitution(institution);
								}
								if (instituteSubcat.getSubcatId() != null) {
									instituteSubcatList.add(instituteSubcat);
								}
								if (institutionCourse.getSubCategory() != null) {
									institutionCoursesList.add(institutionCourse);

								}
							} 
							else if(cell==null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
								Iterator<Cell> cellIterator2 = row.cellIterator();
								while (cellIterator2.hasNext()) {
									Cell cell2 = cellIterator2.next();
									if (cell2.getColumnIndex() == 2) {
										if (cell2.getCellType() != Cell.CELL_TYPE_BLANK) {
											String courseName = cell2.getStringCellValue().toString();
											institutionCourse.setCourseName(courseName);
										}
									} else if (cell2.getColumnIndex() == 3) {
										if (cell2.getCellType() != Cell.CELL_TYPE_BLANK) {
											String courseUrl = cell2.getStringCellValue().toString();
											institutionCourse.setCourseUrl(courseUrl);
											institutionCourse.setStatus(Status.Active);
											if(institution.getCareerSubCat()!=null) {
												institutionCourse.setInstitution(institution);
											}
											else if(institution.getCareerSubCat()==null) {
												institutionCourse.setInstitution(institute);
											}
											institutionCourse.setSubCategory(careerSubcategory);
										}
									}
								}
								if (institutionCourse.getSubCategory() != null) {
									institutionCoursesList.add(institutionCourse);

								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.info(instituteSubcatList.size());
		Log.info(institutionCoursesList.size());
		institutesAndCourses.put("institutes", InstitutionsList);
		institutesAndCourses.put("errors", errorsList);
		institutesAndCourses.put("instituteSubcats", instituteSubcatList);
		institutesAndCourses.put("instituteCourses", institutionCoursesList);
		return institutesAndCourses;
	}
	//for subcategory youtube url excel
	public static Map<String, Object> readExcelFile(File file, CareerSubcategory careerSubcategory) {
		Map<String, Object> subcategoryyoutubeurlAndErrors = new HashMap<String, Object>();
		List<SubCategoryDetails> subCategoryDetailsList = new ArrayList<>();
		List<Notification> notificationList = new ArrayList<Notification>();
		List<String> errorsList = new ArrayList<>();
		try {
			FileInputStream selectedFile = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(selectedFile);
			Sheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() != 0) {
					SubCategoryDetails subCategoryDetail = new SubCategoryDetails();
					Notification notification = new Notification();
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						boolean error = false;
						Cell cell = cellIterator.next();
						if (cell.getColumnIndex() == 0) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String title = cell.getStringCellValue().toString();
								if(title.length()<=200) {
									subCategoryDetail.setTitle(title);
								}
								else {
									error=true;
									errorsList.add("Data too long for title at cell"+cell.getColumnIndex());
								}							}
							
						} else if (cell.getColumnIndex() == 1) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String youtubeUrl = cell.getStringCellValue().toString();
								SimpleDateFormat sdf= new SimpleDateFormat("dd-mm-yyyy");
								String[] val = youtubeUrl.split("=", 2);
								subCategoryDetail.setYoutubeUrl(val[1]);
								subCategoryDetail.setType("video/mp4");
								subCategoryDetail.setDatePublished(sdf.format(new Date()));
								subCategoryDetail.setAuthorName("SectorSeven");
								subCategoryDetail.setTags(careerSubcategory.getCareerCategory().getCategoryName());
								subCategoryDetail.setThumbnailUrl("http://img.youtube.com/vi/" + val[1] + "/0.jpg");
								notification.setStatus(Status.Active);
								notification.setYoutubeUrl(val[1]);
								notification.setAction("Video");
								notification.setScreen("subCategory");
								notification.setSubcategory(careerSubcategory);
								notification.setMessage("New Video Has been Added");
							}
						}
						if (!error) {
							subCategoryDetail.setCareerSubcat(careerSubcategory);
							subCategoryDetail.setVideoType(VideoType.Youtube);
							subCategoryDetail.setStatus(Status.Active);
						}
					}
					subCategoryDetailsList.add(subCategoryDetail);
					notificationList.add(notification);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.info(subCategoryDetailsList.size());
		subcategoryyoutubeurlAndErrors.put("youtubeVideos", subCategoryDetailsList);
		subcategoryyoutubeurlAndErrors.put("errors", errorsList);
		subcategoryyoutubeurlAndErrors.put("notifications", notificationList);
		return subcategoryyoutubeurlAndErrors;
	}
	
	//for subcategory excel upload
	public static Map<String, Object> readExcelFile(File file, CareerSubcategory careerSubcategory, Trending trending) {
		Map<String, Object> subcatAndErrors = new HashMap<String, Object>();
		List<String> errorsList = new ArrayList<>();
		try {
			FileInputStream selectedFile = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(selectedFile);
			Sheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() != 0) {
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						boolean error = false;
						Cell cell = cellIterator.next();
						if (cell.getColumnIndex() == 0) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String subCategoryName= cell.getStringCellValue().toString();
								careerSubcategory.setSubCategoryName(subCategoryName);
							}
							
						} 
						if (cell.getColumnIndex() == 1) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String landingQuote= cell.getStringCellValue().toString();
								if(landingQuote.length()<=1500) {
									careerSubcategory.setLandingQuote(landingQuote);
									}
									else {
										error=true;
										errorsList.add("Data too long for landingQuote");
										
									}
							}
							
						} 
						else if (cell.getColumnIndex() == 2) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String landingDescription= cell.getStringCellValue().toString();
								if(landingDescription.length()<=2000) {
									careerSubcategory.setLandingDesc(landingDescription);
									}
									else {
										error=true;
										errorsList.add("Data too long for landingDescription");
										
									}
							}
						}
						else if (cell.getColumnIndex() == 3) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String amItheOne= cell.getStringCellValue().toString();
								if(amItheOne.length()<=2000) {
									careerSubcategory.setAmITheOne(amItheOne);
									}
									else {
										error=true;
										errorsList.add("Data too long for amItheOne");
										
									}
							}
						}
						else if (cell.getColumnIndex() == 4) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String skills= cell.getStringCellValue().toString();
								if(skills.length()<=2000) {
								careerSubcategory.setSkills(skills);
								}
								else {
									error=true;
									errorsList.add("Data too long for skills");
									
								}
							}
						}
						else if (cell.getColumnIndex() == 5) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String roles= cell.getStringCellValue().toString();
								if(roles.length()<=1500) {
									careerSubcategory.setRoles(roles);
									}
									else {
										error=true;
										errorsList.add("Data too long for roles");
									}
							}
						}
						else if (cell.getColumnIndex() == 6) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String htgt= cell.getStringCellValue().toString();
								if(htgt.length()<=1500) {
									careerSubcategory.setHowToGetThere(htgt);
									}
									else {
										error=true;
										errorsList.add("Data too long for htgt");
									}
							}
						}
						else if (cell.getColumnIndex() == 7) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String cc1= cell.getStringCellValue().toString();
								if(cc1.length()<=1500) {
									careerSubcategory.setCourseCat1(cc1);
									}
									else {
										error=true;
										errorsList.add("Data too long for cc1");
									}
							}
						}
						else if (cell.getColumnIndex() == 8) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String cc2= cell.getStringCellValue().toString();
								if(cc2.length()<=1500) {
									careerSubcategory.setCourseCat2(cc2);
									}
									else {
										error=true;
										errorsList.add("Data too long for cc2");
									}
							}
						}
						else if (cell.getColumnIndex() == 9) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String cc3= cell.getStringCellValue().toString();
								if(cc3.length()<=1500) {
									careerSubcategory.setCourseCat3(cc3);
									}
									else {
										error=true;
										errorsList.add("Data too long for cc3");
									}
							}
						}
						else if (cell.getColumnIndex() == 10) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String cc4= cell.getStringCellValue().toString();
								if(cc4.length()<=1500) {
									careerSubcategory.setCourseCat4(cc4);
									}
									else {
										error=true;
										errorsList.add("Data too long for cc4");
									}
							}
						}
						else if (cell.getColumnIndex() == 11) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String cc5= cell.getStringCellValue().toString();
								if(cc5.length()<=2500) {
									careerSubcategory.setCourseCat5(cc5);
									}
									else {
										error=true;
										errorsList.add("Data too long for cc5");
									}
							}
						}
						else if (cell.getColumnIndex() == 12) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String cc6= cell.getStringCellValue().toString();
								if(cc6.length()<=1500) {
									careerSubcategory.setCourseCat6(cc6);
									}
									else {
										error=true;
										errorsList.add("Data too long for cc6");
									}
							}
						}
						else if (cell.getColumnIndex() == 13) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String ee1= cell.getStringCellValue().toString();
								if(ee1.length()<=1500) {
									careerSubcategory.setEntranceCat1(ee1);
									}
									else {
										error=true;
										errorsList.add("Data too long for ee1");
									}
							}
						}
						else if (cell.getColumnIndex() == 14) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String ee2= cell.getStringCellValue().toString();
								if(ee2.length()<=1500) {
									careerSubcategory.setEntranceCat2(ee2);
									}
									else {
										error=true;
										errorsList.add("Data too long for ee2");
									}
							}
						}
						else if (cell.getColumnIndex() == 15) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String ee3= cell.getStringCellValue().toString();
								if(ee3.length()<=1500) {
									careerSubcategory.setEntranceCat3(ee3);
									}
									else {
										error=true;
										errorsList.add("Data too long for ee3");
									}
							}
						}
						else if (cell.getColumnIndex() == 16) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String ee4= cell.getStringCellValue().toString();
								if(ee4.length()<=5000) {
									careerSubcategory.setEntranceCat4(ee4);
									}
									else {
										error=true;
										errorsList.add("Data too long for ee4");
									}
							}
						}
						else if (cell.getColumnIndex() == 17) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String jd= cell.getStringCellValue().toString();
								if(jd.length()<=1000) {
									careerSubcategory.setJobsDescription(jd);
									}
									else {
										error=true;
										errorsList.add("Data too long for jd");
									}
							}
						}
						else if (cell.getColumnIndex() == 18) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String sd= cell.getStringCellValue().toString();
								if(sd.length()<=1000) {
									careerSubcategory.setSalaryDescription(sd);
									}
									else {
										error=true;
										errorsList.add("Data too long for sd");
									}
							}
						}
						else if (cell.getColumnIndex() == 19) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String istrending= cell.getStringCellValue().toString();
								if(Trending.IsTrending.getId().equals(istrending)) {
									careerSubcategory.setTrending(Trending.IsTrending);
								}
								else if(Trending.IsPrevious.getId().equals(istrending)) {
									careerSubcategory.setTrending(Trending.IsPrevious);
								}
							}
						}
						if (!error) {}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		subcatAndErrors.put("subcatobj", careerSubcategory);
		subcatAndErrors.put("errors", errorsList);
		return subcatAndErrors;
	}
	
	//for student registration
	public static Map<String, Object> readExcelFile(File file, CareerService careerService, StudentService studentService, ParentsService parentsService) {
		Map<String, Object> studentAndErrors = new HashMap<String, Object>();
		List<String> errorsList = new ArrayList<>();
		int studentCount=0;
		try {
			List<Student> studentDetailsExcel= new ArrayList<Student>();
			List<Parents> parentDetailsExcel= new ArrayList<Parents>();
			FileInputStream selectedFile = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(selectedFile);
			Sheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() != 0) {
					Student student= new Student();
					Parents parents= new Parents();
					boolean error = false;
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if (cell.getColumnIndex() == 0) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String firstName = cell.getStringCellValue().toString();
								student.setFirstName(firstName);
							}
							
						} else if (cell.getColumnIndex() == 1) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String lastName = cell.getStringCellValue().toString();
								student.setLastName(lastName);								
							}
						}
						else if (cell.getColumnIndex() == 2) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String date_of_birth = String.valueOf(cell.getStringCellValue().toString());
								student.setDate_of_birth(date_of_birth);						
							}
						}
						else if (cell.getColumnIndex() == 3) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String description = cell.getStringCellValue().toString();
//								student.setDescription(description);				
							}
						}
						else if (cell.getColumnIndex() == 4) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String studentClass = cell.getStringCellValue().toString();
								student.setStudentClass(studentClass);				
							}
						}
						else if (cell.getColumnIndex() == 5) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String section = cell.getStringCellValue().toString();
								student.setSection(section);		
							}
						}
						else if (cell.getColumnIndex() == 6) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String email = cell.getStringCellValue().toString();
								Student checkStudentWithMail = studentService.findByEmail(email);
                                if(checkStudentWithMail != null) {
                                	errorsList.add("Email '"+email+"' already existed");
                                	error = true;
                                }else {
                                	student.setEmail(email);
                                }
									
							}
						}
						else if (cell.getColumnIndex() == 7) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String gender = cell.getStringCellValue().toString();
								if(Gender.Female.getId().equals(gender)) {
									student.setGender(Gender.Female);
								}
								else if(Gender.Male.getId().equals(gender)) {
									student.setGender(Gender.Male);
								}
								
							}
						}
						else if (cell.getColumnIndex() == 8) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String studentStream = cell.getStringCellValue().toString();
								if(YesNo.No.getId().equals(studentStream)) {
									student.setStudentStream(YesNo.No);
								}
								else if(YesNo.Yes.getId().equals(studentStream)) {
									student.setStudentStream(YesNo.Yes);
								}
							}
						}
						
						else if (cell.getColumnIndex() == 9) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String school = cell.getStringCellValue().toString();
								School schoool=careerService.findBySchoolName(school);
								if(schoool==null) {
									errorsList.add("School "+school+" doen'nt exist");
									error=true;
								}
								else {
									student.setSchool(schoool);									
								}
							}
						}
						else if (cell.getColumnIndex() == 10) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String acedemicYear = cell.getStringCellValue().toString();
								AcademicYear academicYear= careerService.findByAcadamicYearName(acedemicYear);
								student.setAcedemicYear(academicYear);
							}
						}
						else if (cell.getColumnIndex() == 11) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String schoolTeacher = cell.getStringCellValue().toString();
								SchoolTeacher schooolTeacher= careerService.findBySchoolTeacherFirstName(schoolTeacher);
								if(schooolTeacher==null) {
									errorsList.add("SchoolTeacher"+schoolTeacher+" doen'nt exist");
									error=true;
								}
								else {
									student.setSchoolTeacher(schooolTeacher);								}
							}
						}
						else if (cell.getColumnIndex() == 12) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								long mobile = (long)cell.getNumericCellValue();
                            	Student existedStudentOrNot = studentService.findByMobile(Double.valueOf(mobile).longValue());
                            	if(existedStudentOrNot != null) {
                                	errorsList.add("Mobile No."+mobile+" already existed");
                                	error = true;
                                }else {
                                	student.setMobile(mobile);
                                }
								
							}
						}
						else if (cell.getColumnIndex() == 13) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String fatherName = cell.getStringCellValue().toString();
								parents.setFatherName(fatherName);
							}
						}
						else if (cell.getColumnIndex() == 14) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								long fatherMobile = (long)cell.getNumericCellValue();
								parents.setFatherMobile(fatherMobile);
							}
						}
						else if (cell.getColumnIndex() == 15) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String fatherEmail = cell.getStringCellValue().toString();
								parents.setFatherEmail(fatherEmail);
							}
						}
						else if (cell.getColumnIndex() == 16) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String motherName = cell.getStringCellValue().toString();
								parents.setMotherName(motherName);
							}
						}
						else if (cell.getColumnIndex() == 17) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								long motherMobile = (long)cell.getNumericCellValue();
								parents.setMotherMobile(motherMobile);
							}
						}
						else if (cell.getColumnIndex() == 18) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String motherEmail = cell.getStringCellValue().toString();
								parents.setMotherEmail(motherEmail);
							}
						}
						else if (cell.getColumnIndex() == 19) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String fatherOccupation = cell.getStringCellValue().toString();
								parents.setFatherOccupation(fatherOccupation);
							}
						}
						else if (cell.getColumnIndex() == 20) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String motherOccupation = cell.getStringCellValue().toString();
								parents.setMotherOccupation(motherOccupation);
							}
						}
						else if (cell.getColumnIndex() == 21) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String address = cell.getStringCellValue().toString();
								parents.setAddress(address);
							}
						}
						else if (cell.getColumnIndex() == 22) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String address1 = cell.getStringCellValue().toString();
								parents.setAddress1(address1);
							}
						}
						else if (cell.getColumnIndex() == 23) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String city = cell.getStringCellValue().toString();
								parents.setCity(city);
							}
						}
						else if (cell.getColumnIndex() == 24) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String state = cell.getStringCellValue().toString();
								parents.setState(state);
							}
						}
						else if (cell.getColumnIndex() == 25) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String pincode = cell.getStringCellValue().toString();
								parents.setPincode(pincode);
							}
						}
						
					}
					if (!error) {
						List<Parents> existedParentData  = 	parentsService.findByFatherMobileOrFatherEmail(parents.getFatherMobile(), parents.getFatherEmail());
						student.setStatus(Status.Active);
						if(student.getSchool()!=null && student.getSchoolTeacher()!=null) {
							if(existedParentData.isEmpty()) {
								student.setParents(parents);
								Student insertedStudent= studentService.saveStudentAndParent(student, true);
								studentDetailsExcel.add(insertedStudent);
							}else {
								Parents existparent = existedParentData.get(0);
								student.setParents(existparent);
								studentService.saveStudent(student, true);
							}
							studentCount++;
						}							
					}
				}
			}
			
			XSSFWorkbook userNamesPasswords = new XSSFWorkbook();
			XSSFSheet userNamesPasswordsSheet = userNamesPasswords.createSheet();
			 
			    int rowCount = 0;
			 
			    for (Student user : studentDetailsExcel) {
			        Row row = userNamesPasswordsSheet.createRow(++rowCount);
			        //writing cells
			        Cell cell = row.createCell(1);
			        cell.setCellValue(user.getUserName());
			     
			        cell = row.createCell(2);
			        cell.setCellValue(user.getDecryptPassword());
			     
			        cell = row.createCell(3);
			        cell.setCellValue(user.getEmail());
			        
			        cell = row.createCell(4);
			        cell.setCellValue("ParentDetails");
			        
			        cell = row.createCell(5);
			        cell.setCellValue(user.getParents().getUserName());
			        
			        cell = row.createCell(6);
			        cell.setCellValue(user.getParents().getDecryptPassword());
			        
			        cell = row.createCell(7);
			        cell.setCellValue(user.getParents().getFatherEmail());
			    }
			    try (FileOutputStream outputStream = new FileOutputStream(filePath+"/userStudents.xlsx")) {
			    	userNamesPasswords.write(outputStream);
			    }
			    BodyPart messageBodypart= new MimeBodyPart();
				messageBodypart.setText("Username and Passwords attached to this mail");
				Multipart multipart= new MimeMultipart();
				DataSource source = new FileDataSource(filePath+"/userStudents.xlsx");
				messageBodypart.setDataHandler(new DataHandler(source));
				messageBodypart.setFileName(filePath+"/userStudents.xlsx");
		         multipart.addBodyPart(messageBodypart);
		         MailEngine.sendMultipartMail(multipart, "Usernames and Passwords", "balaji.siddi@gmail.com");
		         //end of experiment
		} catch (Exception e) {
			e.printStackTrace();
		}
		studentAndErrors.put("count", studentCount);
		studentAndErrors.put("errors", errorsList);
		return studentAndErrors;
	}
	
	//for mentor registration
	public static Map<String, Object> readExcelFile(File file, CareerService careerService, MentorService mentorService) {
		Map<String, Object> mentorAndErrors = new HashMap<String, Object>();
		int mentorCount=0;
		List<String> errorsList = new ArrayList<>();
		try {
			List<Mentor> mentorDetailsExcel= new ArrayList<Mentor>();
			FileInputStream selectedFile = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(selectedFile);
			Sheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() != 0) {
					Mentor mentor= new Mentor();
					boolean error = false;
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if (cell.getColumnIndex() == 0) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String salutation = cell.getStringCellValue().toString();
								if(Salutation.Mr.getId().equals(salutation)) {
									mentor.setSalutation(Salutation.Mr);
								}
								else if(Salutation.Ms.getId().equals(salutation)) {
									mentor.setSalutation(Salutation.Ms);
								}
								else if(Salutation.Mrs.getId().equals(salutation)) {
									mentor.setSalutation(Salutation.Mrs);
								}
								else if(Salutation.Dr.getId().equals(salutation)) {
									mentor.setSalutation(Salutation.Dr);
								}
								
							}
							
						} else if (cell.getColumnIndex() == 1) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String firstName = cell.getStringCellValue().toString();
								mentor.setFirstName(firstName);							
							}
						}
						else if (cell.getColumnIndex() == 2) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String lastName = String.valueOf(cell.getStringCellValue().toString());
								mentor.setLastName(lastName);							
							}
						}
						else if (cell.getColumnIndex() == 3) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String education = cell.getStringCellValue().toString();
								mentor.setEducation(education);			
							}
						}
						else if (cell.getColumnIndex() == 4) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String date_of_birth = cell.getStringCellValue().toString();
								mentor.setDate_of_birth(date_of_birth);				
							}
						}
						else if (cell.getColumnIndex() == 5) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String occupation = cell.getStringCellValue().toString();
								mentor.setOccupation(occupation);	
								mentor.setExpertise(occupation);
							}
						}
						else if (cell.getColumnIndex() == 6) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String categoryName = cell.getStringCellValue().toString();
								CareerCategory occupationcareerCategory=  careerService.findByCategouryName(categoryName);
								if(occupationcareerCategory==null) {
									error=true;
									errorsList.add("occupationcareerCategory doesn't exist");
								}
								else {
									mentor.setOccupationCategory(occupationcareerCategory);
								}
							}
						}
						else if (cell.getColumnIndex() == 7) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String gender = cell.getStringCellValue().toString();
								if(Gender.Male.getId().equals(gender)) {
									mentor.setGender(Gender.Male);
								}
								else if(Gender.Female.getId().equals(gender)){
									mentor.setGender(Gender.Female);
								}
							}
						}
						
						else if (cell.getColumnIndex() == 8) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								long mobile = (long)cell.getNumericCellValue();
								mentor.setMobile(mobile);
							}
						}
						else if (cell.getColumnIndex() == 9) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String email = cell.getStringCellValue().toString();
								mentor.setEmail(email);
							}
						}
						else if (cell.getColumnIndex() == 10) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String address = cell.getStringCellValue().toString();
								mentor.setAddress(address);
							}
						}
						else if (cell.getColumnIndex() == 11) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String address1 = cell.getStringCellValue().toString();
								mentor.setAddress1(address1);
							}
						}
						
						else if (cell.getColumnIndex() == 12) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String city = cell.getStringCellValue().toString();
								mentor.setCity(city);
							}
						}
						else if (cell.getColumnIndex() == 13) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String state = cell.getStringCellValue().toString();
								mentor.setState(state);
							}
						}
						else if (cell.getColumnIndex() == 14) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String pinCode = cell.getStringCellValue().toString();
								mentor.setPinCode(pinCode);
							}
						}
						else if (cell.getColumnIndex() == 15) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String twitterUrl =  cell.getStringCellValue().toString();
								mentor.setTwitterUrl(twitterUrl);
							}
						}
						else if (cell.getColumnIndex() == 16) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String linkedUrl = cell.getStringCellValue().toString();
								mentor.setLinkedUrl(linkedUrl);
							}
						}
						else if (cell.getColumnIndex() == 17) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String instagramUrl = cell.getStringCellValue().toString();
								mentor.setInstagramUrl(instagramUrl);
							}
						}
						else if (cell.getColumnIndex() == 18) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String description = cell.getStringCellValue().toString();
//								mentor.setDescription(description);
							}
						}
						else if (cell.getColumnIndex() == 19) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String solutionToStudents = cell.getStringCellValue().toString();
								if(YesNo.Yes.getId().equals(solutionToStudents)) {
									mentor.setSolutionToStudents(YesNo.Yes);
								}
								else if(YesNo.No.getId().equals(solutionToStudents)) {
									mentor.setSolutionToStudents(YesNo.No);
								}
							}
						}
						else if (cell.getColumnIndex() == 20) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String contributionTime = cell.getStringCellValue().toString();
								mentor.setContributionTime(contributionTime);
							}
						}
						else if (cell.getColumnIndex() == 21) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String childActivities = cell.getStringCellValue().toString();
								if(YesNo.Yes.getId().equals(childActivities)) {
									mentor.setChildActivities(YesNo.Yes);
								}
								else if(YesNo.No.getId().equals(childActivities)) {
									mentor.setChildActivities(YesNo.No);
								}
							}
						}
						else if (cell.getColumnIndex() == 22) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String mentorTalks = cell.getStringCellValue().toString();
								if(YesNo.Yes.getId().equals(mentorTalks)) {
									mentor.setMentorTalks(YesNo.Yes);
								}
								else if(YesNo.No.getId().equals(mentorTalks)) {
									mentor.setChildActivities(YesNo.No);
								}
							}
						}
						else if (cell.getColumnIndex() == 23) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String resonForMentor = cell.getStringCellValue().toString();
								mentor.setResonForMentor(resonForMentor);
							}
						}
						else if (cell.getColumnIndex() == 24) {
							if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
								String diffMentorSectSeven = cell.getStringCellValue().toString();
								mentor.setDiffMentorSectSeven(diffMentorSectSeven);
							}
						}
					}
					if (!error) {
						List<Mentor> mentorr= mentorService.findByEmailOrContactNo(mentor.getEmail(), mentor.getMobile());
						if(mentorr.size()==0) {
							Mentor insertedMentor= mentorService.save(mentor, true);
							mentorCount++;
							mentorDetailsExcel.add(insertedMentor);
						}
						else {
							errorsList.add("For "+mentor.getFirstName()+" Email or Mobile already exists");
						}
					}
				}
			}
			
			XSSFWorkbook userNamesPasswords = new XSSFWorkbook();
			XSSFSheet userNamesPasswordsSheet = userNamesPasswords.createSheet();
			 
			    int rowCount = 0;
			 
			    for (Mentor user : mentorDetailsExcel) {
			        Row row = userNamesPasswordsSheet.createRow(++rowCount);
			        //writing cells
			        Cell cell = row.createCell(1);
			        cell.setCellValue(user.getUserName());
			     
			        cell = row.createCell(2);
			        cell.setCellValue(user.getDecryptPassword());
			     
			        cell = row.createCell(3);
			        cell.setCellValue(user.getEmail());
			    }
			    try (FileOutputStream outputStream = new FileOutputStream(filePath+"/userMentors.xlsx")) {
			    	userNamesPasswords.write(outputStream);
			    }
			    BodyPart messageBodypart= new MimeBodyPart();
				messageBodypart.setText("Username and Passwords attached to this mail");
				Multipart multipart= new MimeMultipart();
				DataSource source = new FileDataSource(filePath+"/userMentors.xlsx");
				messageBodypart.setDataHandler(new DataHandler(source));
				messageBodypart.setFileName(filePath+"/userMentors.xlsx");
		         multipart.addBodyPart(messageBodypart);
		         MailEngine.sendMultipartMail(multipart, "Usernames and Passwords", "balaji.siddi@gmail.com");
		         //end of experiment
		} catch (Exception e) {
			e.printStackTrace();
		}
		mentorAndErrors.put("errors", errorsList);
		mentorAndErrors.put("count", mentorCount);
		return mentorAndErrors;
	}
	public static final String filePath = ViewConstants.SAVE_LOCATION+"/uploadedFiles";
	//for teacher registration
		public static Map<String, Object> readExcelFile(File file, CareerService careerService, SchoolTeacherService schoolTeacherService) {
			Map<String, Object> mentorAndErrors = new HashMap<String, Object>();
			int teacherCount=0;
			
			List<String> errorsList = new ArrayList<>();
			try {
				List<SchoolUsers> schoolUsersDetailsExcel= new ArrayList<SchoolUsers>();
				FileInputStream selectedFile = new FileInputStream(file);
				Workbook workbook = WorkbookFactory.create(selectedFile);
				Sheet sheet = workbook.getSheetAt(0);
				// Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					if (row.getRowNum() != 0) {
						SchoolTeacher schoolTeacher= new SchoolTeacher();
						boolean error = false;
						Iterator<Cell> cellIterator = row.cellIterator();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							if (cell.getColumnIndex() == 0) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String firstName = cell.getStringCellValue().toString();
									schoolTeacher.setFirstName(firstName);						
								}
							}
							else if (cell.getColumnIndex() == 1) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String lastName = String.valueOf(cell.getStringCellValue().toString());
									schoolTeacher.setLastName(lastName);							
								}
							}
							else if (cell.getColumnIndex() == 2) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String date_of_birth = cell.getStringCellValue().toString();
									schoolTeacher.setDate_of_birth(date_of_birth);		
								}
							}
							else if (cell.getColumnIndex() == 3) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String schoolName = cell.getStringCellValue().toString();
									School school= careerService.findBySchoolName(schoolName);
									schoolTeacher.setSchool(school);			
								}
							}
							else if (cell.getColumnIndex() == 4) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String gender = cell.getStringCellValue().toString();
									if(Gender.Male.getId().equals(gender)) {
										schoolTeacher.setGender(Gender.Male);
									}
									else if(Gender.Female.getId().equals(gender)){
										schoolTeacher.setGender(Gender.Female);
									}
								}
							}
							else if (cell.getColumnIndex() == 5) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String expertise = cell.getStringCellValue().toString();
									schoolTeacher.setExpertise(expertise);
									
								}
							}
							else if (cell.getColumnIndex() == 6) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									long mobile = (long)cell.getNumericCellValue();
									schoolTeacher.setMobile(mobile);
								}
							}
							
							
							else if (cell.getColumnIndex() == 7) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String email = cell.getStringCellValue().toString();
									schoolTeacher.setEmail(email);
								}
							}
							else if (cell.getColumnIndex() == 8) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String address = cell.getStringCellValue().toString();
									schoolTeacher.setAddress(address);
								}
							}
							else if (cell.getColumnIndex() == 9) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String address1 = cell.getStringCellValue().toString();
									schoolTeacher.setAddress1(address1);
								}
							}
							
							else if (cell.getColumnIndex() == 10) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String city = cell.getStringCellValue().toString();
									schoolTeacher.setCity(city);
								}
							}
							else if (cell.getColumnIndex() == 11) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String state = cell.getStringCellValue().toString();
									schoolTeacher.setState(state);
								}
							}
							else if (cell.getColumnIndex() == 12) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String pinCode = cell.getStringCellValue().toString();
									schoolTeacher.setPincode(pinCode);
								}
							}
							else if (cell.getColumnIndex() == 13) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String hobbies =  cell.getStringCellValue().toString();
									schoolTeacher.setHobbies(hobbies);
								}
							}
							else if (cell.getColumnIndex() == 14) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String interests = cell.getStringCellValue().toString();
									schoolTeacher.setInterests(interests);
								}
							}
							else if (cell.getColumnIndex() == 15) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String teachersTalkContribution = cell.getStringCellValue().toString();
									if(YesNo.Yes.getId().equals(teachersTalkContribution)) {
										schoolTeacher.setTeachersTalkContribution(true);
									}
									else if(YesNo.No.getId().equals(teachersTalkContribution)) {
										schoolTeacher.setTeachersTalkContribution(false);
									}
								}
							}
							else if (cell.getColumnIndex() == 14) {
								if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
									String description = cell.getStringCellValue().toString();
									if(description.length()<=200) {
//										schoolTeacher.setDescription(description);
									}
									else {
										error=true;
										errorsList.add("Data too long for Description to "+schoolTeacher.getFirstName());
									}
								}
							}
						}
						if (!error) {
							List<SchoolTeacher> schoolTeacherr= schoolTeacherService.findByEmailOrContactNo(schoolTeacher.getEmail(), schoolTeacher.getMobile());
							if(schoolTeacherr.size()==0) {
								SchoolUsers schoolUser= schoolTeacherService.save(schoolTeacher, true);
								teacherCount++;
								schoolUsersDetailsExcel.add(schoolUser);
							}
							else {
								errorsList.add("For "+schoolTeacher.getFirstName()+" Email or Mobile already exists");
							}
						}
					}
				}
				XSSFWorkbook userNamesPasswords = new XSSFWorkbook();
				XSSFSheet userNamesPasswordsSheet = userNamesPasswords.createSheet();
				 
				    int rowCount = 0;
				 
				    for (SchoolUsers user : schoolUsersDetailsExcel) {
				        Row row = userNamesPasswordsSheet.createRow(++rowCount);
				        //writing cells
				        Cell cell = row.createCell(1);
				        cell.setCellValue(user.getUserName());
				     
				        cell = row.createCell(2);
				        cell.setCellValue(user.getDecryptPassword());
				     
				        cell = row.createCell(3);
				        cell.setCellValue(user.getEmail());
				    }
				    try (FileOutputStream outputStream = new FileOutputStream(filePath+"/userTeachers.xlsx")) {
				    	userNamesPasswords.write(outputStream);
				    }
				    BodyPart messageBodypart= new MimeBodyPart();
					messageBodypart.setText("Username and Passwords attached to this mail");
					Multipart multipart= new MimeMultipart();
					DataSource source = new FileDataSource(filePath+"/userTeachers.xlsx");
					messageBodypart.setDataHandler(new DataHandler(source));
					messageBodypart.setFileName(filePath+"/userTeachers.xlsx");
			         multipart.addBodyPart(messageBodypart);
			         MailEngine.sendMultipartMail(multipart, "Usernames and Passwords", "balaji.siddi@gmail.com");
			         //end of experiment
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			mentorAndErrors.put("errors", errorsList);
			mentorAndErrors.put("count", teacherCount);
			return mentorAndErrors;
		}
		
		//for successfulPersonalities creation
				public static Map<String, Object> readExcelFile(File file, CareerService careerService, CommonService commonService) {
					Map<String, Object> mentorAndErrors = new HashMap<String, Object>();
					List<String> errorsList = new ArrayList<>();
					try {
						FileInputStream selectedFile = new FileInputStream(file);
						Workbook workbook = WorkbookFactory.create(selectedFile);
						Sheet sheet = workbook.getSheetAt(0);
						// Iterate through each rows one by one
						Iterator<Row> rowIterator = sheet.iterator();
						while (rowIterator.hasNext()) {
							Row row = rowIterator.next();
							if (row.getRowNum() != 0) {
								SuccessPersons successPersons= new SuccessPersons();
								boolean error = false;
								Iterator<Cell> cellIterator = row.cellIterator();
								while (cellIterator.hasNext()) {
									Cell cell = cellIterator.next();
									if (cell.getColumnIndex() == 0) {
										if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
											String name = cell.getStringCellValue().toString();
											successPersons.setName(name);					
										}
									}
									else if (cell.getColumnIndex() == 1) {
										if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
											String description = cell.getStringCellValue().toString();
											if(description.length()<=1500) {
												successPersons.setDescription(description);													}
												else {
													error=true;
													errorsList.add("Data too long for description");
												}
										}
									}
									else if (cell.getColumnIndex() == 2) {
										if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
											String subCatName = String.valueOf(cell.getStringCellValue().toString());
											if(subCatName!=null) {
												CareerSubcategory careerSubcategory=  careerService.findByCareerSubCategoryName(subCatName);
												if(careerSubcategory==null) {
													error=true;
													errorsList.add("careerSubcategory doesn't exist");
												}
												else {
													successPersons.setCareerSubCat(careerSubcategory);				
												}
											}
											else {
												error=true;
											}
											
										}
									}
								}
								if (!error && successPersons.getCareerSubCat()!=null) {
									commonService.savePerson(successPersons);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					mentorAndErrors.put("errors", errorsList);
					return mentorAndErrors;
				}
				
				//for demand and supply creation
				public static Map<String, Object> readExcelFile(File file, CareerService careerService, CommonService commonService, CareerSubcategory careerSubcategory) {
					Map<String, Object> mentorAndErrors = new HashMap<String, Object>();
					List<String> errorsList = new ArrayList<>();
					try {
						FileInputStream selectedFile = new FileInputStream(file);
						Workbook workbook = WorkbookFactory.create(selectedFile);
						Sheet sheet = workbook.getSheetAt(0);
						// Iterate through each rows one by one
						Iterator<Row> rowIterator = sheet.iterator();
						while (rowIterator.hasNext()) {
							Row row = rowIterator.next();
							if (row.getRowNum() != 0) {
								DemandSupply demandSupply= new DemandSupply();
								boolean error = false;
								Iterator<Cell> cellIterator = row.cellIterator();
								while (cellIterator.hasNext()) {
									Cell cell = cellIterator.next();
									if (cell.getColumnIndex() == 0) {
										if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
											String label = cell.getStringCellValue().toString();
											DemandLabels demandLabel= careerService.findDemandLableByLableName(label);
											if(demandLabel==null) {
												error=true;
												errorsList.add(demandLabel+"demand Label not exist");
											}
											else {
											demandSupply.setLabel(demandLabel);
											}
										}
									}
									else if (cell.getColumnIndex() == 1) {
										if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
											double salary = cell.getNumericCellValue();
											demandSupply.setSalary(salary);			
										}
									}
									else if (cell.getColumnIndex() == 4) {
										if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
											double manpower = cell.getNumericCellValue();
											demandSupply.setManpower(manpower);				
										}
									}
								}
								if (!error) {
									demandSupply.setCareerSubCat(careerSubcategory);
									DemandYears demandYear= careerService.findDemandYearByYear("2020");
									demandSupply.setYear(demandYear);
									DemandColors demandColor= careerService.findDemandColorByColorName("#FB404B");
									demandSupply.setColor(demandColor);
									demandSupply.setBackgroundColor(demandColor.getName());
									commonService.saveDemand(demandSupply);
								}
							}
						}
						//for 2030 and second color
						Iterator<Row> rowIterator2 = sheet.iterator();
						while (rowIterator2.hasNext()) {
							Row row = rowIterator2.next();
							if (row.getRowNum() != 0) {
								DemandSupply demandSupply= new DemandSupply();
								boolean error = false;
								Iterator<Cell> cellIterator = row.cellIterator();
								while (cellIterator.hasNext()) {
									Cell cell = cellIterator.next();
									if (cell.getColumnIndex() == 0) {
										if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
											String label = cell.getStringCellValue().toString();
											DemandLabels demandLabel= careerService.findDemandLableByLableName(label);
											if(demandLabel==null) {
												error=true;
												errorsList.add(demandLabel+"demand Label not exist");
											}
											else {
											demandSupply.setLabel(demandLabel);
											}
										}
									}
									else if (cell.getColumnIndex() == 2) {
										if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
											double salary = cell.getNumericCellValue();
											demandSupply.setSalary(salary);			
										}
									}
									else if (cell.getColumnIndex() == 5) {
										if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
											double manpower = cell.getNumericCellValue();
											demandSupply.setManpower(manpower);				
										}
									}
								}
								if (!error) {
									demandSupply.setCareerSubCat(careerSubcategory);
									DemandYears demandYear= careerService.findDemandYearByYear("2030");
									demandSupply.setYear(demandYear);
									DemandColors demandColor= careerService.findDemandColorByColorName("#1DC7EA");
									demandSupply.setColor(demandColor);
									demandSupply.setBackgroundColor(demandColor.getName());
									commonService.saveDemand(demandSupply);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					mentorAndErrors.put("errors", errorsList);
					return mentorAndErrors;
				}
				public static Map<String, Object> readExcelFile(File file,
						CommonService commonService) {
					Map<String, Object> eventAndErrors = new HashMap<String, Object>();
					int eventsCount=0;
					List<String> errorsList = new ArrayList<>();
					try {
						FileInputStream selectedFile = new FileInputStream(file);
						Workbook workbook = WorkbookFactory.create(selectedFile);
						Sheet sheet = workbook.getSheetAt(0);
						// Iterate through each rows one by one
						Iterator<Row> rowIterator = sheet.iterator();
						while (rowIterator.hasNext()) {
							Row row = rowIterator.next();
							if (row.getRowNum() != 0) {
								AddEvent addEvent= new AddEvent();
								boolean error = false;
								Iterator<Cell> cellIterator = row.cellIterator();
								while (cellIterator.hasNext()) {
									Cell cell = cellIterator.next();
									if (cell.getColumnIndex() == 0) {
										if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
											String eventName = cell.getStringCellValue().toString();
											addEvent.setEventName(eventName);
										}
									} else if (cell.getColumnIndex() == 1) {
										if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
											String event_date = cell.getStringCellValue().toString();
												addEvent.setEvent_date(event_date);										
										}
									} else if (cell.getColumnIndex() == 2) {
										if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
											//String eventTime = cell.getStringCellValue().toString();
											//	addEvent.setEventTime(eventTime);									
										}
									}
									else if (cell.getColumnIndex() == 3) {
										if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
											String description = cell.getStringCellValue().toString();
												addEvent.setDescription(description);								
										}
									
									}
									else if (cell.getColumnIndex() == 4) {
										if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
											String hubZoneName = cell.getStringCellValue().toString();
											CoCreationHub hubbZone=commonService.findByHubNameAndStatus(hubZoneName, Status.Active);
											if(hubbZone!=null) {
												addEvent.setHubZone(hubbZone);				
											}
											else {
												error=true;
												errorsList.add("HubZone doesn't exist at "+row.getRowNum());
											}
										}
									
									}
								}
								if (!error && addEvent.getHubZone()!= null) {
									addEvent.setStatus(Status.Active);
									commonService.saveEvent(addEvent);
									eventsCount++;
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					eventAndErrors.put("errors", errorsList);
					eventAndErrors.put("count", eventsCount);
					return eventAndErrors;
				}
	public static void main(String[] args) {
		double name = 9032508683.0;

		Log.info(Double.valueOf(name).longValue());
	}
}
