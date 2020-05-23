package com.sectorseven.model.util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * 
 * @author Ramesh Naidu Terle
 *
 */
public class EmailTemplateReader {

    public static final String FULL_NAME = "@@user@@";
    public static final Logger log= Logger.getAnonymousLogger();
    /**
     * Returns an email for User registration either for student or trainer.
     * 
     * @param School Name
     * @param userName
     * @param password
     * @return
     * @throws IOException
     */
    public String readUserEmailTemplate(String name, String userName, String password) throws IOException {
    	
    	String template = "";
    	ClassLoader classLoader = getClass().getClassLoader();
    	
		File templateObj = new File(classLoader.getResource("userRegistrationTemplate.html").getFile());
		log.info("File is there:::"+templateObj);
		@SuppressWarnings("resource")
		Scanner templateReader= new Scanner(templateObj);
		 while (templateReader.hasNextLine()) {
		        template += templateReader.nextLine();
		      }
		 template = template.replace(FULL_NAME, name);
		 template = template.replace("@@userName@@", userName);
		 template = template.replace("@@password@@", password);
        return template;
    }

   

    
}
