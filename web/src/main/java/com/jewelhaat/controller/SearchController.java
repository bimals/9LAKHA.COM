package com.jewelhaat.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bimalsahay.model.SearchCriteria;
import com.jewelhaat.db.service.DesignService;
import com.jewelhaat.db.service.ProductService;
import com.jewelhaat.model.Design;
import com.jewelhaat.model.Product;
import com.mongodb.gridfs.GridFSDBFile;

@Controller
public class SearchController {
	
	@Autowired ProductService productService;
	@Autowired DesignService designService;
	
	@RequestMapping(value="/s", method = RequestMethod.GET)
	public String writeProduct() {
		
		return "search";
	}
	
	@RequestMapping(value="/product/search", method = RequestMethod.POST)
	@ResponseBody
	public List<Product> searchProduct(@RequestBody SearchCriteria searchCriteria) {
		List<Product> products = productService.searchProductByKeyWord(searchCriteria);
		
		return products;
	}
	
	@RequestMapping(value="/design/search", method = RequestMethod.POST)
	@ResponseBody
	public List<Design> searchDesign(@RequestBody SearchCriteria searchCriteria) {
		List<Design> designs = designService.searchDesignByKeyWord(searchCriteria);
		
		return designs;
	}
	
	@RequestMapping(value="/product/image/{imageId}", method = RequestMethod.GET)
	@ResponseBody
	public byte[][] getProductImage(@PathVariable("imageId") String imageId, HttpServletResponse response) throws IOException {
		
		List<GridFSDBFile> imageForOutput = productService.getProductImage(imageId);
			int i = 0;
			byte[][] images = new byte[imageForOutput.size()][];
			
			for (GridFSDBFile gridFSDBFile : imageForOutput) {
				if(imageForOutput != null) {
					InputStream is = gridFSDBFile.getInputStream();

					ByteArrayOutputStream buffer = new ByteArrayOutputStream();
					int nRead;
					byte[] data = new byte[16384];
					
					while ((nRead = is.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, nRead);
					}
					
					buffer.flush();
					byte[] imagenEnBytes = buffer.toByteArray();

					response.setHeader("Accept-ranges", "bytes");
					response.setContentType("image/jpeg");
					response.setContentLength(imagenEnBytes.length);
					response.setHeader("Expires", "0");
					response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
					response.setHeader("Content-Description", "File Transfer");
					response.setHeader("Content-Transfer-Encoding:", "binary");

					OutputStream out = response.getOutputStream();
					out.write(imagenEnBytes);
					out.flush();
					out.close();
			
					images[i] = data;
					i++;
				}
			}

			
			return images;
	}
}
