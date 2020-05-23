
package com.sectorseven.web.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.sectorseven.model.Enum.AdminRole;

/**
 * @author Ramesh Naidu
 *
 */
public final class AdminAccessUtils {

    public static boolean isSuperAdmin() {
        final Collection<String> roles = loggedInUserRoles();
        for (String role : roles) {
            if (role.equals(AdminRole.ROLE_SUPER_ADMIN.name())) {
                return true;
            }
        }
        return false;
        
        
    }
    
    public static boolean isAdmin() {
        final Collection<String> roles = loggedInUserRoles();
        for (String role : roles) {
            if (role.equals(AdminRole.ROLE_ADMIN.name())) {
                return true;
            }
        }
        return false;
        
        
    }

    public static boolean isOrganizationAdmin() {
        final Collection<String> roles = loggedInUserRoles();
        for (String role : roles) {
            if (role.equals(AdminRole.ROLE_ORGANIZATION_ADMIN.name())) {
                return true;
            }
        }
        return false;

    }

    /**
     * @returns if organizations user	
     */
    public static boolean isSchoolAdmin() {
        final Collection<String> roles = loggedInUserRoles();
        for (String role : roles) {
            if ( role.equals(AdminRole.ROLE_SCHOOL_ADMIN.name())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isStudentAdmin() {
        final Collection<String> roles = loggedInUserRoles();
        for (String role : roles) {
            if ( role.equals(AdminRole.ROLE_STUDENT.name())) {
                return true;
            }
        }
        return false;
    }

   

    private static Collection<String> loggedInUserRoles() {
        final UserDetails user = loggedInUserDetails();
        final Collection<String> roles = null != user ? toRoles(user.getAuthorities()) : null;
        return roles;
    }

    private static UserDetails loggedInUserDetails() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                final UserDetails user = (UserDetails) principal;
                return user;
            }

        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }

    private static Collection<String> toRoles(Collection<? extends GrantedAuthority> collection) {
        Collection<String> roles = new ArrayList<>(collection.size());
        for (GrantedAuthority r : collection) {
            roles.add(r.getAuthority());
        }
        return Collections.unmodifiableCollection(roles);
    }
    
    public static Collection<String> loggedUserRoles(){
    	final UserDetails user = loggedInUserDetails();
        final Collection<String> roles = null != user ? toRoles(user.getAuthorities()) : null;
        return roles;
    }

	
}
