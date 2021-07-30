package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.CartDetailDTO;
import com.tannd.commercemanager.maper.CartDetailMapper;
import com.tannd.commercemanager.model.CartDetail;
import com.tannd.commercemanager.repository.CartDetailRepository;
import com.tannd.commercemanager.services.CartDetailService;

public class CartDetailServiceImpl extends AbstractServiceImpl<CartDetailRepository, CartDetailMapper, CartDetailDTO, CartDetail>
        implements CartDetailService {
}
