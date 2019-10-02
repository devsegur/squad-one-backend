package dev.codenation.logs.repository;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import dev.codenation.logs.dto.response.LogSumaryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {

    @Query(value = "SELECT DISTINCT ON (l.hash) l.hash, " +
            "CAST(l.id AS VARCHAR) as id, " +
            "l.message," +
            "l.details," +
            "l.severity," +
            "l.environment," +
            "l.origin," +
            "l.archived," +
            "CAST(l.reported_by AS VARCHAR) as reportedBy," +
            "l.created_at as createdAt," +
            "CAST(l2.total AS VARCHAR) as occurrences " +
            "FROM logs l " +
            "INNER JOIN " +
            "  (SELECT count(hash) total, hash FROM logs GROUP BY hash) AS l2 " +
            "   ON l2.hash = l.hash " +
            "WHERE 1 = 1 " +
            "      and (coalesce(:message, null) is null or lower(l.message) like '%' || CAST(:message AS VARCHAR) || '%' )" +
            "      and (coalesce(:details, null) is null or lower(l.details) like '%' || CAST(:details AS VARCHAR) || '%' ) " +
            "      and (CAST(:severity AS VARCHAR) = 'null' or lower(l.severity) = CAST(:severity AS VARCHAR)) " +
            "      and (CAST(:environment AS VARCHAR) = 'null' or lower(l.environment) = CAST(:environment AS VARCHAR)) " +
            "      and (coalesce(:origin, null) is null or lower(l.origin) like '%' || CAST(:origin AS VARCHAR) || '%') " +
            "      and (CAST(:reportedBy AS VARCHAR) = 'null' or CAST(l.reported_by AS VARCHAR) = CAST(:reportedBy AS VARCHAR)) " +
            "      and (l.archived is false) " +
            "ORDER BY l.hash, l.created_at DESC " +
            "\n-- #pageable\n",
            nativeQuery = true)
    Page<LogSumaryResponseDTO> findAllSumarized(@Param("message") String message,
                                                @Param("details") String details,
                                                @Param("severity") String severity,
                                                @Param("environment") String environment,
                                                @Param("origin") String origin,
                                                @Param("reportedBy") String reportedBy,
                                               Pageable pageable);
}
