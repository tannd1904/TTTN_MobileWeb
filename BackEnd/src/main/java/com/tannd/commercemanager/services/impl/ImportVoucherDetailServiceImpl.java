package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.CategoryDTO;
import com.tannd.commercemanager.dto.ImportVoucherDetailDTO;
import com.tannd.commercemanager.maper.CategoryMapper;
import com.tannd.commercemanager.maper.ImportVoucherDetailMapper;
import com.tannd.commercemanager.model.Category;
import com.tannd.commercemanager.model.ImportVoucherDetail;
import com.tannd.commercemanager.repository.CategoryRepository;
import com.tannd.commercemanager.repository.ImportVoucherDetailRepository;
import com.tannd.commercemanager.services.CategoryService;
import com.tannd.commercemanager.services.ImportVoucherDetailService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ServiceHelper
public class ImportVoucherDetailServiceImpl extends
        AbstractServiceImpl<ImportVoucherDetailRepository, ImportVoucherDetailMapper,
                ImportVoucherDetailDTO, ImportVoucherDetail>
        implements ImportVoucherDetailService {

    @Autowired
    ImportVoucherDetailRepository thisRepository;

    private ImportVoucherDetailMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ImportVoucherDetailRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public ImportVoucherDetailMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @Override
    public List<ImportVoucherDetailDTO> getByImportId(Long id) {
        var list1 = getRepository().findAll();
        List<ImportVoucherDetail> list = new ArrayList<>();
        list1.stream().forEach(s -> {
            if (s.getImportVoucher().getId() == id) {
                list.add(s);
            }
        });
        return (List<ImportVoucherDetailDTO>) list.stream()
                .map(entity -> getMapper().toDto(entity, getCycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }

//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    private RoomRepository roomRepository;
//
//    @Override
//    public CategoryDTO save(CategoryRequest categoryRequest) {
//        Category category = new Category();
//        List<Category> categoryList = categoryRepository.findAll();
//        String idNew = "";
//        if(categoryList.size() > 0){
//            Integer end = categoryList.size() - 1;
//            String id = categoryList.get(end).getCategoryId();
//            String IdInt = id.substring(3);
//            String IdBegin = id.substring(0,3);
//            Integer newIdInt = Integer.parseInt(IdInt);
//            newIdInt += 1;
//            String newIdString = newIdInt.toString();
//            if(newIdString.length() == 1)
//            {
//                newIdString = "0000" + newIdString;
//            }else if(newIdString.length() == 2)
//            {
//                newIdString = "000" + newIdString;
//            }else if(newIdString.length() == 3)
//            {
//                newIdString = "00" + newIdString;
//            }
//            else if(newIdString.length() == 4)
//            {
//                newIdString = "0" + newIdString;
//            }
//            idNew = IdBegin + newIdString;
//        }else {
//            idNew = "DMH00001";
//        }
//        category.setCategoryId(idNew);
//        category.setCategoryName(categoryRequest.getCategoryName());
//        Room room = roomRepository.findRoomByRoomId(categoryRequest.getRoom());
//        category.setRoom(room);
//        categoryRepository.save(category);
//        var categoryDTO = CategoryMapper.INSTANCE.toDto(category, new CycleAvoidingMappingContext());
//        categoryDTO.toString();
//        return categoryDTO;
//    }
//
//    @Override
//    public List<CategoryDTO> getAllCategory() {
//        List<Category> categoryList = categoryRepository.findAllByOrderByCategoryIdDesc();
//        List<CategoryDTO> categoryDTOList = categoryList.stream().map(category -> categoryMapper.toDTO(category)).collect(Collectors.toList());
//        return categoryDTOList;
//    }
}
