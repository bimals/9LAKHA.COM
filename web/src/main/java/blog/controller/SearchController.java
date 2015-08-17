package blog.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.gridfs.GridFSDBFile;

import blog.db.service.ProductService;
import blog.model.Product;
import blog.model.SearchCriteria;

@Controller
public class SearchController {
	
	@Autowired ProductService productService;
	
	@RequestMapping(value="product/search", method = RequestMethod.POST)
	@ResponseBody
	public List<Product> searchProduct(@RequestBody SearchCriteria searchCriteria) {
		List<Product> products = productService.searchProductByKeyWord(searchCriteria);
		
		return products;
	}
	
	@RequestMapping(value="/product/image/{imageId}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] getProductImage(@PathVariable("imageId") String imageId, HttpServletResponse response) throws IOException {
		
		GridFSDBFile imageForOutput = productService.getProductImage(imageId);

		int nRead;
		ByteArrayOutputStream buffer=new ByteArrayOutputStream();
		
		byte[] data = new byte[1024];
		 InputStream stream = imageForOutput.getInputStream();
		  while ((nRead=stream.read(data,0,data.length)) != -1) {
		    buffer.write(data,0,nRead);
		  }
		  
		response.setContentType(imageForOutput.getContentType());
		response.setHeader("Content-Type", imageForOutput.getContentType());		
		
		return data;
	}
}
