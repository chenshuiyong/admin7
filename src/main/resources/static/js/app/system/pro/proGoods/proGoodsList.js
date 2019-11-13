$(function() {
    var $proGoodsTableForm = $(".proGoods-table-form");
    var settings = {
        url: ctx + "proGoods/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                keyy: $proGoodsTableForm.find("input[name='keyy']").val().trim(),
                valuee: $proGoodsTableForm.find("input[name='valuee']").val().trim(),
                tableName: $proGoodsTableForm.find("input[name='tableName']").val().trim(),
                fieldName: $proGoodsTableForm.find("input[name='fieldName']").val().trim()
            };
        },
        columns: [{
                checkbox: true
            },
            {
                field: 'goodsId',
                title: '字典ID',
                width: 150
            }, {
                field: 'goodsName',
                title: '键'
            }, {
                field: 'categoryName',
                title: 'categoryName'
            }, {
                field: 'brandName',
                title: '表名'
            }, {
                field: 'inventoryNum',
                title: '字段名'
            }
        ]
    };

    $MB.initTable('proGoodsTable', settings);
});

function search() {
    $MB.refreshTable('proGoodsTable');
}

function refresh() {
    $(".proGoods-table-form")[0].reset();
    search();
}

function deleteProGoodss() {
    var selected = $("#proGoodsTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的字典！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].proGoodsId;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中的字典？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'proGoods/delete', { "ids": ids }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportProGoodsExcel(){
	$.post(ctx+"proGoods/excel",$(".proGoods-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}

function exportProGoodsCsv(){
	$.post(ctx+"proGoods/csv",$(".proGoods-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}