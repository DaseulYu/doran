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
public class SignupDetail {

	private int signupNo;
	private int communityNo;
	private String signupContent;
	private String createDate;
	private int memberNo;
	private String memberNickname;
	private String profileImage;
}
