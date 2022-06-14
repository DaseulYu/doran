package semi.member.community.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardImage {
	
	private int imageNo;
	private String imageName;
	private String imageOriginal;
	private int imageLevel;
	private int boardNo;

}
