package cc.mrbird.system.service.pro;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.pro.ProGoods;

import java.util.List;

public interface ProGoodsService extends IService<ProGoods> {

    List<ProGoods> findAllProGoodss(ProGoods proGoods, QueryRequest request);

    ProGoods findById(Long proGoodsId);

    void addProGoods(ProGoods proGoods);

    void deleteProGoodss(String proGoodsIds);

    void updateProGoods(ProGoods goods);
}
