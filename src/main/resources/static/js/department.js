// var httptod = "http://localhost:8080/approval/"
// var httptod = "http://172.16.20.234:8080/approval/"

//会议论文相关
function godhylwpage() {
    window.location.href = httptod + "djump/godhylwpage";
}



function godhylwBydetail() {
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
    window.location.href = httptod + "djump/godhylwpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godhylwnext(pageNum) {
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
    window.location.href = httptod + "djump/godhylwpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passaudithylw() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "djump/passaudithylw",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godhylwBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoaudithylw() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "djump/passnoaudithylw",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功")
                }
                godhylwBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function downloaddhylwfjsc() {
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
            url: httptod + "tjump/downloadhylwfjsc",
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

function dhylwshow() {
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
        url: httptod + "tjump/hylwshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            //把数据渲染到 页面上
            $("#hylwshowmodal").modal('show');
            $("#TiMu2").val(res.timu);
            $("#DYZZ2").val(res.dyzz);
            $("#QTZZ2").val(res.qtzz);
            $("#Press2").val(res.press);
            $("#HYName2").val(res.hyname);
            $("#Wheres2").val(res.wheres);
            $("#Times2").val(res.times);
            $("#Rank2").val(res.rank);
            $("#Type2").val(res.types);
            $("#Points2").val(res.points);
            $("#Reward2").val(res.reward);
            $("#fq2").val(res.fq);

            $("#ZiShu2").val(res.zishu);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

//期刊论文相关
function godqklwpage() {
    window.location.href = httptod + "djump/godqklwpage";
}

function downloaddqklwfjsc() {
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
            url: httptod + "tjump/downloadqklwfjsc",
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

function dqklwshow() {
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
        url: httptod + "tjump/qklwshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            //把数据渲染到 页面上
            $("#qklwshowmodal").modal('show');
            $("#TiMu2").val(res.timu);
            $("#DYZZ2").val(res.dyzz);
            $("#QTZZ2").val(res.qtzz);
            $("#Type2").val(res.type);
            $("#QiKanName2").val(res.qikanname);
            $("#JQNumber2").val(res.jqnumber);
            $("#Page2").val(res.page);
            $("#Time2").val(res.time);
            // $("#Rank2").val(res.rank);
            $("#ZiShu2").val(res.zishu);
            $("#Reward2").val(res.reward);
            $("#Points2").val(res.points);
            $("#FQ2").val(res.fq);
            $("#remark2").val(res.remark);
            $("#YXYZ2").val(res.yxyz);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function godqklwBydetail() {
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
    window.location.href = httptod + "djump/godqklwpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godqklwnext(pageNum) {
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
    window.location.href = httptod + "djump/godqklwpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passauditqklw() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "djump/passauditqklw",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godqklwBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoauditqklw() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "djump/passnoauditqklw",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godqklwBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}



//学术著作相关
function godxszzpage() {
    window.location.href = httptod + "djump/godxszzpage";
}

function godxszzBydetail() {
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
    window.location.href = httptod + "djump/godxszzpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function dxszzshow() {
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
        url: httptod + "tjump/xszzshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            console.log(res)
            //把数据渲染到 页面上
            $("#xszzshowmodal").modal('show');
            $("#TiMu2").val(res.timu);
            $("#DYZZ2").val(res.dyzz);
            $("#QTZZ2").val(res.qtzz);
            $("#ISBN2").val(res.isbn);
            $("#PressType2").val(res.presstype);
            $("#zzlx2").val(res.zzlx);
            $("#Press2").val(res.press);
            $("#CIP2").val(res.cip);
            $("#Time2").val(res.time);
            $("#ZiShu2").val(res.zishu);
            $("#Points2").val(res.points);
            $("#Reward2").val(res.reward);
            $("#fq2").val(res.fq);
            $("#WorkType2").val(res.worktype);
            // $("#FJSC2").val(res.fjsc);
        },
        error: function (res) {
            console.log(res);
        }
    })

}

function godxszznext(pageNum) {
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
    window.location.href = httptod + "djump/godxszzpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function downloaddxszzfjsc() {
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
            url: httptod + "tjump/downloadxszzfjsc",
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

function passauditxszz() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "djump/passauditxszz",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godxszzBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoauditxszz() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "djump/passnoauditxszz",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godxszzBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}


//横向项目相关
function godhxxmpage() {
    window.location.href = httptod + "djump/godhxxmpage";
}

function dhxxmshow() {
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
        url: httptod + "tjump/hxxmshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            console.log(res)
            //把数据渲染到 页面上
            $("#hxxmshowmodal").modal('show');


            $("#XMName2").val(res.xmname);
            $("#Principal2").val(res.principal);
            $("#XmStatus2").val(res.xmstatus);
            $("#ZJE2").val(res.zje);
            $("#YBK2").val(res.ybk);
            $("#ContractNumber2").val(res.contractnumber);
            $("#AUnit2").val(res.aunit);
            $("#QDTime2").val(res.qdtime);
            $("#STime2").val(res.stime);
            $("#JZTime2").val(res.jztime);
            $("#JXTime2").val(res.jxtime);
            $("#JXJY2").val(res.jxjy);
            $("#JFStatus2").val(res.jfstatus);
            $("#Points2").val(res.points);
            $("#Reward2").val(res.reward);
            $("#Remark2").val(res.remark);
            // $("#FJSC2").val(res.fjsc);
        },
        error: function (res) {
            console.log(res);
        }
    })

}

function godhxxmBydetail() {
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
    window.location.href = httptod + "djump/godhxxmpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godhxxmnext(pageNum) {
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
    window.location.href = httptod + "djump/godhxxmpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passaudithxxm() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "djump/passaudithxxm",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godhxxmBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoaudithxxm() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "djump/passnoaudithxxm",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godhxxmBydetail();

            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function downloaddhxxmfjsc() {
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
            url: httptod + "tjump/downloadhxxmfjsc",
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


//横向立项
function godhxlxpage() {
    window.location.href = httptod + "ddddjump/godhxlxpage";
}

function dhxlxshow() {
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
        url: httptod + "ttttjump/hxlxshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            console.log(res)
            //把数据渲染到 页面上
            $("#hxlxshowmodal").modal('show');


            $("#XMName2").val(res.xmname);
            $("#Principal2").val(res.principal);
            $("#XmStatus2").val(res.xmstatus);
            $("#ZJE2").val(res.zje);
            $("#YBK2").val(res.ybk);
            $("#ContractNumber2").val(res.contractnumber);
            $("#AUnit2").val(res.aunit);
            $("#QDTime2").val(res.qdtime);
            $("#STime2").val(res.stime);
            $("#JZTime2").val(res.jztime);
            $("#Points2").val(res.points);
            $("#Reward2").val(res.reward);
            $("#Remark2").val(res.remark);
            // $("#FJSC2").val(res.fjsc);
        },
        error: function (res) {
            console.log(res);
        }
    })

}

function godhxlxBydetail() {
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
    window.location.href = httptod + "ddddjump/godhxlxpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godhxlxnext(pageNum) {
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
    window.location.href = httptod + "ddddjump/godhxlxpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passaudithxlx() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddddjump/passaudithxlx",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godhxlxBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoaudithxlx() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddddjump/passnoaudithxlx",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godhxlxBydetail();

            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function downloaddhxlxfjsc() {
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
            url: httptod + "ttttjump/downloadhxlxfjsc",
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
//软件著作相关
function godrjzzpage() {
    window.location.href = httptod + "ddjump/godrjzzpage";
}

function drjzzshow() {
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
        url: httptod + "ttjump/rjzzshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            //把数据渲染到 页面上
            $("#rjzzshowmodal").modal('show');
            $("#xmName2").val(res.xmname);
            $("#DYZZ2").val(res.dyzz);
            $("#QTZZ2").val(res.qtzz);
            $("#DJNumber2").val(res.djnumber);
            $("#SYDW2").val(res.sydw);
            $("#FinishTime2").val(res.finishtime);
            $("#DJTime2").val(res.djtime);

            $("#BQType2").val(res.bqtype);
            $("#SYFS2").val(res.syfs);
            $("#fq2").val(res.fq);
            $("#Points2").val(res.points);
            // $("#FJSC2").val(res.fjsc);
        },
        error: function (res) {
            console.log(res);
        }
    })

}

function godrjzzBydetail() {
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
    window.location.href = httptod + "ddjump/godrjzzpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godrjzznext(pageNum) {
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
    window.location.href = httptod + "ddjump/godrjzzpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passauditrjzz() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddjump/passauditrjzz",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godrjzzBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoauditrjzz() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddjump/passnoauditrjzz",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godrjzzBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}
function downloaddrjzzfjsc() {
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
            url: httptod + "ttjump/downloadrjzzfjsc",
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
//获批专利相关
function godhpzlpage() {
    window.location.href = httptod + "ddjump/godhpzlpage";
}
function dhpzlshow() {
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
        url: httptod + "ttjump/hpzlshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            //把数据渲染到 页面上
            $("#hpzlshowmodal").modal('show');
            $("#xmName2").val(res.xmname);
            $("#ZLQR2").val(res.zlqr);
            $("#FMR2").val(res.fmr);
            $("#QTFMR2").val(res.qtfmr);
            $("#BFBM2").val(res.bfbm);
            $("#ZLNumber2").val(res.zlnumber);
            $("#SQTime2").val(res.sqtime);
            $("#SYTime2").val(res.sytime);
            $("#Points2").val(res.points);
            $("#Reward2").val(res.reward);
            $("#fq2").val(res.fq);
            // $("#FJSC2").val(res.fjsc);
            $("#PType2").val(res.ptype);
            $("#Pstatus2").val(res.pstatus);
        },
        error: function (res) {
            console.log(res);
        }
    })

}
function godhpzlBydetail() {
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
    window.location.href = httptod + "ddjump/godhpzlpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godhpzlnext(pageNum) {
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
    window.location.href = httptod + "ddjump/godhpzlpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passaudithpzl() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddjump/passaudithpzl",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godhpzlBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoaudithpzl() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddjump/passnoaudithpzl",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godhpzlBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}
function downloaddhpzlfjsc() {
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
            url: httptod + "ttjump/downloadhpzlfjsc",
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
//学术称号相关
function godxschpage() {
    window.location.href = httptod + "ddjump/godxschpage";
}

function godxschBydetail() {
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
    window.location.href = httptod + "ddjump/godxschpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function dxschshow() {
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
        url: httptod + "ttjump/xschshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            //把数据渲染到 页面上
            $("#xschshowmodal").modal('show');
            $("#xmName2").val(res.xmname);
            $("#Title2").val(res.title);
            $("#Rank2").val(res.rank);
            $("#ZSNumber2").val(res.zsnumber);
            $("#Time2").val(res.time);
            $("#XFBM2").val(res.xfbm);
            $("#fq2").val(res.fq);
            // $("#FJSC").val(res.fjsc);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function godxschnext(pageNum) {
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
    window.location.href = httptod + "ddjump/godxschpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passauditxsch() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddjump/passauditxsch",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godxschBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoauditxsch() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddjump/passnoauditxsch",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godxschBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}
function downloaddxschfjsc() {
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
            url: httptod + "ttjump/downloadxschfjsc",
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
//学术交流相关
function godxsjlpage() {
    window.location.href = httptod + "ddjump/godxsjlpage";
}
function dxsjlshow() {
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
        url: httptod + "ttjump/xsjlshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            //把数据渲染到 页面上
            $("#xsjlshowmodal").modal('show');
            $("#xmName2").val(res.xmname);
            $("#CYRY2").val(res.cyry);
            $("#Wheres2").val(res.wheres);
            $("#Time2").val(res.time);
            $("#ZBDW2").val(res.zbdw);
            $("#Rank2").val(res.rank);
            $("#fayan2").val(res.fayan);
            $("#fq2").val(res.fq);
            $("#LunWen2").val(res.lunwen);

        },
        error: function (res) {
            console.log(res);
        }
    })

}

function godxsjlBydetail() {
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
    window.location.href = httptod + "ddjump/godxsjlpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godxsjlnext(pageNum) {
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
    window.location.href = httptod + "ddjump/godxsjlpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passauditxsjl() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddjump/passauditxsjl",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godxsjlBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoauditxsjl() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddjump/passnoauditxsjl",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godxsjlBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}
function downloaddxsjlfjsc() {
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
            url: httptod + "ttjump/downloadxsjlfjsc",
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
function testssssss() {
    alert("发现彩蛋了qaq")
}


//获批奖励
function godhpjlpage() {
    window.location.href = httptod + "dddjump/godhpjlpage";
}


function godhpjlBydetail() {
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
    window.location.href = httptod + "dddjump/godhpjlpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godhpjlnext(pageNum) {
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
    window.location.href = httptod + "dddjump/godhpjlpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passaudithpjl() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "dddjump/passaudithpjl",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godhpjlBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoaudithpjl() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "dddjump/passnoaudithpjl",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godhpjlBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function dhpjlshow() {
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
        url: httptod + "tttjump/hpjlshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            //把数据渲染到 页面上
            $("#hpjlshowmodal").modal('show');
            $("#CGName2").val(res.cgname);
            $("#JLName2").val(res.jlname);
            $("#DYHJR2").val(res.dyhjr);
            $("#QTHJR2").val(res.qthjr);
            $("#JIXDBM2").val(res.jixdbm);
            $("#ZSNumber2").val(res.zsnumber);
            $("#HJTime2").val(res.hjtime);
            $("#JLRank2").val(res.jlrank);
            $("#HJRank2").val(res.hjrank);
            $("#Remark2").val(res.remark);
            $("#Points2").val(res.points);
            $("#KYJL2").val(res.kyjl);
        },
        error: function (res) {
            console.log(res);
        }
    })

}

function downloaddhpjlfjsc() {
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
            url: httptod + "tttjump/downloadhpjlfjsc",
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
//音美获奖
function godymhjpage() {
    window.location.href = httptod + "dddjump/godymhjpage";
}



function godymhjBydetail() {
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
    window.location.href = httptod + "dddjump/godymhjpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godymhjnext(pageNum) {
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
    window.location.href = httptod + "dddjump/godymhjpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passauditymhj() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "dddjump/passauditymhj",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godymhjBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoauditymhj() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "dddjump/passnoauditymhj",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godymhjBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}
function dymhjshow() {
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
        url: httptod + "tttjump/ymhjshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            //把数据渲染到 页面上
            $("#ymhjshowmodal").modal('show');
            $("#TiMu2").val(res.timu);
            $("#AwardName2").val(res.awardname);
            $("#DYZZ2").val(res.dyzz);
            $("#QTZZ2").val(res.qtzz);
            $("#Organizer2").val(res.organizer);
            $("#AwardTime2").val(res.awardtime);
            $("#RewardSort2").val(res.rewardsort);
            $("#Reward2").val(res.reward);
            $("#Points2").val(res.points);
            $("#Remark2").val(res.remark);
        },
        error: function (res) {
            console.log(res);
        }
    })

}

function downloaddymhjfjsc() {
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
            url: httptod + "tttjump/downloadymhjfjsc",
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
//体育获奖
function godtyhjpage() {
    window.location.href = httptod + "dddjump/godtyhjpage";
}



function godtyhjBydetail() {
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
    window.location.href = httptod + "dddjump/godtyhjpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godtyhjnext(pageNum) {
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
    window.location.href = httptod + "dddjump/godtyhjpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passaudittyhj() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "dddjump/passaudittyhj",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godtyhjBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoaudittyhj() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "dddjump/passnoaudittyhj",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godtyhjBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function dtyhjshow() {
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
        url: httptod + "tttjump/tyhjshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            //把数据渲染到 页面上
            $("#tyhjshowmodal").modal('show');
            $("#Awardee2").val(res.awardee);
            $("#Entryname2").val(res.entryname);
            $("#WinTime2").val(res.wintime);
            $("#CertificateID2").val(res.certificateid);
            $("#SportsLV2").val(res.sportslv);
            $("#Ranking2").val(res.ranking);
            $("#BJBM2").val(res.bjbm);
            $("#Points2").val(res.points);
            $("#fq2").val(res.fq);
            $("#Reward2").val(res.reward);
        },
        error: function (res) {
            console.log(res);
        }
    })

}
function downloaddtyhjfjsc() {
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
            url: httptod + "tttjump/downloadtyhjfjsc",
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
//成果获奖
function godcgpage() {
    window.location.href = httptod + "ddddjump/godcgpage";
}



function godcgBydetail() {
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
    window.location.href = httptod + "ddddjump/godcgpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godcgnext(pageNum) {
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
    window.location.href = httptod + "ddddjump/godcgpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passauditcg() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddddjump/passauditcg",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godcgBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoauditcg() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddddjump/passnoauditcg",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godcgBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function dcgshow() {
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
        url: httptod + "ttttjump/cgshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            //把数据渲染到 页面上
            $("#cgshowmodal").modal('show');
            $("#CGName2").val(res.cgname);
            $("#CGType2").val(res.cgtype);
            $("#DYZZ2").val(res.dyzz);
            $("#QTZZ2").val(res.qtzz);
            $("#CNBM2").val(res.cnbm);
            $("#CGDZ2").val(res.cgdz);
            $("#Time2").val(res.time);
            $("#fq2").val(res.fq);

            $("#Points2").val(res.points);
            $("#Reward2").val(res.reward);
        },
        error: function (res) {
            console.log(res);
        }
    })
}
function downloaddcgfjsc() {
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
            url: httptod + "ttttjump/downloadcgfjsc",
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


//纵向立项
function godhpxmpage() {
    window.location.href = httptod + "ddddjump/godhpxmpage";
}



function godhpxmBydetail() {
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
    window.location.href = httptod + "ddddjump/godhpxmpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godhpxmnext(pageNum) {
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
    window.location.href = httptod + "ddddjump/godhpxmpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passaudithpxm() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddddjump/passaudithpxm",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godhpxmBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoaudithpxm() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddddjump/passnoaudithpxm",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godhpxmBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function dhpxmshow() {
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
        url: httptod + "ttttjump/hpxmshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            //把数据渲染到 页面上
            $("#hpxmshowmodal").modal('show');
            $("#xmname2").val(res.xmname);
            $("#pronumber2").val(res.pronumber);
            $("#wbjf2").val(res.wbjf);
            $("#ptjf2").val(res.ptjf);
            $("#xdbm2").val(res.xdbm);
            $("#xmsource2").val(res.xmsource);
            $("#prorank2").val(res.prorank);
            $("#proproperty2").val(res.proproperty);
            $("#hoster2").val(res.hoster);
            $("#reward2").val(res.reward);
            $("#points2").val(res.points);
            $("#cyry2").val(res.cyry);
            $("#remark2").val(res.remark);
            $("#lxtime2").val(res.lxtime);
        },
        error: function (res) {
            console.log(res);
        }
    })
}
function downloaddhpxmfjsc() {
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
            url: httptod + "ttttjump/downloadhpxmfjsc",
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

//纵向结项
function godjxyspage() {
    window.location.href = httptod + "ddddjump/godjxyspage";
}



function godjxysBydetail() {
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
    window.location.href = httptod + "ddddjump/godjxyspage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godjxysnext(pageNum) {
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
    window.location.href = httptod + "ddddjump/godjxyspage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passauditjxys() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddddjump/passauditjxys",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godjxysBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoauditjxys() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddddjump/passnoauditjxys",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godjxysBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function djxysshow() {
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
        url: httptod + "ttttjump/jxysshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            //把数据渲染到 页面上
            $("#jxysshowmodal").modal('show');
            $("#xmname2").val(res.xmname);
            $("#pronumber2").val(res.pronumber);
            $("#xmly2").val(res.xmly);
            $("#rank2").val(res.rank);
            $("#hoster2").val(res.hoster);
            $("#xdbm2").val(res.xdbm);
            $("#cyry2").val(res.cyry);
            $("#reward2").val(res.reward);
            $("#time2").val(res.time);
            $("#proproperty2").val(res.proproperty);
            $("#points2").val(res.points);
            $("#remark2").val(res.remark);
        },
        error: function (res) {
            console.log(res);
        }
    })
}
function downloaddjxysfjsc() {
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
            url: httptod + "ttttjump/downloadjxysfjsc",
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

//智库建设
function godzkjspage() {
    window.location.href = httptod + "ddddjump/godzkjspage";
}



function godzkjsBydetail() {
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
    window.location.href = httptod + "ddddjump/godzkjspage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godzkjsnext(pageNum) {
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
    window.location.href = httptod + "ddddjump/godzkjspage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passauditzkjs() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddddjump/passauditzkjs",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godzkjsBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoauditzkjs() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddddjump/passnoauditzkjs",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godzkjsBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function dzkjsshow() {
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
        url: httptod + "ttttjump/zkjsshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            //把数据渲染到 页面上
            $("#zkjsshowmodal").modal('show');
            $("#cgname2").val(res.cgname);
            $("#cnjb2").val(res.cnjb);
            $("#dyzz2").val(res.dyzz);
            $("#qtzz2").val(res.qtzz);
            $("#cnbm2").val(res.cnbm);
            $("#cntime2").val(res.cntime);
            $("#reward2").val(res.reward);
            $("#fq2").val(res.fq);
            $("#points2").val(res.points);
        },
        error: function (res) {
            console.log(res);
        }
    })
}
function downloaddzkjsfjsc() {
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
            url: httptod + "ttttjump/downloadzkjsfjsc",
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

//成果转化
function godcgzhpage() {
    window.location.href = httptod + "ddddjump/godcgzhpage";
}



function godcgzhBydetail() {
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
    window.location.href = httptod + "ddddjump/godcgzhpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;
}

function godcgzhnext(pageNum) {
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
    window.location.href = httptod + "ddddjump/godcgzhpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus+"&number="+number;

}

function passauditcgzh() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
    });
    console.log(list,otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddddjump/passauditcgzh",
            type: "post",
            data: JSON.stringify({ids:list,judgestatus:"2"}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godcgzhBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function passnoauditcgzh() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row=$(this).parents("tr");
        var judgestatus=row.find("[name='judgestatus']").html();//注意html()和val()
        if(judgestatus==1){
            list.push($(this).val());
        }else{
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length==0) {
        alert("未选中或选择错误");
        return false;
    }
    var audit = prompt("请输入不通过意见");
    if (audit == null ) {
        console.log("取消或者未取消")
        return;
    }
    if (audit==''){
        alert("退回意见不能为空");
        return;
    }
    if (list.length == 0){
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    }else{
        $.ajax({
            url: httptod + "ddddjump/passnoauditcgzh",
            type: "post",
            data: JSON.stringify({ids: list,judgestatus: "4",departmentopinion:audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length==0){
                    alert(res.description)
                } else{
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                godcgzhBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function dcgzhshow() {
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
        url: httptod + "ttttjump/cgzhshow",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            //把数据渲染到 页面上
            $("#cgzhshowmodal").modal('show');
            $("#cgname2").val(res.cgname);
            $("#zrlx2").val(res.zrlx);
            $("#zrdzjf2").val(res.zrdzjf);
            $("#dyzz2").val(res.dyzz);
            $("#qtzz2").val(res.qtzz);
            $("#zhdw2").val(res.zhdw);
            $("#zhtime2").val(res.zhtime);
            $("#reward2").val(res.reward);
            $("#fq2").val(res.fq);
            $("#points2").val(res.points);
        },
        error: function (res) {
            console.log(res);
        }
    })
}
function downloaddcgzhfjsc() {
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
            url: httptod + "ttttjump/downloadcgzhfjsc",
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