package semi.member.board.model.vo;


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
public class Board {
	private int communityNo;
	private String communityName;
	private String communityImage;
	private String communityInfo;
	private String communityDetail;
	private String communityArea;
	private int memberNo;
	private String communityAdmin;
	private String communityFL;
	private String communityNotice;
	private int categoryNo;
	private String categoryName;
	private int communityMember;
	private int pick;
	

	
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
	public String getCommunityFL() {
		return communityFL;
	}
	public void setCommunityFL(String communityFL) {
		this.communityFL = communityFL;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCommunityMember() {
		return communityMember;
	}
	public void setCommunityMember(int communityMember) {
		this.communityMember = communityMember;
	}
	public int getPick() {
		return pick;
	}
	public void setPick(int pick) {
		this.pick = pick;
	}
	
	
	
}
