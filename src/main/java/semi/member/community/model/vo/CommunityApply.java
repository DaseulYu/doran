package semi.member.community.model.vo;

// 모임 신청 회원 VO
public class CommunityApply {
	
	private int communityNo;
	private int memberNo;
	private String memberNickname;
	private String memberProfileImage;
	
	public int getCommunityNo() {
		return communityNo;
	}
	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getMemberProfileImage() {
		return memberProfileImage;
	}
	public void setMemberProfileImage(String memberProfileImage) {
		this.memberProfileImage = memberProfileImage;
	}	
}
