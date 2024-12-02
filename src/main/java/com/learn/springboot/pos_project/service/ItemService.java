package com.learn.springboot.pos_project.service;

import com.learn.springboot.pos_project.dto.paginated.PaginatedResponseItemDTO;
import com.learn.springboot.pos_project.dto.request.ItemSaveRequestDTO;
import com.learn.springboot.pos_project.dto.response.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);

    public List<ItemGetResponseDTO> getItemByNameAndActive(String itemName);

    public List<ItemGetResponseDTO> getItemByNameAndActiveByMapStruct(String itemName);

    public List<ItemGetResponseDTO> getItemByActiveStatus(boolean activeStatus);

    public PaginatedResponseItemDTO getItemByActiveStatusWithPagination(boolean activeStatus, int page, int size);
}
