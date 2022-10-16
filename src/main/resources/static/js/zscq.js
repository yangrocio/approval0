// var httptod = "http://localhost:8080/approval/"

// var httptod = "http://172.16.20.234:8080/approval/"

function gozscqpage() {
    window.location.href = httptod + "zscq/gozscqpage";
}

function gozscqBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "zscq/gozscqpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function gozscqwrite() {
    window.location.href = httptod + "zscq/gozscqwrite";
}

function gozscqnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "zscq/gozscqpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function zscqdelete() {
    var flag = confirm("确认要删除吗?请谨慎选择");
    if (flag) {
        var list = new Array();
        var returnFunction = false;
        $.each($('input[name="selected"]:checkbox:checked'), function () {
            list.push($(this).val());
            var row = $(this).parents("tr");
            var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
            console.log(judgestatus);
            if (judgestatus == 2 || judgestatus == 3) {
                alert("已经通过审核的不可删除");
                returnFunction = true;
                return false;
            }
        });
        if (list.length == 0) {
            alert("未选中,无法执行删除操作");
            returnFunction = true;
        }
        if (returnFunction == false) {
            // console.log(list);
            // 在这里添加 ajax函数  后端获取到list集合
            $.ajax({
                url: httptod + "zscq/deletezscq",
                type: "post",
                data: JSON.stringify(list),
                contentType: "application/json",
                dataType: "json",
                success: function (res) {
                    alert(res.description);
                    window.location.href = httptod + "zscq/gozscqpage";
                },
                error: function (res) {
                    console.log(res);
                }
            })
        }
    }
}


//院系管理员部分
function godzscqpage() {
    window.location.href = httptod + "zscq/godzscqpage";
}

function godzscqBydetail() {
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    window.location.href = httptod + "zscq/godzscqpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus + "&number=" + number;

}

function godzscqwrite() {
    window.location.href = httptod + "zscq/godzscqwrite";
}

function godzscqnext(pageNum) {
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "zscq/godzscqpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus + "&number=" + number;

}


//部门通过 和退回 部分 理解
function deopinionzscq() {
    var dunitopinion = $("#dunitopinion").val();
    var dunitheadname = $("#dunitheadname").val();
    var dunitchapter = $("#dunitchapter").val();
    var ddate = $("#ddate").val();
    var tid = getUrlParam("tid");
    if (dunitchapter == "" || ddate == "" || dunitheadname == "" || dunitopinion == "") {
        alert("必须填写完整");
    } else {
        $.ajax({
            url: httptod + "zscq/deopinion",
            type: "post",
            data: JSON.stringify({
                tid: tid, dunitopinion: dunitopinion, dunitheadname: dunitheadname,
                dunitchapter: dunitchapter, ddate: ddate,judgestatus:'2'
            }),
            contentType: "application/json",
            dataType: "json",
            // contentType: "application/json",
            success: function (data) {
                window.location.href = httptod + "zscq/godzscqpage";
            },
            error: function () {
                console.log("失败");
            }
        })
    }
}

function nodeopinionzscq() {
    var tid = $("#tid").val();
    var departmentopinion = $("#departopinion").val();
    $.ajax({
        url: httptod + "zscq/deopinion",
        type: "post",
        data: JSON.stringify({
            tid: tid, judgestatus:'4',departmentopinion:departmentopinion
        }),
        contentType: "application/json",
        dataType: "json",
        // contentType: "application/json",
        success: function (data) {
            window.location.href = httptod + "zscq/godzscqpage";
        }
    })


}




//学校部分
function goszscqpage() {
    window.location.href = httptod + "zscq/goszscqpage";
}

function goszscqBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();
    var tcategory = $("#tcategory").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (tcategory == '') {
        tcategory = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    window.location.href = httptod + "zscq/goszscqpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus+"&number="+number+ "&tcategory="
    + tcategory +"&department="+department;
}

function goszscqnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var tcategory = $("#tcategory").val();
    var number = $("#number").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (tcategory == '') {
        tcategory = "-1";
    }
    window.location.href = httptod + "zscq/goszscqpage?pageNum=" + pageNum + "&tcategory=" + tcategory+ "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus + "&number=" + number;
}

function schoolopinionzscq() {
    var suretduty = $("input[name='suretduty']:checked").val();
    var sureapply = $("input[name='sureapply']:checked").val();
    var surename = $("#surename").val();
    var surechapter = $("#surechapter").val();
    var suredate = $("#sdate").val();
    var tid = getUrlParam("tid");

    var tcategory = $("input[name='tcategory']:checked").val();
    // var  tcategory = $()

    if (suretduty == "" || sureapply == "" || surename == "" || surechapter == "" || suredate == "") {
        alert("必须填写完整");
    } else if (sureapply == '否') {
        alert("不同意申请请选择下方退回按钮");
    } else {
        $.ajax({
            url: httptod + "zscq/scopinion",
            type: "post",
            data: JSON.stringify({
                tid: tid, judgestatus: "3", suretduty:suretduty,
                sureapply: sureapply, surename: surename, surechapter: surechapter,suredate:suredate,
                tcategory: tcategory
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "zscq/goszscqpage";
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function noschoolopinionzscq() {
    var tid = $("#tid").val();
    var schoolopinion = $("#schoolopinion").val();
    // if (schoolopinion==null || schoolopinion==)

    $.ajax({
        url: httptod + "zscq/scopinion",
        type: "post",
        data: JSON.stringify({
            tid: tid, judgestatus: "5", schoolopinion: schoolopinion
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            alert("退回成功")
            window.location.href = httptod + "zscq/goszscqpage";
        },
        error: function (res) {
            alert(res);
        }
    })
}


function schoolupdateDoc(tid) {
    window.location.href=httptod+"zscq/goschoolupdate?tid="+tid;
}

function schoolupdatesubmit() {
    var flag = confirm("当前在修改教师申请的知识产权，确认更改吗？");
    if (flag){
        var tid = getUrlParam("tid");
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
                url: httptod + "zscq/schoolupdate",
                type: "post",
                data: JSON.stringify({
                    "tid":tid,"tname": tname, "tcategory": tcategory, "tapply": tapply,
                    "tapplyname": tapplyname, "tproject": tproject, "tprojectid": tprojectid, "tprojectname": tprojectname,
                    "tsortname": tsortname, "tinventionname": tinventionname, "tunitname": tunitname, "tphone": tphone,
                    "temail": temail, "tintroduce": tintroduce, "tduty": tduty, "tinventionname2": tinventionname2,"judgestatus":"2"
                }),
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    var status = data.status;
                    if (status >= 1) {
                        window.location.href = httptod + "zscq/goszscqpage";
                    } else if (status == 0) {
                        alert(data.description);
                    }
                }
            })
        }
    } else{
        alert("已取消");
    }
}
