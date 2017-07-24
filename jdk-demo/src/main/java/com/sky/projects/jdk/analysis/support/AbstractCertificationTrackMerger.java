package com.sky.projects.jdk.analysis.support;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sky.projects.jdk.analysis.CertificationTrack;
import com.sky.projects.jdk.analysis.CertificationTrackMerger;
import com.sky.projects.jdk.analysis.CertificationTrackTimeSlice;

/**
 * AbstractCertificationTrackMerger
 * 
 * @author zealot
 */
public abstract class AbstractCertificationTrackMerger implements CertificationTrackMerger {

	@Override
	public Set<CertificationTrackTimeSlice> merge(List<CertificationTrack> certs, long seconds) {
		HashSet<CertificationTrackTimeSlice> results = new HashSet<>(2);

		if (certs == null || certs.isEmpty()) {
			return results;
		}

		if (certs.size() == 1) { // 只有一个
			CertificationTrack track = certs.get(0);
			results.add(new CertificationTrackTimeSlice(track.getIdNo(), track.getIdType(), track.getServiceCode(),
					track.getStartTime(), Math.max(track.getStartTime(), track.getEndTime())));
			return results;
		}

		return doMerge(certs, seconds);
	}

	protected abstract Set<CertificationTrackTimeSlice> doMerge(List<CertificationTrack> certs, long seconds);
}
