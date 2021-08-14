package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.ProductDTO;
import com.tannd.commercemanager.maper.ProductMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.repository.ProductRepository;
import com.tannd.commercemanager.services.ProductService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ServiceHelper
public class ProductServiceImpl extends AbstractServiceImpl<ProductRepository, ProductMapper, ProductDTO, Product>
        implements ProductService {

    @Autowired
    ProductRepository thisRepository;

    private ProductMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ProductRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public ProductMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @Override
    public List<ProductDTO> getTop4ProductNewArrival() {
        Optional<Product> listEntity = getRepository().findTop4ByOrderByIdDesc();
        List<ProductDTO> list = new ArrayList<>();
        listEntity.stream().forEach(product -> {
            list.add(getMapper().toDto(product, new CycleAvoidingMappingContext()));
        });
        return null;
    }


//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private ProviderRepository providerRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    private ProductMapper productMapper;
//
//    @Override
//    public ProductDTO save(ProductRequest productRequest) {
//        Product product = new Product();
//        List<Product> productList = productRepository.findAll();
//        String idNew = "";
//        if(productList.size() > 0){
//            Integer end = productList.size() - 1;
//            String id = productList.get(end).getProductId();
//            String IdInt = id.substring(2);
//            String IdBegin = id.substring(0,2);
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
//            idNew = "SP00001";
//        }
//        product.setProductId(idNew);
//        product.setProductName(productRequest.getProductName());
//        product.setStatus(productRequest.getStatus());
//        product.setDescription(productRequest.getDescription());
//        Provider provider = providerRepository.findProviderByProviderId(productRequest.getProviderId());
//        product.setProvider(provider);
//        Category category = categoryRepository.findCategoryByCategoryId(productRequest.getCategoryId());
//        product.setCategory(category);
//        productRepository.save(product);
//        ProductDTO productDTO = productMapper.toDTO(product);
//        return productDTO;
//    }
//
//    @Override
//    public List<ProductDTO> getAllProduct() {
//        List<Product> productList = productRepository.findAllByOrderByProductIdDesc();
//        List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
//        return productDTOList;
//    }
}
