package semi.member.admin.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoticeImage {
	
	private int imageNo;
	private String imageName;
	private String imageOriginal;
	private int imageLevel;
	private int noticeNo;
}
 