package com.sectorseven.web.utils;

/**
 * @author Ramesh Naidu
 *         This class is to generate excel file template with dynamic datas
 */

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.sectorseven.model.Enum.AdminRole;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.school.School;
import com.sectorseven.model.school.Student;
import com.sectorseven.model.util.CommonMethods;
import com.sectorseven.service.student.StudentService;

public class StudentUploadExcelTemplate {
	
	
    public static Map<String, Object> readExcelFile(File file, School school ,StudentService studentService) {
        Map<String, Object> studentAndErrors = new HashMap<String, Object>();
        List<Student> studentList = new ArrayList<>();
        List<String> errorsList = new ArrayList<>();
        try {
            FileInputStream selectedFile = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(selectedFile);
            Sheet sheet = workbook.getSheetAt(0);
            // Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
            	Row row = rowIterator.next(); 
            	 if(row.getRowNum() != 0) {
            		 
            		 Iterator<Cell> cellIterator = row.cellIterator();
                	 while (cellIterator.hasNext()) {
                		 	boolean error = false;
                		 	Student student = new Student();
                		 	Cell cell = cellIterator.next();
                		 	if(cell.getColumnIndex() ==1) {
                                String firstName = cell.getStringCellValue().toString();
                                student.setFirstName(firstName);
                		 	}else if(cell.getColumnIndex() ==2) {
                                String lastName = cell.getStringCellValue().toString();
                                student.setLastName(lastName);
                		 	}else if(cell.getColumnIndex() ==3) {
                                Double mobile = cell.getNumericCellValue();
                                if(String.valueOf(mobile.longValue()).length() <= 10) {
                                	Student existedStudentOrNot = studentService.findByMobile(Double.valueOf(mobile).longValue());
                                    if(existedStudentOrNot != null) {
                                    	errorsList.add("Mobile No."+mobile+" already existed");
                                    	error = true;
                                    }else {
                                    	student.setMobile(mobile.longValue());
                                    }
                                }else {
                                	errorsList.add("Mobile No."+mobile+" invalid");
                                	error = true;
                                }
                		 	}else if(cell.getColumnIndex() ==4) {
                                String emailID = cell.getStringCellValue().toString();
                                boolean validEmail= EmailValidator.getInstance().isValid(emailID);
                                if(validEmail) {
                                	Student checkStudentWithMail = studentService.findByEmail(emailID);
                                    if(checkStudentWithMail != null) {
                                    	errorsList.add("Email '"+emailID+"' already existed");
                                    	error = true;
                                    }else {
                                    	student.setEmail(emailID);
                                    }
                                }else {
                                	errorsList.add("Email '"+emailID+"'Invalid");
                                	error = true;
                                }
                                
                		 	}else if(cell.getColumnIndex() ==5) {
                                String dob = cell.getStringCellValue().toString();
                                student.setDate_of_birth(dob);
                                //student.setDateOfBirth(CommonMethods.stringToDate(dob));
                		 	}
                		 	 if(!error) {
                		 		student.setStatus(Status.Active);
                    		 	student.setSchool(school);
                    		 	String studentUserID = studentService.generateUserName(school.getSchoolCode());
                    		 	String password = CommonMethods.generatePassword(8);
                    		 	student.setUserName(studentUserID);
                    		 	student.setDecryptPassword(password);
                    		 	student.setPassword(CommonMethods.decodePassword(password));
                    		 	student.setAuthority(AdminRole.ROLE_STUDENT.name());
                    		 	studentList.add(student);
						}
					}

				}
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(studentList.size());
        studentAndErrors.put("student", studentList);
        studentAndErrors.put("errors", errorsList);
        return studentAndErrors;
    }
    
    public static void main(String[] args) {
		double name = 9032508683.0;
		
		System.out.println(Double.valueOf(name).longValue());
	}

	
}
