package pers.jhshop.cart.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.cart.consts.JhShopCartApiConstants;
import pers.jhshop.common.entity.ResultBo;
import pers.jhshop.cart.model.req.CartsCreateReq;
import pers.jhshop.cart.model.req.CartsQueryReq;
import pers.jhshop.cart.model.req.CartsUpdateReq;
import pers.jhshop.cart.model.vo.CartsVO;
import pers.jhshop.cart.service.ICartsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 购物车表 前端控制器
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Slf4j
@RestController
@RequestMapping(JhShopCartApiConstants.API_USER + "carts")
@RequiredArgsConstructor
public class CartsController {
    private final ICartsService cartsService;

    @GetMapping("test")
    public ResultBo test() {
        return ResultBo.success();
    }

    @PostMapping("create")
    public ResultBo create(@RequestBody CartsCreateReq createReq) {
        cartsService.createBiz(createReq);
        return ResultBo.success();
    }

    @PostMapping("update")
    public ResultBo update(@RequestBody CartsUpdateReq updateReq) {
        cartsService.updateBiz(updateReq);
        return ResultBo.success();
    }

    @GetMapping("getById")
    public ResultBo<CartsVO> getById(Long id) {
        CartsVO vo = cartsService.getByIdBiz(id);
        return ResultBo.success(vo);
    }

    @PostMapping("page")
    public ResultBo<Page<CartsVO>> page(@RequestBody CartsQueryReq queryReq) {
        Page page = cartsService.pageBiz(queryReq);
        return ResultBo.success(page);
    }
}

