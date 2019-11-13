package cc.mrbird.system.domain.pro;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "pro_goods")
public class ProGoods {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 分类
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 品牌
     */
    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "article_no")
    private String articleNo;

    /**
     * 库存
     */
    @Column(name = "inventory_num")
    private Integer inventoryNum;

    /**
     * 最小质量
     */
    @Column(name = "min_weight")
    private String minWeight;

    /**
     * 最大质量
     */
    @Column(name = "max_weight")
    private String maxWeight;

    /**
     * 单位
     */
    @Column(name = "unit")
    private String unit;

    /**
     * 市场价
     */
    @Column(name = "market_price")
    private BigDecimal marketPrice;

    /**
     * 商品价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 获取主键
     *
     * @return goods_id - 主键
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置主键
     *
     * @param goodsId 主键
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取商品名称
     *
     * @return goods_name - 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 获取分类
     *
     * @return category_name - 分类
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置分类
     *
     * @param categoryName 分类
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    /**
     * 获取品牌
     *
     * @return brand_name - 品牌
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 设置品牌
     *
     * @param brandName 品牌
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    /**
     * @return article_no
     */
    public String getArticleNo() {
        return articleNo;
    }

    /**
     * @param articleNo
     */
    public void setArticleNo(String articleNo) {
        this.articleNo = articleNo == null ? null : articleNo.trim();
    }

    /**
     * 获取库存
     *
     * @return inventory_num - 库存
     */
    public Integer getInventoryNum() {
        return inventoryNum;
    }

    /**
     * 设置库存
     *
     * @param inventoryNum 库存
     */
    public void setInventoryNum(Integer inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    /**
     * 获取最小质量
     *
     * @return min_weight - 最小质量
     */
    public String getMinWeight() {
        return minWeight;
    }

    /**
     * 设置最小质量
     *
     * @param minWeight 最小质量
     */
    public void setMinWeight(String minWeight) {
        this.minWeight = minWeight == null ? null : minWeight.trim();
    }

    /**
     * 获取最大质量
     *
     * @return max_weight - 最大质量
     */
    public String getMaxWeight() {
        return maxWeight;
    }

    /**
     * 设置最大质量
     *
     * @param maxWeight 最大质量
     */
    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight == null ? null : maxWeight.trim();
    }

    /**
     * 获取单位
     *
     * @return unit - 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置单位
     *
     * @param unit 单位
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * 获取市场价
     *
     * @return market_price - 市场价
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * 设置市场价
     *
     * @param marketPrice 市场价
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取商品价格
     *
     * @return price - 商品价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     *
     * @param price 商品价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}