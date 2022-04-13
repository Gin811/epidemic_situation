package com.yago.epidemic_management.model.pojo;

/**
 * @Author: YaGo
 * @Date: 2022/4/13 21:58
 * Description:
 **/
public class Goods {
    private Integer id;
    private Integer goodsId;
    private String title;
    private String image;
    private String describe;
    private Integer count;
    private Integer del_count;

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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDel_count() {
        return del_count;
    }

    public void setDel_count(Integer del_count) {
        this.del_count = del_count;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}
