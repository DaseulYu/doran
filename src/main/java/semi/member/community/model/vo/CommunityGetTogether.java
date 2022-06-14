package semi.member.community.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CommunityGetTogether {
	
	private String communityEvent;
	private String communityDate;
	private int remitedMember;
	private String meetingArea;
	
	public String getCommunityEvent() {
		return communityEvent;
	}
	public void setCommunityEvent(String communityEvent) {
		this.communityEvent = communityEvent;
	}
	public String getCommunityDate() {
		return communityDate;
	}
	public void setCommunityDate(String communityDate) {
		this.communityDate = communityDate;
	}
	public String getMeetingArea() {
		return meetingArea;
	}
	public void setMeetingArea(String meetingArea) {
		this.meetingArea = meetingArea;
	}
	public int getRemitedMember() {
		return remitedMember;
	}
	public void setRemitedMember(int remitedMember) {
		this.remitedMember = remitedMember;
	}
}
