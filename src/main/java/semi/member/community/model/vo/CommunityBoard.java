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
public class CommunityBoard {
	
	private int boardNo;
	private int communityNo;
	private String communityName;
	private String boardName;
	private String boardTitle;
	private String boardContent;
	private String memberNo; 
	private String memberNickname;
	private String profileImage;
	private String createDate;
	private int readCount;
	private String image0;
	private String image1;
	
}
