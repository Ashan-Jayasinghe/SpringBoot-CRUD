package com.learn.springboot.pos_project.controller;

import com.learn.springboot.pos_project.dto.CustomerDTO;
import com.learn.springboot.pos_project.dto.paginated.PaginatedResponseItemDTO;
import com.learn.springboot.pos_project.dto.request.ItemSaveRequestDTO;
import com.learn.springboot.pos_project.dto.response.ItemGetResponseDTO;
import com.learn.springboot.pos_project.service.ItemService;
import com.learn.springboot.pos_project.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;

//    @PostMapping(
//            path = {"/save"}
//    )
//    public String saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
//        String message = itemService.saveItem(itemSaveRequestDTO);
//        return message;
//
//    }

    @PostMapping(
            path = {"/save"}
    )
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        String message = itemService.saveItem(itemSaveRequestDTO);
//        ResponseEntity<StandardResponse> response =new ResponseEntity<StandardResponse>(
//                new StandardResponse(201,"Success",message),HttpStatus.CREATED
//
//        );
//        return response;
          return new ResponseEntity<StandardResponse>(
                  new StandardResponse(201,"Success",message),
                  HttpStatus.CREATED
          );
//        Standard output = code , message , data

    }

    @GetMapping(
            path = "/get-by-name",
            params = "name"
    )
    public List<ItemGetResponseDTO> getItemByNameAndStatus(@RequestParam(value = "name") String itemName) {
        List<ItemGetResponseDTO> itemList = itemService.getItemByNameAndActive(itemName);
        return itemList;
    }


    @GetMapping(
            path = "/get-by-name-with-mapstruct",
            params = "name"
    )
    public List<ItemGetResponseDTO> getItemByNameWithMapStruct(@RequestParam(value = "name") String itemName) {
        List<ItemGetResponseDTO> itemGetResponseDTOList = itemService.getItemByNameAndActiveByMapStruct(itemName);
        return itemGetResponseDTOList;
    }
    @GetMapping(
            path = "/get-all-item-by-status",
            params = {"activeStatus","page","size"}
    )
    public ResponseEntity<StandardResponse> getItemByActiveStatus(
            @RequestParam(value = "activeStatus") boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
            ) {
        //List<ItemGetResponseDTO> itemGetResponseDTOList = itemService.getItemByActiveStatus(activeStatus,page,size);
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getItemByActiveStatusWithPagination(activeStatus,page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",paginatedResponseItemDTO),
                HttpStatus.OK
        );
    }

 @GetMapping(
         path = {"/get-all-paginated"},
         params = {"page","size"}
 )
    public ResponseEntity<StandardResponse>getAllItemsPaginated(
            @RequestParam("page") int page,
            @RequestParam("size") int size
 ){
     PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItemsPaginated(page,size);
     return new ResponseEntity<StandardResponse>(
             new StandardResponse(200,"Success",paginatedResponseItemDTO),
             HttpStatus.OK
     );
 }
}
