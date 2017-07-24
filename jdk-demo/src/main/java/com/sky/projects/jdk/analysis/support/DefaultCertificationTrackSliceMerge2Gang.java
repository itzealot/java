package com.sky.projects.jdk.analysis.support;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sky.projects.jdk.analysis.CertificationTrackTimeSlice;
import com.sky.projects.jdk.analysis.GangTrackSlice;

/**
 * DefaultCertificationTrackSliceMerge2Gang
 * 
 * @author zealot
 */
public class DefaultCertificationTrackSliceMerge2Gang extends AbstractCertificationTrackSliceMerge2Gang {

	@Override
	protected Set<GangTrackSlice> doJoin(List<CertificationTrackTimeSlice> slices, long seconds) {
		Set<GangTrackSlice> results = new HashSet<>();

		int i = 0, len = slices.size();
		CertificationTrackTimeSlice slice = slices.get(i);

		StringBuilder builder = new StringBuilder();

		// 指向最新的身份时间段轨迹
		String serviceCode = slice.getServiceCode();
		GangTrackSlice last = new GangTrackSlice(serviceCode);

		last.join(builder, slice);

		i++; // i = 1

		while (i < len) {
			slice = slices.get(i); // 获取指定索引的数据

			if (last.canJoin(slice, seconds)) {// 可以合并则执行合并
				last.join(builder, slice);
			} else { // 不能合并，则代表不在时间范围内，则生成新的时间段轨迹
				results.add(last);
				last = new GangTrackSlice(serviceCode); // 最新的时间段轨迹
			}
		}

		return results;
	}

}
