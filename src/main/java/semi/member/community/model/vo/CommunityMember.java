package semi.member.community.model.vo;

// 모임 회원 VO 
public class CommunityMember {
	
	private int communityNo;
	private int memberNo;
	private String memberFlag;
	private String memberNickname;
	private String memberProfileImage;
	
	public int getCommunityNo() {
		return communityNo;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public String getMemberProfileImage() {
		return memberProfileImage;
	}
	public void setMemberProfileImage(String memberProfileImage) {
		this.memberProfileImage = memberProfileImage;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberFlag() {
		return memberFlag;
	}
	public void setMemberFlag(String memberFlag) {
		this.memberFlag = memberFlag;
	}

	
	
}
