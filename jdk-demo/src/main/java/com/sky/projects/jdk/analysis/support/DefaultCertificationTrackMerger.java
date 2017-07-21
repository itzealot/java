package com.sky.projects.jdk.analysis.support;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sky.projects.jdk.analysis.CertificationTrack;
import com.sky.projects.jdk.analysis.CertificationTrackTimeSlice;

/**
 * DefaultCertificationTrackMerger
 * 
 * @author zealot
 */
public class DefaultCertificationTrackMerger extends AbstractCertificationTrackMerger {

	@Override
	protected Set<CertificationTrackTimeSlice> doMerge(List<CertificationTrack> certs, long seconds) {
		Set<CertificationTrackTimeSlice> results = new HashSet<>();

		int i = 0, len = certs.size();
		CertificationTrack track = certs.get(i);

		// 指向最新的身份时间段轨迹
		CertificationTrackTimeSlice last = new CertificationTrackTimeSlice(track.getIdNo(), track.getIdType(),
				track.getServiceCode(), track.getStartTime(), Math.max(track.getStartTime(), track.getEndTime()));

		i++; // i = 1

		while (i < len) {
			track = certs.get(i); // 获取指定索引的数据

			long startTime = track.getStartTime();
			long endTime = Math.max(track.getStartTime(), track.getEndTime());

			if (last.canMerge(track, seconds)) {// 可以合并则执行合并
				last.merge(track);
			} else { // 不能合并，则代表不在时间范围内，则生成新的时间段轨迹
				results.add(last);
				last = new CertificationTrackTimeSlice(track.getIdNo(), track.getIdType(), track.getServiceCode(),
						startTime, endTime); // 最新的时间段轨迹
			}
		}

		return results;
	}

}
