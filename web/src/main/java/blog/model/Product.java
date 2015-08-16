package blog.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class Product implements Serializable,Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7605052698976876820L;
	@Id
    private String id;
	private String productName;
	private String productDescription;
	private UUID productUUID;
	private String userId;
	private String imageId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public UUID getProductUUID() {
		return productUUID;
	}
	public void setProductUUID(UUID productUUID) {
		this.productUUID = productUUID;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	
}
