package dev.codenation.logs.mapper;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.dto.request.LogCreationDTO;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.dto.response.AllLogSummaryResponseDTO;
import dev.codenation.logs.dto.response.LogSumaryResponseDTO;
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
//
//    @Mappings({
//            @Mapping(source = "id", target = "id"),
//            @Mapping(source = "hash", target = "hash"),
//            @Mapping(source = "message", target = "logDetail.message"),
//            @Mapping(source = "details", target = "logDetail.details"),
//            @Mapping(source = "severity", target = "logDetail.severity"),
//            @Mapping(source = "environment", target = "origin.environment"),
//            @Mapping(source = "origin", target = "origin.origin"),
//            @Mapping(source = "archived", target = "archived"),
//            @Mapping(source = "archivedBy", target = "archivedBy.id"),
//            @Mapping(source = "reportedBy", target = "reportedBy.id")
//    })
//    @Named("toDto")
//    AllLogSummaryResponseDTO map(Log log);
//
//    @Mappings({
//            @Mapping(source = "id", target = "id"),
//            @Mapping(source = "firstName",target = "firstName"),
//            @Mapping(source = "lastName",target = "lastName"),
//            @Mapping(source = "email",target = "email"),
//            @Mapping(source = "createdAt", target = "createdAt"),
//            @Mapping(source = "updatedAt",target = "updatedAt")
//    })
//    AllLogSummaryResponseDTO map(@MappingTarget AllLogSummaryResponseDTO dto, Log parent);
//
//    @IterableMapping(qualifiedByName = "toDto")
//    List<AllLogSummaryResponseDTO> map(List<Log> children);
//
//    static List<AllLogSummaryResponseDTO> map(List<Log> children, Log parent) {
//        throw new UnsupportedOperationException("Not implemented");
//    }
}
