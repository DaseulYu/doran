package semi.member.admin.model.vo;

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
public class Report {
	
	private int reportNo;
	private String reportDate;
	private String reportTITLE;
	private int memberNo;
	private int memberNickname;
	private int communityNo;
	private int communityTitle;
	
}
