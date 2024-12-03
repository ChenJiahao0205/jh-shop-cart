package pers.jhshop.cart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.cart.model.req.CartsCreateReq;
import pers.jhshop.cart.model.req.CartsQueryReq;
import pers.jhshop.cart.model.req.CartsUpdateReq;
import pers.jhshop.cart.model.vo.CartsVO;
import pers.jhshop.cart.model.entity.Carts;
import pers.jhshop.cart.mapper.CartsMapper;
import pers.jhshop.cart.service.ICartsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.jhshop.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CartsServiceImpl extends ServiceImpl<CartsMapper, Carts> implements ICartsService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBiz(CartsCreateReq createReq) {


        Carts entity = new Carts();
        BeanUtil.copyProperties(createReq, entity);

        boolean insertResult = entity.insert();

        if (!insertResult) {
            throw new ServiceException("数据插入失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBiz(CartsUpdateReq updateReq) {

        // 1.入参有效性判断
        if (Objects.isNull(updateReq.getId())) {
            throw new ServiceException("id不能为空");
        }

        Carts entity = getById(updateReq.getId());
        if (Objects.isNull(entity)) {
            throw new ServiceException("购物车表不存在");
        }

        // 2.重复性判断
        Carts entityToUpdate = new Carts();
        BeanUtil.copyProperties(updateReq, entityToUpdate);

        boolean updateResult = entityToUpdate.updateById();
        if (!updateResult) {
            throw new ServiceException("数据更新失败");
        }
    }

    @Override
    public CartsVO getByIdBiz(Long id) {
        // 1.入参有效性判断
        if (Objects.isNull(id)) {
            throw new ServiceException("id不能为空");
        }

        // 2.存在性判断
        Carts entity = getById(id);
        if (Objects.isNull(entity)) {
            throw new ServiceException("购物车表不存在");
        }

        CartsVO vo = new CartsVO();
        BeanUtil.copyProperties(entity, vo);

            return vo;
    }

    @Override
    public Page<CartsVO> pageBiz(CartsQueryReq queryReq) {
        Page<Carts> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        page.addOrder(OrderItem.desc("id"));

        LambdaQueryWrapper<Carts> queryWrapper = getLambdaQueryWrapper(queryReq);

        page(page, queryWrapper);

        Page<CartsVO> pageVOResult = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<Carts> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return pageVOResult;
        }

        List<CartsVO> vos = records.stream().map(record -> {
            CartsVO vo = new CartsVO();
            BeanUtil.copyProperties(record, vo);
    
            return vo;
        }).collect(Collectors.toList());

        pageVOResult.setRecords(vos);
        return pageVOResult;
    }

    @Override
    public Page<Carts> page(CartsQueryReq queryReq) {
        Page<Carts> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        LambdaQueryWrapper<Carts> queryWrapper = getLambdaQueryWrapper(queryReq);
        page(page, queryWrapper);
        return page;
    }

    @Override
    public List<Carts> listByQueryReq(CartsQueryReq queryReq) {
        LambdaQueryWrapper<Carts> queryWrapper = getLambdaQueryWrapper(queryReq);
        List<Carts> listByQueryReq = list(queryWrapper);
        return listByQueryReq;
    }

    @Override
    public Map<Long, Carts> getIdEntityMap(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new HashMap<>();
        }

        LambdaQueryWrapper<Carts> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Carts::getId, ids);
        List<Carts> entities = list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return new HashMap<>();
        }

        return entities.stream().collect(Collectors.toMap(Carts::getId, Function.identity(), (v1, v2) -> v1));
    }

    @Override
    public Carts getOneByQueryReq(CartsQueryReq queryReq) {
        LambdaQueryWrapper<Carts> queryWrapper = getLambdaQueryWrapper(queryReq);
        queryWrapper.last("LIMIT 1");

        List<Carts> listByQueryReq = list(queryWrapper);
        if (CollectionUtils.isEmpty(listByQueryReq)) {
            return null;
        }

        return listByQueryReq.get(0);
    }

    private LambdaQueryWrapper<Carts> getLambdaQueryWrapper(CartsQueryReq queryReq) {
        LambdaQueryWrapper<Carts> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(Objects.nonNull(queryReq.getId()), Carts::getId, queryReq.getId());
        queryWrapper.eq(Objects.nonNull(queryReq.getUserId()), Carts::getUserId, queryReq.getUserId());
        queryWrapper.eq(Objects.nonNull(queryReq.getProductId()), Carts::getProductId, queryReq.getProductId());
        queryWrapper.eq(Objects.nonNull(queryReq.getQuantity()), Carts::getQuantity, queryReq.getQuantity());
        queryWrapper.eq(Objects.nonNull(queryReq.getCreatedAt()), Carts::getCreatedAt, queryReq.getCreatedAt());
        queryWrapper.eq(Objects.nonNull(queryReq.getUpdatedAt()), Carts::getUpdatedAt, queryReq.getUpdatedAt());
        queryWrapper.eq(Objects.nonNull(queryReq.getIsDeleted()), Carts::getIsDeleted, queryReq.getIsDeleted());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getDescription()), Carts::getDescription, queryReq.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getDescriptionLike()), Carts::getDescription, queryReq.getDescriptionLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getValidFlag()), Carts::getValidFlag, queryReq.getValidFlag());

        return queryWrapper;
    }

}
