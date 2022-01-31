package mj.guri.vo;

public class MemberVO {

	private String userid;
	private String userpwd;
	private String nickname;
	
	public MemberVO(String userid, String userpwd, String nickname) {
		super();
		this.userid = userid;
		this.userpwd = userpwd;
		this.nickname = nickname;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
}