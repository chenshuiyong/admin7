function updateProGoods() {
    var selected = $("#proGoodsTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的商品！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个商品！');
        return;
    }
    var proGoodsId = selected[0].proGoodsId;
    $.post(ctx + "proGoods/getProGoods", {"proGoodsId": proGoodsId}, function (r) {
        if (r.code === 0) {
            var $form = $('#proGoods-add');
            $form.modal();
            var proGoods = r.msg;
            $("#proGoods-add-modal-title").html('修改商品');
            $form.find("input[name='proGoodsId']").val(proGoods.proGoodsId);
            $form.find("input[name='keyy']").val(proGoods.keyy);
            $form.find("input[name='valuee']").val(proGoods.valuee);
            $form.find("input[name='tableName']").val(proGoods.tableName);
            $form.find("input[name='fieldName']").val(proGoods.fieldName);
            $("#proGoods-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}