package pers.jhshop.cart.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.common.entity.ResultBo;
import pers.jhshop.cart.consts.JhShopCartApiConstants;
import pers.jhshop.cart.model.req.SnapshotsCreateReq;
import pers.jhshop.cart.model.req.SnapshotsQueryReq;
import pers.jhshop.cart.model.req.SnapshotsUpdateReq;
import pers.jhshop.cart.model.vo.SnapshotsVO;
import pers.jhshop.cart.service.ISnapshotsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 购物车商品快照表 前端控制器
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Slf4j
@RestController
@RequestMapping(JhShopCartApiConstants.API_USER + "snapshots")
@RequiredArgsConstructor
public class SnapshotsController {
    private final ISnapshotsService snapshotsService;

    @PostMapping("create")
    public ResultBo create(@RequestBody SnapshotsCreateReq createReq) {
        snapshotsService.createBiz(createReq);
        return ResultBo.success();
    }

    @PostMapping("update")
    public ResultBo update(@RequestBody SnapshotsUpdateReq updateReq) {
        snapshotsService.updateBiz(updateReq);
        return ResultBo.success();
    }

    @GetMapping("getById")
    public ResultBo<SnapshotsVO> getById(Long id) {
        SnapshotsVO vo = snapshotsService.getByIdBiz(id);
        return ResultBo.success(vo);
    }

    @PostMapping("page")
    public ResultBo<Page<SnapshotsVO>> page(@RequestBody SnapshotsQueryReq queryReq) {
        Page page = snapshotsService.pageBiz(queryReq);
        return ResultBo.success(page);
    }
}

