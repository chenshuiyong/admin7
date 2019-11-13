package cc.mrbird.system.domain.pro;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "pro_goods_img")
public class ProGoodsImg {
    @Id
    @Column(name = "img_id")
    private Integer imgId;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 图片类型：商品主图，1，商品详情图2
     */
    @Column(name = "img_type")
    private Byte imgType;

    /**
     * 是否主图 是1否0
     */
    @Column(name = "is_main")
    private Byte isMain;

    /**
     * @return img_id
     */
    public Integer getImgId() {
        return imgId;
    }

    /**
     * @param imgId
     */
    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    /**
     * @return img_url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * @return goods_id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * @param goodsId
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取图片类型：商品主图，1，商品详情图2
     *
     * @return img_type - 图片类型：商品主图，1，商品详情图2
     */
    public Byte getImgType() {
        return imgType;
    }

    /**
     * 设置图片类型：商品主图，1，商品详情图2
     *
     * @param imgType 图片类型：商品主图，1，商品详情图2
     */
    public void setImgType(Byte imgType) {
        this.imgType = imgType;
    }

    /**
     * 获取是否主图 是1否0
     *
     * @return is_main - 是否主图 是1否0
     */
    public Byte getIsMain() {
        return isMain;
    }

    /**
     * 设置是否主图 是1否0
     *
     * @param isMain 是否主图 是1否0
     */
    public void setIsMain(Byte isMain) {
        this.isMain = isMain;
    }
}