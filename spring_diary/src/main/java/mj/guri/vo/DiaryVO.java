package mj.guri.vo;

public class DiaryVO {
	
	private int diaryCode;
	private String userid;
	private String title;
	private String content;
	private String diaryDate;
	
	public DiaryVO() {}
	
	public DiaryVO(int diaryCode, String userid, String title, String content, String diaryDate) {
		super();
		this.diaryCode = diaryCode;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.diaryDate = diaryDate;
	}
	
	public int getDiaryCode() {
		return diaryCode;
	}
	public void setDiaryCode(int diaryCode) {
		this.diaryCode = diaryCode;
	}
	public String getDiaryDate() {
		return diaryDate;
	}
	public void setDiaryDate(String diaryDate) {
		this.diaryDate = diaryDate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
