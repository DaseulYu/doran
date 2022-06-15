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

public class Member2 {

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
	
}
