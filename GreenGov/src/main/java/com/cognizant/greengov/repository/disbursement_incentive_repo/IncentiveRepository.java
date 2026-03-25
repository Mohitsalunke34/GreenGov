package com.cognizant.greengov.repository.disbursement_incentive_repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.greengov.model.incentive_subsidy.Incentive;
import com.cognizant.greengov.model.register_login.ParticipantProfile;
import com.cognizant.greengov.model.sustainability_renewable_proj.EnergyProgram;
import com.cognizant.greengov.model.sustainability_renewable_proj.ProgramApplication;

@Repository
public interface IncentiveRepository
        extends JpaRepository<Incentive, Long> {

    Optional<Incentive> findByApplication(ProgramApplication application);

    List<Incentive> findByBeneficiary(ParticipantProfile beneficiary);

    List<Incentive> findByProgram(EnergyProgram program);
}