var validator;
var $proGoodsAddForm = $("#proGoods-add-form");

$(function () {
    validateRule();

    $("#proGoods-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $proGoodsAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "proGoods/add", $proGoodsAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "proGoods/update", $proGoodsAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#proGoods-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#proGoods-add-button").attr("name", "save");
    $("#proGoods-add-modal-title").html('新增商品');
    validator.resetForm();
    $MB.closeAndRestModal("proGoods-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $proGoodsAddForm.validate({
        rules: {
            keyy: {
                required: true,
                digits: true,
                maxlength: 10
            },
            valuee: {
                required: true,
                maxlength: 10
            },
            tableName: {
                required: true,
                maxlength: 10
            },
            fieldName: {
                required: true,
                maxlength: 10
            }
        },
        messages: {
            keyy: {
                required: icon + "请输入键名",
                digits: icon + "请输入整数",
                maxlength: icon + "长度不能超过10个字符"
            },
            valuee: {
                required: icon + "请输入键值",
                maxlength: icon + "长度不能超过10个字符"
            },
            tableName: {
                required: icon + "请输入关联表名",
                maxlength: icon + "长度不能超过10个字符"
            },
            fieldName: {
                required: icon + "请输入字段名",
                maxlength: icon + "长度不能超过10个字符"
            }
        }
    });
}


//检查图片
function checkImage() {
    var fileName = $("#filename").val();
    fileName = fileName.replace("C:\\fakepath\\", "");
    var flag = true;
    if (fileName == "") {
        flag = false;
        $MB.n_warning("请选择图片");
    }
    else {
        var size = $("#filename")[0].files[0].size;
        if (size / 20000 > 100) {
            flag = false;
            $MB.n_warning("图片大小不能超过2000KB");
        }
    }
    /*  if (flag) {
         // onLoadImage();
      } else {//清除input type=file的显示内容
          $("#filename").val("");
          return;
      }*/
    return flag;
}

//预览图片
/*function onLoadImage() {
    var file = $('#filename').get(0).files[0];
    var reader = new FileReader();
    //将文件以Data URL形式读入页面
    reader.readAsDataURL(file);
    reader.onload = function (e) {
        //显示文件
        $("#onLoadImage").html('<img src="' + this.result + '" alt="" />');
    }
}*/

/*上传图片
详细参考链接：https://www.cnblogs.com/qiumingcheng/p/6854933.html
1.processData设置为false。因为data值是FormData对象，不需要对数据做处理。
2.<form>标签添加enctype="multipart/form-data"属性。
3.cache设置为false，上传文件不需要缓存。
4.contentType设置为false，不设置contentType值，因为是由<form>表单构造的FormData对象，且已经声明了属性enctype="multipart/form-data"，所以这里设置为false。
*/
function checkSubmit() {
    if (!checkImage()) {
        return false;
    };
    var formdata = new FormData();
    formdata.append('fileName', $('#filename').get(0).files[0]);
    $.ajax({
        async: false,
        type: 'POST',
        url: "/fileUpload",
        dataType: 'json',
        data: formdata,
        contentType: false,//ajax上传图片需要添加
        processData: false,//ajax上传图片需要添加
        success: function (data) {
            if (data.hasOwnProperty("relativePath")) {
                $("#showImage").attr('src', data.relativePath);
                $("#showImage").show();
            }
            $MB.n_info(data.result_msg);
        },
        error: function (e) {
            $MB.n_warning("上传失败");
        }
    })
}
