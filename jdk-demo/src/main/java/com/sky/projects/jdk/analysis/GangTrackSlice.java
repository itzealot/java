package com.sky.projects.jdk.analysis;

import java.util.HashMap;
import java.util.Map;

/**
 * GangTrackSlice，聚集的时间段轨迹
 * 
 * @author zealot
 */
public class GangTrackSlice {

	private final String serviceCode; // 场所编码
	private long minStartTime; // 最小开始时间
	private long maxEndTime; // 最大结束时间
	private long startTime; // 起始聚集时间
	private long endTime; // 最后聚集时间
	private int lingerTime; // 聚集时长(endTime-startTime)

	// 聚集成员集合,key=idType|idNo,value=CertificationTrackTimeSlice
	private final Map<String, CertificationTrackTimeSlice> slices;

	public GangTrackSlice(String serviceCode) {
		super();
		this.serviceCode = serviceCode;
		this.slices = new HashMap<>();
		this.minStartTime = Long.MAX_VALUE;
		this.maxEndTime = Long.MIN_VALUE;
	}

	public boolean canJoin(CertificationTrackTimeSlice slice, long seconds) {
		return serviceCode.equals(slice.getServiceCode()) && Math.abs(slice.getEndTime() - endTime) <= seconds;
	}

	/**
	 * <code>
	 * 	if(canJoin(slice, seconds)) {
	 * 		join(slice);
	 * 	}
	 * </code>
	 * 
	 * @param slice
	 */
	public void join(CertificationTrackTimeSlice slice) {
		join(new StringBuilder(), slice);
	}

	/**
	 * <code>
	 * 	if(canJoin(slice, seconds)) {
	 * 		join(builder, slice);
	 * 	}
	 * </code>
	 * 
	 * @param builder
	 * @param slice
	 */
	public void join(StringBuilder builder, CertificationTrackTimeSlice slice) {
		String key = builder.append(slice.getIdType()).append("|").append(slice.getIdNo()).toString();

		// save min and max time
		minStartTime = Math.min(minStartTime, slice.getStartTime());
		maxEndTime = Math.max(maxEndTime, slice.getEndTime());

		// merge the cluster time
		startTime = Math.max(startTime, slice.getStartTime());
		endTime = Math.min(endTime, slice.getEndTime());
		lingerTime = (int) (endTime - startTime);

		CertificationTrackTimeSlice old = slices.get(key);

		if (slices.isEmpty() || old == null) {
			slices.put(key, slice);
		} else {// merge time
			if (old.getServiceCode().equals(slice.getServiceCode())) {
				old.setEndTime(slice.getEndTime());
				old.calculateLingerTime();
			}
		}

		builder.setLength(0);
	}

	public int getSize() {
		return slices.size();
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public long getMinStartTime() {
		return minStartTime;
	}

	public long getMaxEndTime() {
		return maxEndTime;
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

	public Map<String, CertificationTrackTimeSlice> getSlices() {
		return slices;
	}

}
