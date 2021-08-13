package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.repository.helper.AbstractRepositoryHelper;
import com.tannd.commercemanager.util.FunctionUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface AbstractRepository<E, ID> extends
        JpaRepository<E, ID>, AbstractRepositoryHelper<E, ID> {

    @Override
    default Optional<E> findById(ID id) {
        return findByIdCustom(id, FunctionUtil.getModelClassFromRepo(this.getClass().getGenericInterfaces()));
    }

    @Override
    @SuppressWarnings("unchecked")
    default List<E> findAll() {
        var resultTypeClzz = FunctionUtil.getModelClassFromRepo(this.getClass().getGenericInterfaces());
//        boolean hasRemovalFlag = hasRemovalFlag(resultTypeClzz);
        CriteriaBuilder cb =  getEntityManagerClass().getCriteriaBuilder();
        CriteriaQuery<E> query = (CriteriaQuery<E>) cb.createQuery(resultTypeClzz);
        Root<E> root = (Root<E>) query.from(resultTypeClzz);
//        if (hasRemovalFlag) {
//            query.where(cb.equal(root.get("removalFlag"), false));
//        }
        return getEntityManagerClass().createQuery(query).getResultList();
    }

    @SuppressWarnings("unchecked")
    default Optional<E> findByIdCustom(ID id, Class resultTypeClzz) {

//        boolean hasRemovalFlag = hasRemovalFlag(resultTypeClzz);
//        if(hasRemovalFlag) {
//            return findByIdAndRemovalFlagFalse(id, resultTypeClzz);
//        } else {
            return (Optional<E>) Optional.ofNullable(getEntityManagerClass().find(resultTypeClzz, id));
//        }
    }

}
