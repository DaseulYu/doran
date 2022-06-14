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
	public int getImageNo() {
		return imageNo;
	}
	public void setImageNo(int imageNo) {
		this.imageNo = imageNo;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImageOriginal() {
		return imageOriginal;
	}
	public void setImageOriginal(String imageOriginal) {
		this.imageOriginal = imageOriginal;
	}
	public int getImageLevel() {
		return imageLevel;
	}
	public void setImageLevel(int imageLevel) {
		this.imageLevel = imageLevel;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	
	
}
 