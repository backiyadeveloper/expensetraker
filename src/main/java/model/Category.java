package model;

public class Category {

	private int categoryid;
	private String name;
	private String type;

	public Category(int categoryid, String name, String type) {
		this.categoryid = categoryid;
		this.name = name;
		this.type = type;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int catogryid) {
		this.categoryid = catogryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
