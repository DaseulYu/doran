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
public class Community {
	
	private int boardNo;
	private String boardTitle;
	private String memberNickname;
	private String createDate;
	

}
