package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.AccountDTO;
import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.model.Account;
import com.tannd.commercemanager.model.Wishlist;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper extends AbstractMapper<AccountDTO, Account>{
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
}
