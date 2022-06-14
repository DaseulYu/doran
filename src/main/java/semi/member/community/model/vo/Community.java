package semi.member.community.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Community {
	
	private int communityNo;
	private String communityName;
	private String communityImage;
	private String communityInfo; 
	private String communityDetail;
	private String communityArea;
	private String memberNickname;
	private String profileImage;
	private String communityFlag;
	private String communityNotice;
	private int categoryNo;
	private String communityAdmin;
	private int memberNo;
	
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getCommunityAdmin() {
		return communityAdmin;
	}
	public void setCommunityAdmin(String communityAdmin) {
		this.communityAdmin = communityAdmin;
	}
	public int getCommunityNo() {
		return communityNo;
	}
	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getCommunityImage() {
		return communityImage;
	}
	public void setCommunityImage(String communityImage) {
		this.communityImage = communityImage;
	}
	public String getCommunityInfo() {
		return communityInfo;
	}
	public void setCommunityInfo(String communityInfo) {
		this.communityInfo = communityInfo;
	}
	public String getCommunityDetail() {
		return communityDetail;
	}
	public void setCommunityDetail(String communityDetail) {
		this.communityDetail = communityDetail;
	}
	public String getCommunityArea() {
		return communityArea;
	}
	public void setCommunityArea(String communityArea) {
		this.communityArea = communityArea;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getCommunityFlag() {
		return communityFlag;
	}
	public void setCommunityFlag(String communityFlag) {
		this.communityFlag = communityFlag;
	}
	public String getCommunityNotice() {
		return communityNotice;
	}
	public void setCommunityNotice(String communityNotice) {
		this.communityNotice = communityNotice;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	
	
}
