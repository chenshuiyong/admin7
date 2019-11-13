package cc.mrbird.system.controller.pro;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.FileUtil;
import cc.mrbird.system.domain.pro.ProGoods;
import cc.mrbird.system.service.pro.ProGoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ProGoodsController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProGoodsService proGoodsService;

    @Log("获取商品信息")
    @RequestMapping("proGoods")
    @RequiresPermissions("proGoods:list")
    public String index() {
        return "system/pro/proGoods/proGoodsList";
    }

    @RequestMapping("proGoods/list")
    @RequiresPermissions("proGoods:list")
    @ResponseBody
    public Map<String, Object> proGoodsList(QueryRequest request, ProGoods proGoods) {
        return super.selectByPageNumSize(request, () -> this.proGoodsService.findAllProGoodss(proGoods, request));
    }

    @RequestMapping("proGoods/excel")
    @ResponseBody
    public ResponseBo proGoodsExcel(ProGoods proGoods) {
        try {
            List<ProGoods> list = this.proGoodsService.findAllProGoodss(proGoods, null);
            return FileUtil.createExcelByPOIKit("商品表", list, ProGoods.class);
        } catch (Exception e) {
            log.error("导出商品信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("proGoods/csv")
    @ResponseBody
    public ResponseBo proGoodsCsv(ProGoods proGoods) {
        try {
            List<ProGoods> list = this.proGoodsService.findAllProGoodss(proGoods, null);
            return FileUtil.createCsv("商品表", list, ProGoods.class);
        } catch (Exception e) {
            log.error("导出商品信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequestMapping("proGoods/getProGoods")
    @ResponseBody
    public ResponseBo getProGoods(Long proGoodsId) {
        try {
            ProGoods proGoods = this.proGoodsService.findById(proGoodsId);
            return ResponseBo.ok(proGoods);
        } catch (Exception e) {
            log.error("获取商品信息失败", e);
            return ResponseBo.error("获取商品信息失败，请联系网站管理员！");
        }
    }

    @Log("新增商品 ")
    @RequiresPermissions("proGoods:add")
    @RequestMapping("proGoods/add")
    @ResponseBody
    public ResponseBo addProGoods(ProGoods proGoods) {
        try {
            this.proGoodsService.addProGoods(proGoods);
            return ResponseBo.ok("新增商品成功！");
        } catch (Exception e) {
            log.error("新增商品失败", e);
            return ResponseBo.error("新增商品失败，请联系网站管理员！");
        }
    }

    @Log("删除商品")
    @RequiresPermissions("proGoods:delete")
    @RequestMapping("proGoods/delete")
    @ResponseBody
    public ResponseBo deleteProGoodss(String ids) {
        try {
            this.proGoodsService.deleteProGoodss(ids);
            return ResponseBo.ok("删除商品成功！");
        } catch (Exception e) {
            log.error("删除商品失败", e);
            return ResponseBo.error("删除商品失败，请联系网站管理员！");
        }
    }

    @Log("修改商品 ")
    @RequiresPermissions("proGoods:update")
    @RequestMapping("proGoods/update")
    @ResponseBody
    public ResponseBo updateProGoods(ProGoods proGoods) {
        try {
            this.proGoodsService.updateProGoods(proGoods);
            return ResponseBo.ok("修改商品成功！");
        } catch (Exception e) {
            log.error("修改商品失败", e);
            return ResponseBo.error("修改商品失败，请联系网站管理员！");
        }
    }
}
