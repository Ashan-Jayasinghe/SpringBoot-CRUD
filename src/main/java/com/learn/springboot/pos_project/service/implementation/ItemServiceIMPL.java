package com.learn.springboot.pos_project.service.implementation;

import com.learn.springboot.pos_project.dto.paginated.PaginatedResponseItemDTO;
import com.learn.springboot.pos_project.dto.request.ItemSaveRequestDTO;
import com.learn.springboot.pos_project.dto.response.ItemGetResponseDTO;
import com.learn.springboot.pos_project.entity.Item;
import com.learn.springboot.pos_project.exception.NotFoundException;
import com.learn.springboot.pos_project.repository.ItemRepo;
import com.learn.springboot.pos_project.service.ItemService;
import com.learn.springboot.pos_project.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;


    @Override
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {
//        Item item = new Item(
//                1,
//                itemSaveRequestDTO.getItemName(),
//                itemSaveRequestDTO.getMeasuringUnitType(),
//                itemSaveRequestDTO.getBalanceQty(),
//                itemSaveRequestDTO.getSupplierPrice(),
//                itemSaveRequestDTO.getSellingPrice(),
//                true
//        );
//        itemRepo.save(item);
//        String message = "Successfully save item";
//        return message;

        //Item item = modelMapper.map(itemSaveRequestDTO, Item.class);
        Item item = itemMapper.dtoToEntity(itemSaveRequestDTO);
        if(!itemRepo.existsById(item.getItemId())){
            itemRepo.save(item);
            return "item saved successfully: " + item.getItemId();
        }else{
            throw new DuplicateKeyException("Already Added");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndActive(String itemName) {
        boolean status=true;
        List<Item> itemList = itemRepo.findItemsByItemNameEqualsAndActiveStatus(itemName, status);
        if(itemList.size()>0){
            List<ItemGetResponseDTO> itemGetResponseDTOList = modelMapper.map(itemList, new TypeToken<List<ItemGetResponseDTO>>() {}.getType());
            return itemGetResponseDTOList;

        }else {
            throw new EmptyResultDataAccessException(1);
        }


//        List<Item> itemList = itemRepo.findItemsByItemNameEquals(itemName);
//        List<ItemGetResponseDTO> itemGetResponseDTOList = new ArrayList<>();
//        for(Item item: itemList){
//            if(item.isActiveStatus()){
//                ItemGetResponseDTO itemGetResponseDTO = modelMapper.map(item, ItemGetResponseDTO.class);
//                itemGetResponseDTOList.add(itemGetResponseDTO);
//            }else{
//                throw new RuntimeException("An Error has been there");
//            }
//
//        }
//        return itemGetResponseDTOList;


    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndActiveByMapStruct(String itemName) {
       boolean status=true;
       List<Item> itemList = itemRepo.findItemsByItemNameEqualsAndActiveStatus(itemName, status);
       if(itemList.size()>0){
           List<ItemGetResponseDTO> itemGetResponseDTOList = itemMapper.entityListToResponseDTOList(itemList);
           return itemGetResponseDTOList;

       }else{
           throw new EmptyResultDataAccessException(1);
       }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByActiveStatus(boolean activeStatus) {
        boolean status=true;
        List<Item> itemList =itemRepo.findAllByActiveStatusEquals(activeStatus);
        if(itemList.size()>0) {
            List<ItemGetResponseDTO> itemGetResponseDTOList = itemMapper.entityListToResponseDTOList(itemList);
            return itemGetResponseDTOList;
        }else{
            throw new NotFoundException("Item Not Found");
        }
    }

    @Override
    public PaginatedResponseItemDTO getItemByActiveStatusWithPagination(boolean activeStatus, int page, int size) {
        boolean status=true;
        Page<Item> itemList = itemRepo.findAllByActiveStatusEquals(activeStatus, PageRequest.of(page,size));
        int countTotal = itemRepo.countAllByActiveStatusEquals(activeStatus);
        if(itemList.getSize() <1){
            throw new EmptyResultDataAccessException(1);
        }
        PaginatedResponseItemDTO paginatedResponseItemDTOList = new PaginatedResponseItemDTO(
                itemMapper.entityPageToDtoList(itemList),
                countTotal
        );

        return paginatedResponseItemDTOList;
    }

    @Override
    public PaginatedResponseItemDTO getAllItemsPaginated(int page, int size) {
        Page<Item> itemList = itemRepo.findAll(PageRequest.of(page,size));
        long countTotal = itemRepo.count();
        if(itemList.getSize() > 0 ){
            PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                    itemMapper.entityPageToDtoList(itemList),
                    countTotal
            );
            return paginatedResponseItemDTO;
        }else{
            throw new EmptyResultDataAccessException(1);
        }
    }



}
