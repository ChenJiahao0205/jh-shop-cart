package pers.jhshop.cart.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.jhshop.cart.model.entity.Snapshots;
import pers.jhshop.cart.model.req.SnapshotsCreateReq;
import pers.jhshop.cart.model.req.SnapshotsQueryReq;
import pers.jhshop.cart.model.req.SnapshotsUpdateReq;
import pers.jhshop.cart.model.vo.SnapshotsVO;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 购物车商品快照表 服务类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
public interface ISnapshotsService extends IService<Snapshots> {

    void createBiz(SnapshotsCreateReq createReq);

    void updateBiz(SnapshotsUpdateReq updateReq);

    SnapshotsVO getByIdBiz(Long id);

    Page<SnapshotsVO> pageBiz(SnapshotsQueryReq queryReq);

    Page<Snapshots> page(SnapshotsQueryReq queryReq);

    List<Snapshots> listByQueryReq(SnapshotsQueryReq queryReq);

    Map<Long, Snapshots> getIdEntityMap(List<Long> ids);

    Snapshots getOneByQueryReq(SnapshotsQueryReq queryReq);

}
