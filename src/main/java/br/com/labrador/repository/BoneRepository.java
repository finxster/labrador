package br.com.labrador.repository;

import br.com.labrador.domain.Bone;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Bone entity.
 */
@SuppressWarnings("unused")
public interface BoneRepository extends JpaRepository<Bone,Long> {

    @Query("select bone from Bone bone where bone.owner.login = ?#{principal.username}")
    List<Bone> findByOwnerIsCurrentUser();

}
