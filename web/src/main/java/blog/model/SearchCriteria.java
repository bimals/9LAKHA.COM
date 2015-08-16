package blog.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class SearchCriteria implements Serializable,Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    private String id;
	private String searchText;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
