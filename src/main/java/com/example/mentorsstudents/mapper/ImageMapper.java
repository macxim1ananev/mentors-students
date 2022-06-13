package com.example.mentorsstudents.mapper;

import com.example.mentorsstudents.dto.ImageDto;
import com.example.mentorsstudents.entity.Image;
import com.example.mentorsstudents.mapper.annotation.BaseImageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDto toDto(Image image);
    @BaseImageInfo
    Image toImage(ImageDto imageDto);
    Image toImageFromUserRegistrationDto(ImageDto imageDto);
}
