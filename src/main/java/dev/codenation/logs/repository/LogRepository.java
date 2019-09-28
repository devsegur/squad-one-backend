package dev.codenation.logs.repository;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {

    @Query(value = "select count(l.hash) as total," +
            "l2.*" +
            "from logs l," +
            "(select * from logs" +
            " order by created_at DESC " +
            " LIMIT 1) as l2 " +
            "where l2.hash = l.hash " +
            "              and (:hash is null or l.hash like %:hash%) " +
            "              and (:message is null or lower(l.message) like %:message%) " +
            "              and (:details is null or lower(l.details) like %:details%) " +
            "              and (:severity is null or l.severity = :severity) " +
            "              and (:environment is null or l.environment like :environment) " +
            "              and (:origin is null or l.origin like :origin) " +
            "              and (:reportedBy is null or l.reported_by like :reportedBy) " +
            "              and (l.archived is false) " +
            "group by l.hash, l.id, l.hash, l.message, l.details," +
            "  l.severity, l.environment, l.origin," +
            "  l.archived, l.archived_by, l.reported_by, l.archived_at," +
            "  l.created_at " +
            "\n-- #pageable\n",
            nativeQuery = true,
            countQuery = "select count(hash) from logs")
    Page<Object> findAllSumarized(@Param("hash") Integer hash,
                                  @Param("message") String message,
                                  @Param("details") String details,
                                  @Param("severity") Severity severity,
                                  @Param("environment") Environment environment,
                                  @Param("origin") String origin,
                                  @Param("reportedBy") UUID reportedBy,
                                  Pageable pageable);
}
