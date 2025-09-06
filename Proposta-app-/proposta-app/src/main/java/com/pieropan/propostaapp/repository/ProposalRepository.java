package com.pieropan.propostaapp.repository;

import com.pieropan.propostaapp.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {


    List<Proposal> findAllByIntegratedIsFalse();

    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_proposals SET approved = :approved, observation = :observation WHERE id = :id", nativeQuery = true)
    void updateProposal(Long id, boolean approved, String observation);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Proposal SET integrated = :integrated WHERE id = :id")
    void updateIntegratedStatus(Long id, boolean integrated);
}
