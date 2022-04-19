package com.yago.epidemic_management.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.exception.MyException;
import com.yago.epidemic_management.model.dao.GoodsMapper;
import com.yago.epidemic_management.model.dto.add.AddGoodsDto;
import com.yago.epidemic_management.model.dto.update.UpdateGoods;
import com.yago.epidemic_management.model.pojo.Goods;
import com.yago.epidemic_management.service.GoodsService;
import com.yago.epidemic_management.service.OssService;
import com.yago.epidemic_management.utils.ServiceUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YaGo
 * @Date: 2022/4/13 23:43
 * Description:
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    OssService ossService;

    @Override
    public List<Goods> getAllGoods() {
        List<Goods> goodsList = goodsMapper.getGoodsAll();
        return goodsList;
    }

    @Override
    public PageInfo getAllPageGoods(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goodsAll = goodsMapper.getGoodsAll();
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsAll);
        return goodsPageInfo;
    }

    @Override
    public void addGoods(AddGoodsDto addGoodsDto) {
        Goods goods = new Goods();
        String timeUuid = ServiceUtil.getTimeUuid();
        addGoodsDto.setGoodsId(timeUuid);
        BeanUtils.copyProperties(addGoodsDto, goods);
        int count = goodsMapper.insertSelective(goods);
        if (count == 0) {
            throw new MyException(ExceptionEnum.INSERT_FAILED);
        }
        return;
    }

    @Override
    public void deleteByGoodsId(String goodsId) {
        goodsMapper.deleteByGoodsId(goodsId);
        return;
    }

    @Override
    public void updateByGoodsId(UpdateGoods updateGoods) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(updateGoods, goods);

        int count = goodsMapper.updateByGoodsId(goods);
        if (count == 0) {
            throw new MyException(ExceptionEnum.UPDATE_FAILED);
        }
        return;
    }
}
