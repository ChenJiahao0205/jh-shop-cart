package pers.jhshop.cart.model.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.cart.model.entity.Snapshots;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 购物车商品快照表查询Req
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SnapshotsQueryReq", description = "购物车商品快照表查询Req")
public class SnapshotsQueryReq extends Page<Snapshots> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "快照ID，主键")
    private Long id;

    @ApiModelProperty(value = "购物车ID，外键关联购物车表")
    private Integer cartId;

    @ApiModelProperty(value = "商品ID，外键关联商品表")
    private Integer productId;

    @ApiModelProperty(value = "商品价格(记录商品的当前价格，在结算时与商品表中的价格保持一致)")
    private BigDecimal price;

    @ApiModelProperty(value = "商品库存(记录商品的实时库存，确保结算时库存的一致性)")
    private Integer availableStock;

    @ApiModelProperty(value = "快照时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "描述-模糊匹配")
    private String descriptionLike;

    @ApiModelProperty(value = "生效标志(TRUE-生效, FALSE-失效)")
    private Boolean validFlag;



}
