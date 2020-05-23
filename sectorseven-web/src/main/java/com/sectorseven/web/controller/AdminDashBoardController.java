package com.sectorseven.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.common.Apk;
import com.sectorseven.model.common.CommonImages;
import com.sectorseven.model.common.Contribution;
import com.sectorseven.model.common.ContributionDocs;
import com.sectorseven.model.common.Feedback;
import com.sectorseven.model.common.Notification;
import com.sectorseven.model.common.SubCategoryDetails;
import com.sectorseven.service.student.CommonService;
import com.sectorseven.web.utils.ImageUtility;
import com.sectorseven.web.utils.ViewConstants;

/**
 * @author RameshNaidu
 	
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminDashBoardController {
	
	@Autowired  
	private CommonService commonService;
	
	@ResponseBody
	@RequestMapping(value="/notificationsAdmin", method= RequestMethod.GET)
	public List<ContributionDocs> getSubCatDetailList() {
		return commonService.findAllSubmittedContributions();
	}
	
	@RequestMapping(value="/dashboard", method= RequestMethod.GET)
	public String showDashboard(Model model) {  
		model.addAttribute("adminHomeData", commonService.getadminHomeData());
		return "admin/dashboard";
	}
	@RequestMapping(value="/contributionList", method= RequestMethod.GET)
	public String contributionList(Model model,RedirectAttributes redirectAttributes,HttpServletResponse response)  {
		List<Contribution> contributions = commonService.findAllContributions();
        model.addAttribute("contributions", contributions);
		redirectAttributes.addFlashAttribute("msg", "Got Contributions successfully..!!");
		return "admin/contributionList";
	}
	@RequestMapping(value="/contributionAccepted", method= RequestMethod.POST)
	public String contributionAccepted(Model model,RedirectAttributes redirectAttributes,
			@ModelAttribute("contributionId") Long contributionId,@ModelAttribute("acceptance") String acceptance)  {
		if(acceptance.equals("Accepted")){
			Contribution contribution=commonService.findContributionById(contributionId);
			contribution.setAcceptance(AdminAcceptance.Accepted);
			commonService.saveContribution(contribution);
		}
		else if(acceptance.equals("Rejected")){
			Contribution contribution=commonService.findContributionById(contributionId);
			contribution.setAcceptance(AdminAcceptance.Rejected);
			commonService.saveContribution(contribution);
		}
		List<Contribution> contributions = commonService.findAllContributions();
        model.addAttribute("contributions", contributions);
		return "admin/contributionList";
	}
	@RequestMapping(value="/contributionDocsList", method= RequestMethod.GET)
	public String contributionDocsList(Model model,RedirectAttributes redirectAttributes,HttpServletResponse response)  {
		List<ContributionDocs> contributionsDocs = commonService.findAllContributionDocuments();
        model.addAttribute("contributionsDocs", contributionsDocs);
		redirectAttributes.addFlashAttribute("msg", "Got Contributions docs successfully..!!");
		return "admin/contributionList";
	}
	@RequestMapping(value="/contributionDocsView", method= RequestMethod.POST)
	public String contributionDocsView(Model model,RedirectAttributes redirectAttributes,HttpServletResponse response,@ModelAttribute("contributionDocId") Long contributionDocId)  {
		ContributionDocs contributionsDoc = commonService.findAllContributionDocById(contributionDocId);
        model.addAttribute("contributionsDoc", contributionsDoc);
		redirectAttributes.addFlashAttribute("msg", "Got Contributions docs successfully..!!");
		return "admin/contributionDocsView";
	}
	@RequestMapping(value="/contributionDocsverify", method= RequestMethod.POST)
	public String contributionDocsverify(Model model,RedirectAttributes redirectAttributes,
			@ModelAttribute("contributionDocId") Long contributionDocId,@ModelAttribute("acceptance") String acceptance,
			@ModelAttribute("description") String description,
			@ModelAttribute("title") String title,
			@RequestParam(value="document",required=false) MultipartFile file
			) throws Exception  {
		ContributionDocs contributionsDoc = commonService.findAllContributionDocById(contributionDocId);
		contributionsDoc.setDescription(description);   
		contributionsDoc.setTitle(title);
		Notification ntf=new Notification();
		ntf.setStatus(Status.Active);
		ntf.setAction("contribution");
		ntf.setScreen("contribution");
		ntf.setUser(contributionsDoc.getUser());
		ntf.setAuthority(contributionsDoc.getAuthority().getAuthorityCode());
		ntf.setMessage("Contributed Your "+title+"In S7 Talks");
		String[] pathAndName;
		ImageUtility imu = new ImageUtility();
		if(!file.isEmpty()){
			try {
				pathAndName = imu.getAbsoluteImagePathWithFileName(file, contributionsDoc.getId(),
						ViewConstants.CONTRIBUTION_DOCUMENTS);
				contributionsDoc.setContDocName(pathAndName[1]);
				contributionsDoc.setContDocPath(pathAndName[0]);
				contributionsDoc.setType(file.getContentType());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		if(acceptance.equals("Accepted")){
			ContributionDocs contributionDoc=commonService.findContributionDocById(contributionDocId);
			Contribution contribution=commonService.findContributionById(contributionDoc.getContribution().getId());
			contribution.setAcceptance(AdminAcceptance.Accepted);
			contributionDoc.setAcceptance(AdminAcceptance.Accepted);
			commonService.saveContributionDocs(contributionDoc);
			commonService.saveNotification(ntf);
			commonService.saveContribution(contribution);
		}
		else if(acceptance.equals("Rejected")){
			ContributionDocs contributionDoc=commonService.findContributionDocById(contributionDocId);
			Contribution contribution=commonService.findContributionById(contributionDoc.getContribution().getId());
			contribution.setAcceptance(AdminAcceptance.Rejected);
			contributionDoc.setAcceptance(AdminAcceptance.Rejected);
			commonService.saveContributionDocs(contributionDoc);
			commonService.saveContribution(contribution);
		}
		List<Contribution> contributions = commonService.findAllContributions();
        model.addAttribute("contributions", contributions);
		List<ContributionDocs> contributionsDocs = commonService.findAllContributionDocuments();
        model.addAttribute("contributionsDocs", contributionsDocs);
		redirectAttributes.addFlashAttribute("live", "Contribution pushed to Live!");
		return "admin/contributionList";
	}
	@RequestMapping(value="/apkUpdate", method= RequestMethod.GET)
	public String updateAPK(Model model)  {
		model.addAttribute("apkObj", new Apk());
		return "admin/apkupdate";
	}
	@RequestMapping(value="/apkUpdate", method= RequestMethod.POST)
	public String updateAPK(Model model,RedirectAttributes redirectAttributes,@ModelAttribute("apkObj") Apk apk)  {
		apk.setStatus(Status.Active);
		commonService.updateApk(apk);
		redirectAttributes.addFlashAttribute("msg", "apk update successful");
		return "admin/apkupdate";
	}
	@RequestMapping(value="/commonImage", method= RequestMethod.GET)
	public String uploadCommonImage(Model model)  {
		model.addAttribute("commonImg", new CommonImages());
		return "admin/uploadCmImg";
	}
	@RequestMapping(value="/commonImage", method= RequestMethod.POST)
	public String uploadCommonImage(Model model,RedirectAttributes redirectAttributes,@ModelAttribute("commonImg") CommonImages ci)  {
		ci.setStatus(Status.Active);
		CommonImages cis=commonService.findCommonImagesByScreen(ci.getScreen());
		if(cis==null){
			cis=commonService.saveCommonImages(ci);

		}
		else{
			CommonsMultipartFile file = ci.getCmImage();
			String[] pathAndName;
			ImageUtility imu = new ImageUtility();
			try {
				pathAndName = imu.getAbsoluteImagePathWithFileName(file, cis.getId(),
						ViewConstants.COMMON_IMAGES+"/"+cis.getScreen());
				cis.setImgName(pathAndName[1]);
				cis.setImgPath(pathAndName[0]);
				cis.setImgType(file.getContentType());
				commonService.saveCommonImages(cis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			redirectAttributes.addFlashAttribute("msg", "Common Image Uploaded successful");
		}
			
		return "admin/uploadCmImg";
	}
	
	@RequestMapping(value="/userFeedbacks", method= RequestMethod.GET)
	public String userFeedBacks(Model model)  {
		List<Feedback> userfeedbacks= commonService.findAllfeedbacks();
		model.addAttribute("userfeedbacks", userfeedbacks);
		model.addAttribute("msg", "Getting all the userFeedbacks");
		return "admin/userFeedbacks";
	}
}
