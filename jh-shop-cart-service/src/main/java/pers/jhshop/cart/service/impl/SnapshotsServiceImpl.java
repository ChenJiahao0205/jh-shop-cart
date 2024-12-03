package pers.jhshop.cart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.cart.model.req.SnapshotsCreateReq;
import pers.jhshop.cart.model.req.SnapshotsQueryReq;
import pers.jhshop.cart.model.req.SnapshotsUpdateReq;
import pers.jhshop.cart.model.vo.SnapshotsVO;
import pers.jhshop.cart.model.entity.Snapshots;
import pers.jhshop.cart.mapper.SnapshotsMapper;
import pers.jhshop.cart.service.ISnapshotsService;
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
 * 购物车商品快照表 服务实现类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SnapshotsServiceImpl extends ServiceImpl<SnapshotsMapper, Snapshots> implements ISnapshotsService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBiz(SnapshotsCreateReq createReq) {


        Snapshots entity = new Snapshots();
        BeanUtil.copyProperties(createReq, entity);

        boolean insertResult = entity.insert();

        if (!insertResult) {
            throw new ServiceException("数据插入失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBiz(SnapshotsUpdateReq updateReq) {

        // 1.入参有效性判断
        if (Objects.isNull(updateReq.getId())) {
            throw new ServiceException("id不能为空");
        }

        Snapshots entity = getById(updateReq.getId());
        if (Objects.isNull(entity)) {
            throw new ServiceException("购物车商品快照表不存在");
        }

        // 2.重复性判断
        Snapshots entityToUpdate = new Snapshots();
        BeanUtil.copyProperties(updateReq, entityToUpdate);

        boolean updateResult = entityToUpdate.updateById();
        if (!updateResult) {
            throw new ServiceException("数据更新失败");
        }
    }

    @Override
    public SnapshotsVO getByIdBiz(Long id) {
        // 1.入参有效性判断
        if (Objects.isNull(id)) {
            throw new ServiceException("id不能为空");
        }

        // 2.存在性判断
        Snapshots entity = getById(id);
        if (Objects.isNull(entity)) {
            throw new ServiceException("购物车商品快照表不存在");
        }

        SnapshotsVO vo = new SnapshotsVO();
        BeanUtil.copyProperties(entity, vo);

            return vo;
    }

    @Override
    public Page<SnapshotsVO> pageBiz(SnapshotsQueryReq queryReq) {
        Page<Snapshots> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        page.addOrder(OrderItem.desc("id"));

        LambdaQueryWrapper<Snapshots> queryWrapper = getLambdaQueryWrapper(queryReq);

        page(page, queryWrapper);

        Page<SnapshotsVO> pageVOResult = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<Snapshots> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return pageVOResult;
        }

        List<SnapshotsVO> vos = records.stream().map(record -> {
            SnapshotsVO vo = new SnapshotsVO();
            BeanUtil.copyProperties(record, vo);
    
            return vo;
        }).collect(Collectors.toList());

        pageVOResult.setRecords(vos);
        return pageVOResult;
    }

    @Override
    public Page<Snapshots> page(SnapshotsQueryReq queryReq) {
        Page<Snapshots> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        LambdaQueryWrapper<Snapshots> queryWrapper = getLambdaQueryWrapper(queryReq);
        page(page, queryWrapper);
        return page;
    }

    @Override
    public List<Snapshots> listByQueryReq(SnapshotsQueryReq queryReq) {
        LambdaQueryWrapper<Snapshots> queryWrapper = getLambdaQueryWrapper(queryReq);
        List<Snapshots> listByQueryReq = list(queryWrapper);
        return listByQueryReq;
    }

    @Override
    public Map<Long, Snapshots> getIdEntityMap(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new HashMap<>();
        }

        LambdaQueryWrapper<Snapshots> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Snapshots::getId, ids);
        List<Snapshots> entities = list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return new HashMap<>();
        }

        return entities.stream().collect(Collectors.toMap(Snapshots::getId, Function.identity(), (v1, v2) -> v1));
    }

    @Override
    public Snapshots getOneByQueryReq(SnapshotsQueryReq queryReq) {
        LambdaQueryWrapper<Snapshots> queryWrapper = getLambdaQueryWrapper(queryReq);
        queryWrapper.last("LIMIT 1");

        List<Snapshots> listByQueryReq = list(queryWrapper);
        if (CollectionUtils.isEmpty(listByQueryReq)) {
            return null;
        }

        return listByQueryReq.get(0);
    }

    private LambdaQueryWrapper<Snapshots> getLambdaQueryWrapper(SnapshotsQueryReq queryReq) {
        LambdaQueryWrapper<Snapshots> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(Objects.nonNull(queryReq.getId()), Snapshots::getId, queryReq.getId());
        queryWrapper.eq(Objects.nonNull(queryReq.getCartId()), Snapshots::getCartId, queryReq.getCartId());
        queryWrapper.eq(Objects.nonNull(queryReq.getProductId()), Snapshots::getProductId, queryReq.getProductId());
        queryWrapper.eq(Objects.nonNull(queryReq.getPrice()), Snapshots::getPrice, queryReq.getPrice());
        queryWrapper.eq(Objects.nonNull(queryReq.getAvailableStock()), Snapshots::getAvailableStock, queryReq.getAvailableStock());
        queryWrapper.eq(Objects.nonNull(queryReq.getCreatedAt()), Snapshots::getCreatedAt, queryReq.getCreatedAt());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getDescription()), Snapshots::getDescription, queryReq.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getDescriptionLike()), Snapshots::getDescription, queryReq.getDescriptionLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getValidFlag()), Snapshots::getValidFlag, queryReq.getValidFlag());

        return queryWrapper;
    }

}
