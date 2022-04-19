package com.yago.epidemic_management.model.dto.update;

/**
 * @Author: YaGo
 * @Date: 2022/4/13 21:58
 * Description:
 **/
public class UpdateGoods {
    private Integer id;
    private String goodsId;
    private String title;
    private String image;
    private String describes;
    private Integer count;
    private Integer delCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDelCount() {
        return delCount;
    }

    public void setDelCount(Integer delCount) {
        this.delCount = delCount;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
