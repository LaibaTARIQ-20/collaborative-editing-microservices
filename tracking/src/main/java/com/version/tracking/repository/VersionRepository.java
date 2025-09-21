package com.version.tracking.repository;

import com.version.tracking.entity.VersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
@Transactional
public interface VersionRepository extends JpaRepository<VersionEntity,Long> {
    @Query(value = "SELECT MAX(version_number) FROM version_tracking.version_entity WHERE document_id = :documentId", nativeQuery = true)
    Optional<Integer> findMaxVersionNumberByDocumentId(@Param("documentId") Long documentId);
   VersionEntity findByDocumentIdAndVersionNumber(Long documentId, int versionNumber);

}
