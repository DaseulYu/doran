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
public class BoardDetail {
	
	private int boardNo;
	private int communityNo;
	private String boardTitle;
	private String boardContent;
	private String createDate;
	private int readCount;
	private String boardName;
	private int memberNo;
	private String memberNickname;
	private String profileImage;
	private List<BoardImage> imageList;

}
