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
	
}
