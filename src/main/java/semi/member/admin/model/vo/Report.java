package semi.member.admin.model.vo;

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
public class Report {
	
	private int reportNo;
	private String reportDate;
	private String reportTitle;
	private int memberNo;
	private String memberID;
	private int boardNo;
	private String boardTitle;
	private int communityNo;
	private String communityName;
	
}
