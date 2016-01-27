package com.zt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zt.entity.AuditDepartment;

public interface AuditDepartmentRepository extends
		JpaRepository<AuditDepartment, Long> {

}
