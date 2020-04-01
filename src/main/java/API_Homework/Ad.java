package API_Homework;


public class Ad {
	private String company;
	private String url;
	private String text;
	
	public Ad(String company, String url, String text) {
		super();
		this.company = company;
		this.url = url;
		this.text = text;
	}
	
	public String getCompany() {
		return company;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getText() {
		return text;
	}

	
	public void setCompany(String company) {
		this.company = company;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
