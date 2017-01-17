package br.com.labrador.repository;

import br.com.labrador.domain.Loans;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Loans entity.
 */
@SuppressWarnings("unused")
public interface LoansRepository extends JpaRepository<Loans,Long> {

    @Query("select loans from Loans loans where loans.taker.login = ?#{principal.username}")
    List<Loans> findByTakerIsCurrentUser();

}
