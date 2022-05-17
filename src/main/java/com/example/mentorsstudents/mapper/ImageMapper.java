package com.example.mentorsstudents.mapper;

import com.example.mentorsstudents.dto.ImageDto;
import com.example.mentorsstudents.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDto toDto(Image image);

    Image toImage(ImageDto imageDto);
    Image toImageFromUserRegistrationDto(ImageDto imageDto);
}
