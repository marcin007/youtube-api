package com.marcinwo.youtubeapi.demo.mapper;

import com.marcinwo.youtubeapi.demo.dto.UserDTO;
import com.marcinwo.youtubeapi.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({ // workaround for bug. Id was appearing as null.
            @Mapping(target = "id", source = "id")
    })
    UserDTO toUserDTO(User user);
    List<UserDTO> toUserDTO(Collection<User> user);

}
