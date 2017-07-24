package com.sky.projects.jdk.analysis;

/**
 * CertificationTrackTimeSlice, 身份的时间段轨迹
 * 
 * @author zealot
 */
public class CertificationTrackTimeSlice {

	private final String idNo; // 身份
	private final String idType; // 身份对应的协议号
	private final String serviceCode; // 场所编码
	private long startTime; // 最小区间，绝对秒数
	private long endTime; // 最大区间，绝对秒数
	private int lingerTime; // 逗留时长，单位秒
	private int counts; // 合并的记录数

	public CertificationTrackTimeSlice(String idNo, String idType, String serviceCode, long startTime, long endTime) {
		this(idNo, idType, serviceCode, startTime, endTime, 1);
	}

	public CertificationTrackTimeSlice(String idNo, String idType, String serviceCode, long startTime, long endTime,
			int counts) {
		super();
		this.idNo = idNo;
		this.idType = idType;
		this.serviceCode = serviceCode;
		this.startTime = startTime;
		this.endTime = Math.max(startTime, endTime);
		this.calculateLingerTime();
		this.counts = counts;
	}

	public boolean canMerge(CertificationTrack track, long seconds) {
		return serviceCode.equals(track.getServiceCode()) && idNo.equals(track.getIdNo())
				&& idType.equals(track.getIdType()) && Math.abs(track.getEndTime() - endTime) <= seconds;
	}

	public void merge(CertificationTrack track) {
		this.counts++;
		this.calculateLingerTime();
		this.endTime = Math.max(track.getStartTime(), track.getEndTime());
	}

	public String getIdNo() {
		return idNo;
	}

	public String getIdType() {
		return idType;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public int getLingerTime() {
		return lingerTime;
	}

	public void setLingerTime(int lingerTime) {
		this.lingerTime = lingerTime;
	}

	public void calculateLingerTime() {
		this.lingerTime = (int) (endTime - startTime);
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

}
