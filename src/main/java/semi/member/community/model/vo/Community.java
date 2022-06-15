package semi.member.community.model.vo;

import java.util.List;

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
	private char communityFlag;
	private String communityNotice;
	private int categoryNo;
	
	private List<CommunityBoard> boardList;
	private List<BoardReply> replyList;
	
	
}
