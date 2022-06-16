package semi.member.member.model.vo;

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

public class Member {

		private int memberNo;
		private String memberEmail;
		private String memberPw;
		private String memberName;
		private String memberGender;
		private String memberBirth;
		private String memberPhone;
		private String memberNickname;
		private String secessionFlag;
		private String profileImage;
		private String memberLive;
		
		
		private String communityName;
		private String communitArea;
		private String comunityFlag;
		private String applyFlag;
		private String applyDate;
		
		
		
		
		public int getMemberNo() {
			return memberNo;
		}
		public void setMemberNo(int memberNo) {
			this.memberNo = memberNo;
		}
		public String getMemberEmail() {
			return memberEmail;
		}
		public void setMemberEmail(String memberEmail) {
			this.memberEmail = memberEmail;
		}
		public String getMemberPw() {
			return memberPw;
		}
		public void setMemberPw(String memberPw) {
			this.memberPw = memberPw;
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
		public String getMemberPhone() {
			return memberPhone;
		}
		public void setMemberPhone(String memberPhone) {
			this.memberPhone = memberPhone;
		}
		public String getMemberNickname() {
			return memberNickname;
		}
		public void setMemberNickname(String memberNickname) {
			this.memberNickname = memberNickname;
		}
		public String getSecessionFlag() {
			return secessionFlag;
		}
		public void setSecessionFlag(String secessionFlag) {
			this.secessionFlag = secessionFlag;
		}
		public String getProfileImage() {
			return profileImage;
		}
		public void setProfileImage(String profileImage) {
			this.profileImage = profileImage;
		}
		public String getMemberLive() {
			return memberLive;
		}
		public void setMemberLive(String memberLive) {
			this.memberLive = memberLive;
		}
		
		
}
