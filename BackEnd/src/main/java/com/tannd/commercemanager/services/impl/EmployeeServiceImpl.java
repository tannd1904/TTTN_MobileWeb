package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.CategoryDTO;
import com.tannd.commercemanager.dto.EmployeeDTO;
import com.tannd.commercemanager.maper.CategoryMapper;
import com.tannd.commercemanager.maper.EmployeeMapper;
import com.tannd.commercemanager.model.Category;
import com.tannd.commercemanager.model.Employee;
import com.tannd.commercemanager.repository.CategoryRepository;
import com.tannd.commercemanager.repository.EmployeeRepository;
import com.tannd.commercemanager.services.CategoryService;
import com.tannd.commercemanager.services.EmployeeService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class EmployeeServiceImpl extends AbstractServiceImpl<EmployeeRepository, EmployeeMapper, EmployeeDTO, Employee>
        implements EmployeeService {

    @Autowired
    EmployeeRepository thisRepository;

    private EmployeeMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public EmployeeRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public EmployeeMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
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
