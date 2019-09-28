package dev.codenation.logs.mapper;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.dto.UserFindFilterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User map(UserFindFilterDTO filter);

    List<UserFindFilterDTO> map(List<User> all);
}
