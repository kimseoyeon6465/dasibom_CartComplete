package com.booksajo.dasibom.vo;

public class UserVO {
	private int user_Seq;
	private String user_Id;
	private String pw;
	private String irum;
	private String tel;
	private String address;
	private String email;
	private String name;
	private int isAdmin;
	private int point;
	private String profileImgPath;

	public int getUser_Seq() {
		return user_Seq;
	}

	public void setUser_Seq(int user_Seq) {
		this.user_Seq = user_Seq;
	}

	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getIrum() {
		return irum;
	}

	public void setIrum(String irum) {
		this.irum = irum;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getProfileImgPath() {
		return profileImgPath;
	}

	public void setProfileImgPath(String profileImgPath) {
		this.profileImgPath = profileImgPath;
	}

	// jsp용 커스텀 메서드
	public String getUserId() {
		return user_Id;
	}

	public void setUserId(String user_Id) {
		this.user_Id = user_Id;
	}

	public int getUserSeq() {
		return user_Seq;
	}

	public void setUserSeq(int user_Seq) {
		this.user_Seq = user_Seq;
	}
}