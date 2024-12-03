package pers.jhshop.cart.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 购物车表
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("carts")
@ApiModel(value = "Carts对象", description = "购物车表")
public class Carts extends Model<Carts> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车ID，主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID，外键关联用户表")
    @TableField("USER_ID")
    private Integer userId;

    @ApiModelProperty(value = "商品ID，外键关联商品表")
    @TableField("PRODUCT_ID")
    private Integer productId;

    @ApiModelProperty(value = "商品数量")
    @TableField("QUANTITY")
    private Integer quantity;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATED_AT")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATED_AT")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "是否已删除（0=未删除，1=已删除）")
    @TableField("IS_DELETED")
    private Boolean isDeleted;

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
