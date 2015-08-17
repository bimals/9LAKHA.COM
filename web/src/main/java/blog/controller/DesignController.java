package blog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import blog.db.service.DesignService;
import blog.db.service.UserService;
import blog.model.AccountUser;
import blog.model.Design;

@Controller
public class DesignController {
	
	@Autowired DesignService designService;
	@Autowired UserService userService;
	
	@RequestMapping(value="/design", method = RequestMethod.GET)
	public String writeBlog() {
		
		return "home";
	}
	
	@RequestMapping(value="/user/addcustomdesign", method = RequestMethod.POST)
	@ResponseBody
	public Design createDesign(@RequestBody Design design) throws Exception {
		AccountUser user = userService.getLoggedInUser();
		design.setUserId(user.getId());
		Design newDesign = designService.createDesign(design);
		//user.getDesignId().add(newDesign.getId());
		//userService.updateUser(user);
		return design;
	}

	@RequestMapping(value="/user/addcustomdesignimage", method = RequestMethod.POST)
	@ResponseBody
	public Design addDesignImage(@RequestParam(value = "designId", required = false) String designId, @RequestParam(value = "fileToUpload", required = false) CommonsMultipartFile fileToUpload, 
			MultipartHttpServletRequest mrequest) throws IOException {
		
		Design design = designService.findById(designId);
		designService.addDesignImage(design, fileToUpload);

		return design;
	}
	
}
