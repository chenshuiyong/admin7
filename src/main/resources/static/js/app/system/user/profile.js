var validator;
var $userProfileForm = $("#update-profile-form");
$(function () {
    if (!avatar){
        avatar = ctx+"/uploaded/"+head;
    }
    $(".profile__img").find("img").attr("src", avatar);
    $("#update-profile").bind('hide.bs.modal', function () {
        $(".modal-backdrop").remove();
    });
    $("#default-avatars").bind('hide.bs.modal', function () {
        $(".modal-backdrop").remove();
    });
    $("#update-profile .btn-close").click(function () {
        $MB.closeAndRestModal("update-profile");
        validator.resetForm();
        $MB.resetJsTree("deptTree");
    });
    $(".default_avatars_list").find("img").each(function () {
        var $this = $(this);
        $this.on("click", function () {
            var target_src = $(this).attr("src");
            $.post(ctx + "user/changeAvatar", {"imgName": target_src}, function (r) {
                if (r.code === 0) {
                    $("#close_update_avatar_button").trigger("click");
                    $MB.n_success(r.msg);
                    refreshUserProfile();
                    $(".user__img").attr("src", ctx + target_src);
                } else $MB.n_danger(r.msg);
            });
        });
    });
    $(".profile__img__edit").on('click', function () {
        $('#default-avatars').modal();
    });
    validateRule();
    createDeptTree();

    $("#update-profile .btn-save").click(function () {
        getDept();
        var validator = $userProfileForm.validate();
        var flag = validator.form();
        if (flag) {
            $.post(ctx + "user/updateUserProfile", $userProfileForm.serialize(), function (r) {
                if (r.code === 0) {
                    $("#update-profile .btn-close").trigger("click");
                    $MB.n_success(r.msg);
                    refreshUserProfile();
                } else $MB.n_danger(r.msg);
            });
        }
    });

});

function refreshUserProfile() {
    $.post(ctx + "user/profile", function (r) {
        $main_content.html("").append(r);
    });
}

function editUserProfile() {
    $.post(ctx + "user/getUserProfile", {"userId": userId}, function (r) {
        if (r.code === 0) {
            var $form = $('#update-profile');
            var $deptTree = $('#deptTree');
            $form.modal();
            var user = r.msg;
            $form.find("input[name='username']").val(user.username).attr("readonly", true);
            $form.find("input[name='oldusername']").val(user.username);
            $form.find("input[name='userId']").val(user.userId);
            $form.find("input[name='email']").val(user.email);
            $form.find("input[name='mobile']").val(user.mobile);
            $form.find("input[name='description']").val(user.description);
            $("input:radio[value='" + user.ssex + "']").attr("checked", true);
            $deptTree.jstree().open_all();
            $deptTree.jstree('select_node', user.deptId, true);
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $userProfileForm.validate({
        rules: {
            email: {
                email: true
            },
            mobile: {
                checkPhone: true
            },
            ssex: {
                required: true
            },
            description: {
                maxlength: 100
            }
        },
        errorPlacement: function (error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages: {
            email: icon + "邮箱格式不正确",
            ssex: icon + "请选择性别",
            description: icon + "个人描述不能超过100个字符"
        }
    });
}

function createDeptTree() {
    $.post(ctx + "dept/tree", {}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            $('#deptTree').jstree({
                "core": {
                    'data': data.children,
                    'multiple': false
                },
                "state": {
                    "disabled": true
                },
                "checkbox": {
                    "three_state": false
                },
                "plugins": ["wholerow", "checkbox"]
            });
        } else {
            $MB.n_danger(r.msg);
        }
    })

}

function getDept() {
    var ref = $('#deptTree').jstree(true);
    $("[name='deptId']").val(ref.get_selected()[0]);
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
        url: "/imageUpload",
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
