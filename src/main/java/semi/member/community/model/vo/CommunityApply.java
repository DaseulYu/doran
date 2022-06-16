package semi.member.community.model.vo;

// 모임 신청 회원 VO
public class CommunityApply {
	
	private int applyNo;
	private int communityNo;
	private int memberNo;
	private String memberNickname;
	private String memberProfileImage;
	private String applyFlag;
	private String memberName;
	private String memberGender;
	private String memberBirth;
	private String communityName;
	
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public String getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}
	public String getApplyFlag() {
		return applyFlag;
	}
	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}
	public int getCommunityNo() {
		return communityNo;
	}
	public int getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(int applyNo) {
		this.applyNo = applyNo;
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
