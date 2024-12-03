package pers.jhshop.cart.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.jhshop.cart.model.entity.Carts;
import pers.jhshop.cart.model.req.CartsCreateReq;
import pers.jhshop.cart.model.req.CartsQueryReq;
import pers.jhshop.cart.model.req.CartsUpdateReq;
import pers.jhshop.cart.model.vo.CartsVO;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
public interface ICartsService extends IService<Carts> {

    void createBiz(CartsCreateReq createReq);

    void updateBiz(CartsUpdateReq updateReq);

    CartsVO getByIdBiz(Long id);

    Page<CartsVO> pageBiz(CartsQueryReq queryReq);

    Page<Carts> page(CartsQueryReq queryReq);

    List<Carts> listByQueryReq(CartsQueryReq queryReq);

    Map<Long, Carts> getIdEntityMap(List<Long> ids);

    Carts getOneByQueryReq(CartsQueryReq queryReq);

}
