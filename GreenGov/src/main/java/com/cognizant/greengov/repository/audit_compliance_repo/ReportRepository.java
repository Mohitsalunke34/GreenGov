package com.cognizant.greengov.repository.audit_compliance_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.greengov.model.compliance_audit.Report;
import com.cognizant.greengov.model.register_login.UserAccount;

public interface ReportRepository
        extends JpaRepository<Report, Long> {

    List<Report> findByGeneratedBy(UserAccount user);
}