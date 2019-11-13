package cc.mrbird.system.service.impl.pro;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.system.domain.pro.ProGoods;
import cc.mrbird.system.service.pro.ProGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("proGoodsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProGoodsServiceImpl extends BaseService<ProGoods> implements ProGoodsService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<ProGoods> findAllProGoodss(ProGoods proGoods, QueryRequest request) {
		try {
			Example example = new Example(ProGoods.class);
			Criteria criteria = example.createCriteria();
			if (StringUtils.isNotBlank(proGoods.getGoodsName())) {
				criteria.andCondition("goodsName=", proGoods.getGoodsName());
			}
			//example.setOrderByClause("goods_id");
			List<ProGoods> list  = this.selectByExample(example);
			return  list;
		} catch (Exception e) {
			log.error("获取商品信息失败", e);
			return new ArrayList<>();
		}
	}

	@Override
	@Transactional
	public void addProGoods(ProGoods proGoods) {
		this.save(proGoods);
	}

	@Override
	@Transactional
	public void deleteProGoodss(String proGoodsIds) {
		List<String> list = Arrays.asList(proGoodsIds.split(","));
		this.batchDelete(list, "proGoodsId", ProGoods.class);
	}

	@Override
	@Transactional
	public void updateProGoods(ProGoods proGoods) {
		this.updateNotNull(proGoods);
	}

	@Override
	public ProGoods findById(Long proGoodsId) {
		return this.selectByKey(proGoodsId);
	}

}
