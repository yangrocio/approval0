//团队管理
function goteam() {
    window.location.href = httptod + "teamJump/goteampage";
}

function putTeam() {
    var department = $("#Dept").val();
    var no = $("#No").val();
    var name = $("#TeamName").val();
    var type = $("#Genre").val();
    var source = $("#Source").val();
    var principal = $("#Manager").val();
    var level = $("#Gradation").combotree('getText');
    var point = $("#Point").val();
    var scientific = $("#Scientific").val();
    var subsidize = $("#Subsidize").val();
    var approved_time = $("#receiveTime").val();
    var approve_department = $("#receiveApproval").val();
    var annex = $("#annex")[0].files[0];
    // if (annex == null || annex == "" ||department == null || department == "" ||
    //     no == null || no == "" || name == null || name == "" || subsidize == ""||
    //     subsidize == null|| type == null || type == "" || source == "" || source == null ||
    //     level == null|| level == "" || principal == null || principal == "" ||
    //     approve_department == null || approve_department == "" || approved_time == "" ||
    //     approved_time == null) {
    //         alert("请填写完整且文件必须上传！");
    //     }else {
                var formdata = new FormData();
                formdata.append("annex", annex);
                var filetype = getFileType(annex.name);
                console.log(filetype);
                if (filetype != "pdf") {
                    alert("文件格式错误");
                    return;
                }

        if (annex.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadTrueAnnex/" + "tdgl",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                console.log("文件地址是：", data)
                $.ajax({
                    url: httptod + "kytd/tdgl/post",
                    type: "post",
                    data: JSON.stringify({
                        department: department, no: no, name: name, type: type, source: source,scientific: scientific, point: point,
                        principal: principal, level: level, subsidize:subsidize, approve_department: approve_department,
                        approved_time: approved_time, annex: data
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
            },
            error: function (res) {
                console.log("error", res);
            }
        })
}

function teamdelete() {
    let list = getSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "teamJump/deleteteam",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "teamJump/goteampage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}



// function teamshow() {
//     var list = new Array();
//     $.each($('input[name="selected"]:checkbox:checked'), function () {
//         list.push($(this).val());
//     });
//     //查看是只取第一个
//     if (list.length > 1) {
//         alert("选中较多，只显示第一个");
//     }
//     if (list.length == 0) {
//         alert("未选中或选择错误");
//         return false;
//     }
//     $.ajax({
//         url: httptod + "kytd/tdgl/teamshow",
//         type: "post",
//         data: {id: list[0]},
//         dataType: "json",
//         success: function (res) {
//             //把数据渲染到 页面上
//             $("#teamshowmodal").modal('show');
//             $("#Dept2").val(res.department);
//             $("#No2").val(res.no);
//             $("#TeamName2").val(res.name);
//             $("#Genre2").val(res.type);
//             $("#Source2").val(res.source);
//             $("#Rank2").val(res.rank);
//             $("#Gradation2").val(res.level);
//             $("#Subsidize2").val(res.subsidize);
//             $("#receiveApproval2").val(res.approve_department);
//             $("#Manager2").val(res.principal);
//             $("#receiveTime2").val(res.approved_time);
//         },
//         error: function (res) {
//             console.log(res);
//         }
//     })
// }





//会议论文相关
function gohylwpage() {
    window.location.href = httptod + "tjump/gohylwpage";
}

function gohylwBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "tjump/gohylwpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function gohylwwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "tjump/gohylwwrite";
    }

}

function gohylwnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "tjump/gohylwpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function hylwdelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "tjump/deletehylw",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "tjump/gohylwpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var otherlist = new Array();
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         if (judgestatus == '4' || judgestatus == '5' || judgestatus == '100' ) {
    //             list.push($(this).val());
    //         } else {
    //             otherlist.push($(this).val());
    //         }
    //     });
    //     console.log("可以删除的", list, "不可以删除的", otherlist);
    //     if (list.length == 0 && otherlist.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         return false;
    //     }
    //     if (list.length == 0) {
    //         alert("选择有问题，请勿选择属于不可以删除的内容");
    //     } else {
    //         console.log("在这里完成删除操作");
    //         $.ajax({
    //             url: httptod + "tjump/deletehylw",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "tjump/gohylwpage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         })
    //     }
    // }
}

function downloadhylwfjsc() {
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

function hylwexportall() {
    window.location.href = httptod + "tjump/hylwexportall";
}

//一个 编辑  一个查看 都放在 modal 只能选中一个进行操作  如果多选只显示一个 先做显示
function hylwshow() {
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
            $("#ShouLu2").val(res.shoulu);
            $("#ZiShu2").val(res.zishu);
        },
        error: function (res) {
            console.log(res);
        }
    })

}

function hylwupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "tjump/hylwshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                //把数据渲染到 页面上
                $("#hylwupdatemodal").modal('show');
                $("#hid").val(res.hid);
                $("#TiMu").val(res.timu);
                $("#DYZZ").val(res.dyzz);
                $("#QTZZ").val(res.qtzz);
                $("#Press").val(res.press);
                $("#HYName").val(res.hyname);
                $("#Wheres").val(res.wheres);
                $("#Times").val(res.times);
                $("#Rank").val(res.rank);
                $('#Type').combotree('setValue', res.types);
                // $("#Type").val(res.types);
                $("#Points").val(res.points);
                $("#Reward").val(res.reward);
                $("#ShouLu").val(res.shoulu);
                $("#ZiShu").val(res.zishu);
                $("#fq").val(res.fq);
                $("#FJSCdisabled").val(res.fjsc);
                $("#judgestatusdisabled").val(res.judgestatus);

            },
            error: function (res) {
                console.log(res);
            }
        })
    }

}

function hylwupdatesubmit() {
    var hid = $("#hid").val();
    var timu = $("#TiMu").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var press = $("#Press").val();
    var hyname = $("#HYName").val();
    var wheres = $("#Wheres").val();
    var times = $("#Times").val();
    var rank = $("#Rank").val();
    var types = $("#Type").combotree('getText');
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var fq = $("#fq").val();
    // var shoulu = $("#ShouLu").val();
    var zishu = $("#ZiShu").val();

    var judgestatus = $("#judgestatusdisabled").val();
    var fjsc = $("#FJSC")[0].files[0];  //可能为 空  考虑为空的情况下的处理


    if (timu == null || timu == "" || dyzz == null || dyzz == "" || qtzz == null || qtzz == "" || press == null || press == "" || hyname == null || hyname == "" ||
        wheres == "" || wheres == null || times == null || times == "" || types == "请选择" || types == "" || rank == null || rank == ""
        ||  points == null || points == "" || zishu == null || zishu == "") {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "tjump/hylwupdate",
            type: "post",
            data: JSON.stringify({
                hid: hid, timu: timu, dyzz: dyzz, qtzz: qtzz, press: press,
                hyname: hyname, wheres: wheres, times: times, rank: rank,
                types: types, points: points, reward: reward, fq: fq,
                zishu: zishu, judgestatus: "1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "tjump/gohylwpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "tjump/hylwupdate",
                    type: "post",
                    data: JSON.stringify({
                        hid: hid, timu: timu, dyzz: dyzz, qtzz: qtzz, press: press,
                        hyname: hyname, wheres: wheres, times: times, rank: rank,
                        types: types, points: points, reward: reward, fq: fq,
                        zishu: zishu, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "tjump/gohylwpage";
                    }
                })
            }
        })


        //先 添加一下文件 然后传递到数据库

    }
}

function hylwupdatesubmittemp() {
    var timu = $("#TiMu").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var press = $("#Press").val();
    var hyname = $("#HYName").val();
    var wheres = $("#Wheres").val();
    var times = $("#Times").val();
    var rank = $("#Rank").val();
    var types = $("#Type").combotree('getText');
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var fq = $("#fq").val();
    // var shoulu = $("#ShouLu").val();
    var zishu = $("#ZiShu").val();
    if (times == null || times == '') {
        alert("请务必完成时间内容的填写");
    }

    // JSON.parse()
    // JSON.stringify()
    $.ajax({
        url: httptod + "tjump/puthylw",
        type: "post",

        data: JSON.stringify({
            timu: timu, dyzz: dyzz, qtzz: qtzz, press: press,
            hyname: hyname, wheres: wheres, times: times, rank: rank,
            types: types, points: points, reward: reward, fq: fq,
            zishu: zishu, judgestatus: "100"
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            console.log(res)
            alert(res.description + "\n友情提示，暂存状态文件不上传，需正式提交院系审核才能上传文件");
            window.location.href = httptod + "tjump/gohylwpage";
        },
        error: function (res) {
            console.log(res);
        }
    })
}


function puthylw() {
    var timu = $("#TiMu").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var press = $("#Press").val();
    var hyname = $("#HYName").val();
    var wheres = $("#Wheres").val();
    var times = $("#Times").val();
    var rank = $("#Rank").val();
    var types = $("#Type").combotree('getText');
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var fq = $("#fq").val();
    // var shoulu = $("#ShouLu").val();
    var zishu = $("#ZiShu").val();


    var fjsc = $("#FJSC")[0].files[0];

    //逻辑有 问题   总是提示  内容 不够完整  解决 在write 界面 少一个times
    if (timu == null || timu == "" || dyzz == null || dyzz == ""  || press == null || press == "" || hyname == null || hyname == "" ||
        wheres == "" || wheres == null || times == null || times == "" || types == null || types == "" || rank == null || rank == ""
        || points == null || points == "" || zishu == null || zishu == ""
        || fjsc == null || fjsc == '') {
        alert("请填写完整且文件必须上传！");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "tjump/puthylw",
                    type: "post",
                    data: JSON.stringify({
                        timu: timu, dyzz: dyzz,  press: press,qtzz:qtzz,
                        hyname: hyname, wheres: wheres, times: times, rank: rank,
                        types: types, points: points, reward: reward, fq: fq,
                        zishu: zishu, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "tjump/gohylwpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
            error: function (res) {
                console.log("erroe", res);
            }
        })
    }
}


//期刊论文相关
function goqklwpage() {
    window.location.href = httptod + "tjump/goqklwpage";
}

function goqklwBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "tjump/goqklwpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function goqklwwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "tjump/goqklwwrite";
    }
}


function goqklwnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "tjump/goqklwpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function qklwdelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "tjump/deleteqklw",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "tjump/goqklwpage";
            },
            error: function (res) {
                console.log(res);
            }
        });
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var otherlist = new Array();
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         if (judgestatus == '4' || judgestatus == '5' || judgestatus == '100') {
    //             list.push($(this).val());
    //         } else {
    //             otherlist.push($(this).val());
    //         }
    //     });
    //     if (list.length == 0 && otherlist.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         return false;
    //     }
    //     if (list.length == 0) {
    //         alert("选择有问题，请勿选择属于不可以删除的内容");
    //     } else {
    //         $.ajax({
    //             url: httptod + "tjump/deleteqklw",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "tjump/goqklwpage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         });
    //     }
    // }
}


function qklwshow() {
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
            console.log(res)
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

function qklwupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "tjump/qklwshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                //把数据渲染到 页面上
                $("#qklwupdatemodal").modal('show');
                $("#qid").val(res.qid);
                $("#TiMu").val(res.timu);
                $("#DYZZ").val(res.dyzz);
                $("#QTZZ").val(res.qtzz);
                $('#Type').combotree('setValue', res.type);
                $('#QiKanName').combotree('setValue', res.qikanname);
                // $('#Rank').combotree('setValue', res.rank);
                // $("#QiKanName").val(res.qikanname);
                $("#JQNumber").val(res.jqnumber);
                $("#Page").val(res.page);
                $("#Time").val(res.time);
                $("#ZiShu").val(res.zishu);
                $("#Reward").val(res.reward);
                $("#Points").val(res.points);
                $("#FQ").val(res.fq);
                $("#remark").val(res.remark);
                $("#YXYZ").val(res.yxyz);
                $("#FJSCdisabled").val(res.fjsc);
                $("#judgestatusdisabled").val(res.judgestatus);
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function qklwupdatesubmit() {
    var qid = $("#qid").val();
    var timu = $("#TiMu").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var type = $("#Type").combotree('getText');
    var qikanname = $("#QiKanName").combotree('getText');
    // var rank = $("#Rank").combotree('getText');
    // var qikanname = $("#QiKanName").val();
    var jqnumber = $("#JQNumber").val();
    var page = $("#Page").val();
    var time = $("#Time").val();
    // var rank = $("#Rank").val();
    var zishu = $("#ZiShu").val();
    var reward = $("#Reward").val();
    var points = $("#Points").val();
    var fq = $("#FQ").val();
    var remark = $("#remark").val();
    var yxyz = $("#YXYZ").val();
    var judgestatus = $("#judgestatusdisabled").val();

    var fjsc = $("#FJSC")[0].files[0];  //可能为 空  考虑为空的情况下的处理

    if (qid == null || qid == "" || timu == null || timu == "" || dyzz == null || dyzz == "" || type == null || type == "" || qikanname == null ||
        qikanname == "" || jqnumber == "" || jqnumber == null || time == null || time == "" || zishu == null || zishu == "" || points == null || points == "" || fq == null || fq == "") {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "tjump/qklwupdate",
            type: "post",
            data: JSON.stringify({
                qid: qid, timu: timu, dyzz: dyzz, qtzz: qtzz, type: type, qikanname: qikanname, jqnumber: jqnumber,
                page: page, time: time, zishu: zishu, reward: reward, points: points, fq: fq, remark: remark,
                yxyz: yxyz, judgestatus: "1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "tjump/goqklwpage";
            }
        })
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            async: false,
            success: function (data) {
                $.ajax({
                    url: httptod + "tjump/qklwupdate",
                    type: "post",
                    data: JSON.stringify({
                        qid: qid,
                        timu: timu,
                        dyzz: dyzz,
                        qtzz: qtzz,
                        type: type,
                        qikanname: qikanname,
                        jqnumber: jqnumber,
                        page: page,
                        time: time,
                        zishu: zishu,
                        reward: reward,
                        points: points,
                        fq: fq,
                        remark: remark,
                        yxyz: yxyz,
                        judgestatus: "1",
                        fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "tjump/goqklwpage";
                    },
                })
            }
        })
    }
}

function qklwupdatesubmittemp() {
    var qid = $("#qid").val();
    var timu = $("#TiMu").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var type = $("#Type").combotree('getText');
    var qikanname = $("#QiKanName").val();
    var jqnumber = $("#JQNumber").val();
    var page = $("#Page").val();
    var time = $("#Time").val();
    // var rank = $("#Rank").val();
    var zishu = $("#ZiShu").val();
    var reward = $("#Reward").val();
    var points = $("#Points").val();
    var fq = $("#FQ").val();
    var yxyz = $("#YXYZ").val();
    $.ajax({
        url: httptod + "tjump/qklwupdate",
        type: "post",
        data: JSON.stringify({
            qid: qid, timu: timu, dyzz: dyzz, qtzz: qtzz, type: type, qikanname: qikanname, jqnumber: jqnumber,
            page: page, time: time, zishu: zishu, reward: reward, points: points, fq: fq,
            judgestatus: "100"
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            alert(res.description);
            window.location.href = httptod + "tjump/goqklwpage";
        },
        error: function (res) {
            console.log(res);
        }
    })
}


function putqklw() {
    var timu = $("#TiMu").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var type = $('#Type').combotree('getText');
    var qikanname = $('#QiKanName').combotree('getText');
    // var rank = $('#Rank').combotree('getText');
    // var qikanname = $("#QiKanName").val();
    var jqnumber = $("#JQNumber").val();
    var page = $("#Page").val();
    var time = $("#Time").val();
    // var rank = $("#Rank").val();
    var zishu = $("#ZiShu").val();
    var reward = $("#Reward").val();
    var points = $("#Points").val();
    var fq = $("#FQ").val();
    var remark = $("#remark").val();
    var yxyz = $("#YXYZ").val();

    var fjsc = $("#FJSC")[0].files[0];

    if (timu == null || timu == "" || dyzz == null || dyzz == "" || type == null || type == "" || qikanname == null ||
        qikanname == "" || jqnumber == "" || jqnumber == null || time == null || time == "" ||
        zishu == null || zishu == "" || points == null || points == "" || fq == null || fq == "" || fjsc == null || fjsc == '') {
        alert("请填写完整且文件必须上传！");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "tjump/putqklw",
                    type: "post",
                    data: JSON.stringify({
                        timu: timu, dyzz: dyzz, qtzz: qtzz, type: type, qikanname: qikanname, jqnumber: jqnumber,
                        page: page, time: time, zishu: zishu, reward: reward, points: points, fq: fq, remark: remark,
                        yxyz: yxyz, fjsc: data, judgestatus: "1"
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "tjump/goqklwpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
            error: function (res) {
                console.log("erroe", res);
            }
        })


    }

}

function qklwexportall() {
    window.location.href = httptod + "tjump/qklwexportall";
}


function downloadqklwfjsc() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus != '100') {
            list.push($(this).val());
        }
    });
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

//学术著作相关
function goxszzpage() {
    window.location.href = httptod + "tjump/goxszzpage";
}

function goxszzBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "tjump/goxszzpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function goxszzwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "tjump/goxszzwrite";
    }
}

function goxszznext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "tjump/goxszzpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function xszzdelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "tjump/deletexszz",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "tjump/goxszzpage";
            },
            error: function (res) {
                console.log(res);
            }
        });
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var otherlist = new Array();
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         if (judgestatus == '4' || judgestatus == '5' || judgestatus == '100') {
    //             list.push($(this).val());
    //         } else {
    //             otherlist.push($(this).val());
    //         }
    //     });
    //     if (list.length == 0 && otherlist.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         return false;
    //     }
    //     if (list.length == 0) {
    //         alert("选择有问题，请勿选择属于不可以删除的内容");
    //     } else {
    //         $.ajax({
    //             url: httptod + "tjump/deletexszz",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "tjump/goxszzpage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         });
    //     }
    // }
}

function downloadxszzfjsc() {
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

function xszzexportall() {
    window.location.href = httptod + "tjump/xszzexportall";
}

function xszzshow() {
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
            $("#Press2").val(res.press);
            $("#zzlx2").val(res.zzlx);
            $("#CIP2").val(res.cip);
            $("#Time2").val(res.time);
            $("#ZiShu2").val(res.zishu);
            $("#Points2").val(res.points);
            $("#Reward2").val(res.reward);
            $("#fq2").val(res.fq);
            $("#WorkType2").val(res.worktype);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function xszzupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "tjump/xszzshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                $("#xszzupdatemodal").modal('show');
                $("#xid").val(res.xid);
                $("#TiMu").val(res.timu);
                $("#DYZZ").val(res.dyzz);
                $("#QTZZ").val(res.qtzz);
                $("#ISBN").val(res.isbn);
                $("#zzlx").val(res.zzlx);
                $("#Press").val(res.press);
                $("#CIP").val(res.cip);
                $("#Time").val(res.time);
                $("#ZiShu").val(res.zishu);
                $("#Points").val(res.points);
                $("#Reward").val(res.reward);
                $("#fq").val(res.fq);
                $('#WorkType').combotree('setValue', res.worktype);

                // $("#WorkType").val(res.worktype);
                $("#FJSCdisabled").val(res.fjsc);
                $("#judgestatusdisabled").val(res.judgestatus);

            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function xszzupdatesubmit() {
    var xid = $("#xid").val();
    var timu = $("#TiMu").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var isbn = $("#ISBN").val();
    // var presstype = $("#PressType").val();
    var press = $("#Press").val();
    var zzlx = $("#zzlx").val();
    var cip = $("#CIP").val();
    var time = $("#Time").val();
    var zishu = $("#ZiShu").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var fq = $("#fq").val();
    var worktype = $("#WorkType").combotree('getText');
    var judgestatus = $("#judgestatusdisabled").val();

    var fjsc = $("#FJSC")[0].files[0];  //可能为 空  考虑为空的情况下的处理
    if (xid == null || xid == "" || timu == null || timu == "" || dyzz == null || dyzz == "" || isbn == null || isbn == "" || press == "" || press == null || time == null || time == "" || cip == null || cip == "" ||
        zishu == null || zishu == "" || points == null || points == "" || worktype == null || worktype == "") {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "tjump/xszzupdate",
            type: "post",
            data: JSON.stringify({
                xid: xid, timu: timu, dyzz: dyzz, qtzz: qtzz, isbn: isbn,
                press: press, cip: cip, time: time, zishu: zishu, points: points, reward: reward, fq: fq,
                reward: reward, points: points, worktype: worktype, judgestatus: "1", zzlx: zzlx
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "tjump/goxszzpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            success: function (data) {
                $.ajax({
                    url: httptod + "tjump/xszzupdate",
                    type: "post",
                    data: JSON.stringify({
                        xid: xid, timu: timu, dyzz: dyzz, qtzz: qtzz, isbn: isbn, fq: fq,
                        press: press, cip: cip, time: time, zishu: zishu, points: points, reward: reward,
                        reward: reward, points: points, worktype: worktype, judgestatus: "1", fjsc: data, zzlx: zzlx
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "tjump/goxszzpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }
}

function xszzupdatesubmittemp() {
    var xid = $("#xid").val();
    var timu = $("#TiMu").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var isbn = $("#ISBN").val();
    var press = $("#Press").val();
    var cip = $("#CIP").val();
    var time = $("#Time").val();
    var zishu = $("#ZiShu").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var worktype = $("#WorkType").combotree('getText');

    $.ajax({
        url: httptod + "tjump/xszzupdate",
        type: "post",
        data: JSON.stringify({
            xid: xid, timu: timu, dyzz: dyzz, qtzz: qtzz, isbn: isbn,
            press: press, cip: cip, time: time, zishu: zishu, points: points, reward: reward,
            reward: reward, points: points, worktype: worktype, judgestatus: "100", zzlx: zzlx
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            alert(res.description);
            window.location.href = httptod + "tjump/goxszzpage";
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function putxszz() {
    var timu = $("#TiMu").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var isbn = $("#ISBN").val();
    var press = $("#Press").val();
    var cip = $("#CIP").val();
    var time = $("#Time").val();
    var zishu = $("#ZiShu").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var zzlx = $("#zzlx").val();
    var fq = $("#fq").val();
    var worktype = $("#WorkType").combotree('getText');
    var fjsc = $("#FJSC")[0].files[0];
    if (timu == null || timu == "" || dyzz == null || dyzz == "" || isbn == null || isbn == "" || press == ""
        || press == null || time == null || time == "" || cip == null || cip == "" || zishu == null || zishu == ""||
        zzlx == null || zzlx == null
        || points == null || points == "" ||  worktype == null || worktype == "" || fjsc == null || fjsc == '') {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            async: false,
            success: function (data) {
                console.log("文件地址是：", data)
                $.ajax({
                    url: httptod + "tjump/putxszz",
                    type: "post",
                    data: JSON.stringify({
                        timu: timu, dyzz: dyzz, qtzz: qtzz, isbn: isbn, fq: fq,
                        press: press, cip: cip, time: time, zishu: zishu, points: points, reward: reward,
                        reward: reward, points: points, worktype: worktype, judgestatus: "1", fjsc: data, zzlx: zzlx
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "tjump/goxszzpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }


}


//横向结项相关
function gohxxmpage() {
    window.location.href = httptod + "tjump/gohxxmpage";
}

function gohxxmBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "tjump/gohxxmpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function gohxxmwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "tjump/gohxxmwrite";
    }
}

function gohxxmnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "tjump/gohxxmwrite?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;

}

function downloadhxxmfjsc() {
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

function hxxmexportall() {
    window.location.href = httptod + "tjump/hxxmexportall";
}

function hxxmdelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "tjump/deletehxxm",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "tjump/gohxxmpage";
            },
            error: function (res) {
                console.log(res);
            }
        });
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var otherlist = new Array();
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         if (judgestatus == '4' || judgestatus == '5' || judgestatus == '100') {
    //             list.push($(this).val());
    //         } else {
    //             otherlist.push($(this).val());
    //         }
    //     });
    //     if (list.length == 0 && otherlist.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         return false;
    //     }
    //     if (list.length == 0) {
    //         alert("选择有问题，请勿选择属于不可以删除的内容");
    //     } else {
    //         $.ajax({
    //             url: httptod + "tjump/deletehxxm",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "tjump/gohxxmpage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         });
    //     }
    // }
}


function hxxmshow() {
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

function hxxmupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "tjump/hxxmshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                $("#hxxmupdatemodal").modal('show');
                $("#hid").val(res.hid);
                $("#XMName").val(res.xmname);
                $("#Principal").val(res.principal);
                $("#XmStatus").val(res.xmstatus);
                $("#ZJE").val(res.zje);
                $("#YBK").val(res.ybk);
                $("#ContractNumber").val(res.contractnumber);
                $("#AUnit").val(res.aunit);
                $("#QDTime").val(res.qdtime);
                $("#STime").val(res.stime);
                $("#JZTime").val(res.jztime);
                $("#JXTime").val(res.jxtime);
                $("#JXJY").val(res.jxjy);
                $("#JFStatus").val(res.jfstatus);
                $("#Points").val(res.points);
                $("#Reward").val(res.reward);
                $("#Remark").val(res.remark);


                $("#judgestatusdisabled").val(res.judgestatus);
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function hxxmupdatesubmit() {
    var hid = $("#hid").val();
    var xmname = $("#XMName").val();
    var principal = $("#Principal").val();
    var xmstatus = $("#XmStatus").val();
    var zje = $("#ZJE").val();
    var ybk = $("#YBK").val();
    var contractnumber = $("#ContractNumber").val();
    var aunit = $("#AUnit").val();
    var qdtime = $("#QDTime").val();
    var stime = $("#STime").val();
    var jztime = $("#JZTime").val();
    var jxtime = $("#JXTime").val();
    var jxjy = $("#JXJY").val();
    var jfstatus = $("#JFStatus").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var remark = $("#Remark").val();

    var judgestatus = $("#judgestatusdisabled").val();

    var fjsc = $("#FJSC")[0].files[0];  //可能为 空  考虑为空的情况下的处理


    if (hid == null || hid == "" || xmname == null || xmname == "" || principal == null || principal == "" || zje == null || zje == "" || ybk == null || ybk == "" || contractnumber == null || contractnumber == "" ||
        aunit == null || aunit == "" || qdtime == null || qdtime == "" || stime == "" || stime == null || jztime == null || jztime == "" || jxtime == null || jxtime == "" ||
        jxjy == null || jxjy == "" || jfstatus == null || jfstatus == "" || points == null || points == "") {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "tjump/hxxmupdate",
            type: "post",
            data: JSON.stringify({
                hid: hid, xmname: xmname, principal: principal, xmstatus: xmstatus, zje: zje, ybk: ybk,
                contractnumber: contractnumber, aunit: aunit, qdtime: qdtime, stime: stime,
                jztime: jztime, jxtime: jxtime, jxjy: jxjy, jfstatus: jfstatus, points: points,
                reward: reward, remark: remark, judgestatus: "1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "tjump/gohxxmpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            async: false,
            success: function (data) {
                $.ajax({
                    url: httptod + "tjump/hxxmupdate",
                    type: "post",
                    data: JSON.stringify({
                        hid: hid, xmname: xmname, principal: principal, xmstatus: xmstatus, zje: zje, ybk: ybk,
                        contractnumber: contractnumber, aunit: aunit, qdtime: qdtime, stime: stime,
                        jztime: jztime, jxtime: jxtime, jxjy: jxjy, jfstatus: jfstatus, points: points,
                        reward: reward, remark: remark, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "tjump/gohxxmpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }
}

function hxxmupdatesubmittemp() {
    var hid = $("#hid").val();
    var xmname = $("#XMName").val();
    var principal = $("#Principal").val();
    var xmstatus = $("#XmStatus").val();
    var zje = $("#ZJE").val();
    var ybk = $("#YBK").val();
    var contractnumber = $("#ContractNumber").val();
    var aunit = $("#AUnit").val();
    var qdtime = $("#QDTime").val();
    var stime = $("#STime").val();
    var jztime = $("#JZTime").val();
    var jxtime = $("#JXTime").val();
    var jxjy = $("#JXJY").val();
    var jfstatus = $("#JFStatus").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var remark = $("#Remark").val();

    $.ajax({
        url: httptod + "tjump/hxxmupdate",
        type: "post",
        data: JSON.stringify({
            hid: hid, xmname: xmname, principal: principal, xmstatus: xmstatus, zje: zje, ybk: ybk,
            contractnumber: contractnumber, aunit: aunit, qdtime: qdtime, stime: stime,
            jztime: jztime, jxtime: jxtime, jxjy: jxjy, jfstatus: jfstatus, points: points,
            reward: reward, remark: remark, judgestatus: "100"
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            alert(res.description);
            window.location.href = httptod + "tjump/gohxxmpage";
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function puthxxm() {
    var xmname = $("#XMName").val();
    var principal = $("#Principal").val();
    var xmstatus = $("#XmStatus").val();
    var zje = $("#ZJE").val();
    var ybk = $("#YBK").val();
    var contractnumber = $("#ContractNumber").val();
    var aunit = $("#AUnit").val();
    var qdtime = $("#QDTime").val();
    var stime = $("#STime").val();
    var jztime = $("#JZTime").val();
    var jxtime = $("#JXTime").val();
    var jxjy = $("#JXJY").val();
    var jfstatus = $("#JFStatus").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var remark = $("#Remark").val();

    var fjsc = $("#FJSC")[0].files[0];

    if (xmname == null || xmname == "" || principal == null || principal == "" || zje == null || zje == "" || ybk == null || ybk == "" || contractnumber == null || contractnumber == "" ||
        aunit == null || aunit == "" || qdtime == null || qdtime == "" || stime == "" || stime == null || jztime == null || jztime == "" || jxtime == null || jxtime == "" ||
        jxjy == null || jxjy == "" || jfstatus == null || jfstatus == "" || points == null || points == ""
        ||fjsc == null || fjsc == '' ||xmstatus == null || xmstatus == '') {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }

        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            async: false,
            success: function (data) {
                console.log("文件地址是：", data)
                $.ajax({
                    url: httptod + "tjump/puthxxm",
                    type: "post",
                    data: JSON.stringify({
                        xmname: xmname, principal: principal, xmstatus: xmstatus, zje: zje, ybk: ybk,
                        contractnumber: contractnumber, aunit: aunit, qdtime: qdtime, stime: stime,
                        jztime: jztime, jxtime: jxtime, jxjy: jxjy, jfstatus: jfstatus, points: points,
                        reward: reward, remark: remark, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "tjump/gohxxmpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
            error: function (res) {
                console.log("erroe", res);
            }
        })
    }


}

//横向立项
function gohxlxpage() {
    window.location.href = httptod + "ttttjump/gohxlxpage";
}

function gohxlxBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttttjump/gohxlxpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function gohxlxwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "ttttjump/gohxlxwrite";
    }
}

function gohxlxnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttttjump/gohxlxwrite?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;

}

function downloadhxlxfjsc() {
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

function hxlxexportall() {
    window.location.href = httptod + "ttttjump/hxlxexportall";
}

function hxlxdelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "ttttjump/deletehxlx",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttttjump/gohxlxpage";
            },
            error: function (res) {
                console.log(res);
            }
        });
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var otherlist = new Array();
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         if (judgestatus == '4' || judgestatus == '5' || judgestatus == '100') {
    //             list.push($(this).val());
    //         } else {
    //             otherlist.push($(this).val());
    //         }
    //     });
    //     if (list.length == 0 && otherlist.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         return false;
    //     }
    //     if (list.length == 0) {
    //         alert("选择有问题，请勿选择属于不可以删除的内容");
    //     } else {
    //         $.ajax({
    //             url: httptod + "ttttjump/deletehxlx",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "ttttjump/gohxlxpage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         });
    //     }
    // }
}


function hxlxshow() {
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

function hxlxupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "ttttjump/hxlxshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                $("#hxlxupdatemodal").modal('show');
                $("#hid").val(res.hid);
                $("#XMName").val(res.xmname);
                $("#Principal").val(res.principal);
                $("#XmStatus").val(res.xmstatus);
                $("#ZJE").val(res.zje);
                $("#YBK").val(res.ybk);
                $("#ContractNumber").val(res.contractnumber);
                $("#AUnit").val(res.aunit);
                $("#QDTime").val(res.qdtime);
                $("#STime").val(res.stime);
                $("#JZTime").val(res.jztime);

                $("#Points").val(res.points);
                $("#Reward").val(res.reward);
                $("#Remark").val(res.remark);


                $("#judgestatusdisabled").val(res.judgestatus);
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function hxlxupdatesubmit() {
    var hid = $("#hid").val();
    var xmname = $("#XMName").val();
    var principal = $("#Principal").val();
    var xmstatus = $("#XmStatus").val();
    var zje = $("#ZJE").val();
    var ybk = $("#YBK").val();
    var contractnumber = $("#ContractNumber").val();
    var aunit = $("#AUnit").val();
    var qdtime = $("#QDTime").val();
    var stime = $("#STime").val();
    var jztime = $("#JZTime").val();

    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var remark = $("#Remark").val();

    var judgestatus = $("#judgestatusdisabled").val();

    var fjsc = $("#FJSC")[0].files[0];  //可能为 空  考虑为空的情况下的处理


    if (hid == null || hid == "" || xmname == null || xmname == "" || principal == null || principal == "" || zje == null || zje == "" || ybk == null || ybk == "" || contractnumber == null || contractnumber == "" ||
        aunit == null || aunit == "" || qdtime == null || qdtime == "" || stime == "" || stime == null || jztime == null || jztime == "" || points == null || points == ""
        ) {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "ttttjump/hxlxupdate",
            type: "post",
            data: JSON.stringify({
                hid: hid, xmname: xmname, principal: principal, xmstatus: xmstatus, zje: zje, ybk: ybk,
                contractnumber: contractnumber, aunit: aunit, qdtime: qdtime, stime: stime,
                jztime: jztime, points: points,
                reward: reward, remark: remark, judgestatus: "1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttttjump/gohxlxpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            async: false,
            success: function (data) {
                $.ajax({
                    url: httptod + "ttttjump/hxlxupdate",
                    type: "post",
                    data: JSON.stringify({
                        hid: hid, xmname: xmname, principal: principal, xmstatus: xmstatus, zje: zje, ybk: ybk,
                        contractnumber: contractnumber, aunit: aunit, qdtime: qdtime, stime: stime,
                        jztime: jztime, points: points,
                        reward: reward, remark: remark, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttttjump/gohxlxpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }
}


function puthxlx() {
    var xmname = $("#XMName").val();
    var principal = $("#Principal").val();
    var xmstatus = $("#XmStatus").val();
    var zje = $("#ZJE").val();
    var ybk = $("#YBK").val();
    var contractnumber = $("#ContractNumber").val();
    var aunit = $("#AUnit").val();
    var qdtime = $("#QDTime").val();
    var stime = $("#STime").val();
    var jztime = $("#JZTime").val();

    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var remark = $("#Remark").val();

    var fjsc = $("#FJSC")[0].files[0];

    if (xmname == null || xmname == "" || principal == null || principal == "" || zje == null || zje == "" || ybk == null || ybk == "" || contractnumber == null || contractnumber == "" ||
        aunit == null || aunit == "" || qdtime == null || qdtime == "" || stime == "" || stime == null || jztime == null || jztime == "" || points == null || points == ""
        ||  fjsc == null || fjsc == '' ||  xmstatus == null || xmstatus == '') {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }

        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            async: false,
            success: function (data) {
                console.log("文件地址是：", data)
                $.ajax({
                    url: httptod + "ttttjump/puthxlx",
                    type: "post",
                    data: JSON.stringify({
                        xmname: xmname, principal: principal, xmstatus: xmstatus, zje: zje, ybk: ybk,
                        contractnumber: contractnumber, aunit: aunit, qdtime: qdtime, stime: stime,
                        jztime: jztime, points: points,
                        reward: reward, remark: remark, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttttjump/gohxlxpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
            error: function (res) {
                console.log("erroe", res);
            }
        })
    }


}

//软件著作相关
function gorjzzpage() {
    window.location.href = httptod + "ttjump/gorjzzpage";
}

function gorjzzBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttjump/gorjzzpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function gorjzzwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "ttjump/gorjzzwrite";
    }
}

function gorjzznext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttjump/gorjzzpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;

}


function rjzzdelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "ttjump/deleterjzz",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttjump/gorjzzpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var otherlist = new Array();
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         if (judgestatus == '4' || judgestatus == '5' || judgestatus == '100') {
    //             list.push($(this).val());
    //         } else {
    //             otherlist.push($(this).val());
    //         }
    //     });
    //     if (list.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         returnFunction = true;
    //     }
    //     if (returnFunction == false) {
    //         $.ajax({
    //             url: httptod + "ttjump/deleterjzz",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "ttjump/gorjzzpage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         })
    //     }
    // }
}

function downloadrjzzfjsc() {
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

function rjzzexportall() {
    window.location.href = httptod + "ttjump/rjzzexportall";
}

//一个 编辑  一个查看 都放在 modal 只能选中一个进行操作  如果多选只显示一个 先做显示
function rjzzshow() {
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
            $("#fq2").val(res.fq);
            $("#BQType2").val(res.bqtype);
            $("#SYFS2").val(res.syfs);
            $("#Points2").val(res.points);
        },
        error: function (res) {
            console.log(res);
        }
    })

}

function rjzzupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "ttjump/rjzzshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                //把数据渲染到 页面上
                $("#rjzzupdatemodal").modal('show');
                $("#rid").val(res.rid);
                $("#xmName").val(res.xmname);
                $("#DYZZ").val(res.dyzz);
                $("#QTZZ").val(res.qtzz);
                $("#DJNumber").val(res.djnumber);
                $("#SYDW").val(res.sydw);
                $("#FinishTime").val(res.finishtime);
                $("#DJTime").val(res.djtime);
                $("#fq").val(res.fq);
                // $("#SFTime").val(res.sftime);
                $("#BQType").val(res.bqtype);
                $("#SYFS").val(res.syfs);
                $("#Points").val(res.points);

                $("#judgestatusdisabled").val(res.judgestatus);
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function rjzzupdatesubmit() {
    var rid = $("#rid").val();
    var xmname = $("#xmName").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var djnumber = $("#DJNumber").val();
    var sydw = $("#SYDW").val();
    var finishtime = $("#FinishTime").val();
    var djtime = $("#DJTime").val();
    // var sftime = $("#SFTime").val();
    var bqtype = $("#BQType").val();
    var syfs = $("#SYFS").val();
    var points = $("#Points").val();
    var fq = $("#fq").val();

    var judgestatus = $("#judgestatusdisabled").val();


    var fjsc = $("#FJSC")[0].files[0];  //可能为 空  考虑为空的情况下的处理

    if (rid == null || rid == "" || xmname == null || xmname == "" || dyzz == null || dyzz == "" || djnumber == null || djnumber == "" || finishtime == null ||
        finishtime == "" || djtime == "" || djtime == null || bqtype == "" || bqtype == null
        || syfs == "" || syfs == null || points == "" || points == null) {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "ttjump/rjzzupdate",
            type: "post",
            data: JSON.stringify({
                rid: rid, xmname: xmname, dyzz: dyzz, qtzz: qtzz, djnumber: djnumber, sydw: sydw,
                finishtime: finishtime, djtime: djtime, bqtype: bqtype, syfs: syfs, fq: fq,
                points: points, judgestatus: "1",
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttjump/gorjzzpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            async: false,
            success: function (data) {
                $.ajax({
                    url: httptod + "ttjump/rjzzupdate",
                    type: "post",
                    data: JSON.stringify({
                        rid: rid, xmname: xmname, dyzz: dyzz, qtzz: qtzz, djnumber: djnumber,
                        sydw: sydw, finishtime: finishtime, djtime: djtime, fq: fq,
                        bqtype: bqtype, syfs: syfs, points: points, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttjump/gorjzzpage";
                    }
                })
            }
        })
    }
}

function rjzzupdatesubmittemp() {
    var rid = $("#rid").val();
    var xmname = $("#xmName").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var djnumber = $("#DJNumber").val();
    var sydw = $("#SYDW").val();
    var finishtime = $("#FinishTime").val();
    var djtime = $("#DJTime").val();
    // var sftime = $("#SFTime").val();
    var bqtype = $("#BQType").val();
    var syfs = $("#SYFS").val();
    var points = $("#Points").val();
    $.ajax({
        url: httptod + "ttjump/rjzzupdate",
        type: "post",
        data: JSON.stringify({
            rid: rid, xmname: xmname, dyzz: dyzz, qtzz: qtzz, djnumber: djnumber, sydw: sydw, finishtime: finishtime,
            djtime: djtime, bqtype: bqtype, syfs: syfs, points: points,
            judgestatus: "100",
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            alert(res.description);
            window.location.href = httptod + "ttjump/gorjzzpage";
        },
        error: function (res) {
            console.log(res);
        }
    })
}


function putrjzz() {
    var xmname = $("#xmName").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var djnumber = $("#DJNumber").val();
    var sydw = $("#SYDW").val();
    var finishtime = $("#FinishTime").val();
    var djtime = $("#DJTime").val();

    var bqtype = $("#BQType").val();
    var syfs = $("#SYFS").val();
    var points = $("#Points").val();
    var fq = $("#fq").val();

    var fjsc = $("#FJSC")[0].files[0];

    if (xmname == null || xmname == "" || dyzz == null || dyzz == "" || djnumber == null || djnumber == "" || finishtime == null ||
        finishtime == "" || djtime == "" || djtime == null || bqtype == "" || bqtype == null
        || syfs == "" || syfs == null || points == "" || points == null || fjsc == null || fjsc == '') {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "ttjump/putrjzz",
                    type: "post",
                    data: JSON.stringify({
                        xmname: xmname, dyzz: dyzz, qtzz: qtzz, djnumber: djnumber, sydw: sydw, finishtime: finishtime,
                        djtime: djtime, bqtype: bqtype, syfs: syfs, points: points, fq: fq,
                        judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttjump/gorjzzpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }

}


//获批专利相关
function gohpzlpage() {
    window.location.href = httptod + "ttjump/gohpzlpage";
}

function gohpzlBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttjump/gohpzlpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function gohpzlwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "ttjump/gohpzlwrite";
    }
}

function gohpzlnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttjump/gohpzlpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function hpzldelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "ttjump/deletehpzl",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttjump/gohpzlpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var otherlist = new Array();
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         if (judgestatus == '4' || judgestatus == '5' || judgestatus == '100') {
    //             list.push($(this).val());
    //         } else {
    //             otherlist.push($(this).val());
    //         }
    //     });
    //     if (list.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         returnFunction = true;
    //     }
    //     if (returnFunction == false) {
    //         $.ajax({
    //             url: httptod + "ttjump/deletehpzl",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "ttjump/gohpzlpage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         })
    //     }
    // }
}

function downloadhpzlfjsc() {
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

function hpzlexportall() {
    window.location.href = httptod + "ttjump/hpzlexportall";
}

function hpzlshow() {
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
            $("#PType2").val(res.ptype);
            $("#fq2").val(res.fq);
            $("#Pstatus2").val(res.pstatus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function hpzlupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "ttjump/hpzlshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                //把数据渲染到 页面上
                $("#hpzlupdatemodal").modal('show');
                $("#hid").val(res.hid);
                $("#xmName").val(res.xmname);
                $("#ZLQR").val(res.zlqr);
                $("#FMR").val(res.fmr);
                $("#QTFMR").val(res.qtfmr);
                $("#BFBM").val(res.bfbm);
                $("#ZLNumber").val(res.zlnumber);
                $("#SQTime").val(res.sqtime);
                $("#SYTime").val(res.sytime);
                $("#Points").val(res.points);
                $("#Reward").val(res.reward);
                $("#fq").val(res.fq);
                // $("#FJSC").val(res.fjsc);
                $('#PType').combotree('setValue', res.ptype);

                // $("#PType").val(res.ptype);
                $("#Pstatus").val(res.pstatus);
                $("#judgestatusdisabled").val(res.judgestatus);
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function hpzlupdatesubmit() {
    var hid = $("#hid").val();
    var xmname = $("#xmName").val();
    var zlqr = $("#ZLQR").val();
    var fmr = $("#FMR").val();
    var qtfmr = $("#QTFMR").val();
    var bfbm = $("#BFBM").val();
    var zlnumber = $("#ZLNumber").val();
    var sqtime = $("#SQTime").val();
    var sytime = $("#SYTime").val();
    var ptype = $("#PType").combotree('getText');
    var pstatus = $("#Pstatus").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var fq = $("#fq").val();
    var judgestatus = $("#judgestatusdisabled").val();
    var fjsc = $("#FJSC")[0].files[0];  //可能为 空  考虑为空的情况下的处理
    if (hid == null || hid == "" || xmname == null || xmname == "" || zlqr == null || zlqr == "" || fmr == null || fmr == "" ||
        qtfmr == null || qtfmr == "" || bfbm == "" || bfbm == null || zlnumber == null || zlnumber == "" ||
        sqtime == null || sqtime == "" || sytime == null || sytime == "" || ptype == null || ptype == "" || pstatus == null || pstatus == "" ||
        points == null || points == "" ) {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "ttjump/hpzlupdate",
            type: "post",
            data: JSON.stringify({
                hid: hid, xmname: xmname, xmname: xmname, zlqr: zlqr, fmr: fmr,
                qtfmr: qtfmr, bfbm: bfbm, zlnumber: zlnumber, sqtime: sqtime, fq: fq,
                sytime: sytime, ptype: ptype, pstatus: pstatus, points: points,
                reward: reward, judgestatus: "1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttjump/gohpzlpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            async: false,
            success: function (data) {
                $.ajax({
                    url: httptod + "ttjump/hpzlupdate",
                    type: "post",
                    data: JSON.stringify({
                        hid: hid, xmname: xmname, xmname: xmname, zlqr: zlqr, fmr: fmr,
                        qtfmr: qtfmr, bfbm: bfbm, zlnumber: zlnumber, sqtime: sqtime, fq: fq,
                        sytime: sytime, ptype: ptype, pstatus: pstatus, points: points,
                        reward: reward, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttjump/gohpzlpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }
}

function hpzlupdatesubmittemp() {
    var hid = $("#hid").val();
    var xmname = $("#xmName").val();
    var zlqr = $("#ZLQR").val();
    var fmr = $("#FMR").val();
    var qtfmr = $("#QTFMR").val();
    var bfbm = $("#BFBM").val();
    var zlnumber = $("#ZLNumber").val();
    var sqtime = $("#SQTime").val();
    var sytime = $("#SYTime").val();
    var ptype = $("#PType").combotree('getText');
    var pstatus = $("#Pstatus").val();
    var points = $("#Points").val();
    var fq = $("#fq").val();
    var reward = $("#Reward").val();
    $.ajax({
        url: httptod + "ttjump/hpzlupdate",
        type: "post",
        data: JSON.stringify({
            hid: hid, xmname: xmname, xmname: xmname, zlqr: zlqr, fmr: fmr,
            qtfmr: qtfmr, bfbm: bfbm, zlnumber: zlnumber, sqtime: sqtime,
            sytime: sytime, ptype: ptype, pstatus: pstatus, points: points, fq: fq,
            reward: reward, judgestatus: "100"
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            alert(res.description);
            window.location.href = httptod + "ttjump/gohpzlpage";
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function puthpzl() {
    var xmname = $("#xmName").val();
    var zlqr = $("#ZLQR").val();
    var fmr = $("#FMR").val();
    var qtfmr = $("#QTFMR").val();
    var bfbm = $("#BFBM").val();
    var zlnumber = $("#ZLNumber").val();
    var sqtime = $("#SQTime").val();
    var sytime = $("#SYTime").val();
    var ptype = $("#PType").combotree('getText');
    var pstatus = $("#Pstatus").val();
    var points = $("#Points").val();
    var fq = $("#fq").val();
    var reward = $("#Reward").val();

    var fjsc = $("#FJSC")[0].files[0];
    if (xmname == null || xmname == "" || zlqr == null || zlqr == "" || fmr == null || fmr == "" ||
        qtfmr == null || qtfmr == "" || bfbm == "" || bfbm == null || zlnumber == null || zlnumber == "" ||
        sqtime == null || sqtime == "" || sytime == null || sytime == "" || ptype == null || ptype == "" || pstatus == null || pstatus == "" ||
        points == null || points == "" || fjsc == null || fjsc == '') {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                console.log("文件地址是：", data)
                $.ajax({
                    url: httptod + "ttjump/puthpzl",
                    type: "post",
                    data: JSON.stringify({
                        xmname: xmname, xmname: xmname, zlqr: zlqr, fmr: fmr, fq: fq,
                        qtfmr: qtfmr, bfbm: bfbm, zlnumber: zlnumber, sqtime: sqtime,
                        sytime: sytime, ptype: ptype, pstatus: pstatus, points: points,
                        reward: reward, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttjump/gohpzlpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
        })
    }
}


//学术称号相关
function goxschpage() {
    window.location.href = httptod + "ttjump/goxschpage";
}

function goxschBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttjump/goxschpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function goxschwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "ttjump/goxschwrite";
    }
}

function goxschnext(pageNum) {
    window.location.href = httptod + "ttjump/goxschpage?pageNum=" + pageNum;
}

function xschshow() {
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

function xschupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "ttjump/xschshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                //把数据渲染到 页面上
                $("#xschupdatemodal").modal('show');
                $("#xid").val(res.xid);
                $("#xmName").val(res.xmname);
                $("#Title").val(res.title);
                $("#Rank").val(res.rank);
                $("#ZSNumber").val(res.zsnumber);
                $("#Time").val(res.time);
                $("#XFBM").val(res.xfbm);
                $("#fq").val(res.fq);
                $("#judgestatusdisabled").val(res.judgestatus);

            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}


function xschupdatesubmit() {
    var xid = $("#xid").val();
    var xmname = $("#xmName").val();
    var title = $("#Title").val();
    var rank = $("#Rank").val();
    var zsnumber = $("#ZSNumber").val();
    var time = $("#Time").val();
    var xfbm = $("#XFBM").val();
    var fq = $("#fq").val();
    var judgestatus = $("#judgestatusdisabled").val();
    var fjsc = $("#FJSC")[0].files[0];  //可能为 空  考虑为空的情况下的处理
    if (xid == null || xid == "" || xmname == null || xmname == "" || title == null || title == "" || rank == null || rank == ""
        || time == "" || time == null || xfbm == "" || xfbm == null) {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "ttjump/xschupdate",
            type: "post",
            data: JSON.stringify({
                xid: xid, xmname: xmname, title: title, rank: rank, zsnumber: zsnumber, fq: fq,
                time: time, xfbm: xfbm, judgestatus: "1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttjump/goxschpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "ttjump/xschupdate",
                    type: "post",
                    data: JSON.stringify({
                        xid: xid, xmname: xmname, title: title, rank: rank, zsnumber: zsnumber, fq: fq,
                        time: time, xfbm: xfbm, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttjump/goxschpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }
}

function xschupdatesubmittemp() {
    var xid = $("#xid").val();
    var xmname = $("#xmName").val();
    var title = $("#Title").val();
    var rank = $("#Rank").val();
    var zsnumber = $("#ZSNumber").val();
    var time = $("#Time").val();
    var fq = $("#fq").val();
    var xfbm = $("#XFBM").val();
    $.ajax({
        url: httptod + "ttjump/xschupdate",
        type: "post",
        data: JSON.stringify({
            xid: xid, xmname: xmname, title: title, rank: rank, zsnumber: zsnumber, fq: fq,
            time: time, xfbm: xfbm, judgestatus: "100"
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            alert(res.description);
            window.location.href = httptod + "ttjump/goxschpage";
        },
        error: function (res) {
            console.log(res);
        }
    })
}


function xschdelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        // 在这里添加 ajax函数  后端获取到list集合
        $.ajax({
            url: httptod + "ttjump/deletexsch",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttjump/goxschpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var otherlist = new Array();
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         if (judgestatus == '4' || judgestatus == '5' || judgestatus == '100') {
    //             list.push($(this).val());
    //         } else {
    //             otherlist.push($(this).val());
    //         }
    //     });
    //     if (list.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         returnFunction = true;
    //     }
    //     if (returnFunction == false) {
    //         // console.log(list);
    //         // 在这里添加 ajax函数  后端获取到list集合
    //         $.ajax({
    //             url: httptod + "ttjump/deletexsch",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "ttjump/goxschpage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         })
    //     }
    // }
}

function downloadxschfjsc() {
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

function xschexportall() {
    window.location.href = httptod + "ttjump/xschexportall";
}

function putxsch() {
    var xmname = $("#xmName").val();
    var title = $("#Title").val();
    var rank = $("#Rank").val();
    var zsnumber = $("#ZSNumber").val();
    var time = $("#Time").val();
    var xfbm = $("#XFBM").val();
    var fq = $("#fq").val();

    var fjsc = $("#FJSC")[0].files[0];


    if (xmname == null || xmname == "" || title == null || title == "" || rank == null || rank == ""
        ||  time == "" || time == null || xfbm == "" || xfbm == null || fjsc == null || fjsc == '') {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                console.log("文件地址是：", data)
                $.ajax({
                    url: httptod + "ttjump/putxsch",
                    type: "post",
                    data: JSON.stringify({
                        xmname: xmname, title: title, rank: rank, zsnumber: zsnumber, fq: fq,
                        time: time, xfbm: xfbm, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttjump/goxschpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
            error: function (res) {
                console.log("erroe", res);
            }
        })

    }


}


//学术交流相关
function goxsjlpage() {
    window.location.href = httptod + "ttjump/goxsjlpage";
}

function goxsjlBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttjump/goxsjlpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function goxsjlwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "ttjump/goxsjlwrite";
    }
}

function goxsjlnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttjump/goxsjlpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function xsjldelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "ttjump/deletexsjl",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttjump/goxsjlpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var returnFunction = false;
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         list.push($(this).val());
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         console.log(judgestatus);
    //         if (judgestatus == 2 || judgestatus == 3) {
    //             alert("已经通过审核的不可删除");
    //             returnFunction = true;
    //             return false;
    //         }
    //     });
    //     if (list.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         returnFunction = true;
    //     }
    //     if (returnFunction == false) {
    //         // console.log(list);
    //         // 在这里添加 ajax函数  后端获取到list集合
    //         $.ajax({
    //             url: httptod + "ttjump/deletexsjl",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "ttjump/goxsjlpage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         })
    //     }
    // }
}

function xsjlshow() {
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

function xsjlupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "ttjump/xsjlshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                //把数据渲染到 页面上
                $("#xsjlupdatemodal").modal('show');
                $("#xid").val(res.xid);
                $("#xmName").val(res.xmname);
                $("#CYRY").val(res.cyry);
                $("#Wheres").val(res.wheres);
                $("#Time").val(res.time);
                $("#ZBDW").val(res.zbdw);
                $("#Rank").val(res.rank);
                $("#fayan").val(res.fayan);
                $("#LunWen").val(res.lunwen);
                $("#fq").val(res.fq);
                $("#judgestatusdisabled").val(res.judgestatus);
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function xsjlupdatesubmit() {
    var xid = $("#xid").val();
    var xmname = $("#xmName").val();
    var cyry = $("#CYRY").val();
    var wheres = $("#Wheres").val();
    var time = $("#Time").val();
    var zbdw = $("#ZBDW").val();
    var rank = $("#Rank").val();
    var lunwen = $("#LunWen").val();
    var fayan = $("#fayan").val();
    var fq = $("#fq").val();
    var judgestatus = $("#judgestatusdisabled").val();
    var fjsc = $("#FileName")[0].files[0];  //可能为 空  考虑为空的情况下的处理
    if (xid == null || xid == "" || xmname == null || xmname == "" || cyry == null || cyry == "" || wheres == null || wheres == "" || time == null ||
        time == "" || zbdw == "" || zbdw == null || rank == "" || rank == null || lunwen == "" || lunwen == null) {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "ttjump/xsjlupdate",
            type: "post",
            data: JSON.stringify({
                xid: xid, xmname: xmname, cyry: cyry, wheres: wheres, time: time, zbdw: zbdw, fq: fq,
                rank: rank, lunwen: lunwen, judgestatus: "1",fayan:fayan
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttjump/goxsjlpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "ttjump/xsjlupdate",
                    type: "post",
                    data: JSON.stringify({
                        xid: xid, xmname: xmname, cyry: cyry, wheres: wheres, time: time, zbdw: zbdw, fq: fq,
                        rank: rank, lunwen: lunwen, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttjump/goxsjlpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }
}

function xsjlupdatesubmittemp() {
    var xid = $("#xid").val();
    var xmname = $("#xmName").val();
    var cyry = $("#CYRY").val();
    var wheres = $("#Wheres").val();
    var time = $("#Time").val();
    var zbdw = $("#ZBDW").val();
    var rank = $("#Rank").val();
    var fq = $("#fq").val();
    var lunwen = $("#LunWen").val();
    $.ajax({
        url: httptod + "ttjump/xsjlupdate",
        type: "post",
        data: JSON.stringify({
            xid: xid, xmname: xmname, cyry: cyry, wheres: wheres, time: time, zbdw: zbdw, fq: fq,
            rank: rank, lunwen: lunwen, judgestatus: "100"
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            alert(res.description);
            window.location.href = httptod + "ttjump/goxsjlpage";
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function downloadxsjlfjsc() {
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

function xsjlexportall() {
    window.location.href = httptod + "ttjump/xsjlexportall";
}

function putxsjl() {
    var xmname = $("#xmName").val();
    var cyry = $("#CYRY").val();
    var wheres = $("#Wheres").val();
    var time = $("#Time").val();
    var zbdw = $("#ZBDW").val();
    var rank = $("#Rank").val();
    var lunwen = $("#LunWen").val();
    var fayan = $("#fayan").val();
    var fq = $("#fq").val();

    var fjsc = $("#FileName")[0].files[0];
    if (xmname == null || xmname == "" || cyry == null || cyry == "" || wheres == null || wheres == "" || time == null ||
        time == "" || zbdw == "" || zbdw == null || rank == "" || rank == null || lunwen == "" || lunwen == null || fayan == "" || fayan == null ||
        fjsc == null || fjsc == "") {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                console.log("文件地址是：", data)
                $.ajax({
                    url: httptod + "ttjump/putxsjl",
                    type: "post",
                    data: JSON.stringify({
                        xmname: xmname, cyry: cyry, wheres: wheres, time: time, zbdw: zbdw, fq: fq,
                        rank: rank, lunwen: lunwen, fayan: fayan, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttjump/goxsjlpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
            error: function (res) {
                console.log("erroe", res);
            }
        })
    }


}

//获批奖励相关
function gohpjlpage() {
    window.location.href = httptod + "tttjump/gohpjlpage";
}

function gohpjlBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "tttjump/gohpjlpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function gohpjlwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "tttjump/gohpjlwrite";
    }
}

function gohpjlnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "tttjump/gohpjlpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function hpjldelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "tttjump/deletehpjl",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "tttjump/gohpjlpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var returnFunction = false;
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         list.push($(this).val());
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         console.log(judgestatus);
    //         if (judgestatus == 2 || judgestatus == 3) {
    //             alert("已经通过审核的不可删除");
    //             returnFunction = true;
    //             return false;
    //         }
    //     });
    //     if (list.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         returnFunction = true;
    //     }
    //     if (returnFunction == false) {
    //         // console.log(list);
    //         // 在这里添加 ajax函数  后端获取到list集合
    //         $.ajax({
    //             url: httptod + "tttjump/deletehpjl",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "tttjump/gohpjlpage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         })
    //     }
    // }
}

//一个 编辑  一个查看 都放在 modal 只能选中一个进行操作  如果多选只显示一个 先做显示
function hpjlshow() {
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

function hpjlupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "tttjump/hpjlshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                //把数据渲染到 页面上
                $("#hpjlupdatemodal").modal('show');
                $("#hid").val(res.hid);
                $("#CGName").val(res.cgname);
                $('#JLName').val(res.jlname);
                $("#DYHJR").val(res.dyhjr);
                $("#QTHJR").val(res.qthjr);
                $("#JIXDBM").val(res.jixdbm);
                $("#ZSNumber").val(res.zsnumber);
                $("#HJTime").val(res.hjtime);
                $('#JLRank').combotree('setValue', res.jlrank);
                $("#HJRank").val(res.hjrank);
                $("#Remark").val(res.remark);
                $("#Points").val(res.points);
                $("#KYJL").val(res.kyjl);
                $("#judgestatusdisabled").val(res.judgestatus);
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function hpjlupdatesubmit() {
    var hid = $("#hid").val();
    var cgname = $("#CGName").val();
    var jlname = $("#JLName").val();
    var dyhjr = $("#DYHJR").val();
    var qthjr = $("#QTHJR").val();
    var jixdbm = $("#JIXDBM").val();
    var zsnumber = $("#ZSNumber").val();
    var hjtime = $("#HJTime").val();
    var jlrank = $("#JLRank").combotree('getText');
    var hjrank = $("#HJRank").val();
    var remark = $("#Remark").val();
    var points = $("#Points").val();
    var kyjl = $("#KYJL").val();
    var judgestatus = $("#judgestatusdisabled").val();
    var fjsc = $("#FJSC")[0].files[0];
    if (hid == null || hid == "" || cgname == null || cgname == "" || jlname == null || jlname == "" ||
        dyhjr == null || dyhjr == "" || qthjr == null || qthjr == "" || jixdbm == "" || jixdbm == null ||
        points == null || points == "" || zsnumber == null || zsnumber == "" ||
        hjtime == null || hjtime == "" || jlrank == "" || jlrank == null) {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "tttjump/hpjlupdate",
            type: "post",
            data: JSON.stringify({
                hid: hid, cgname: cgname, jlname: jlname, dyhjr: dyhjr, qthjr: qthjr, jixdbm: jixdbm,
                zsnumber: zsnumber, hjtime: hjtime, jlrank: jlrank, hjrank: hjrank,
                remark: remark, points: points, kyjl: kyjl, judgestatus: "1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "tttjump/gohpjlpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "tttjump/hpjlupdate",
                    type: "post",
                    data: JSON.stringify({
                        hid: hid, cgname: cgname, jlname: jlname, dyhjr: dyhjr, qthjr: qthjr, jixdbm: jixdbm,
                        zsnumber: zsnumber, hjtime: hjtime, jlrank: jlrank, hjrank: hjrank,
                        remark: remark, points: points, kyjl: kyjl, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "tttjump/gohpjlpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }
}

function downloadhpjlfjsc() {
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

function hpjlexportall() {
    window.location.href = httptod + "tttjump/hpjlexportall";
}

function hpjlupdatesubmittemp() {
    var hid = $("#hid").val();
    var cgname = $("#CGName").val();
    var jlname = $("#JLName").val();
    var dyhjr = $("#DYHJR").val();
    var qthjr = $("#QTHJR").val();
    var jixdbm = $("#JIXDBM").val();
    var zsnumber = $("#ZSNumber").val();
    var hjtime = $("#HJTime").val();
    var jlrank = $("#JLRank").combotree('getText');
    var hjrank = $("#HJRank").val();
    var remark = $("#Remark").val();
    var points = $("#Points").val();
    var kyjl = $("#KYJL").val();

    //先 添加一下文件 然后传递到数据库
    $.ajax({
        url: httptod + "tttjump/hpjlupdate",
        type: "post",
        data: JSON.stringify({
            hid: hid, cgname: cgname, jlname: jlname, dyhjr: dyhjr, qthjr: qthjr, jixdbm: jixdbm,
            zsnumber: zsnumber, hjtime: hjtime, jlrank: jlrank, hjrank: hjrank,
            remark: remark, points: points, kyjl: kyjl, judgestatus: "100"
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            alert(res.description);
            window.location.href = httptod + "tttjump/gohpjlpage";
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function puthpjl() {
    var cgname = $("#CGName").val();
    var jlname = $("#JLName").val();
    var dyhjr = $("#DYHJR").val();
    var qthjr = $("#QTHJR").val();
    var jixdbm = $("#JIXDBM").val();
    var zsnumber = $("#ZSNumber").val();
    var hjtime = $("#HJTime").val();
    var jlrank = $("#JLRank").combotree('getText');
    var hjrank = $("#HJRank").val();
    var remark = $("#Remark").val();
    var points = $("#Points").val();
    var kyjl = $("#KYJL").val();

    var fjsc = $("#FJSC")[0].files[0];

    if (cgname == null || cgname == "" || jlname == null || jlname == "" || hjrank == null || hjrank == "" ||
        dyhjr == null || dyhjr == "" || qthjr == null || qthjr == "" || jixdbm == "" || jixdbm == null || points == null || points == ""  ||
        zsnumber == null || zsnumber == "" || hjtime == null || hjtime == "" || jlrank == "" || jlrank == null || fjsc == null || fjsc == '') {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            async: false,
            success: function (data) {
                console.log("文件地址是：", data)
                $.ajax({
                    url: httptod + "tttjump/puthpjl",
                    type: "post",
                    data: JSON.stringify({
                        cgname: cgname, jlname: jlname, dyhjr: dyhjr, qthjr: qthjr, jixdbm: jixdbm,
                        zsnumber: zsnumber, hjtime: hjtime, jlrank: jlrank, hjrank: hjrank,
                        remark: remark, points: points, kyjl: kyjl, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "tttjump/gohpjlpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
            error: function (res) {
                console.log("erroe", res);
            }
        })
    }
}

//音美展示比赛 获奖
function goymhjpage() {
    window.location.href = httptod + "tttjump/goymhjpage";
}

function goymhjBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "tttjump/goymhjpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function goymhjwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "tttjump/goymhjwrite";
    }
}

function goymhjnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "tttjump/goymhjwrite?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function ymhjdelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "tttjump/deleteymhj",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "tttjump/goymhjpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var returnFunction = false;
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         list.push($(this).val());
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         console.log(judgestatus);
    //         if (judgestatus == 2 || judgestatus == 3) {
    //             alert("已经通过审核的不可删除");
    //             returnFunction = true;
    //             return false;
    //         }
    //     });
    //     if (list.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         returnFunction = true;
    //     }
    //     if (returnFunction == false) {
    //         // console.log(list);
    //         // 在这里添加 ajax函数  后端获取到list集合
    //         $.ajax({
    //             url: httptod + "tttjump/deleteymhj",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "tttjump/goymhjpage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         })
    //     }
    // }
}

function ymhjshow() {
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


function downloadymhjfjsc() {
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

function ymhjexportall() {
    window.location.href = httptod + "tttjump/ymhjexportall";
}

function ymhjupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "tttjump/ymhjshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                //把数据渲染到 页面上
                $("#ymhjupdatemodal").modal('show');
                $("#yid").val(res.yid);
                $("#TiMu").val(res.timu);
                $("#AwardName").val(res.awardname);
                $("#DYZZ").val(res.dyzz);
                $("#QTZZ").val(res.qtzz);
                $("#Organizer").val(res.organizer);
                $("#AwardTime").val(res.awardtime);
                $('#RewardSort').combotree('setValue', res.rewardsort);

                $("#RewardSort").val(res.rewardsort);
                $("#Reward").val(res.reward);
                $("#Points").val(res.points);
                $("#Remark").val(res.remark);
                $("#judgestatusdisabled").val(res.judgestatus);

            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function ymhjupdatesubmit() {
    var yid = $("#yid").val();
    var timu = $("#TiMu").val();
    var awardname = $("#AwardName").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var organizer = $("#Organizer").val();
    var awardtime = $("#AwardTime").val();
    var rewardsort = $("#RewardSort").combotree('getText');
    var reward = $("#Reward").val();
    var points = $("#Points").val();
    var remark = $("#Remark").val();
    var judgestatus = $("#judgestatusdisabled").val();
    var fjsc = $("#FJSC")[0].files[0];  //可能为 空  考虑为空的情况下的处理

    if (timu == null || timu == "" || awardname == null || awardname == "" ||
        dyzz == null || dyzz == "" || qtzz == null || qtzz == "" || organizer == "" || organizer == null ||
        awardtime == null || awardtime == "" || rewardsort == null || rewardsort == ""  ||
        points == null || points == "" || yid == null || yid == "") {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "tttjump/ymhjupdate",
            type: "post",
            data: JSON.stringify({
                yid: yid, timu: timu, awardname: awardname, dyzz: dyzz, qtzz: qtzz, organizer: organizer,
                awardtime: awardtime, rewardsort: rewardsort, reward: reward, points: points, remark: remark,
                judgestatus: "1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "tttjump/goymhjpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        //先 添加一下文件 然后传递到数据库
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "tttjump/ymhjupdate",
                    type: "post",
                    data: JSON.stringify({
                        yid: yid, timu: timu, awardname: awardname, dyzz: dyzz, qtzz: qtzz, organizer: organizer,
                        awardtime: awardtime, rewardsort: rewardsort, reward: reward, points: points, remark: remark,
                        judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "tttjump/goymhjpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }
}

function ymhjupdatesubmittemp() {
    var yid = $("#yid").val();
    var timu = $("#TiMu").val();
    var awardname = $("#AwardName").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var organizer = $("#Organizer").val();
    var awardtime = $("#AwardTime").val();
    var rewardsort = $("#RewardSort").combotree('getText');
    var reward = $("#Reward").val();
    var points = $("#Points").val();
    var remark = $("#Remark").val();

    //先 添加一下文件 然后传递到数据库
    $.ajax({
        url: httptod + "tttjump/ymhjupdate",
        type: "post",
        data: JSON.stringify({
            yid: yid, timu: timu, awardname: awardname, dyzz: dyzz, qtzz: qtzz, organizer: organizer,
            awardtime: awardtime, rewardsort: rewardsort, reward: reward, points: points, remark: remark,
            judgestatus: "100"
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            alert(res.description);
            window.location.href = httptod + "tttjump/goymhjpage";
        },
        error: function (res) {
            console.log(res);
        }
    })

}

function putymhj() {
    var timu = $("#TiMu").val();
    var awardname = $("#AwardName").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var organizer = $("#Organizer").val();
    var awardtime = $("#AwardTime").val();
    var rewardsort = $("#RewardSort").combotree('getText');
    var reward = $("#Reward").val();
    var points = $("#Points").val();
    var remark = $("#Remark").val();

    var fjsc = $("#FJSC")[0].files[0];
    if (timu == null || timu == "" || awardname == null || awardname == "" ||
        dyzz == null || dyzz == "" || qtzz == null || qtzz == "" || organizer == "" || organizer == null ||
        awardtime == null || awardtime == "" || rewardsort == null || rewardsort == "" ||
        points == null || points == "" || fjsc == null || fjsc == "") {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                console.log("文件地址是：", data)
                $.ajax({
                    url: httptod + "tttjump/putymhj",
                    type: "post",
                    data: JSON.stringify({
                        timu: timu,
                        awardname: awardname,
                        dyzz: dyzz,
                        qtzz: qtzz,
                        organizer: organizer,
                        awardtime: awardtime,
                        rewardsort: rewardsort,
                        reward: reward,
                        points: points,
                        remark: remark,
                        judgestatus: "1",
                        fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "tttjump/goymhjpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
            error: function (res) {
                console.log("erroe", res);
            }
        })

    }

}


//体育比赛获奖
function gotyhjpage() {
    window.location.href = httptod + "tttjump/gotyhjpage";
}

function gotyhjBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "tttjump/gotyhjpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function gotyhjwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "tttjump/gotyhjwrite";
    }
}

function gotyhjnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "tttjump/gotyhjpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function tyhjdelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "tttjump/deletetyhj",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "tttjump/gotyhjpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var returnFunction = false;
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         list.push($(this).val());
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         console.log(judgestatus);
    //         if (judgestatus == 2 || judgestatus == 3) {
    //             alert("已经通过审核的不可删除");
    //             returnFunction = true;
    //             return false;
    //         }
    //     });
    //     if (list.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         returnFunction = true;
    //     }
    //     if (returnFunction == false) {
    //         // console.log(list);
    //         // 在这里添加 ajax函数  后端获取到list集合
    //         $.ajax({
    //             url: httptod + "tttjump/deletetyhj",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "tttjump/gotyhjpage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         })
    //     }
    // }
}

function tyhjshow() {
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

function tyhjupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "tttjump/tyhjshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                //把数据渲染到 页面上
                $("#tyhjupdatemodal").modal('show');
                $("#tid").val(res.tid);
                $("#Awardee").val(res.awardee);
                $("#Entryname").val(res.entryname);
                $("#WinTime").val(res.wintime);
                $("#CertificateID").val(res.certificateid);
                $('#SportsLV').combotree('setValue', res.sportslv);
                // $("#SportsLV").val(res.sportslv);
                $("#Ranking").val(res.ranking);
                $("#BJBM").val(res.bjbm);
                $("#Points").val(res.points);
                $("#Reward").val(res.reward);
                $("#fq").val(res.fq);
                $("#judgestatusdisabled").val(res.judgestatus);

            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function downloadtyhjfjsc() {
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

function tyhjexportall() {
    window.location.href = httptod + "tttjump/tyhjexportall";
}

function tyhjupdatesubmit() {
    var tid = $("#tid").val();
    var awardee = $("#Awardee").val();
    var entryname = $("#Entryname").val();
    var wintime = $("#WinTime").val();
    var certificateid = $("#CertificateID").val();
    var sportslv = $("#SportsLV").combotree('getText');
    var ranking = $("#Ranking").val();
    var bjbm = $("#BJBM").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var fq = $("#fq").val();

    var judgestatus = $("#judgestatusdisabled").val();
    var fjsc = $("#FJSC")[0].files[0];  //可能为 空  考虑为空的情况下的处理
    if (tid == null || tid == "" || awardee == null || awardee == "" || entryname == null || entryname == "" ||
        wintime == null || wintime == "" || bjbm == null || bjbm == "" || sportslv == "" || sportslv == null ||
        ranking == null || ranking == "" || points == null || points == "" ||  certificateid == "" || certificateid == null) {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "tttjump/tyhjupdate",
            type: "post",
            data: JSON.stringify({
                tid: tid, awardee: awardee, entryname: entryname, wintime: wintime, bjbm: bjbm,
                sportslv: sportslv, ranking: ranking, sportslv: sportslv, points: points, fq: fq,
                reward: reward, certificateid: certificateid, judgestatus: "1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "tttjump/gotyhjpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "tttjump/tyhjupdate",
                    type: "post",
                    data: JSON.stringify({
                        tid: tid, awardee: awardee, entryname: entryname, wintime: wintime, bjbm: bjbm,
                        sportslv: sportslv, ranking: ranking, sportslv: sportslv, points: points, fq: fq,
                        reward: reward, certificateid: certificateid, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "tttjump/gotyhjpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }
}

function tyhjupdatesubmittemp() {
    var tid = $("#tid").val();
    var awardee = $("#Awardee").val();
    var entryname = $("#Entryname").val();
    var wintime = $("#WinTime").val();
    var certificateid = $("#CertificateID").val();
    var sportslv = $("#SportsLV").combotree('getText');
    var ranking = $("#Ranking").val();
    var bjbm = $("#BJBM").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var fq = $("#fq").val();
    $.ajax({
        url: httptod + "tttjump/tyhjupdate",
        type: "post",
        data: JSON.stringify({
            tid: tid, awardee: awardee, entryname: entryname, wintime: wintime, bjbm: bjbm,
            sportslv: sportslv, ranking: ranking, sportslv: sportslv, points: points, fq: fq,
            reward: reward, certificateid: certificateid, judgestatus: "100"
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            alert(res.description);
            window.location.href = httptod + "tttjump/gotyhjpage";
        },
        error: function (res) {
            console.log(res);
        }
    })

}

function puttyhj() {
    var awardee = $("#Awardee").val();
    var entryname = $("#Entryname").val();
    var wintime = $("#WinTime").val();
    var certificateid = $("#CertificateID").val();
    var sportslv = $("#SportsLV").combotree('getText');
    var ranking = $("#Ranking").val();
    var bjbm = $("#BJBM").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var fq = $("#fq").val();
    var fjsc = $("#FJSC")[0].files[0];

    if (fjsc == null || fjsc == "" || awardee == null || awardee == "" || entryname == null || entryname == "" ||
        wintime == null || wintime == "" || bjbm == null || bjbm == "" || sportslv == "" || sportslv == null ||
        ranking == null || ranking == "" || points == null || points == "" || certificateid == "" || certificateid == null) {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                console.log("文件地址是：", data)
                $.ajax({
                    url: httptod + "tttjump/puttyhj",
                    type: "post",
                    data: JSON.stringify({
                        awardee: awardee, entryname: entryname, wintime: wintime, bjbm: bjbm,
                        sportslv: sportslv, ranking: ranking, sportslv: sportslv, points: points, fq: fq,
                        reward: reward, certificateid: certificateid, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "tttjump/gotyhjpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
            error: function (res) {
                console.log("erroe", res);
            }
        })

    }

}


//成果获奖
function gocgpage() {
    window.location.href = httptod + "ttttjump/gocgpage";
}

function gocgwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "ttttjump/gocgwrite";
    }
}

function gocgBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttttjump/gocgpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function gocgnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttttjump/gocgpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function cgdelete() {
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
                url: httptod + "ttttjump/deletecg",
                type: "post",
                data: JSON.stringify(list),
                contentType: "application/json",
                dataType: "json",
                success: function (res) {
                    alert(res.description);
                    window.location.href = httptod + "ttttjump/gocgpage";
                },
                error: function (res) {
                    console.log(res);
                }
            })
        }
    }
}

function cgshow() {
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

            $("#Points2").val(res.points);
            $("#Reward2").val(res.reward);
        },
        error: function (res) {
            console.log(res);
        }
    })

}

function cgupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "ttttjump/cgshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                //把数据渲染到 页面上
                $("#cgupdatemodal").modal('show');
                $("#cid").val(res.cid);
                $("#CGName").val(res.cgname);
                $("#CGType").val(res.cgtype);
                $("#DYZZ").val(res.dyzz);
                $("#QTZZ").val(res.qtzz);
                $("#CNBM").val(res.cnbm);
                $("#CGDZ").val(res.cgdz);
                $("#Time").val(res.time);

                $("#Points").val(res.points);
                $("#Reward").val(res.reward);
                $("#judgestatusdisabled").val(res.judgestatus);

            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function downloadcgfjsc() {
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

function cgexportall() {
    window.location.href = httptod + "ttttjump/cgexportall";
}


function cgupdatesubmit() {
    var cid = $("#cid").val();
    var cgname = $("#CGName").val();
    var cgtype = $("#CGType").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var cnbm = $("#CNBM").val();
    var cgdz = $("#CGDZ").val();
    var time = $("#Time").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var judgestatus = $("#judgestatusdisabled").val();
    var fjsc = $("#FJSC")[0].files[0];  //可能为 空  考虑为空的情况下的处理
    if (cid == null || cid == "" || cgname == null || cgname == "" || cgtype == null || cgtype == "" ||
        dyzz == null || dyzz == "" || qtzz == null || qtzz == "" || cnbm == "" || cnbm == null ||
        cgdz == null || cgdz == "" || time == null || time == "" || reward == "" || reward == null || points == "" || points == null) {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "ttttjump/cgupdate",
            type: "post",
            data: JSON.stringify({
                cgname: cgname, cgtype: cgtype, dyzz: dyzz, qtzz: qtzz, cnbm: cnbm,
                cgdz: cgdz, time: time, points: points, reward: reward, judgestatus: "1", cid: cid
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttttjump/gocgpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        //先 添加一下文件 然后传递到数据库
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "ttttjump/cgupdate",
                    type: "post",
                    data: JSON.stringify({
                        cgname: cgname, cgtype: cgtype, dyzz: dyzz, qtzz: qtzz, cnbm: cnbm,
                        cgdz: cgdz, time: time, points: points, reward: reward, judgestatus: "1", cid: cid,
                        fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttttjump/gocgpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }
}

function cgupdatesubmittemp() {
    var cid = $("#cid").val();
    var cgname = $("#CGName").val();
    var cgtype = $("#CGType").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var cnbm = $("#CNBM").val();
    var cgdz = $("#CGDZ").val();
    var time = $("#Time").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();

    //先 添加一下文件 然后传递到数据库
    $.ajax({
        url: httptod + "ttttjump/cgupdate",
        type: "post",
        data: JSON.stringify({
            cgname: cgname, cgtype: cgtype, dyzz: dyzz, qtzz: qtzz, cnbm: cnbm,
            cgdz: cgdz, time: time, points: points, reward: reward, judgestatus: "100", cid: cid
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            alert(res.description);
            window.location.href = httptod + "ttttjump/gocgpage";
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function putcg() {
    var cgname = $("#CGName").val();
    var cgtype = $("#CGType").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var cnbm = $("#CNBM").val();
    var cgdz = $("#CGDZ").val();
    var time = $("#Time").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var fjsc = $("#FJSC")[0].files[0];
    if (fjsc == null || fjsc == "" || cgname == null || cgname == "" || cgtype == null || cgtype == "" ||
        dyzz == null || dyzz == "" || qtzz == null || qtzz == "" || cnbm == "" || cnbm == null ||
        cgdz == null || cgdz == "" || time == null || time == "" || reward == "" || reward == null || points == "" || points == null) {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                console.log("文件地址是：", data)
                $.ajax({
                    url: httptod + "ttttjump/putcg",
                    type: "post",
                    data: JSON.stringify({
                        cgname: cgname, cgtype: cgtype, dyzz: dyzz, qtzz: qtzz, cnbm: cnbm,
                        cgdz: cgdz, time: time, points: points, reward: reward, judgestatus: "1", fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttttjump/gocgpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
            error: function (res) {
                console.log("error", res);
            }
        })

    }

}

//结项验收
function gojxyspage() {
    window.location.href = httptod + "ttttjump/gojxyspage";
}

function gojxyswrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "ttttjump/gojxyswrite";
    }
}

function gojxysBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttttjump/gojxyspage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function gojxysnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttttjump/gojxyspage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function jxysdelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "ttttjump/deletejxys",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttttjump/gojxyspage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var returnFunction = false;
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         list.push($(this).val());
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         console.log(judgestatus);
    //         if (judgestatus == 2 || judgestatus == 3) {
    //             alert("已经通过审核的不可删除");
    //             returnFunction = true;
    //             return false;
    //         }
    //     });
    //     if (list.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         returnFunction = true;
    //     }
    //     if (returnFunction == false) {
    //         // console.log(list);
    //         // 在这里添加 ajax函数  后端获取到list集合
    //         $.ajax({
    //             url: httptod + "ttttjump/deletejxys",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "ttttjump/gojxyspage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         })
    //     }
    // }
}


function jxysshow() {
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

function jxysupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "ttttjump/jxysshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                //把数据渲染到 页面上
                $("#jxysupdatemodal").modal('show');
                $("#jid").val(res.jid);
                $("#xmname").val(res.xmname);
                $("#pronumber").val(res.pronumber);
                // $("#xmly").val(res.xmly);
                $('#xmly').combotree('setValue', res.xmly);
                $("#rank").val(res.rank);
                $("#hoster").val(res.hoster);
                $("#xdbm").val(res.xdbm);
                $("#cyry").val(res.cyry);
                $("#reward").val(res.reward);
                $("#time").val(res.time);
                $("#proproperty").val(res.proproperty);
                $("#points").val(res.points);
                $("#remark").val(res.remark);
                $("#judgestatusdisabled").val(res.judgestatus);

            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function downloadjxysfjsc() {
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

function jxysexportall() {
    window.location.href = httptod + "ttttjump/jxysexportall";
}


function jxysupdatesubmit() {
    var jid = $("#jid").val();
    var xmname = $("#xmname").val();
    var pronumber = $("#pronumber").val();
    // var xmly = $("#xmly").val();
    var xmly = $("#xmly").combotree('getText');
    var rank = $("#rank").val();
    var hoster = $("#hoster").val();
    var xdbm = $("#xdbm").val();
    var cyry = $("#cyry").val();
    var reward = $("#reward").val();
    var time = $("#time").val();
    var points = $("#points").val();
    var proproperty = $("#proproperty").val();
    var remark = $("#remark").val();
    var judgestatus = $("#judgestatusdisabled").val();
    var fjsc = $("#FJSC")[0].files[0];  //可能为 空  考虑为空的情况下的处理
    if (jid == null || jid == "" || xmname == null || xmname == "" || pronumber == null || pronumber == "" || xmly == null || xmly == "" ||
        rank == null || rank == "" || hoster == null || hoster == "" || proproperty == null || proproperty == "" || xdbm == "" || xdbm == null ||
        cyry == null || cyry == "" || time == "" || time == null || points == "" || points == null) {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "ttttjump/jxysupdate",
            type: "post",
            data: JSON.stringify({
                xmname: xmname, pronumber: pronumber, xmly: xmly, rank: rank, hoster: hoster, proproperty: proproperty,
                xdbm: xdbm, cyry: cyry, reward: reward, time: time, points: points, remark: remark, judgestatus: "1",
                jid: jid
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttttjump/gojxyspage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        //先 添加一下文件 然后传递到数据库
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "ttttjump/jxysupdate",
                    type: "post",
                    data: JSON.stringify({
                        xmname: xmname,
                        pronumber: pronumber,
                        xmly: xmly,
                        rank: rank,
                        hoster: hoster,
                        proproperty: proproperty,
                        xdbm: xdbm,
                        cyry: cyry,
                        reward: reward,
                        time: time,
                        points: points,
                        remark: remark,
                        judgestatus: "1",
                        fjsc: data,
                        jid: jid

                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttttjump/gojxyspage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }
}


function putjxys() {
    var xmname = $("#xmname").val();
    var pronumber = $("#pronumber").val();
    // var xmly = $("#xmly").val();
    var xmly = $("#xmly").combotree('getText');

    var rank = $("#rank").val();
    var hoster = $("#hoster").val();
    var xdbm = $("#xdbm").val();
    var cyry = $("#cyry").val();
    var reward = $("#reward").val();
    var time = $("#time").val();
    var points = $("#points").val();
    var proproperty = $("#proproperty").val();
    var remark = $("#remark").val();
    var fjsc = $("#FJSC")[0].files[0];
    if (xmname == null || xmname == "" || pronumber == null || pronumber == "" || proproperty == null || proproperty == "" || xmly == null || xmly == "" ||
        rank == null || rank == "" || hoster == null || hoster == "" || xdbm == "" || xdbm == null ||
        cyry == null || cyry == "" ||  time == "" || time == null || points == "" || points == null
        || fjsc == "" || fjsc == null) {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "ttttjump/putjxys",
                    type: "post",
                    data: JSON.stringify({
                        xmname: xmname,
                        pronumber: pronumber,
                        xmly: xmly,
                        proproperty: proproperty,
                        rank: rank,
                        hoster: hoster,
                        xdbm: xdbm,
                        cyry: cyry,
                        reward: reward,
                        time: time,
                        points: points,
                        remark: remark,
                        judgestatus: "1",
                        fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttttjump/gojxyspage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
            error: function (res) {
                console.log("error", res);
            }
        })

    }

}


//纵向立项
function gohpxmpage() {
    window.location.href = httptod + "ttttjump/gohpxmpage";
}

function gohpxmwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "ttttjump/gohpxmwrite";
    }
}

function gohpxmBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttttjump/gohpxmpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function gohpxmnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttttjump/gohpxmpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function hpxmdelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "ttttjump/deletehpxm",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttttjump/gohpxmpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var returnFunction = false;
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         list.push($(this).val());
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         console.log(judgestatus);
    //         if (judgestatus == 2 || judgestatus == 3) {
    //             alert("已经通过审核的不可删除");
    //             returnFunction = true;
    //             return false;
    //         }
    //     });
    //     if (list.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         returnFunction = true;
    //     }
    //     if (returnFunction == false) {
    //         // console.log(list);
    //         // 在这里添加 ajax函数  后端获取到list集合
    //         $.ajax({
    //             url: httptod + "ttttjump/deletehpxm",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "ttttjump/gohpxmpage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         })
    //     }
    // }
}


function hpxmshow() {
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

function hpxmupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "ttttjump/hpxmshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                //把数据渲染到 页面上
                $("#hpxmupdatemodal").modal('show');
                $("#hid").val(res.hid);
                $("#xmname").val(res.xmname);
                $("#pronumber").val(res.pronumber);
                $("#wbjf").val(res.wbjf);
                $("#ptjf").val(res.ptjf);
                $("#xdbm").val(res.xdbm);
                $('#xmsource').combotree('setValue', res.xmsource);

                // $("#xmsource").val(res.xmsource);
                $("#prorank").val(res.prorank);
                $("#proproperty").val(res.proproperty);
                $("#hoster").val(res.hoster);
                $("#reward").val(res.reward);
                $("#points").val(res.points);
                $("#cyry").val(res.cyry);
                $("#remark").val(res.remark);
                $("#lxtime").val(res.lxtime);
                $("#judgestatusdisabled").val(res.judgestatus);

            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function downloadhpxmfjsc() {
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

function hpxmexportall() {
    window.location.href = httptod + "ttttjump/hpxmexportall";
}


function hpxmupdatesubmit() {
    var hid = $("#hid").val();
    var xmname = $("#xmname").val();
    var pronumber = $("#pronumber").val();
    var wbjf = $("#wbjf").val();
    var ptjf = $("#ptjf").val();
    var xdbm = $("#xdbm").val();
    // var xmsource = $("#xmsource").val();
    var xmsource = $("#xmsource").combotree('getText');

    var prorank = $("#prorank").val();
    var proproperty = $("#proproperty").val();
    var hoster = $("#hoster").val();
    var prorank = $("#prorank").val();
    var reward = $("#reward").val();
    var points = $("#points").val();
    var cyry = $("#cyry").val();
    var remark = $("#remark").val();
    var lxtime = $("#lxtime").val();
    var judgestatus = $("#judgestatusdisabled").val();
    var fjsc = $("#FJSC")[0].files[0];  //可能为 空  考虑为空的情况下的处理
    if (hid == null || hid == "" || xmname == null || xmname == "" || pronumber == null || pronumber == "" || wbjf == null || wbjf == "" || ptjf == null || ptjf == "" ||
        xdbm == null || xdbm == "" || xmsource == null || xmsource == "" || prorank == null || prorank == "" || proproperty == "" || proproperty == null ||
        hoster == null || hoster == "" || prorank == null || prorank == "" ||   points == "" || points == null
        || cyry == "" || cyry == null ||  lxtime == "" || lxtime == null) {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "ttttjump/hpxmupdate",
            type: "post",
            data: JSON.stringify({
                xmname: xmname,
                pronumber: pronumber,
                wbjf: wbjf,
                ptjf: ptjf,
                xmsource: xmsource,
                prorank: prorank,
                xdbm: xdbm,
                proproperty: proproperty,
                hoster: hoster,
                prorank: prorank,
                reward: reward,
                points: points,
                cyry: cyry,
                remark: remark,
                lxtime: lxtime,
                judgestatus: "1",
                hid: hid
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttttjump/gohpxmpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        //先 添加一下文件 然后传递到数据库
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "ttttjump/hpxmupdate",
                    type: "post",
                    data: JSON.stringify({
                        xmname: xmname,
                        pronumber: pronumber,
                        wbjf: wbjf,
                        ptjf: ptjf,
                        xmsource: xmsource,
                        prorank: prorank,
                        xdbm: xdbm,
                        proproperty: proproperty,
                        hoster: hoster,
                        prorank: prorank,
                        reward: reward,
                        points: points,
                        cyry: cyry,
                        remark: remark,
                        lxtime: lxtime,
                        judgestatus: "1",
                        fjsc: data,
                        hid: hid

                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttttjump/gohpxmpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }
}


function puthpxm() {
    var xmname = $("#xmname").val();
    var pronumber = $("#pronumber").val();
    var wbjf = $("#wbjf").val();
    var ptjf = $("#ptjf").val();
    var xdbm = $("#xdbm").val();
    // var xmsource = $("#xmsource").val();
    var xmsource = $("#xmsource").combotree('getText');

    var prorank = $("#prorank").val();
    var proproperty = $("#proproperty").val();
    var hoster = $("#hoster").val();
    var prorank = $("#prorank").val();
    var reward = $("#reward").val();
    var points = $("#points").val();
    var cyry = $("#cyry").val();
    var remark = $("#remark").val();
    var lxtime = $("#lxtime").val();
    var fjsc = $("#FJSC")[0].files[0];
    if (xmname == null || xmname == "" || pronumber == null || pronumber == "" || wbjf == null || wbjf == "" || ptjf == null || ptjf == ""
        || xdbm == null || xdbm == "" || xmsource == null || xmsource == "" || prorank == null || prorank == ""
        || proproperty == "" || proproperty == null || hoster == null || hoster == ""
        || prorank == null || prorank == "" ||  points == "" || points == null || cyry == "" || cyry == null
        ||  lxtime == "" || lxtime == null || fjsc == "" || fjsc == null) {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "ttttjump/puthpxm",
                    type: "post",
                    data: JSON.stringify({
                        xmname: xmname,
                        pronumber: pronumber,
                        wbjf: wbjf,
                        ptjf: ptjf,
                        xmsource: xmsource,
                        prorank: prorank,
                        xdbm: xdbm,
                        proproperty: proproperty,
                        hoster: hoster,
                        prorank: prorank,
                        reward: reward,
                        points: points,
                        cyry: cyry,
                        remark: remark,
                        lxtime: lxtime,
                        judgestatus: "1",
                        fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttttjump/gohpxmpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
            error: function (res) {
                console.log("error", res);
            }
        })

    }

}


//成果转化
function gocgzhpage() {
    window.location.href = httptod + "ttttjump/gocgzhpage";
}

function gocgzhwrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "ttttjump/gocgzhwrite";
    }
}

function gocgzhBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttttjump/gocgzhpage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function gocgzhnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttttjump/gocgzhpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function cgzhdelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "ttttjump/deletecgzh",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttttjump/gocgzhpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var returnFunction = false;
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         list.push($(this).val());
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         console.log(judgestatus);
    //         if (judgestatus == 2 || judgestatus == 3) {
    //             alert("已经通过审核的不可删除");
    //             returnFunction = true;
    //             return false;
    //         }
    //     });
    //     if (list.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         returnFunction = true;
    //     }
    //     if (returnFunction == false) {
    //         // console.log(list);
    //         // 在这里添加 ajax函数  后端获取到list集合
    //         $.ajax({
    //             url: httptod + "ttttjump/deletecgzh",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "ttttjump/gocgzhpage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         })
    //     }
    // }
}


function cgzhshow() {
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

function cgzhupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "ttttjump/cgzhshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                //把数据渲染到 页面上
                $("#cgzhupdatemodal").modal('show');
                $("#cid").val(res.cid);
                $("#cgname").val(res.cgname);
                $("#zrlx").val(res.zrlx);
                $("#zrdzjf").val(res.zrdzjf);
                $("#dyzz").val(res.dyzz);
                $("#qtzz").val(res.qtzz);
                $("#zhdw").val(res.zhdw);
                $("#zhtime").val(res.zhtime);
                $("#reward").val(res.reward);
                $("#points").val(res.points);
                $("#fq").val(res.fq);
                $("#judgestatusdisabled").val(res.judgestatus);

            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function downloadcgzhfjsc() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus != '100') {
            list.push($(this).val());
        }
    });
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
            }
        })
    }
}

function cgzhexportall() {
    window.location.href = httptod + "ttttjump/cgzhexportall";
}


function cgzhupdatesubmit() {
    var cid = $("#cid").val();
    var cgname = $("#cgname").val();
    var zrlx = $("#zrlx").val();
    var zrdzjf = $("#zrdzjf").val();
    var dyzz = $("#dyzz").val();
    var qtzz = $("#qtzz").val();
    var zhdw = $("#zhdw").val();
    var zhtime = $("#zhtime").val();
    var reward = $("#reward").val();
    var points = $("#points").val();
    var fq = $("#fq").val();
    var judgestatus = $("#judgestatusdisabled").val();
    var fjsc = $("#FJSC")[0].files[0];
    if (cid == null || cid == "" || cgname == null || cgname == "" || zrlx == null || zrlx == "" || zrdzjf == null || zrdzjf == ""
        || dyzz == null || dyzz == "" || qtzz == null || qtzz == "" || zhdw == null || zhdw == "" || zhtime == null
        || zhtime == "" ||  points == "" || points == null) {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "ttttjump/cgzhupdate",
            type: "post",
            data: JSON.stringify({
                cgname: cgname, zrlx: zrlx, zrdzjf: zrdzjf, dyzz: dyzz, qtzz: qtzz, zhdw: zhdw, zhtime: zhtime, fq: fq,
                reward: reward, points: points, judgestatus: "1", cid: cid
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttttjump/gocgzhpage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        //先 添加一下文件 然后传递到数据库
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            async: false,
            success: function (data) {
                $.ajax({
                    url: httptod + "ttttjump/cgzhupdate",
                    type: "post",
                    data: JSON.stringify({
                        cgname: cgname,
                        zrlx: zrlx,
                        zrdzjf: zrdzjf,
                        dyzz: dyzz,
                        qtzz: qtzz,
                        zhdw: zhdw,
                        zhtime: zhtime,
                        fq: fq,
                        reward: reward,
                        points: points,
                        judgestatus: "1",
                        cid: cid,
                        fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttttjump/gocgzhpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }
}


function putcgzh() {
    var cgname = $("#cgname").val();
    var zrlx = $("#zrlx").val();
    var zrdzjf = $("#zrdzjf").val();
    var dyzz = $("#dyzz").val();
    var qtzz = $("#qtzz").val();
    var zhdw = $("#zhdw").val();
    var zhtime = $("#zhtime").val();
    var reward = $("#reward").val();
    var points = $("#points").val();
    var fq = $("#fq").val();
    var fjsc = $("#FJSC")[0].files[0];
    if (cgname == null || cgname == "" || zrlx == null || zrlx == "" || zrdzjf == null || zrdzjf == ""
        || dyzz == null || dyzz == "" || qtzz == null || qtzz == "" ||
        zhdw == null || zhdw == "" || zhtime == null || zhtime == ""
        || points == "" || points == null || fjsc == "" || fjsc == null) {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            // dataType: "json",
            //返回 string对象时候 就不能够再要求返回的是一个json对象了  json是对象
            async: false,
            //默认为true  会不等待返回结果 就继续执行下去  设置后false 可以等待返回数据后 在执行 这里影响不大
            success: function (data) {
                $.ajax({
                    url: httptod + "ttttjump/putcgzh",
                    type: "post",
                    data: JSON.stringify({
                        cgname: cgname,
                        zrlx: zrlx,
                        zrdzjf: zrdzjf,
                        dyzz: dyzz,
                        qtzz: qtzz,
                        zhdw: zhdw,
                        zhtime: zhtime,
                        fq: fq,
                        reward: reward,
                        points: points,
                        judgestatus: "1",
                        fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttttjump/gocgzhpage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
            error: function (res) {
                console.log("error", res);
            }
        })

    }

}

//智库建设
function gozkjspage() {
    window.location.href = httptod + "ttttjump/gozkjspage";
}

function gozkjswrite() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
        window.location.href = httptod + "ttttjump/gozkjswrite";
    }
}

function gozkjsBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttttjump/gozkjspage?start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function gozkjsnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ttttjump/gozkjspage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function zkjsdelete() {
    let list=getSelect();
    console.log(list);
    if (list!=false){
        $.ajax({
            url: httptod + "ttttjump/deletezkjs",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttttjump/gozkjspage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
    // var flag = confirm("确认要删除吗?请谨慎选择");
    // if (flag) {
    //     var list = new Array();
    //     var returnFunction = false;
    //     $.each($('input[name="selected"]:checkbox:checked'), function () {
    //         list.push($(this).val());
    //         var row = $(this).parents("tr");
    //         var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
    //         console.log(judgestatus);
    //         if (judgestatus == 2 || judgestatus == 3) {
    //             alert("已经通过审核的不可删除");
    //             returnFunction = true;
    //             return false;
    //         }
    //     });
    //     if (list.length == 0) {
    //         alert("未选中,无法执行删除操作");
    //         returnFunction = true;
    //     }
    //     if (returnFunction == false) {
    //         // console.log(list);
    //         // 在这里添加 ajax函数  后端获取到list集合
    //         $.ajax({
    //             url: httptod + "ttttjump/deletezkjs",
    //             type: "post",
    //             data: JSON.stringify(list),
    //             contentType: "application/json",
    //             dataType: "json",
    //             success: function (res) {
    //                 alert(res.description);
    //                 window.location.href = httptod + "ttttjump/gozkjspage";
    //             },
    //             error: function (res) {
    //                 console.log(res);
    //             }
    //         })
    //     }
    // }
}


function zkjsshow() {
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

function zkjsupdate() {
    var time = getsystime();
    if (time.status == 0) {
        alert(time.description)
    } else {
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
            url: httptod + "ttttjump/zkjsshow",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                $("#zkjsupdatemodal").modal('show');
                $("#zid").val(res.zid);
                $("#cgname").val(res.cgname);
                $("#cnjb").val(res.cnjb);
                $("#dyzz").val(res.dyzz);
                $("#qtzz").val(res.qtzz);
                $("#cnbm").val(res.cnbm);
                $("#cntime").val(res.cntime);
                $("#reward").val(res.reward);
                $("#points").val(res.points);
                $("#fq").val(res.fq);
                $("#judgestatusdisabled").val(res.judgestatus);
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function downloadzkjsfjsc() {
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

function zkjsexportall() {
    window.location.href = httptod + "ttttjump/zkjsexportall";
}


function zkjsupdatesubmit() {
    var zid = $("#zid").val();
    var cgname = $("#cgname").val();
    var cnjb = $("#cnjb").val();
    var dyzz = $("#dyzz").val();
    var qtzz = $("#qtzz").val();
    var cnbm = $("#cnbm").val();
    var cntime = $("#cntime").val();
    var reward = $("#reward").val();
    var points = $("#points").val();
    var fq = $("#fq").val();
    var judgestatus = $("#judgestatusdisabled").val();
    var fjsc = $("#FJSC")[0].files[0];
    if (zid == null || zid == "" || cgname == null || cgname == "" || cnjb == null || cnjb == "" || dyzz == null ||
        dyzz == "" || qtzz == null || qtzz == "" || cnbm == null || cnbm == "" || cntime == null || cntime == "" ||
        points == "" || points == null) {
        alert("请填写完整！");
    } else if (fjsc == null && judgestatus == '100') {
        alert("待完善内容上传前，请务必上传文件")
    } else if (fjsc == null) {
        $.ajax({
            url: httptod + "ttttjump/zkjsupdate",
            type: "post",
            data: JSON.stringify({
                cgname: cgname, cnjb: cnjb, dyzz: dyzz, qtzz: qtzz, cnbm: cnbm, cntime: cntime, reward: reward, fq: fq,
                points: points, judgestatus: "1", zid: zid
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ttttjump/gozkjspage";
            },
            error: function (res) {
                console.log(res);
            }
        })
    } else {
        //先 添加一下文件 然后传递到数据库
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        console.log(filetype);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            async: false,
            success: function (data) {
                $.ajax({
                    url: httptod + "ttttjump/zkjsupdate",
                    type: "post",
                    data: JSON.stringify({
                        cgname: cgname,
                        cnjb: cnjb,
                        dyzz: dyzz,
                        qtzz: qtzz,
                        cnbm: cnbm,
                        cntime: cntime,
                        reward: reward,
                        fq: fq,
                        points: points,
                        judgestatus: "1",
                        zid: zid,
                        fjsc: data,
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttttjump/gozkjspage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            }
        })
    }
}


function putzkjs() {
    var cgname = $("#cgname").val();
    var cnjb = $("#cnjb").val();
    var dyzz = $("#dyzz").val();
    var qtzz = $("#qtzz").val();
    var cnbm = $("#cnbm").val();
    var cntime = $("#cntime").val();
    var reward = $("#reward").val();
    var points = $("#points").val();
    var fq = $("#fq").val();
    var fjsc = $("#FJSC")[0].files[0];
    if (cgname == null || cgname == "" || cnjb == null || cnjb == "" || dyzz == null || dyzz == "" || qtzz == null || qtzz == ""
        || cnbm == null || cnbm == "" || cntime == null || cntime == "" || points == "" || points == null
        || fjsc == "" || fjsc == null) {
        alert("请填写完整且文件必须上传");
    } else {
        var formdata = new FormData();
        formdata.append("fjsc", fjsc);
        var filetype = getFileType(fjsc.name);
        if (filetype != "pdf") {
            alert("文件格式错误");
            return;
        }
        if (fjsc.size >= 20 * 1024 * 1024) {
            alert("文件超过20M");
            return;
        }
        $.ajax({
            url: httptod + "file/uploadfjsc",
            type: "post",
            data: formdata,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            async: false,
            success: function (data) {
                $.ajax({
                    url: httptod + "ttttjump/putzkjs",
                    type: "post",
                    data: JSON.stringify({
                        cgname: cgname,
                        cnjb: cnjb,
                        dyzz: dyzz,
                        qtzz: qtzz,
                        cnbm: cnbm,
                        cntime: cntime,
                        reward: reward,
                        points: points,
                        fq: fq,
                        judgestatus: "1",
                        fjsc: data
                    }),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (res) {
                        alert(res.description);
                        window.location.href = httptod + "ttttjump/gozkjspage";
                    },
                    error: function (res) {
                        console.log(res);
                    }
                })
            },
            error: function (res) {
                console.log("error", res);
            }
        })

    }

}

//通用函数   不需要管
function getFileType(filePath) {
    var startIndex = filePath.lastIndexOf(".");
    if (startIndex != -1)
        return filePath.substring(startIndex + 1, filePath.length).toLowerCase();
    else return "";
}


