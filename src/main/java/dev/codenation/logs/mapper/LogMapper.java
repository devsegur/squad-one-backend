package dev.codenation.logs.mapper;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.dto.request.LogCreationDTO;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.dto.response.LogResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface LogMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "hash", target = "hash"),
            @Mapping(source = "message", target = "logDetail.message"),
            @Mapping(source = "details", target = "logDetail.details"),
            @Mapping(source = "severity", target = "logDetail.severity"),
            @Mapping(source = "environment", target = "origin.environment"),
            @Mapping(source = "origin", target = "origin.origin"),
            @Mapping(source = "archived", target = "archived"),
            @Mapping(source = "archivedBy", target = "archivedBy.id"),
            @Mapping(source = "reportedBy", target = "reportedBy.id")
    })
    Log map(LogFilterRequestDTO filter);

    @Mappings({
            @Mapping(source = "hash", target = "hash"),
            @Mapping(source = "message", target = "logDetail.message"),
            @Mapping(source = "details", target = "logDetail.details"),
            @Mapping(source = "severity", target = "logDetail.severity"),
            @Mapping(source = "environment", target = "origin.environment"),
            @Mapping(source = "origin", target = "origin.origin"),
            @Mapping(source = "reportedBy.id", target = "reportedBy.id")
    })
    Log map(LogCreationDTO logCreationDTO);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "hash", target = "hash"),
            @Mapping(source = "logDetail.message", target = "message"),
            @Mapping(source = "logDetail.details", target = "details"),
            @Mapping(source = "logDetail.severity", target = "severity"),
            @Mapping(source = "origin", target = "origin"),
            @Mapping(source = "archived", target = "archived"),
            @Mapping(source = "archivedBy.id", target = "archivedBy.id"),
            @Mapping(source = "reportedBy.id", target = "reportedBy.id")
    })
    @Named("toDto")
    LogResponseDTO map(Log log);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "hash", target = "hash"),
            @Mapping(source = "logDetail.message", target = "message"),
            @Mapping(source = "logDetail.details", target = "details"),
            @Mapping(source = "logDetail.severity", target = "severity"),
            @Mapping(source = "origin", target = "origin"),
            @Mapping(source = "archived", target = "archived"),
            @Mapping(source = "archivedBy.id", target = "archivedBy.id"),
            @Mapping(source = "reportedBy.id", target = "reportedBy.id")
    })
    LogResponseDTO map(@MappingTarget LogResponseDTO dto, Log parent);

    @IterableMapping(qualifiedByName = "toDto")
    List<LogResponseDTO> map(List<Log> children);

    static List<LogResponseDTO> map(List<Log> children, Log parent) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
