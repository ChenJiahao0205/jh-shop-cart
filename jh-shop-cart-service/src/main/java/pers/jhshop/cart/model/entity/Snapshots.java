package pers.jhshop.cart.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 购物车商品快照表
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cart_snapshots")
@ApiModel(value = "Snapshots对象", description = "购物车商品快照表")
public class Snapshots extends Model<Snapshots> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "快照ID，主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "购物车ID，外键关联购物车表")
    @TableField("CART_ID")
    private Integer cartId;

    @ApiModelProperty(value = "商品ID，外键关联商品表")
    @TableField("PRODUCT_ID")
    private Integer productId;

    @ApiModelProperty(value = "商品价格(记录商品的当前价格，在结算时与商品表中的价格保持一致)")
    @TableField("PRICE")
    private BigDecimal price;

    @ApiModelProperty(value = "商品库存(记录商品的实时库存，确保结算时库存的一致性)")
    @TableField("AVAILABLE_STOCK")
    private Integer availableStock;

    @ApiModelProperty(value = "快照时间")
    @TableField("CREATED_AT")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "描述")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty(value = "生效标志(TRUE-生效, FALSE-失效)")
    @TableField("VALID_FLAG")
    private Boolean validFlag;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
