package com.news.model.dto.converter;


import com.news.model.Writer;
import com.news.model.dto.WriterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WriterToWriterDTOConvert implements Converter<Writer, WriterDTO> {

    private final ModelMapper modelMapper;

    public WriterToWriterDTOConvert(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public  WriterDTO convert(Writer source) {
        return modelMapper.map(source, WriterDTO.class);
    }

}

