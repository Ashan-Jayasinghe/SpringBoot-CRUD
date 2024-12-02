package com.learn.springboot.pos_project.util.mappers;

import com.learn.springboot.pos_project.dto.request.ItemSaveRequestDTO;
import com.learn.springboot.pos_project.dto.response.ItemGetResponseDTO;
import com.learn.springboot.pos_project.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    //List<Item> itemList ---> List<ItemGetResponseDTO>
    List<ItemGetResponseDTO> entityListToResponseDTOList(List<Item> itemList);

    //ItemSaveRequestDTO --->  Item
    Item dtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemGetResponseDTO> entityPageToDtoList(Page<Item> itemList);

}
