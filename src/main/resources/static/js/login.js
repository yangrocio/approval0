 var httptod = "http://localhost/approval/"
 // var httptod = "http://172.16.20.234:80/approval/"
// 一个是配置在 linux服务器  一个是配置在windows上

function keyFun(){
    var key=event.keyCode;
    if (key==13) {
        loginsystem();
    }
}

function judgeBrowserType() {
    let browser = getOs()
    if (browser!='Chrome') {
        let sayString = "您的浏览器类型为:"+browser;
        sayString = sayString+"\n请使用谷歌浏览器或360浏览器(极速模式)\n若已是规定浏览器提示此消息，请更新至最新版本(登录按钮处下载)\n";
        alert(sayString)
        return false;
    }
    return true;


}
function getOs() {
    var OsObject = "";
    if(navigator.userAgent.indexOf("MSIE")>0) {
        return "MSIE";
    }
    else if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){
        return "Firefox";
    }
    else if(isMozilla=navigator.userAgent.indexOf("Opera")>0){ //这个也被判断为chrome
        return "Opera";
    }
    else if(isFirefox=navigator.userAgent.indexOf("Chrome")>0){
        return "Chrome";
    }
    else if(isSafari=navigator.userAgent.indexOf("Safari")>0) {
        return "Safari";
    }
    else if(isCamino=navigator.userAgent.indexOf("Camino")>0){
        return "Camino";
    }
    else if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){
        return "Gecko";
    }
}

function loginsystem() {
    if (!judgeBrowserType()){
        return;
    }
    var select = $('input:radio:checked').val();
    var username = $("#username").val();
    var password = $("#password").val();
    var operator = $("#operator").val();
    if (username == "" || username.length == 0) {
        swal("账号不能为空")
    } else {
        if (password == "" || password.length == 0) {
            swal("密码不能为空")
        } else {
            //考虑登录的系统
            if (select == "zscq") {
                $.ajax({
                    url: httptod + "login/opelogin",
                    type: "post",
                    data: {
                        "username": username,
                        "password": password,
                        "operator": operator
                    },
                    dataType: "json",
                    success: function (data) {
                        var status = data.status;
                        if (status >= 1) {
                            swal("登录知识产权审核系统")
                            window.location.href = httptod + "jump/loginSuccess";
                        } else if (status == 0) {
                            swal(data.description);
                        }
                    },
                    error: function () {
                        swal("失败");
                    }
                })
            } else if (select == "kygl") {
                $.ajax({
                    url: httptod + "login/opelogin",
                    type: "post",
                    data: {
                        "username": username,
                        "password": password,
                        "operator": operator
                    },
                    dataType: "json",
                    success: function (data) {
                        var status = data.status;
                        if (status >= 1) {
                            swal("登录科研成果管理系统")
                            window.location.href = httptod + "jump/kygllogin";
                        } else if (status == 0) {
                            swal(data.description);
                        }
                        // && status<=3
                        // else if (status==4) {
                        //     swal("尚未开发完毕")
                        // }
                    },
                    error: function () {
                        swal("失败");
                    }
                })
            }

        }
    }
}

function teacherGoLoginSuccessPage() {
    window.location.href = httptod + "jump/loginSuccess";
}

function goTeacherNoticeManage() {
    window.location.href = httptod + "jump/goTeacherNoticeManage";
}

function goDepartmentNoticeManage() {
    window.location.href = httptod + "jump/goDepartmentNoticeManage";

}

function departmentListPeople() {
    window.location.href = httptod + "jump/departmentListPeople";
}


//teacherpage.html
function goapplypage() {
    window.location.href = httptod + "jump/goApply";
}


$("#tapplyname").focus(function () {
    $("#special").prop("checked", 'checked');
})
$("#special2").focus(function () {
    $("#tapplyname").val("");  //并将值设置为空
})


$("#tsave").click(function () {
    var tname = $("#tname").val();
    var tcategory = $("input[name='tcategory']:checked").val();
    // var tcategory2 = $("input[name='tcategory2']:checked").val();
    var tapply = $("input[name='tapply']:checked").val();
    var tapplyname = $("#tapplyname").val();
    var tproject = $("input[name='tproject']:checked").val();
    var tprojectid = $("#tprojectid").val();
    var tprojectname = $("#tprojectname").val();
    var tsortname = $("#tsortname").val();
    var tinventionname = $("#tinventionname").val();
    var tunitname = $("#tunitname").val();
    var tphone = $("#tphone").val();
    var temail = $("#temail").val();
    var tintroduce = $("#tintroduce").val();
    var tduty = $("input[name='tduty']:checked").val();
    var tinventionname2 = $("#tinventionname2").val();

    if (tname == "" || tcategory == null || tapply == null || tsortname == ""
        || tinventionname == "" || tunitname == "" || tphone == "" || temail == "" || tintroduce == "" || tduty == null || tinventionname2 == "") {
        alert("必须填写完整");
    } else if (tapply == "代理" && tapplyname == "") {
        alert("必须填写完整");
    } else {
        $.ajax({
            url: httptod + "teacher/SaveDoc",
            type: "post",
            data: JSON.stringify({
                "tname": tname, "tcategory": tcategory, "tapply": tapply,
                "tapplyname": tapplyname, "tproject": tproject, "tprojectid": tprojectid, "tprojectname": tprojectname,
                "tsortname": tsortname, "tinventionname": tinventionname, "tunitname": tunitname, "tphone": tphone,
                "temail": temail, "tintroduce": tintroduce, "tduty": tduty, "tinventionname2": tinventionname2
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                var status = data.status;
                if (status >= 1) {
                    console.log(data.description + "描述-----更新成功");
                    window.location.href = httptod + "zscq/gozscqpage";
                } else if (status == 0) {
                    console.log(data.description);
                    // alert("错误");
                    alert(data.description);
                }
            },
            error: function () {
                console.log("失败");
            }
        })
    }
})

//关于文件相关的
function Values(tid) {
    $("#uploadPdf").modal({
        backdrop: 'static', keyboard: false
    });
    $("#keytid").val(tid);
    //先去判断一下文件是否存在  如果不存在  下载按钮不能被点亮
    //否则可以显示
    $.ajax({
        // url: httptod + "file/download?tid="+tid,
        url: httptod + "file/existFile",
        type: "post",
        data: {"tid": tid},
        dataType: "json",
        success: function (data) {
            // console.log(data.description);
            if (data.status == 0) {
                $("#download").attr("disabled", true);
            } else {
                $("#download").attr("disabled", false);
            }
            $(".Existingfile").val(data.description);
            $("#pdffile").val("");
            $("#progressWidth").css("width", "0%");
            $("#spanprogress").text("0%");
        },
        error: function () {
            console.log("失败");
        }
    })
}


function uploadpdffile() {
    var tid = $("#keytid").val();
    var formFile = new FormData();
    var file = $("#pdffile")[0].files[0];
    if (file == null) {
        alert("文件内容填写为空")
        return;
    }
    var filetype = getFileType(file.name);
    if (filetype != "pdf") {
        alert("文件格式错误");
        $("#uploadPdf").modal("hide");
        $("#pdffile").val("");
        return;
    }
    if (file.size >= 20 * 1024 * 1024) {
        alert("文件超过20M");
        $("#uploadPdf").modal("hide");
        $("#pdffile").val("");
        return;
    }
    formFile.append("file", file); //加入文件对象
    formFile.append("tid", tid);
    formFile.append("filesize", file.size)
    $.ajax({
        url: httptod + "file/upload",
        type: "post",
        data: formFile,
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        dataType: "json",
        // async:false,
        beforeSend: function () {
            swal({
                title: "正在上传",
                allowEscapeKey: false,
                allowOutsideClick: false,
            })
        },
        success: function (data) {
            swal({
                title: "上传成功",
                allowOutsideClick: false,
                text: data.description,
                type: "success",
            })

            $(".Existingfile").val(data.description);
        },
        error: function (res) {
            alert("上传失败")
        },
        complete: function () {
            swal.close;
        }

    })
    return
    // $("#uploadPdf").modal("hide");
    // $("#pdffile").val("");
}


function getFileType(filePath) {
    var startIndex = filePath.lastIndexOf(".");
    if (startIndex != -1)
        return filePath.substring(startIndex + 1, filePath.length).toLowerCase();
    else return "";
}


function downpdffile() {
    var tid = $("#keytid").val();
    window.location.href = httptod + 'file/download?tid=' + tid;
    $("#uploadPdf").modal("hide");
}

//暂存
$("#ttempsave").click(function () {
    var tname = $("#tname").val();
    var tcategory = $("input[name='tcategory']:checked").val();
    // var tcategory2 = $("input[name='tcategory2']:checked").val();
    var tapply = $("input[name='tapply']:checked").val();
    var tapplyname = $("#tapplyname").val();
    var tproject = $("input[name='tproject']:checked").val();
    var tprojectid = $("#tprojectid").val();
    var tprojectname = $("#tprojectname").val();
    var tsortname = $("#tsortname").val();
    var tinventionname = $("#tinventionname").val();
    var tunitname = $("#tunitname").val();
    var tphone = $("#tphone").val();
    var temail = $("#temail").val();
    var tintroduce = $("#tintroduce").val();
    var tduty = $("input[name='tduty']:checked").val();
    var tinventionname2 = $("#tinventionname2").val();
    //暂存的情况 给一个新的status
    var specialstatus = 100;  //考虑获取的方式
    $.ajax({
        url: httptod + "teacher/SaveDoc",
        type: "post",
        data: JSON.stringify({
            "tname": tname, "tcategory": tcategory, "tapply": tapply,
            "tapplyname": tapplyname, "tproject": tproject, "tprojectid": tprojectid, "tprojectname": tprojectname,
            "tsortname": tsortname, "tinventionname": tinventionname, "tunitname": tunitname, "tphone": tphone,
            "temail": temail, "tintroduce": tintroduce, "tduty": tduty, "tinventionname2": tinventionname2,
            "judgestatus": specialstatus
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            var status = data.status;
            if (status >= 1) {
                window.location.href = httptod + "zscq/gozscqpage";
            } else if (status == 0) {
                console.log(data.description);
                alert(data.description);
            }
        },
        error: function () {
            console.log("失败");
        }
    })

})

//可能用不上了
function ShowQuitOpinion(tid) {
    $.ajax({
        url: httptod + "teacher/ShowQuitOpinion",
        data: {
            tid: tid
        },
        method: "post",
        dataType: "json",
        success: function (data) {
            alert(data.description);
        },
        error: function () {
            alert("系统故障")
        }
    })
}


//teacherwritedisabled  提供一组可以修改的情况
$("#disable_save").click(function () {
    var tname = $("#tname").val();
    var tcategory = $("input[name='tcategory']:checked").val();
    // var tcategory2 = $("input[name='tcategory2']:checked").val();
    var tapply = $("input[name='tapply']:checked").val();
    var tapplyname = $("#tapplyname").val();
    var tproject = $("input[name='tproject']:checked").val();
    var tprojectid = $("#tprojectid").val();
    var tprojectname = $("#tprojectname").val();
    var tsortname = $("#tsortname").val();
    var tinventionname = $("#tinventionname").val();
    var tunitname = $("#tunitname").val();
    var tphone = $("#tphone").val();
    var temail = $("#temail").val();
    var tintroduce = $("#tintroduce").val();
    var tduty = $("input[name='tduty']:checked").val();
    var tinventionname2 = $("#tinventionname2").val();
    var tid = $("#tid").val();
    var specialstatus = 1;
    //用update 的方法  //需要判断清苦  设置status
    if (tname == "" || tcategory == null || tapply == null || tsortname == ""
        || tinventionname == "" || tunitname == "" || tphone == "" || temail == "" || tintroduce == "" || tduty == null || tinventionname2 == "") {
        alert("必须填写完整");
    } else if (tapply == "代理" && tapplyname == "") {
        alert("必须填写完整");
    } else {
        $.ajax({
            url: httptod + "teacher/UpdateDoc",
            type: "post",
            data: JSON.stringify({
                "tname": tname, "tcategory": tcategory, "tapply": tapply,
                "tapplyname": tapplyname, "tproject": tproject, "tprojectid": tprojectid, "tprojectname": tprojectname,
                "tsortname": tsortname, "tinventionname": tinventionname, "tunitname": tunitname, "tphone": tphone,
                "temail": temail, "tintroduce": tintroduce, "tduty": tduty, "tinventionname2": tinventionname2,
                "judgestatus": specialstatus, "tid": tid
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                var status = data.status;
                if (status >= 1) {
                    console.log(data.description + "描述-----更新成功");
                    window.location.href = httptod + "zscq/gozscqpage";
                } else if (status == 0) {
                    console.log(data.description);
                    // alert("错误");
                    alert(data.description);
                }
            },
            error: function () {
                console.log("失败");
            }
        })
    }
})


$("#temp_disable_save").click(function () {
    var tname = $("#tname").val();
    var tcategory = $("input[name='tcategory']:checked").val();
    // var tcategory2 = $("input[name='tcategory2']:checked").val();
    var tapply = $("input[name='tapply']:checked").val();
    var tapplyname = $("#tapplyname").val();
    var tproject = $("input[name='tproject']:checked").val();
    var tprojectid = $("#tprojectid").val();
    var tprojectname = $("#tprojectname").val();
    var tsortname = $("#tsortname").val();
    var tinventionname = $("#tinventionname").val();
    var tunitname = $("#tunitname").val();
    var tphone = $("#tphone").val();
    var temail = $("#temail").val();
    var tintroduce = $("#tintroduce").val();
    var tduty = $("input[name='tduty']:checked").val();
    var tinventionname2 = $("#tinventionname2").val();
    var tid = $("#tid").val();
    var specialstatus = 100;
    //用update 的方法  //需要判断清苦  设置status


    $.ajax({
        url: httptod + "teacher/UpdateDoc",
        type: "post",
        data: JSON.stringify({
            "tname": tname, "tcategory": tcategory, "tapply": tapply,
            "tapplyname": tapplyname, "tproject": tproject, "tprojectid": tprojectid, "tprojectname": tprojectname,
            "tsortname": tsortname, "tinventionname": tinventionname, "tunitname": tunitname, "tphone": tphone,
            "temail": temail, "tintroduce": tintroduce, "tduty": tduty, "tinventionname2": tinventionname2,
            "judgestatus": specialstatus, "tid": tid
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            var status = data.status;
            if (status >= 1) {
                console.log(data.description + "描述-----更新成功");
                window.location.href = httptod + "zscq/gozscqpage";

            } else if (status == 0) {
                console.log(data.description);
                // alert("错误");
                alert(data.description);
            }
        },
        error: function () {
            console.log("失败");
        }
    })

})


$("#psave").click(function () {
    var name = $("#name").val();
    var sex = $("input[name='sex']:checked").val();
    var birthday = $("#datepicker").val();
    var idcard = $("#idcard").val();
    var nationality = $("#nationality").val();
    var nation = $("#nation").val();
    var telephone = $("#telephone").val();
    var email = $("#email").val();
    var qualifications = $("#qualifications").val();
    var degree = $("#degree").val();
    var hdgu = $("#hdgu").val();
    var major = $("#major").val();
    var rdirection = $("#rdirection").val();
    var department = $("select[name='department']").val();
    var profession = $("select[name='profession']").val();

    $.ajax({
        url: httptod + "login/changePerson",
        type: "post",
        data: JSON.stringify({
            "name": name, "sex": sex, "birthday": birthday, "idcard": idcard,
            "nationality": nationality, "nation": nation, "telephone": telephone, "email": email,
            "qualifications": qualifications, "degree": degree, "hdgu": hdgu, "major": major,
            "rdirection": rdirection, "department": department, "profession": profession
        }),
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            console.log(data.description + "描述");
            alert("修改成功");
            // window.history.back(-1);
            window.location.href = httptod + "login/lougout";
        },
        error: function () {
            // console.log("失败");
            alert("修改失败");
        }
    })
})

$("#saveChangePassword").click(function () {
    var pwd1 = $("#pwd1").val();
    var pwd2 = $("#pwd2").val();
    if (pwd1 == null || pwd1 == "" || pwd2 == null || pwd2 == "") {
        alert("输入的密码不能为空");
    } else if (pwd1 != pwd2) {
        alert("两次输入的密码不一样")
    } else {
        $.ajax({
            url: httptod + "login/changePassword",
            type: "post",
            data: {
                password: pwd1
            },
            dataType: "json",
            // contentType: "application/json",
            success: function (data) {
                console.log(data.description + "描述");
                window.location.href = httptod + "page/index";
            },
            error: function () {
                console.log("失败");
            }
        })
    }
})


function readDoc(tid) {
    window.location.href = httptod + 'jump/goTeaDoc?tid=' + tid;
}

function translateDoc(tid) {
    window.location.href = httptod + 'file/translateDoc?tid=' + tid;
}

function translatePdf(tid) {
    window.location.href = httptod + 'file/translatePdf?tid=' + tid;
}

function deleteDoc(tid) {
    alert("暂时不能使用");
    // window.location.href = 'http://localhost:8080/jump/deleteDoc?tid='+tid;
}


//department.html
function readdepartmentDoc(tid) {
    window.location.href = httptod + 'jump/readdepartmentDoc?tid=' + tid;
}


//departmentwrite.html
$("#dsavewrite").click(function () {
    var dunitopinion = $("#dunitopinion").val();
    var dunitheadname = $("#dunitheadname").val();
    var dunitchapter = $("#dunitchapter").val();
    var ddate = $("#ddate").val();
    var tid = getUrlParam("tid");
    //接收这个对象  用update来更新
    if (dunitchapter == "" || ddate == "" || dunitheadname == "" || dunitopinion == "") {
        alert("必须填写完整");
    } else {
        $.ajax({
            url: httptod + "teacher/updateUnitOpinion",
            type: "post",
            data: JSON.stringify({
                tid: tid, dunitopinion: dunitopinion, dunitheadname: dunitheadname,
                dunitchapter: dunitchapter, ddate: ddate
            }),
            contentType: "application/json",
            dataType: "json",
            // contentType: "application/json",
            success: function (data) {
                console.log(data.description + "描述");
                window.location.href = httptod + "jump/loginSuccess";
                console.log("成功")
            },
            error: function () {
                console.log("失败");
            }
        })
    }


})


$("#dQuitDoc").click(function () {
    var tid = $("#tid").val();
    var tusername = $("#tusername").val();
    var roletype = $("#roletype").val();
    var departopinion = $("#departopinion").val();
    var schoolopinion = $("#schoolopinion").val();
    $.ajax({
        url: httptod + "teacher/dQuitDoc",
        type: "post",
        data: JSON.stringify({
            //需要一个tid
            "tid": tid, "tusername": tusername,
            "roletype": roletype, "departopinion": departopinion, "schoolopinion": schoolopinion
        }),
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            console.log(data.description + "描述");
            window.location.href = httptod + "jump/loginSuccess";
            console.log("成功")
        },
        error: function () {
            console.log("失败");
        }
    })
})


//school.html
function readSchoolDoc(tid) {
    window.location.href = httptod + 'jump/readSchoolDoc?tid=' + tid;
}


function ExcelBrief() {
    window.location.href = httptod + 'file/ExcelBrief';
}

function ExcelBriefByDepartment() {
    window.location.href = httptod + 'file/ExcelBriefByDepartment';
}

//学校 展示 各类数据的  详细 查询
function selectDocByDetail() {
    var department = $("#select_department").val();
    var ids = $("#select_tea_id").val();
    var names = $("#select_name").val();
    //如果这几个数 没有输入的话 就默认认为是输入为空 需要判断混合sql
    if (ids == '') {
        ids = "-1";
    }
    if (names == '') {
        names = "-1";
    }
    window.location.href = httptod + 'jump/goDocByDetail?department=' + department + "&id=" + ids + "&name=" + names;

}


function goDocNext(pageNum) {
    var department = $("#select_department").val();
    var ids = $("#select_tea_id").val();
    var names = $("#select_name").val();
    if (ids == '') {
        ids = "-1";
    }
    if (names == '') {
        names = "-1";
    }
    window.location.href = httptod + 'jump/goDocByDetail?pageNum=' + pageNum + "&department=" + department + "&id=" + ids + "&name=" + names;
}

//再写一个 函数 专门判断下面角标  然后跳转 每次到新界面 把原来值赋值
//每次点击角标 传递过来一个函数  关于 页面数据  进来以后先查询 一下几个核心数据 如果都没有选择的话 直接调用controller  否则把对应数据传递过去
function goListPeopleNext(pageNum) {
    var department = $("#select_department").val();
    var roletype = $("#select_roletype").val();
    var ids = $("#select_tea_id").val();
    var names = $("#select_name").val();
    if (ids == '') {
        ids = "-1";
    }
    if (names == '') {
        names = "-1";
    }
    window.location.href = httptod + 'jump/goListPeoplePlus?pageNum=' + pageNum + "&department=" + department + "&id=" + ids + "&name=" + names + "&roletype=" + roletype;
}

//schoolpeoplepage 函数  学校页面 根据 细节查询
function selectByname() {
    // var teaid = $("#teaid").val();
    var department = $("#select_department").val();
    var roletype = $("#select_roletype").val();
    var select_tea_id = $("#select_tea_id").val();
    var select_name = $("#select_name").val();

    //如果这几个数 没有输入的话 就默认认为是输入为空 需要判断混合sql
    if (select_tea_id == '') {
        select_tea_id = "-1";
    }
    if (select_name == '') {
        select_name = "-1";
    }
    window.location.href = httptod + 'jump/goSchoolPeopByDetail?department=' + department + "&id=" + select_tea_id + "&name=" + select_name + "&roletype=" + roletype;
}


function selectSuperPageByDetail() {
    // var teaid = $("#teaid").val();
    var department = $("#select_department").val();
    var select_tea_id = $("#select_tea_id").val();
    var select_name = $("#select_name").val();

    //如果这几个数 没有输入的话 就默认认为是输入为空 需要判断混合sql
    if (select_tea_id == '') {
        select_tea_id = "-1";
    }
    if (select_name == '') {
        select_name = "-1";
    }
    window.location.href = httptod + 'super/gosuperpage?department=' + department + "&id=" + select_tea_id + "&name=" + select_name;
}

//department peoppage
function departmentPeoplePageByDetail() {
    var select_tea_id = $("#select_tea_id").val();
    var select_name = $("#select_name").val();

    //如果这几个数 没有输入的话 就默认认为是输入为空 需要判断混合sql
    if (select_tea_id == '') {
        select_tea_id = "-1";
    }
    if (select_name == '') {
        select_name = "-1";
    }
    window.location.href = httptod + 'departjump/goDepartPeopByDetail?id=' + select_tea_id + "&name=" + select_name;

}

function godepartPeopNext(pageNum) {
    var select_tea_id = $("#select_tea_id").val();
    var select_name = $("#select_name").val();
    if (select_tea_id == '') {
        select_tea_id = "-1";
    }
    if (select_name == '') {
        select_name = "-1";
    }
    window.location.href = httptod + 'departjump/goDepartPeopByDetail?pageNum=' + pageNum + "&id=" + select_tea_id + "&name=" + select_name;
}

//departmentpage  多查询 多 跳转
function departmentApplyPageByDetail() {
    var select_tea_id = $("#select_tea_id").val();
    var select_name = $("#select_name").val();

    //如果这几个数 没有输入的话 就默认认为是输入为空 需要判断混合sql
    if (select_tea_id == '') {
        select_tea_id = "-1";
    }
    if (select_name == '') {
        select_name = "-1";
    }
    window.location.href = httptod + 'departjump/goDepartDocByDetail?id=' + select_tea_id + "&name=" + select_name;
}

function godepartApplyPageByDetaiNext(pageNum) {
    var select_tea_id = $("#select_tea_id").val();
    var select_name = $("#select_name").val();
    if (select_tea_id == '') {
        select_tea_id = "-1";
    }
    if (select_name == '') {
        select_name = "-1";
    }
    window.location.href = httptod + 'departjump/goDepartDocByDetail?pageNum=' + pageNum + "&id=" + select_tea_id + "&name=" + select_name;
}

function insert_department_model() {
    //先 展示 出来 一个 model  然后 输入数据  调用一个
    $.ajax({
        url: httptod + "department/findAll",
        type: "get",
        dataType: "json",
        success: function (res) {
            var data = res.list;
            var htmls;
            for (var i = 0; i < data.length; i++) {
                // html = html+"<tr> value=" + data[i].name +">"+data[i].name+"</tr>";
                htmls = htmls + "<tr><td>" + data[i].name + "</td>";
                // <td>X</td></tr>";
                htmls = htmls + "<td><button class='btn btn-warning' onclick='delete_department(" + data[i].id + ")'>删除</button></td></tr>";
            }
            $("#department_modal").html(htmls);
            $("#showdepartment").modal('show');
        },
        error: function (res) {
            console.log(res);
        }
    })

}

//添加院系 功能
function insert_department(department) {
    var department = $("#add_department").val();
    $.ajax({
        url: httptod + "department/save",
        type: "post",
        data: JSON.stringify({name: department}),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            // console.log();
            $("#showdepartment").modal('hide');
            $("#add_department").val(null);
            alert(res.description);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function delete_department(ids) {
    confirm("确认要删除吗");
    // console.log(ids);
    $.ajax({
        url: httptod + "department/delete",
        type: "post",
        data: JSON.stringify({
            id: ids
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            // console.log(res.description);
            $("#showdepartment").modal('hide');
            alert(res.description);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function insert_profession_model() {
    $.ajax({
        url: httptod + "profession/findAll",
        type: "get",
        dataType: "json",
        success: function (res) {
            var data = res.list;
            var htmls;
            for (var i = 0; i < data.length; i++) {
                // html = html+"<tr> value=" + data[i].name +">"+data[i].name+"</tr>";
                htmls = htmls + "<tr><td>" + data[i].name + "</td>";
                // <td>X</td></tr>";
                htmls = htmls + "<td><button class='btn btn-warning' onclick='delete_profession(" + data[i].id + ")'>删除</button></td></tr>";
            }
            $("#professionbody").html(htmls);
            $("#showprofession").modal('show');
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function insert_profession() {
    var department = $("#add_profession").val();
    $.ajax({
        url: httptod + "profession/save",
        type: "post",
        data: JSON.stringify({name: department}),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            // console.log();
            $("#showprofession").modal('hide');
            $("#add_profession").val(null);
            alert(res.description);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function delete_profession(ids) {
    confirm("确认要删除吗");
    // console.log(ids);
    $.ajax({
        url: httptod + "profession/delete",
        type: "post",
        data: JSON.stringify({
            id: ids
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            // console.log(res.description);
            $("#showprofession").modal('hide');
            alert(res.description);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function auto_backup() {
    // var r = confirm("确认要开启自动备份吗？默认设置每星期备份一次");
    // if (r==true){
    //     alert("开启成功");
    // }
    var rr = prompt("请输入是否开启，开始输入yes，关闭输入no", "yes");
    if (rr == 'yes') {
        alert("开启成功，每周日23点自动备份")
    } else if (rr == 'no') {
        alert("关闭成功");
    } else {
        alert("请重新输入");
    }
}


//schoolwrite
$("#suresave").click(function () {
    var suretduty = $("input[name='suretduty']:checked").val();
    var sureapply = $("input[name='sureapply']:checked").val();
    var surename = $("#surename").val();
    var surechapter = $("#surechapter").val();
    var suredate = $("#datepicker").val();
    var tid = getUrlParam("tid");

    if (suretduty == "" || sureapply == "" || surename == "" || surechapter == "" || suredate == "") {
        alert("必须填写完整");
    } else if (sureapply == '否') {
        alert("不同意申请请选择下方退回按钮");
    } else {

        $.ajax({
            url: httptod + "teacher/suresave",
            type: "post",
            data: JSON.stringify({
                //需要一个tid
                tid: tid,
                suretduty: suretduty,
                sureapply: sureapply,
                surename: surename,
                surechapter: surechapter,
                suredate: suredate,
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                console.log(data.description + "描述");
                window.location.href = httptod + "jump/loginSuccess";
                console.log("成功")
            },
            error: function () {
                console.log("失败");
            }
        })
    }


})

//super page
function resetpassword(number, roletype) {
    var password = prompt("请输入密码", ""); //将输入的内容赋给变量 name ，

    //这里需要注意的是，prompt有两个参数，前面是提示的话，后面是当对话框出来后，在对话框里的默认值
    if (password){
        $.ajax({
            url: httptod + "super/resetpassword",
            type: "post",
            data: {
                //需要一个tid
                number: number, roletype: roletype,password:password
            },
            dataType: "json",
            success: function (data) {
                console.log(data.description);
                alert("密码已修改");
            },
            error: function () {
                console.log("失败");
            }
        })
    }else{
        alert("密码不能设置为空");
    }


}

var numbers = null;
var roletypes = null;

function updatePermissions(number, roletype) {
    //先将需要模态框内容清空
    $("#selectPermissions").val("");
    numbers = number;
    roletypes = roletype;
}


$("#savePermissions").click(function () {
    var newroletype = $("#selectPermissions").val();
    if (newroletype == "" || newroletype == null) {
        alert("没有选择权限");
    } else {
        console.log(numbers + ":" + roletypes + ":" + newroletype);
        $.ajax({
            url: httptod + "super/updatePermissions",
            type: "post",
            data: {
                //需要一个tid
                number: numbers, roletype: roletypes, newroletype: newroletype
            },
            dataType: "json",
            success: function (data) {
                console.log(data.description);
                $('#Permissions').modal('hide');//隐藏modal
                //需要重新刷新界面
                window.location.href = httptod + "jump/loginSuccess";
            },
            error: function () {
                console.log("失败");
                alert("系统故障，请联系XX");
            }
        })

    }
})


function deleteUser(number, roletype) {
    alert("暂不支持删除");
}


function resetMessage() {
    $("#newname").val("");
    $("#newnumber").val("");
    $("#newcollege").val("");
    $("#newPermissions").val("");
}


function createUsermodal() {
    $.ajax({
        url: httptod + "department/findAll",
        type: "get",
        dataType: "json",
        success: function (res) {
            var data = res.list;
            var htmls;
            for (var i = 0; i < data.length; i++) {
                htmls = htmls + "<option value=" + data[i].name + ">" + data[i].name + "</option>";
            }
            $("#newcollege").html(htmls);
            $("#newUser").modal('show');
        },
        error: function (res) {
            console.log(res);
        }
    })
}

//TODO 需要添加上院系
function createuser() {
    var newname = $("#newname").val();
    var newnumber = $("#newnumber").val();
    var newcollege = $("#newcollege").val();
    var newPermissions = $("#newPermissions").val();
    var sex = $("input[name='sex']:checked").val();
    console.log(newcollege);
    if (newname == null || newname == "" || newnumber == null || newnumber == "" || newcollege == null || newcollege == "" || newPermissions == null || newPermissions == "") {
        alert("输入有空值");
    } else {
        //用两个ajax来判断
        $.ajax({
            url: httptod + "super/JudgeNumber",
            type: "get",
            data: {
                number: newnumber
            },
            dataType: "json",
            success: function (data) {
                if (data.status == 0) {
                    alert("系统中存在该账户，请重新输入");
                } else if (data.status == 1) {
                    $.ajax({
                        url: httptod + "super/CreateUser",
                        type: "post",
                        data: {
                            number: newnumber, name: newname, password: "123",
                            department: newcollege, sex: sex, roletype: newPermissions
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data.status == 1) {
                                alert("添加成功，初始密码为123");
                                window.location.href = httptod + "jump/loginSuccess";
                                // window.location.href = "http://localhost:8080/approval/jump/loginSuccess";
                            } else {
                                alert("添加失败，请咨询科研处");
                            }
                        },
                        error: function () {
                            alert("添加失败，请咨询科研处");
                        }
                    })
                }
            },
            error: function () {
                console.log("失败");
            }
        })
    }
}

function backup() {
    // alert("good111");
    $.ajax({
        url: httptod + "file/backup",
        type: "get",
        dataType: "json",
        success: function (data) {
            alert("请到服务器" + data.allfilename + "查看");
            // downloadfile(data.filename);
            // window.open(httptod+"file/preview?filename="+data);
        },
        error: function () {
            alert("添加失败，请咨询科研处");
        }
    })
    // window.location.href = httptod + 'file/backup';
}

function backuptoexcel() {
    window.location.href = httptod + 'file/backuptoexcel';
}

function clearOldMessage() {
    $("input[name='fmzl']").val(0);
    $("input[name='xxzl']").val(0);
    $("input[name='sjzl']").val(0);
    $("input[name='rjzzq']").val(0);
    $("input[name='btsj']").val(0);
    $("input[name='qtsj']").val(0);
}

function showapplyforAll() {
    clearOldMessage();
    $("#updateapplyAll").modal("show");
}

function updateApplySingle(totarget) {
    var tds = $(totarget).parents("tr").children('td');
    var name = tds.eq(1).children('input').attr('name');
    var value = tds.eq(1).children('input').val();

    // console.log(name);
    // console.log(value);
    var reg = /^\d+$/;
    if (!reg.test(value)) {
        alert("输入不合理，请重新输入");
        return false;
    } else {
        $.ajax({
            url: httptod + "super/updateAllApply",
            type: "post",
            data: {
                name: name, value: value
            },
            dataType: "json",
            success: function (data) {
                alert(data.description);
            },
            error: function (data) {
                console.log(data);
                alert("系统错误");
            }
        })
    }
}

function showApplyByNumber(number) {
    clearOldMessage();
    $.ajax({
        url: httptod + "super/selectApplyByNumber",
        type: "post",
        data: {"number": number},
        dataType: "json",
        success: function (data) {
            // console.log(data);
            $("#setnumber").val(data.number);
            $("input[name='fmzl']").val(data.fmzl);
            $("input[name='xxzl']").val(data.xxzl);
            $("input[name='sjzl']").val(data.sjzl);
            $("input[name='rjzzq']").val(data.rjzzq);
            $("input[name='btsj']").val(data.btsj);
            $("input[name='qtsj']").val(data.qtsj);
            $("#updateapplyBynumber").modal("show");
        },
        error: function () {
            alert("失败");
        }
    })
}

function updateApplyBynumber(totarget) {
    var number = $("#setnumber").val();
    var tds = $(totarget).parents("tr").children('td');
    var name = tds.eq(1).children('input').attr('name');
    var value = tds.eq(1).children('input').val();
    console.log(number);

    $.ajax({
        url: httptod + "super/updateApplyBynumber",
        type: "post",
        data: {
            number: number, name: name, value: value
        },
        dataType: "json",
        success: function (data) {
            alert(data.description);
        },
        error: function (data) {
            console.log(data);
            alert("系统错误");
        }
    })
}


// function showApplyByNumber() {
//     $.ajax({
//         url: httptod + "super/selectApplyByNumber",
//         type: "get",
//         dataType: "json",
//         success: function (data) {
//             console.log(data);
//             var htmls = "";
//             for(var i = 0;i<data.length;i++){
//                 htmls = htmls + "<tr><td class='text-center'>"+data[i].name+"</td>";
//                 htmls = htmls + "<td class='text-center'><input  type='number'  class='btn btn-github btn-xs' value="+data[i].value+" /></td>";
//                 htmls = htmls + "<td class='text-center'><button type='button' class='btn btn-default btn-xs' onclick='updateApplyNumByOne(this)'>更新</button></td></tr>";
//             }
//             $("#showapplydata").html(htmls);
//             $("#updateapplyBynumber").modal("show");
//         },
//         error: function () {
//             console.log("失败");
//         }
//     })
// }

function updateApplyNumByOne(update) {
    var tds = $(update).parents("tr").children('td');
    var name = tds.eq(0).text();
    var value = tds.eq(1).children('input').val();

    // typeof (value);
    var reg = /^\d+$/;
    if (!reg.test(value)) {
        alert("输入不合理，请重新输入");
        return false;
    } else {
        $.ajax({
            url: httptod + "super/updateApplyNum",
            type: "post",
            data: JSON.stringify({
                name: name, value: value
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                alert(data.description);
                $("#updateapply").modal('hide');
            },
            error: function () {
                alert(data.description);
            }
        })
    }

}


//学校管理员  查看 人员列表  //TODO维护一下
function goListPeople() {
    window.location.href = httptod + 'jump/goListPeople';
}

// 跳转到  loginSucces
function gologinSuccess() {
    window.location.href = httptod + 'jump/loginSuccess';
}

function school_get_person_apply(number) {
    window.location.href = httptod + 'zscq/goszscqpage?number=' + number;
}


//上传 excel文件
function ExcelUpload() {
    var formFile = new FormData();
    var file = $("#excelfile")[0].files[0];
    var filetype = getFileType(file.name);
    console.log(filetype);
    formFile.append("file", file); //加入文件对象
    formFile.append("filetype", filetype); //加入文件对象

    // var httptod = "http://localhost:8080/approval/"
    $.ajax({
        url: httptod + "file/uploadExcel",
        type: "post",
        data: formFile,
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        dataType: "json",
        async: false,
        success: function (data) {
            alert(data.description);
            $("#excelimport").modal("hide");
            $("#excelfile").val("");
            // location.href = httptod + "jump/goListPeople";
            location.reload(true);
        },
        error:function (res) {
            console.log("失败");
        }
    })


}


function goTest() {
    window.location.href = httptod + "jump/temp";
}


function goNoticeManage() {
    window.location.href = httptod + "jump/goNoticeManage";
}

function insertNotice() {
    $("#insert_notice_top").val("");
    $("#insert_notice_article").val("");
    $("#datalist_input").val("");
    $("#numbers").empty();
    //去查询 一套 所有人员的number  然后 添加一个全体人员进去
    $.ajax({
        url: httptod + "school/getAllNumber",
        type: "post",
        dataType: "json",
        beforeSend: function () {
            // $("#loding").modal({backdrop:'static'});
            // $("#loding").modal('show');
            swal({
                title: "数据获取中",
                text: "请稍等",
                type: "info",
            })
        },
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                //TODO
                $('#numbers').append('<option  label=' + data[i].name + '  value=' + data[i].number + '></option>');
            }
        },
        complete: function () {
            swal.close;
            $("#insertnoticemanage").modal('show');
        }
    })
}

//插入 公告 需要产生 id top 内容 生成时间具体到时分秒 发布人
//在 主页面显示公告
function insert_notice_submit() {
    var notice_top = $("#insert_notice_top").val();
    var notice_article = $("#insert_notice_article").val();
    var datalist_input = $("#datalist_input").val();
    console.log(datalist_input);
    $.ajax({
        url: httptod + "school/insertNotice",
        type: "post",
        data: {
            notice_top: notice_top, notice_article: notice_article, datalist_input: datalist_input
        },

        dataType: "json",
        success: function (data) {
            // alert(data.description);
            goNoticeManage();
        }
    })
}

function deleteNotice(id) {
    console.log(id)
    var answer = window.confirm("你确认要删除吗");
    if (answer == true) {
        $.ajax({
            url: httptod + "school/deleteNotice",
            type: "post",
            data: {
                nid: id
            },

            dataType: "json",
            success: function (data) {
                // alert(data.description);
                goNoticeManage();
            }
        })
    }
}

function showNoticeById(id) {
    $.ajax({
        url: httptod + "school/showNoticeById",
        type: "post",
        data: {
            nid: id
        },

        dataType: "json",
        success: function (data) {
            var notice = JSON.stringify(data);
            $("#notice_top").text(data.noticeTop);
            $("#notice_article").text(data.noticeArticle);
            var date = new Date(data.generateDate);
            // var date = data.generateDate;
            $("#notice_date").text(date.getFullYear() + "年" + (date.getMonth() + 1) + "月" + date.getDate() + "日  " + date.getHours() + ":" + date.getMinutes());
            $("#notice_name").text(data.name);
            $("#notice_assnumber").text(data.assnumber);
        }
    })

}


function goTeaBonus() {
    window.location.href = httptod + "jump/goTeaBonusPage";
}

function getTeaBonus() {
    $.ajax({
        url: httptod + "teacher/getTeaBonus",
        type: "get",
        dataType: "json",
        success: function (data) {
            // console.log(data);
            var html = "";
            var all_bonus = 0;
            var html2 = "";
            for (var i = 0; i < data.length; i++) {
                if (data[i]['judgestatus'] == '3') {
                    var temp = 0;
                    var beizhu = "";
                    if (data[i]['tcategory'] == '发明专利') {
                        temp = temp + 40;
                        beizhu = beizhu + "发明专利+40；";
                    }
                    if (data[i]['tcategory'] == '实用新型专利') {
                        temp = temp + 100;
                        beizhu = beizhu + "实用新型发明+100；";
                    }
                    if (data[i]['tcategory'] == '软件著作权') {
                        temp = temp + 70;
                        beizhu = beizhu + "软件著作权+70；";
                    }
                    if (data[i]['tcategory'] == '集成电路布图设计') {
                        temp = temp + 70;
                        beizhu = beizhu + "集成电路布图设计+70；";
                    }
                    if (data[i]['tproject'] == '横向') {
                        temp = temp + 20;
                        beizhu = beizhu + "横向+20；";
                    }
                    all_bonus = all_bonus + temp;
                    html = html + "<tr><td>" + data[i]['adate'] + "</td><td>" + data[i]['tname'] + "</td><td>" + data[i]['tcategory'] + "</td><td>"
                        + data[i]['tapply'] + "</td><td>" + data[i]['tproject'] + "</td><td>" + data[i]['tsortname']
                        + "</td><td>" + temp + "</td><td>" + beizhu + "</td></tr>";
                } else {
                    html2 = html2 + "<tr><td>" + data[i]['adate'] + "</td><td>" + data[i]['tname'] + "</td><td>" + data[i]['tcategory'] + "</td><td>"
                        + data[i]['tapply'] + "</td><td>" + data[i]['tproject'] + "</td><td>" + data[i]['tsortname']
                        + "</td><tr>";
                }


            }
            var all_message = "<span>最终绩点为:" + all_bonus + "</span>";
            $("#pass_table").html(html);
            $("#all_message").html(all_message);
            $("#pass_table2").html(html2);
        }, error: function () {
            console.log("error");
        }

    })
}

//计算绩点
function getDocListBynumber(number) {
    console.log(number);
    $.ajax({
        url: httptod + "teacher/getDocListBynumber",
        data: {
            number: number
        },
        type: "post",
        dataType: "json",
        success: function (data) {
            var html = "";
            var all_bonus = 0;
            for (var i = 0; i < data.length; i++) {
                if (data[i]['judgestatus'] == '3') {
                    var temp = 0;
                    var beizhu = "";
                    if (data[i]['tcategory'] == '发明专利' || data[i]['tcategory2'] == '发明专利') {
                        temp = temp + 40;
                        beizhu = beizhu + "发明专利+40；";
                    }
                    if (data[i]['tcategory'] == '实用新型专利' || data[i]['tcategory2'] == '实用新型专利') {
                        temp = temp + 100;
                        beizhu = beizhu + "实用新型发明+100；";
                    }
                    if (data[i]['tcategory'] == '软件著作权' || data[i]['tcategory2'] == '软件著作权') {
                        temp = temp + 70;
                        beizhu = beizhu + "软件著作权+70；";
                    }
                    if (data[i]['tcategory'] == '集成电路布图设计' || data[i]['tcategory2'] == '集成电路布图设计') {
                        temp = temp + 70;
                        beizhu = beizhu + "集成电路布图设计+70；";
                    }
                    if (data[i]['tproject'] == '横向') {
                        temp = temp + 20;
                        beizhu = beizhu + "横向+20；";
                    }
                    all_bonus = all_bonus + temp;
                    html = html + "<tr><td>" + data[i]['adate'] + "</td><td>" + data[i]['tname'] + "</td><td>" + data[i]['tcategory'] + "</td><td>"
                        + data[i]['tcategory2'] + "</td><td>" + data[i]['tapply'] + "</td><td>" + data[i]['tproject'] + "</td><td>" + data[i]['tsortname']
                        + "</td><td>" + temp + "</td><td>" + beizhu + "</td></tr>";
                }


            }
            var all_message = "<span>最终绩点为:" + all_bonus + "</span>";
            $("#pass_table").html(html);
            $("#all_message").html(all_message);
        }, error: function () {
            console.log("error");
        }

    })
}

//完成一个柱状图
function showzhuzhuangtu() {
    $.ajax({
        // http://122.51.191.79:8080/approval/
        url: httptod + "school/showTableData",
        type: "post",
        dataType: "json",
        success: function (data) {
            var lable = new Set();
            var boy_count = [];
            var women_count = [];
            for (var i = 0; i < data.length; i++) {
                let k = data[i];
                lable.add(data[i].department);
                if (k.sex == 1) {
                    boy_count.push(k.count);
                } else if (k.sex == 2) {
                    women_count.push(k.count);
                }

            }

            var areaChartData = {
                labels: Array.from(lable),
                datasets: [{
                    label: "男",
                    fillColor: "rgba(210, 214, 222, 1)",
                    strokeColor: "rgba(210, 214, 222, 1)",
                    pointColor: "rgba(210, 214, 222, 1)",
                    pointStrokeColor: "#c1c7d1",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(220,220,220,1)",
                    data: boy_count,
                },
                    {
                        label: "女",
                        fillColor: "rgba(60,141,188,0.9)",
                        strokeColor: "rgba(60,141,188,0.8)",
                        pointColor: "#3b8bba",
                        pointStrokeColor: "rgba(60,141,188,1)",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(60,141,188,1)",
                        data: women_count,
                    }
                ]
            };
            var barChartCanvas = $("#barChart").get(0).getContext("2d");
            var barChart = new Chart(barChartCanvas);
            var barChartData = areaChartData;
            barChartData.datasets[1].fillColor = "#00a65a";
            barChartData.datasets[1].strokeColor = "#00a65a";
            barChartData.datasets[1].pointColor = "#00a65a";
            var barChartOptions = {
                //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
                scaleBeginAtZero: true,
                //Boolean - Whether grid lines are shown across the chart
                scaleShowGridLines: true,
                //String - Colour of the grid lines
                scaleGridLineColor: "rgba(0,0,0,.05)",
                //Number - Width of the grid lines
                scaleGridLineWidth: 1,
                //Boolean - Whether to show horizontal lines (except X axis)
                scaleShowHorizontalLines: true,
                //Boolean - Whether to show vertical lines (except Y axis)
                scaleShowVerticalLines: true,
                //Boolean - If there is a stroke on each bar
                barShowStroke: true,
                //Number - Pixel width of the bar stroke
                barStrokeWidth: 2,
                //Number - Spacing between each of the X value sets
                barValueSpacing: 5,
                //Number - Spacing between data sets within X values
                barDatasetSpacing: 1,
                //String - A legend template
                legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
                //Boolean - whether to make the chart responsive
                responsive: true,
                maintainAspectRatio: true
            };

            barChartOptions.datasetFill = false;
            barChart.Bar(barChartData, barChartOptions);
        },
        error: function (data) {
            console.log("失败", data);
        }
    })
}


//teacher 界面 根据 查询具体日期  不再使用了
function teacherByDetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "departjump/goTeacherPageByDetail?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}


//下面均为通用函数
//在线打开pdf文件
function onlineshow(filename) {
    window.open(httptod + "file/preview?filename=" + encodeURIComponent(filename));
}

function downloadfile(filename) {
    // console.log(filename)
    // window.open(httptod+"file/downloadfile?filename="+filename)
    window.location.href = httptod + "file/downloadfile?filename=" + encodeURIComponent(filename);
}


//批量下载文件
function batchfile() {
    var listFile = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var fjsc = row.find("[name='fjsc']").html();//注意html()和val()
        listFile.push(fjsc)
    });
    for (var i = 0; i < listFile.length; i++) {
        downloadfile(listFile[i])
    }

}


//personmessage.html
function goPersonPage() {
    //当前页面打开URL页面
    window.location.href = httptod + "jump/personMessage";
}

function lougout() {
    window.location.href = httptod + "login/lougout";
}


//
function getsystime() {
    let t = null;
    $.ajax({
        url: httptod + "common/getTimeOpe",
        type: "get",
        dataType: "json",
        async: false,
        success: function (data) {
            t = data;
        }
    })
    return t;
}


function systimemodalShow() {
    $.ajax({
        url: httptod + "common/getTime",
        type: "get",
        dataType: "json",
        success: function (data) {
            // console.log(data.starttime)
            // console.log(data.endtime)
            $("#starttime").val(data.starttime)
            $("#endtime").val(data.endtime)
            $("#systime").modal("show");
        },
        error: function () {
            alert("失败");
        }
    })
}

function setsystime() {
    var starttime = $("#starttime").val();
    var endtime = $("#endtime").val();
    $.ajax({
        url: httptod + "common/setTime",
        type: "post",
        data: {
            starttime: starttime, endtime: endtime
        },

        dataType: "json",
        success: function (data) {
            alert(data.description);
            $("#systime").modal("hide");
        },
        error: function () {
            alert("失败");
        }
    })
}


function gomxhzb() {
    window.location.href = httptod + "mxhzb/gomxhzbpage";
}

 function goform() {
     window.location.href = httptod + "teamFrom/goformpage";
 }

function findmxhzb() {
    //传递一下时间
    let mySlider = document.getElementById("ex8");
    let startAndEnd = mySlider.value.toString();
    let start = startAndEnd + '-01-01';
    let end = startAndEnd + '-12-31';
    window.location.href = httptod + "mxhzb/gomxhzbpage?start=" + start + "&end=" + end;
}

 function findForm() {
     //传递一下时间
     let mySlider = document.getElementById("ex8");
     let startAndEnd = mySlider.value.toString();
     let start = startAndEnd + '-01-01';
     let end = startAndEnd + '-12-31';
     window.location.href = httptod + "teamFrom/goformpage?start=" + start + "&end=" + end;
 }

function findmxhzbnext(page) {
    let mySlider = document.getElementById("ex8");
    let startAndEnd = mySlider.value.toString();
    let start = startAndEnd + '-01-01';
    let end = startAndEnd + '-12-31';
    window.location.href = httptod + "mxhzb/gomxhzbpage?start=" + start + "&end=" + end + "&pageNum=" + page;

}

function mxhzbexport() {
    let mySlider = document.getElementById("ex8");
    let startAndEnd = mySlider.value.toString();
    window.location.href = httptod + 'mxhzb/annualExcel?year=' + startAndEnd;
}

 function fromexport() {
     let mySlider = document.getElementById("ex8");
     let startAndEnd = mySlider.value.toString();
     window.location.href = httptod + 'teamFrom/annualExcel?year=' + startAndEnd;
 }


function gotjb() {
    window.location.href = httptod + "tjb/gotjbpage";
}

function findtjb() {
    //传递一下时间
    let mySlider = document.getElementById("ex8");
    let startAndEnd = mySlider.value.toString();
    let start = startAndEnd + '-01-01';
    let end = startAndEnd + '-12-31';
    window.location.href = httptod + "tjb/gotjbpage?start=" + start + "&end=" + end;
}

function findtjbnext(page) {
    let mySlider = document.getElementById("ex8");
    let startAndEnd = mySlider.value.toString();
    let start = startAndEnd + '-01-01';
    let end = startAndEnd + '-12-31';
    window.location.href = httptod + "tjb/gotjbpage?start=" + start + "&end=" + end + "&pageNum=" + page;
}

function tjbexport() {
    let mySlider = document.getElementById("ex8");
    let startAndEnd = mySlider.value.toString();
    window.location.href = httptod + 'tjb/annualExcel?year=' + startAndEnd;

}


function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}


//到统计图界面
function gotjt() {
    window.location.href = httptod + "tjt/gotjtpage";
}


function getRewardDocx() {
    window.location.href = httptod + "json/reward.docx";
}

function getPointDocx() {
    window.location.href = httptod + "json/point.docx";
}

function getOldDataImport() {
    window.location.href = httptod + "json/oldDataImport.xlsx";
}
function getUserinfoImport() {
    window.location.href = httptod + "json/userinfoImport.xlsx";
}


$("#ExcelAll").click(function () {
    var clicks = $(this).is(':checked');
    if (!clicks) {
        $("input[name='ExcelRows']").iCheck("uncheck");
    } else {
        $("input[name='ExcelRows']").iCheck("check");
    }
    $(this).data("clicks", !clicks);
})

function ExcelCheckboxModal() {
    $("#selectExcelshow").modal('show');
}

function ExcelCheckbox() {
    var params = new Array();
    $("input[name='ExcelRows']:checked").each(function () {
        params.push($(this).val());
    });
    return params;
}

function ExcelCheckboxCancle() {
    $("#selectExcelshow").modal('hide');
}

function returnExcelTime() {
    let startExcelTime = $("#start").val();
    let endExcelTime = $("#end").val();
    let department = $("#department").val();
    let name = $("#number").val();
    var myDate = new Date();
    if (startExcelTime == null || startExcelTime == '') {
        startExcelTime = myDate.getFullYear() + "-01-01";
    }
    if (endExcelTime == null || endExcelTime == '') {
        endExcelTime = myDate.getFullYear() + "-12-31";
    }
    if (department == null || department == '') {
        department = "-1";
    }
    if (name == null || name == '') {
        name = "-1";
    }
    let ExcelTime = new Array();
    ExcelTime.push(startExcelTime);
    ExcelTime.push(endExcelTime);
    ExcelTime.push(department);
    ExcelTime.push(name);
    return ExcelTime;
}

//hylw相关
function hylwExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "tjump/hylwExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3],
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

//qklw
function qklwExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "tjump/qklwExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

//xszz
function xszzExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "tjump/xszzExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

//hpxm
function hpxmExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "ttttjump/hpxmExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

//jxys
function jxysExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "ttttjump/jxysExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

//hxlx
function hxlxExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "ttttjump/hxlxExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

//hxxm
function hxxmExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "tjump/hxxmExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

//rjzz
function rjzzExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "ttjump/rjzzExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

//hpzl
function hpzlExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "ttjump/hpzlExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

//xsch
function xschExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "ttjump/xschExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

//xsjl
function xsjlExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "ttjump/xsjlExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}
//团队
 function teamExcelDownload() {
     var params = ExcelCheckbox();
     let ExcelTime = returnExcelTime();
     $.ajax({
         url: httptod + "tttjump/teamExcelJson",
         type: "post",
         data: JSON.stringify({
             "params": params, "startExcelTime": ExcelTime[0],
             "endExcelTime": ExcelTime[1],
             "department": ExcelTime[2],
             "name": ExcelTime[3]
         }),
         dataType: "json",
         contentType: 'application/json',
         success: function (res) {
             if (res.status == 0) {
                 alert(res.description);
             } else if (res.status == 1) {
                 window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
             }
         },
         error: function (res) {
             alert("服务器内部错误，请联系管理员")
             console.log(res);
         }
     })
 }


//hpjl
function hpjlExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "tttjump/hpjlExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

//ymhj
function ymhjExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "tttjump/ymhjExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

//tyhj
function tyhjExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "tttjump/tyhjExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

//zkjs
function zkjsExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "ttttjump/zkjsExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

//cgzh
function cgzhExcelDownload() {
    var params = ExcelCheckbox();
    let ExcelTime = returnExcelTime();
    $.ajax({
        url: httptod + "ttttjump/cgzhExcelJson",
        type: "post",
        data: JSON.stringify({
            "params": params, "startExcelTime": ExcelTime[0],
            "endExcelTime": ExcelTime[1],
            "department": ExcelTime[2],
            "name": ExcelTime[3]
        }),
        dataType: "json",
        contentType: 'application/json',
        success: function (res) {
            if (res.status == 0) {
                alert(res.description);
            } else if (res.status == 1) {
                window.location.href = httptod + "file/ExcelDownloadByPath?fileName=" + res.description
            }
        },
        error: function (res) {
            alert("服务器内部错误，请联系管理员")
            console.log(res);
        }
    })
}

function getSelect() {
    var flag = confirm("确认要删除吗?请谨慎选择");
    if (flag) {
        var list = new Array();
        var otherlist = new Array();
        $.each($('input[name="selected"]:checkbox:checked'), function () {
            var row = $(this).parents("tr");
            var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
            if (judgestatus == '4' || judgestatus == '5' || judgestatus == '100'|| judgestatus == '1' ) {
                list.push($(this).val());
            }
        });
        if (list.length == 0 ) {
            alert("所选可被删除内容为空");
            return false;
        }else{
            return list;
        }
    }
}

function remainCount(temp) {
    var url = window.location.href;
    if (url.endsWith("write")){
        console.log(url.endsWith("write"))
        return;
    }
    if (temp=='tjt' || temp =='tjb' || temp =='mxhzb' || temp =='ptgl' || temp =='tdgl') {
        return
    }
    $.ajax({
        url: httptod + "common/remainCount",
        type: "post",
        data: {
            "selecttype": temp,
        },
        success: function (data) {
            if (data.status==1){
                alert(data.description);
            }

        },
        error: function () {
            console.log("失败");
        }
    })
}




//平台管理页面  和人才管理页面


//到平台管理页面去
function goptgl() {
    window.location.href = httptod + "kytd/ptgl/page";
}


function ptglInsert() {

    var platform = $("#platform").val();
    var no = $("#no").val();
    var level = $("#level").val();
    var approve_department = $("#approve_department").val();
    var department = $("#department").val();
    var principal = $("#principal").val();
    var approved_time = $("#approved_time").val();
    var annex = $("#annex")[0].files[0];
    if (annex == null) {
        alert("附件不能为空");
        return;
    }
    var formdata = new FormData();
    formdata.append("annex", annex);

    $.ajax({
        url: httptod + "file/uploadTrueAnnex/" + "ptgl",
        type: "post",
        data: formdata,
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            $.ajax({
                url: httptod + "kytd/ptgl/post",
                type: "post",
                data: JSON.stringify({
                    platform: platform, no: no, level: level, approve_department: approve_department,
                    department: department, principal: principal, approved_time: approved_time, annex: data
                }),
                contentType: "application/json",
                dataType: "json",
                success: function (res) {
                    alert(res.description);
                    goptglBydetail();
                },
            })
        },
        error: function (res) {
            console.log("erroe", res);
        }
    });

}

function ptglUpdate(id) {
    var ptgl = ptglGet(id);

    $("#platform2").val(ptgl.platform);
    $("#no2").val(ptgl.no);
    $("#level2").val(ptgl.level);
    $("#approve_department2").val(ptgl.approve_department);
    $("#department2").val(ptgl.department);
    $("#principal2").val(ptgl.principal);
    $("#approved_time2").val(ptgl.approved_time);
    $("#annex2").val(ptgl.annex);
    $("#id2").val(ptgl.id);
    $("#updatemodal").modal('show');

}

function ptglUpdateData() {
    var id = $("#id2").val();
    var platform = $("#platform2").val();
    var no = $("#no2").val();
    var level = $("#level2").val();
    var approve_department = $("#approve_department2").val();
    var department = $("#department2").val();
    var principal = $("#principal2").val();
    var annex = $("#annex2").val();
    var approved_time = $("#approved_time2").val();

    $.ajax({
        url: httptod + "kytd/ptgl/put",
        type: "post",
        data: JSON.stringify({
            id: id, platform: platform, no: no, level: level, approve_department: approve_department,
            department: department, principal: principal, approved_time: approved_time,annex:annex
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            alert(res.description);
            goptglBydetail();
        },
        error: function (res) {
            alert("失败")
        }
    })
}


function ptglGet(id) {
    let temp;
    $.ajax({
        url: httptod + "kytd/ptgl/" + id,
        type: "get",
        contentType: "application/json",
        dataType: "json",
        async: false,
        success: function (res) {
            temp = res;
        },
        error: function (res) {
            alert("失败")
        }
    })

    return temp;
}

function ptglDelete(id) {
    var r = confirm("确认要删除吗？")
    if (r == true) {
        $.ajax({
            url: httptod + "kytd/ptgl/" + id + "/delete",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                goptglBydetail();
            },
            error: function (res) {
                alert("失败")
            }
        })
    } else {
        return
    }
}
//获取当天日期
function getTotay(){
    var time = new Date();
    var year = time.getFullYear();
    var month = time.getMonth()+1;
    var date = time.getDate();
    return year+'-'+add0(month)+'-'+add0(date);
}
//修改月、天的格式，保持两位数显示
function add0(m){
    return m<10?'0'+m:m
}
var today = getTotay();


function addPtglModal() {
    $("#platform").val("");
    $("#no").val("");
    $("#level").val("国家级");
    $("#approve_department").val("");
    $("#department").val("");
    $("#principal").val("");
    $("#approved_time").val(today);
    $("#annex").val("");
    $("#addmodal").modal('show');

}

function goptglBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var name = $("#name").val();
    var department = $("#departmenttop").val();
    window.location.href = httptod + "kytd/ptgl/page?start=" + start + "&end="
        + end + "&name=" + name + "&department=" + department;
}


function goptglnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var name = $("#name").val();
    var department = $("#departmenttop").val();
    window.location.href = httptod + "kytd/ptgl/page?pageNum=" + pageNum + "&department=" + department + "&name=" + name +
        "&start=" + start + "&end=" + end;
}


function showResetModalFilePtgl(id, fjscname, type,department,platform) {
    $("#newfile").val("");
    $("#newid").val(id);
    $("#oldname").val(fjscname);
    $("#newtype").val(type);
    $("#newdepartment").val(department);
    $("#newplatform").val(platform);
    $("#retransmissionFile").modal("show");
}

function resetFilePtgl() {
    let id = $("#newid").val();
    let type = $("#newtype").val();
    let department = $("#newdepartment").val();
    let platform =$("#newplatform").val();
    let oldname =$("#oldname").val();
    let newfile = $("#annexFile")[0].files[0];
    if (type == '平台管理') {
        type = 'ptgl';
    } else  if (type == '人才团队管理'){
        type = 'tdgl';
    }

    if (newfile == null || newfile == '') {
        alert("不支持更新内容为空");
        return;
    }
    var filetype = getFileType(newfile.name);
    if (filetype != "pdf") {
        alert("文件格式错误");
        return;
    }
    if (newfile.size >= 20 * 1024 * 1024) {
        alert("文件超过20M");
        return;
    }
    var formdata = new FormData();
    formdata.append("annex", newfile);
    formdata.append("id", id);
    formdata.append("oldname", oldname);

    $.ajax({
        url: httptod + "file/uploadAnnex/" + type,
        type: "post",
        data: formdata,
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        async: false,
        success: function (res) {
            alert("成功");
            $("#retransmissionFile").modal("hide");
            window.location.reload();
        },
        error: function (res) {
            alert("失败")
        }
    })
    //新文件覆盖老文件  删除老文件  //把院系信息 也传一下 最多就传个院系

}

//到团队管理页面
function gorctdgl() {
    window.location.href = httptod + "kytd/tdgl/page";
}
//到团队管理里页面
 function goteamwork() {
     window.location.href = httptod + "kytd/tdgl/page";
 }

 function cll() {
     //首先获得下拉框的节点对象；
     var select = document.getElementById("Gradation");
     //获得该下拉框所有的option的节点对象
     var options = select.options;
     //获得当前选中的option的索引
     var index = select.selectedIndex;
     var selectedText = options[index].text;
     if(selectedText==="校级"){
         document.getElementById('Point').write("50");
         document.getElementById('Scientific').write("0");
     }else if(selectedText==="地厅级"){
         document.getElementById('Point').write("100");
         document.getElementById('Scientific').write("0");
     }else if(selectedText==="省部级"){
         document.getElementById('Point').write("500");
         document.getElementById('Scientific').write("5");
     }else if(selectedText==="国家级"){
         document.getElementById('Point').write("1000");
         document.getElementById('Scientific').write("10");
     }else{
         document.getElementById('Scientific').write("selectedText");
         document.getElementById('Point').write("selectedText");
     }
     window.location.href = httptod + "kytd/tdgl/join";
 }

 function addCll() {
     //首先获得下拉框的节点对象；
     var select = document.getElementById("level");
     //获得该下拉框所有的option的节点对象
     var options = select.options;
     //获得当前选中的option的索引
     var index = select.selectedIndex;
     var selectedText = options[index].text;
     if(selectedText==="校级"){
         document.getElementById('point').write("50");
         document.getElementById('scientific').write("0");
     }else if(selectedText==="地厅级"){
         document.getElementById('point').write("100");
         document.getElementById('scientific').write("0");
     }else if(selectedText==="省部级"){
         document.getElementById('point').write("500");
         document.getElementById('scientific').write("5");
     }else if(selectedText==="国家级"){
         document.getElementById('point').write("1000");
         document.getElementById('scientific').write("10");
     }else{
         document.getElementById('scientific').write("selectedText");
         document.getElementById('point').write("selectedText");
     }
     window.location.href = httptod + "kytd/tdgl/join";
 }

 function teamshow() {
     var list = new Array();
     $.each($('input[name="selected"]:checkbox:checked'), function () {
         list.push($(this).val());
     });
     //查看是只取第一个
     if (list.length > 1) {
         alert("选中较多，只显示第一个");
     }
     if (list.length == 0) {
         alert("未选中或选择错误");
         return false;
     }
     $.ajax({
         url: httptod + "kytd/tdgl/teamshow",
         type: "post",
         data: {id: list[0]},
         dataType: "json",
         success: function (res) {
             //把数据渲染到 页面上
             $("#teamshowmodal").modal('show');
             $("#Dept2").val(res.department);
             $("#No2").val(res.no);
             $("#TeamName2").val(res.name);
             $("#Genre2").val(res.type);
             $("#Source2").val(res.source);
             $("#Gradation2").val(res.level);
             $("#Point2").val(res.point);
             $("#Scientific2").val(res.scientific);
             $("#Subsidize2").val(res.subsidize);
             $("#receiveApproval2").val(res.approve_department);
             $("#Manager2").val(res.principal);
             $("#receiveTime2").val(res.approved_time);
         },
         error: function (res) {
             console.log(res);
         }
     })
 }

 function teamupdate() {
    var list = new Array();
    var returnFunction = false;
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        list.push($(this).val());
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2 || judgestatus == 3) {
            alert("已经通过审核的不可编辑");
            returnFunction = true;
            return false;
        }
         });
         if (returnFunction == true) {
             return false;
         }
         //查看是只取第一个
         if (list.length > 1) {
             alert("选中较多，请重新选择");
             return false;
         }
         if (list.length == 0) {
             alert("未选中或选择错误");
             return false;
         }
         $.ajax({
             url: httptod + "kytd/tdgl/teamshow",
             type: "post",
             data: {id: list[0]},
             dataType: "json",
             success: function (res) {
                 //把数据渲染到 页面上
                 $("#teamupdatemodal").modal('show');
                 $("#id").val(res.id);
                 $("#Dept").val(res.department);
                 $('#No').val(res.no);
                 $("#TeamName").val(res.name);
                 $("#Genre").val(res.type);
                 $("#Source").val(res.source);
                 $("#Manager").val(res.principal);
                 $("#Subsidize").val(res.subsidize);
                 $("#Point").val(res.point);
                 $("#Scientific").val(res.scientific);
                 $("#Gradation").combotree('setValue', res.level);
                 $("#receiveTime").val(res.approved_time);
                 $("#receiveApproval").val(res.approve_department);
                 $("#judgestatusdisabled").val(res.judgestatus);
             },
             error: function (res) {
                 console.log(res);
             }
         })
 }

 function teamupdatesubmit() {
     var id = $("#id").val();
     var department = $("#Dept").val();
     var no = $("#No").val();
     var name = $("#TeamName").val();
     var type = $("#Genre").val();
     var source = $("#Source").val();
     var principal = $("#Manager").val();
     var level = $("#Gradation").combotree('getText');
     var subsidize = $("#Subsidize").val();
     var approved_time = $("#receiveTime").val();
     var point = $("#Point").val();
     var scientific = $("#Scientific").val();
     var approve_department = $("#receiveApproval").val();
     var judgestatus = $("#judgestatusdisabled").val();

     // if (department == null || department == "" || no == null || no == "" ||
     //     name == null || name == "" || subsidize == ""|| subsidize == null||
     //     type == null || type == "" || source == "" || source == null || level == null||
     //     level == "" || principal == null || principal == "" || approve_department == null ||
     //     approve_department == "" || approved_time == "" || approved_time == null) {
     //     alert("请填写完整！");
     // }
     if (department == null || department == "" || no == null || no == "" ||
         principal == null || principal == "" ) {
         alert("请填写完整！");
     }
     else {
         $.ajax({
             success: function () {
                 $.ajax({
                     url: httptod + "kytd/tdgl/put",
                     type: "post",
                     data: JSON.stringify({
                         id: id, department: department, no: no, name: name, type: type, source: source,point:point,scientific:scientific,
                         principal: principal, level: level, subsidize:subsidize, approve_department: approve_department,
                         approved_time: approved_time, judgestatus: "1"
                     }),
                     contentType: "application/json",
                     dataType: "json",
                     success: function (res) {
                         alert(res.description);
                         window.location.href = httptod + "kytd/tdgl/page";
                     },
                     error: function (res) {
                         console.log(res);
                     }
                 })
             }
         })
     }
 }

function TdglInsert() {

    var type = $("#type").val();
    var name = $("#name").val();
    var source = $("#source").val();
    var no = $("#no").val();
    var subsidize = $("#subsidize").val();
    var level = $("#level").combotree('getText');
    var point = $("#point").val();
    var scientific = $("#scientific").val();
    var approve_department = $("#approve_department").val();
    var department = $("#department").val();
    var principal = $("#principal").val();
    var approved_time = $("#approved_time").val();
    var annex = $("#annex")[0].files[0];
    // if (annex == null) {
    //     alert("附件不能为空");
    //     return;
    // }
    var formdata = new FormData();
    formdata.append("annex", annex);

    $.ajax({
        url: httptod + "file/uploadTrueAnnex/" + "tdgl",
        type: "post",
        data: formdata,
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            $.ajax({
                url: httptod + "kytd/tdgl/post",
                type: "post",
                data: JSON.stringify({
                    type: type, name: name,source: source,no: no,subsidize: subsidize,
                    level: level, approve_department: approve_department,point:point,scientific:scientific,
                    department: department, principal: principal, approved_time: approved_time, annex: data
                }),
                contentType: "application/json",
                dataType: "json",
                success: function (res) {
                    alert(res.description);
                    goTdglBydetail();
                },
            })
        },
        error: function (res) {
            console.log("error", res);
        }
    });

}

function tdglDelete(id) {
    var r = confirm("确认要删除吗？")
    if (r == true) {
        $.ajax({
            url: httptod + "kytd/tdgl/" + id + "/delete",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                goTdglBydetail();
            },
            error: function (res) {
                alert("失败")
            }
        })
    } else {
        return;
    }
}


function tdglGet(id) {
    let temp;
    $.ajax({
        url: httptod + "kytd/tdgl/" + id,
        type: "get",
        contentType: "application/json",
        dataType: "json",
        async: false,
        success: function (res) {
            temp = res;
        },
        error: function (res) {
            alert("失败")
        }
    })

    return temp;
}

function tdglUpdate(id) {
    var tdgl = tdglGet(id);

    $("#type2").val(tdgl.type);
    $("#name2").val(tdgl.name);
    $("#source2").val(tdgl.source);
    $("#no2").val(tdgl.no);
    $("#id2").val(tdgl.id);
    $("#subsidize2").val(tdgl.subsidize);
    $("#level2").val(tdgl.level);
    $("#approve_department2").val(tdgl.approve_department);
    $("#department2").val(tdgl.department);
    $("#principal2").val(tdgl.principal);
    $("#approved_time2").val(tdgl.approved_time);
    $("#annex2").val(tdgl.annex);
    $("#updatemodal").modal('show');

}

function tdglUpdateData() {
    var type = $("#type2").val();
    var name = $("#name2").val();
    var source = $("#source2").val();
    var no = $("#no2").val();
    var subsidize = $("#subsidize2").val();
    var level = $("#level2").val();
    var approve_department = $("#approve_department2").val();
    var department = $("#department2").val();
    var principal = $("#principal2").val();
    var approved_time = $("#approved_time2").val();
    var annex = $("#annex2").val();
    var id = $("#id2").val();

    $.ajax({
        url: httptod + "kytd/tdgl/put",
        type: "post",
        data: JSON.stringify({
            id:id,type: type, name: name,source: source,no: no,subsidize: subsidize,
            level: level, approve_department: approve_department,
            department: department, principal: principal, approved_time: approved_time,annex:annex
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            alert(res.description);
            goTdglBydetail();
        },
        error: function (res) {
            alert("失败")
        }
    })
}

 function goteamwrite() {
    window.location.href = httptod + "kytd/tdgl/goteamwrite";
 }

 function downloadTeamSubsidize() {
     var list = new Array();
     $.each($('input[name="selected"]:checkbox:checked'), function () {
         var row = $(this).parents("tr");
         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
         if (judgestatus != '100') {
             list.push($(this).val());
         }
     });
     console.log(list);
     if (list.length == 0) {
         alert("您未选中或选择的均为待完善项目,故无法执行操作");
         return false;
     } else {
         $.ajax({
             url: httptod + "kytd/tdgl/download",
             type: "post",
             data: JSON.stringify(list),
             contentType: "application/json",
             dataType: "json",
             success: function (res) {
                 console.log(res.description);
                 window.location.href = httptod + "file/downloadfiledelteold?filename=" + res.description;
             },
             error: function (res) {
                 alert("服务器内部错误，请联系管理员")
                 // console.log(res);
             }
         })
     }
 }



function addTdglModal() {
    $("#name").val("");
    $("#type").val("");
    $("#source").val("");
    $("#subsidize").val("");
    $("#level").val("国家级");
    $("#approve_department").val("");
    $("#department").val("");
    $("#principal").val("");
    $("#approved_time").val(today);
    $("#annex").val("");
    $("#point").val("");
    $("#scientific").val("");
    $("#addmodal").modal('show');

}

function goTdglBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var name = $("#name_platform").val();
    var department = $("#departmenttop").val();
    window.location.href = httptod + "kytd/tdgl/page?start=" + start + "&end="
        + end + "&name=" + name + "&department=" + department;
}


function goTdglnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var name = $("#name_platform").val();
    var department = $("#departmenttop").val();
    window.location.href = httptod + "kytd/tdgl/page?pageNum=" + pageNum + "&department=" + department + "&name=" + name +
        "&start=" + start + "&end=" + end;
}


function showResetModalFileTdgl(id, fjscname, type,department,platform) {
    $("#newfile").val("");
    $("#newid").val(id);
    $("#oldname").val(fjscname);
    $("#newtype").val(type);
    $("#newdepartment").val(department);
    $("#newplatform").val(platform);
    $("#retransmissionFile").modal("show");
}

function resetFileTdgl() {
    let id = $("#newid").val();
    let type = $("#newtype").val();
    let department = $("#newdepartment").val();
    let platform =$("#newplatform").val();
    let oldname =$("#oldname").val();
    let newfile = $("#annexFile")[0].files[0];
    if (type == '平台管理') {
        type = 'ptgl';
    } else {
        type = 'tdgl';
    }

    if (newfile == null || newfile == '') {
        alert("不支持更新内容为空");
        return;
    }
    var filetype = getFileType(newfile.name);
    if (filetype != "pdf") {
        alert("文件格式错误");
        return;
    }
    if (newfile.size >= 20 * 1024 * 1024) {
        alert("文件超过20M");
        return;
    }
    var formdata = new FormData();
    formdata.append("annex", newfile);
    formdata.append("id", id);
    formdata.append("oldname", oldname);
    formdata.append("type", type);

    $.ajax({
        url: httptod + "file/uploadAnnex/" + type,
        type: "post",
        data: formdata,
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        async: false,
        success: function (res) {
            alert("成功");
            $("#retransmissionFile").modal("hide");
            window.location.reload();
        },
        error: function (res) {
            alert("失败");
        }
    })
    //新文件覆盖老文件  删除老文件  //把院系信息 也传一下 最多就传个院系

}

 function passTdgl() {
     var list = new Array();
     var otherlist = new Array();
     $.each($('input[name="selected"]:checkbox:checked'), function () {
         var row = $(this).parents("tr");
         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
         if (judgestatus == -1||judgestatus == 1||judgestatus == 2) {
             list.push($(this).val());
         } else {
             otherlist.push($(this).val());
         }
     });
     console.log(list, otherlist);
     //如果 所选择数组为空
     if (list.length == 0 && otherlist.length == 0) {
         alert("未选中或选择错误");
         return false;
     }
     //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
     if (list.length == 0) {
         alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
     } else {
         $.ajax({
             url: httptod + "kytd/tdgl/passTdgl",
             type: "post",
             data: JSON.stringify({ids: list, judgestatus: "3"}),
             contentType: "application/json",
             dataType: "json",
             success: function (res) {
                 if (otherlist.length == 0) {
                     alert(res.description)
                 } else {
                     alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                 }
                 goTdglBydetail();
             },
             error: function (res) {
                 alert(res);
             }
         })
     }
 }

 function noPassTdgl() {
     var list = new Array();
     var otherlist = new Array();
     $.each($('input[name="selected"]:checkbox:checked'), function () {
         var row = $(this).parents("tr");
         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
         if (judgestatus == -1||judgestatus == 1||judgestatus == 2) {
             list.push($(this).val());
         } else {
             otherlist.push($(this).val());
         }
         //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
     });
     if (list.length == 0 && otherlist.length == 0) {
         alert("未选中或选择错误");
         return false;
     }
     var audit = prompt("请输入不通过意见");
     if (audit == null) {
         console.log("取消或者未取消")
         return;
     }
     if (audit == '') {
         alert("退回意见不能为空");
         return;
     }

     if (list.length == 0) {
         alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
     } else {
         $.ajax({
             url: httptod + "kytd/tdgl/noPassTdgl",
             type: "post",
             data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
             contentType: "application/json",
             dataType: "json",
             success: function (res) {
                 if (otherlist.length == 0) {
                     alert(res.description)
                 } else {
                     alert("部分审核成功");
                 }
                 goshylwBydetail();

                 // window.location.href = httptod + "sjump/goshylwpage";
             },
             error: function (res) {
                 alert(res);
             }
         })
     }

 }

 function teamCheck() {
     var list = new Array();
     $.each($('input[name="selected"]:checkbox:checked'), function () {
         list.push($(this).val());
     });
     //查看是只取第一个
     if (list.length > 1) {
         alert("选中较多，只显示第一个");
     }
     if (list.length == 0) {
         alert("未选中或选中错误，请重新选择");
         return false;
     }
     $.ajax({
         url: httptod + "kytd/tdgl/teamCheck",
         type: "post",
         data: {id: list[0]},
         dataType: "json",
         success: function (res) {
             // var timus="与以下题目相似度过高："+'\n';
             var name="";
             for(var i = 0;i < res.length; i++){
                 name=name+res[i]+'\n';

             }
             alert(name);
         },
         error: function (res) {
             console.log(res);
         }
     })
 }