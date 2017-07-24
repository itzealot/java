package com.sky.projects.jdk.analysis.support;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sky.projects.jdk.analysis.CertificationTrackSliceMerge2Gang;
import com.sky.projects.jdk.analysis.CertificationTrackTimeSlice;
import com.sky.projects.jdk.analysis.GangTrackSlice;

/**
 * AbstractCertificationTrackSliceMerge2Gang
 * 
 * @author zealot
 */
public abstract class AbstractCertificationTrackSliceMerge2Gang implements CertificationTrackSliceMerge2Gang {

	@Override
	public Set<GangTrackSlice> join(List<CertificationTrackTimeSlice> slices, long seconds) {
		Set<GangTrackSlice> results = new HashSet<>(2);

		if (slices == null || slices.isEmpty()) {
			return results;
		}

		if (slices.size() == 1) {
			CertificationTrackTimeSlice last = slices.get(0);
			GangTrackSlice slice = new GangTrackSlice(last.getServiceCode());
			slice.join(last);
			results.add(slice);
			return results;
		}

		return doJoin(slices, seconds);
	}

	protected abstract Set<GangTrackSlice> doJoin(List<CertificationTrackTimeSlice> slices, long seconds);

}
