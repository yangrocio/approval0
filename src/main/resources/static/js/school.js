

function goSNextPageMessage() {
    var department = $("#department").val();
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
    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    return arr;
}

//会议论文-------分页跳转条件读取
function hylwSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var tm=$("#TM8").val();
    var dyzz = $("#DYZZ8").val();
    var qtzz = $("#QTZZ8").val();
    var hymc = $("#HYMC8").val();
    var hysj = $("#HYSJ8").val();
    var hyjb = $("#HYJB8").val();
    var jllb = $("#JLLB8").val();

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (tm == '') {
        tm = "-1";
    }
    if (dyzz == '') {
        dyzz = "-1";
    }
    if (qtzz == '') {
        qtzz = "-1";
    }
    if (hymc == '') {
        hymc = "-1";
    }
    if (hysj == '') {
        hysj = "-1";
    }
    if (hyjb == '') {
        hyjb = "-1";
    }
    if (jllb == '') {
        jllb = "-1";
    }
    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(tm);
    arr.push(dyzz);
    arr.push(qtzz);
    arr.push(hymc);
    arr.push(hysj);
    arr.push(hyjb);
    arr.push(jllb);
    return arr;
}

//期刊论文-------分页跳转条件读取
function qklwSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var timu=$("#TiMu8").val();
    var dyzz = $("#DYZZ8").val();
    var qtzz = $("#QTZZ8").val();
    var type = $("#Type8").val();
    var fbqk = $("#QiKanName8").val();
    var time = $("#Time8").val();
    var fq = $("#FQ8").val();

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (timu == '') {
        timu = "-1";
    }
    if (dyzz == '') {
        dyzz = "-1";
    }
    if (qtzz == '') {
        qtzz = "-1";
    }
    if (type == '') {
        type = "-1";
    }
    if (fbqk == '') {
        fbqk = "-1";
    }
    if (time == '') {
        time = "-1";
    }
    if (fq == '') {
        fq = "-1";
    }
    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(timu);
    arr.push(dyzz);
    arr.push(qtzz);
    arr.push(type);
    arr.push(fbqk);
    arr.push(time);
    arr.push(fq);
    return arr;
}

//学术著作-------分页跳转条件读取
function xszzSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var timu=$("#TiMu8").val();
    var dyzz = $("#DYZZ8").val();
    var qtzz = $("#QTZZ8").val();
    var cbstype = $("#CBSType8").val();
    var zztype = $("#zzType8").val();
    var times = $("#Times8").val();

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (timu == '') {
        timu = "-1";
    }
    if (dyzz == '') {
        dyzz = "-1";
    }
    if (qtzz == '') {
        qtzz = "-1";
    }
    if (cbstype == '') {
        cbstype = "-1";
    }
    if (zztype == '') {
        zztype = "-1";
    }
    if (times == '') {
        times = "-1";
    }

    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(timu);
    arr.push(dyzz);
    arr.push(qtzz);
    arr.push(cbstype);
    arr.push(zztype);
    arr.push(times);
    return arr;
}

//纵向立项-------分页跳转条件读取
function hpxmSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var xmname = $("#xmname8").val();
    var hoster = $("#hoster8").val();
    var cyry = $("#cyry8").val();
    var xdbm = $("#xdbm8").val();
    var xmsource = $("#xmsource8").val();
    var prorank = $("#prorank8").val();
    var proproperty = $("#proproperty8").val();
    var lxtime = $("#lxtime8").val();

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (hoster == '') {
        hoster = "-1";
    }
    if (cyry == '') {
        cyry = "-1";
    }
    if (xdbm == '') {
        xdbm = "-1";
    }
    if (xmsource == '') {
        xmsource = "-1";
    }
    if (prorank == '') {
        prorank = "-1";
    }
    if (proproperty == '') {
        proproperty = "-1";
    }
    if (lxtime == '') {
        lxtime = "-1";
    }

    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(xmname);
    arr.push(hoster);
    arr.push(cyry);
    arr.push(xdbm);
    arr.push(xmsource);
    arr.push(prorank);
    arr.push(proproperty);
    arr.push(lxtime);
    return arr;
}

//纵向结项-------分页跳转条件读取
function jxysSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var xmname = $("#xmname8").val();
    var hoster = $("#hoster8").val();
    var cyry = $("#cyry8").val();
    var xdbm = $("#xdbm8").val();
    var xmly = $("#xmly8").val();
    var rank = $("#rank8").val();
    var proproperty = $("#proproperty8").val();
    var time = $("#time8").val();

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (hoster == '') {
        hoster = "-1";
    }
    if (cyry == '') {
        cyry = "-1";
    }
    if (xdbm == '') {
        xdbm = "-1";
    }
    if (xmly == '') {
        xmly = "-1";
    }
    if (rank == '') {
        rank = "-1";
    }
    if (proproperty == '') {
        proproperty = "-1";
    }
    if (time == '') {
        time = "-1";
    }

    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(xmname);
    arr.push(hoster);
    arr.push(cyry);
    arr.push(xdbm);
    arr.push(xmly);
    arr.push(rank);
    arr.push(proproperty);
    arr.push(time);
    return arr;
}

//横向立项-------分页跳转条件读取
function hxlxSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var xmname = $("#XMName8").val();
    var principal = $("#Principal8").val();
    var aunit = $("#AUnit8").val();
    var zje = $("#ZJE8").val();
    var cn = $("#ContractNumber8").val();
    var qdtime = $("#QDTime8").val();


    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (principal == '') {
        principal = "-1";
    }
    if (aunit == '') {
        aunit = "-1";
    }
    if (zje == '') {
        zje = "-1";
    }
    if (cn == '') {
        cn = "-1";
    }
    if (qdtime == '') {
        qdtime = "-1";
    }

    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(xmname);
    arr.push(principal);
    arr.push(aunit);
    arr.push(zje);
    arr.push(cn);
    arr.push(qdtime);
    return arr;
}

//横向项目-------分页跳转条件读取
function hxxmSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var xmname = $("#XMName8").val();
    var principal = $("#Principal8").val();
    var aunit = $("#AUnit8").val();
    var zje = $("#ZJE8").val();
    var cn = $("#ContractNumber8").val();
    var jxtime = $("#JXTime8").val();


    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (principal == '') {
        principal = "-1";
    }
    if (aunit == '') {
        aunit = "-1";
    }
    if (zje == '') {
        zje = "-1";
    }
    if (cn == '') {
        cn = "-1";
    }
    if (jxtime == '') {
        jxtime = "-1";
    }

    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(xmname);
    arr.push(principal);
    arr.push(aunit);
    arr.push(zje);
    arr.push(cn);
    arr.push(jxtime);
    return arr;
}

//软件著作-------分页跳转条件读取
function rjzzSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var xmname = $("#XMName8").val();
    var dyzz = $("#DYZZ8").val();
    var qtzz = $("#QTZZ8").val();
    var djnumber = $("#DJNumber8").val();
    var djtime = $("#DJTime8").val();

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (dyzz == '') {
        dyzz = "-1";
    }
    if (qtzz == '') {
        qtzz = "-1";
    }
    if (djnumber == '') {
        djnumber = "-1";
    }
    if (djtime == '') {
        djtime = "-1";
    }

    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(xmname);
    arr.push(dyzz);
    arr.push(qtzz);
    arr.push(djnumber);
    arr.push(djtime);
    return arr;
}

//获批专利-------分页跳转条件读取
function hpzlSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var xmname = $("#XMName8").val();
    var fmr = $("#FMR8").val();
    var qtfmr = $("#QTFMR8").val();
    var zlnumber = $("#ZLNumber8").val();
    var ptype = $("#PType8").val();
    var sytime = $("#SYTime8").val();

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (fmr == '') {
        fmr = "-1";
    }
    if (qtfmr == '') {
        qtfmr = "-1";
    }
    if (zlnumber == '') {
        zlnumber = "-1";
    }
    if (ptype == '') {
        ptype = "-1";
    }
    if (sytime == '') {
        sytime = "-1";
    }
    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(xmname);
    arr.push(fmr);
    arr.push(qtfmr);
    arr.push(zlnumber);
    arr.push(ptype);
    arr.push(sytime);
    return arr;
}

//学术称号-------分页跳转条件读取
function xschSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var xmname = $("#XMName8").val();
    var title = $("#Title8").val();
    var rank = $("#Rank8").val();
    var xfbm = $("#XFBM8").val();
    var time = $("#Time8").val();

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (title == '') {
        title = "-1";
    }
    if (rank == '') {
        rank = "-1";
    }
    if (xfbm == '') {
        xfbm = "-1";
    }
    if (time == '') {
        time = "-1";
    }
    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(xmname);
    arr.push(title);
    arr.push(rank);
    arr.push(xfbm);
    arr.push(time);
    return arr;
}

//学术交流-------分页跳转条件读取
function xsjlSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var xmname = $("#XMName8").val();
    var cyry = $("#CYRY8").val();
    var rank = $("#Rank8").val();

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (cyry == '') {
        cyry = "-1";
    }
    if (rank == '') {
        rank = "-1";
    }

    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(xmname);
    arr.push(cyry);
    arr.push(rank);
    return arr;
}

//获批奖励-------分页跳转条件读取
function hpjlSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var cgname = $("#CGName8").val();
    var jlname = $("#JLName8").val();
    var dyhjr = $("#DYHJR8").val();
    var qthjr = $("#QTHJR8").val();
    var jixdbm = $("#JIXDBM8").val();
    var jlrank = $("#JLRank8").val();
    var hjtime = $("#HJTime8").val();

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (cgname == '') {
        cgname = "-1";
    }
    if (jlname == '') {
        jlname = "-1";
    }
    if (dyhjr == '') {
        dyhjr = "-1";
    }
    if (qthjr == '') {
        qthjr = "-1";
    }
    if (jixdbm == '') {
        jixdbm = "-1";
    }
    if (jlrank == '') {
        jlrank = "-1";
    }
    if (hjtime == '') {
        hjtime = "-1";
    }

    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(cgname);
    arr.push(jlname);
    arr.push(dyhjr);
    arr.push(qthjr);
    arr.push(jixdbm);
    arr.push(jlrank);
    arr.push(hjtime);
    return arr;
}
//音美获奖-------分页跳转条件读取
function ymhjSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var timu = $("#TiMu8").val();
    var aname = $("#AwardName8").val();
    var dyzz = $("#DYZZ8").val();
    var qtzz = $("#QTZZ8").val();
    var org = $("#Organizer8").val();
    var rsort = $("#RewardSort8").val();
    var atime = $("#AwardTime8").val();

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (timu == '') {
        timu = "-1";
    }
    if (aname == '') {
        aname = "-1";
    }
    if (dyzz == '') {
        dyzz = "-1";
    }
    if (qtzz == '') {
        qtzz = "-1";
    }
    if (org == '') {
        org = "-1";
    }
    if (rsort == '') {
        rsort = "-1";
    }
    if (atime == '') {
        atime = "-1";
    }

    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(timu);
    arr.push(aname);
    arr.push(dyzz);
    arr.push(qtzz);
    arr.push(org);
    arr.push(rsort);
    arr.push(atime);
    return arr;
}

//体育获奖-------分页跳转条件读取
function tyhjSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var adee = $("#Awardee8").val();
    var ename = $("#Entryname8").val();
    var sportslv = $("#SportsLV8").val();
    var wintime = $("#WinTime8").val();

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (adee == '') {
        adee = "-1";
    }
    if (ename == '') {
        ename = "-1";
    }
    if (sportslv == '') {
        sportslv = "-1";
    }
    if (wintime == '') {
        wintime = "-1";
    }

    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(adee);
    arr.push(ename);
    arr.push(sportslv);
    arr.push(wintime);
    return arr;
}

//智库建设-------分页跳转条件读取
function zkjsSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var cgname = $("#cgname8").val();
    var cnjb = $("#cnjb8").val();
    var dyzz = $("#dyzz8").val();
    var qtzz = $("#qtzz8").val();
    var cnbm = $("#cnbm8").val();
    var cntime = $("#cntime8").val();


    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (cgname == '') {
        cgname = "-1";
    }
    if (cnjb == '') {
        cnjb = "-1";
    }
    if (dyzz == '') {
        dyzz = "-1";
    }
    if (qtzz == '') {
        qtzz = "-1";
    }
    if (cnbm == '') {
        cnbm = "-1";
    }
    if (cntime == '') {
        cntime = "-1";
    }

    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(cgname);
    arr.push(cnjb);
    arr.push(dyzz);
    arr.push(qtzz);
    arr.push(cnbm);
    arr.push(cntime);
    return arr;
}

//成果转化-------分页跳转条件读取
function cgzhSNextPageMessage() {
    var department = $("#department").val();
    var number = $("#number").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var cgname = $("#cgname9").val();
    var dyzz = $("#dyzz9").val();
    var qtzz = $("#qtzz9").val();
    var zhdw = $("#zhdw9").val();
    var zrlx = $("#zrlx9").val();
    var zhtime = $("#zhtime9").val();

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (cgname == '') {
        cgname = "-1";
    }
    if (dyzz == '') {
        dyzz = "-1";
    }
    if (qtzz == '') {
        qtzz = "-1";
    }
    if (zhdw == '') {
        zhdw = "-1";
    }
    if (zrlx == '') {
        zrlx = "-1";
    }
    if (zhtime == '') {
        zhtime = "-1";
    }
    let arr = new Array();
    arr.push(department);
    arr.push(number);
    arr.push(start);
    arr.push(end);
    arr.push(judgestatus);
    arr.push(cgname);
    arr.push(dyzz);
    arr.push(qtzz);
    arr.push(zhdw);
    arr.push(zrlx);
    arr.push(zhtime);
    return arr;
}

function getSchoolSelect() {
    var flag = confirm("确认要删除吗?请谨慎选择");
    if (flag) {
        var list = new Array();
        $.each($('input[name="selected"]:checkbox:checked'), function () {
            var row = $(this).parents("tr");
            list.push($(this).val());
        });
        if (list.length == 0) {
            alert("所选可被删除内容为空");
            return false;
        } else {
            return list;
        }
    }
}

//会议论文
function goshylwpage() {
    window.location.href = httptod + "sjump/goshylwpage";
}

//会议论文--------------------------------------------------------------------------------------------------------检索查询
function shylwQueryModal() {
    $("#hylwQueryModal").modal('show');


}

//审核--------------------------------------------------------------------新增功能
function shylwcheck() {
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
            url: httptod + "sjump/hylwcheck",
            type: "post",
            data: {id: list[0]},
            dataType: "json",
            success: function (res) {
                // var timus="与以下题目相似度过高："+'\n';
                var timus="";
                for(var i = 0;i < res.length; i++){
                    timus=timus+res[i]+'\n';

                }
                alert(timus);
            },
            error: function (res) {
                console.log(res);
            }
        })
    }

function shylwdelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "sjump/deleteshylw",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}


//会议论文，条件查询 多功能一体
function goshylwBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var tm = $("#TM9").val();
    var dyzz = $("#DYZZ9").val();
    var qtzz = $("#QTZZ9").val();
    var hymc = $("#HYMC9").val();
    var hysj = $("#HYSJ9").val();
    var hyjb = $("#HYJB9").val();
    var jllb = $("#JLLB9").val();
    if(tm == ''&&dyzz == ''&&qtzz == ''&&hymc == ''&&hysj == ''&&hyjb == ''&&jllb == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (tm == '') {
        tm = "-1";
    }
    if (dyzz == '') {
        dyzz = "-1";
    }
    if (qtzz == '') {
        qtzz = "-1";
    }
    if (hymc == '') {
        hymc = "-1";
    }
    if (hysj == '') {
        hysj = "-1";
    }
    if (hyjb == '') {
        hyjb = "-1";
    }
    if (jllb == '') {
        jllb = "-1";
    }

    window.location.href = httptod + "sjump/goshylwpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&tm="
        + tm + "&dyzz=" + dyzz + "&qtzz=" + qtzz + "&hymc=" + hymc+ "&hysj=" + hysj + "&hyjb=" + hyjb + "&jllb=" + jllb;
}

//会议论文 分页功能页码跳转
function goshylwnext(pageNum) {
    let arr = hylwSNextPageMessage();
    window.location.href = httptod + "sjump/goshylwpage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&tm=" + arr[5] + "&dyzz=" + arr[6] + "&qtzz="
        + arr[7] + "&hymc=" + arr[8]+ "&hysj=" + arr[9] + "&hyjb=" + arr[10] + "&jllb=" + arr[11];
}

function passaudithylw() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "djump/passaudithylw",
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
                goshylwBydetail();

                // window.location.href = httptod + "sjump/goshylwpage";
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "djump/passnoaudithylw",
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

function shylwexportall() {
    window.location.href = httptod + "sjump/shylwexportall";
}


function downloadshylwfjsc() {
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

function shylwshow() {
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

function shylwupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
    //查看是只取第一个
    if (list.length > 1) {
        alert("选中较多，请重新选择");
        return false;
    }
    if (list.length == 0) {
        alert("未选中可编辑的，请重新选择");
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
            $("#Points").val(res.points);
            $("#Reward").val(res.reward);
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
    var types = $("#Type").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var zishu = $("#ZiShu").val();
    var fq = $("#fq").val();

    if (timu == null || timu == "" || dyzz == null || dyzz == "" || qtzz == null || qtzz == "" || press == null || press == "" || hyname == null || hyname == "" ||
        wheres == "" || wheres == null || times == null || times == "" || types == null || types == "" || rank == null || rank == ""
        || points == null || points == "" || zishu == "") {
        alert("请填写完整")
    } else {
        $.ajax({
            url: httptod + "tjump/hylwupdate",
            type: "post",
            data: JSON.stringify({
                hid: hid, timu: timu, dyzz: dyzz, qtzz: qtzz, press: press,
                hyname: hyname, wheres: wheres, times: times, rank: rank,
                types: types, points: points, reward: reward, fq: fq,
                zishu: zishu, judgestatus: "2", systime: "-1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                goshylwBydetail();
            }

        })
    }
}


//期刊论文
function gosqklwpage() {
    window.location.href = httptod + "sjump/gosqklwpage";
}

//期刊论文--------------------------------------------------------------------------------------------------------检索查询
function sqklwQueryModal() {
    $("#qklwQueryModal").modal('show');


}

function sqklwcheck() {
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
        url: httptod + "sjump/qklwcheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function sqklwdelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "sjump/deletesqklw",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

//期刊论文，条件查询 多功能一体
function gosqklwBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var timu = $("#TiMu9").val();       //题目
    var dyzz = $("#DYZZ9").val();       //第一作者
    var qtzz = $("#QTZZ9").val();       //其他作者
    var type = $("#Type9").combotree('getText');       //奖励类别
    var fbqk = $("#QiKanName9").val();  //发表期刊
    var time = $("#Time9").val();       //发表时间
    var fq = $("#FQ9").val();           //分区

    if(timu == ''&&dyzz == ''&&qtzz == ''&&type == ''&&fbqk == ''&&time == ''&&fq == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (timu == '') {
        timu = "-1";
    }
    if (dyzz == '') {
        dyzz = "-1";
    }
    if (qtzz == '') {
        qtzz = "-1";
    }
    if (type == '') {
        type = "-1";
    }
    if (fbqk == '') {
        fbqk = "-1"
    }
    if (time == '') {
        time = "-1"
    }
    if (fq == '') {
        fq = "-1"
    }
    window.location.href = httptod + "sjump/gosqklwpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department
        + "&timu=" + timu + "&dyzz=" + dyzz + "&qtzz=" + qtzz + "&type=" + type + "&fbqk=" + fbqk + "&time=" + time + "&fq=" + fq;
}

function gosqklwnext(pageNum) {
    let arr = qklwSNextPageMessage();
    window.location.href = httptod + "sjump/gosqklwpage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1] +
        "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4]+ "&timu=" + arr[5] + "&dyzz=" + arr[6] + "&qtzz=" + arr[7]
        + "&type=" + arr[8] + "&fbqk=" + arr[9] + "&time=" + arr[10] + "&fq=" + arr[11];
}

function passauditqklw() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "djump/passauditqklw",
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
                gosqklwBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
            list.push($(this).val());
        } else {
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length == 0) {
        alert("未选中或选中错误，请重新选择");
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
            url: httptod + "djump/passnoauditqklw",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                gosqklwBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}


function sqklwexportall() {
    window.location.href = httptod + "sjump/sqklwexportall";
}


function downloadsqklwfjsc() {
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

function sqklwshow() {
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

function sqklwupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
    //查看是只取第一个
    if (list.length > 1) {
        alert("选中较多，请重新选择");
        return false;
    }
    if (list.length == 0) {
        alert("未选中或选中错误，请重新选择");
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
            // $("#QiKanName").val(res.qikanname);
            $("#JQNumber").val(res.jqnumber);
            $("#Page").val(res.page);
            $("#Time").val(res.time);
            // $("#Rank").val(res.rank);
            $("#ZiShu").val(res.zishu);
            $("#Reward").val(res.reward);
            $("#Points").val(res.points);
            $("#FQ").val(res.fq);
            $("#remark").val(res.remark);
            $("#YXYZ").val(res.yxyz);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function qklwupdatesubmit() {
    var qid = $("#qid").val();
    var timu = $("#TiMu").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var type = $("#Type").combotree('getText');
    var qikanname = $("#QiKanName").combotree('getText');
    // var type = $("#Type").val();
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
    if (qid == null || qid == "" || timu == null || timu == "" || dyzz == null || dyzz == "" || type == null || type == "" || qikanname == null ||
        qikanname == "" || jqnumber == "" || jqnumber == null || time == null || time == "" ||
        zishu == null || zishu == "" || points == null || points == "" || fq == null || fq == "") {
        alert("请填写完整！");
    } else {
        $.ajax({
            url: httptod + "tjump/qklwupdate",
            type: "post",
            data: JSON.stringify({
                qid: qid, timu: timu, dyzz: dyzz, qtzz: qtzz, type: type, qikanname: qikanname, jqnumber: jqnumber,
                page: page, time: time, zishu: zishu, reward: reward, points: points, fq: fq, remark: remark,
                yxyz: yxyz, judgestatus: "2", systime: "-1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                gosqklwBydetail();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}


//学术著作
function gosxszzpage() {
    window.location.href = httptod + "sjump/gosxszzpage";
}

//学术著作--------------------------------------------------------------------------------------------------------检索查询
function sxszzQueryModal() {
    $("#xszzQueryModal").modal('show');


}
//审核
function sxszzcheck() {
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
        url: httptod + "sjump/xszzcheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function sxszzdelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "sjump/deletesxszz",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

//学术著作，条件查询 多功能一体
function gosxszzBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var timu = $("#TiMu9").val();
    var dyzz = $("#DYZZ9").val();
    var qtzz = $("#QTZZ9").val();
    var cbstype = $("#CBSType9").val();
    var zztype = $("#zzType9").val();
    var times = $("#Times9").val();
    if(timu == ''&&dyzz == ''&&qtzz == ''&&cbstype == ''&&zztype == ''&&times == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (timu == '') {
        timu = "-1";
    }
    if (dyzz == '') {
        dyzz = "-1";
    }
    if (qtzz == '') {
        qtzz = "-1";
    }
    if (cbstype == '') {
        cbstype = "-1";
    }
    if (zztype == '') {
        zztype = "-1";
    }
    if (times == '') {
        times = "-1";
    }

    window.location.href = httptod + "sjump/gosxszzpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&timu="
        + timu + "&dyzz=" + dyzz + "&qtzz=" + qtzz + "&cbstype=" + cbstype+ "&zztype=" + zztype + "&times=" + times;
}

//学术著作 分页功能页码跳转
function gosxszznext(pageNum) {
    let arr = xszzSNextPageMessage();
    window.location.href = httptod + "sjump/gosxszzpage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&timu=" + arr[5] + "&dyzz=" + arr[6] + "&qtzz="
        + arr[7] + "&cbstype=" + arr[8]+ "&zztype=" + arr[9] + "&times=" + arr[10];
}



function sxszzexportall() {
    window.location.href = httptod + "sjump/sxszzexportall";
}


function downloadsxszzfjsc() {
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
            list.push($(this).val());
        } else {
            otherlist.push($(this).val());
        }
    });
    console.log(list, otherlist);
    //如果 所选择数组为空
    if (list.length == 0 && otherlist.length == 0) {
        alert("未选中或选中错误，请重新选择");
        return false;
    }
    //如果 其中 list部分没有选择上  就直接推出  没有选择上可以修改的
    if (list.length == 0) {
        alert("选择有问题，请勿选择不属于本账户修改的范畴的项目");
    } else {
        $.ajax({
            url: httptod + "djump/passauditxszz",
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
                gosxszzBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
            list.push($(this).val());
        } else {
            otherlist.push($(this).val());
        }
        //根据judgestatus 只让他选中选中是1  其它部分不允许考虑
    });
    if (list.length == 0 && otherlist.length == 0) {
        alert("未选中或选中错误，请重新选择");
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
            url: httptod + "djump/passnoauditxszz",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                gosxszzBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function sxszzshow() {
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
            $("#Press2").val(res.press);
            $("#zzlx2").val(res.zzlx);
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

function sxszzupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
    //查看是只取第一个
    if (list.length > 1) {
        alert("选中较多，请重新选择");
        return false;
    }
    if (list.length == 0) {
        alert("未选中或选中错误，请重新选择");
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
            $("#Press").val(res.press);
            $("#zzlx").val(res.zzlx);
            $("#CIP").val(res.cip);
            $("#Time").val(res.time);
            $("#ZiShu").val(res.zishu);
            $("#Points").val(res.points);
            $("#Reward").val(res.reward);
            $("#fq").val(res.fq);
            $('#WorkType').combotree('setValue', res.worktype);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function xszzupdatesubmit() {
    var xid = $("#xid").val();
    var timu = $("#TiMu").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var isbn = $("#ISBN").val();
    var press = $("#Press").val();
    var zzlx = $("#zzlx").val();
    var cip = $("#CIP").val();
    var time = $("#Time").val();
    var zishu = $("#ZiShu").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var fq = $("#fq").val();
    var worktype = $("#WorkType").combotree('getText');
    if (xid == null || xid == "" || timu == null || timu == "" || dyzz == null || dyzz == "" || isbn == null || isbn == "" ||
        press == "" || press == null || time == null || time == "" || cip == null || cip == "" ||
        zishu == null || zishu == "" || points == null || points == "" || worktype == null || worktype == "") {
        alert("请填写完整！");
    } else {
        $.ajax({
            url: httptod + "tjump/xszzupdate",
            type: "post",
            data: JSON.stringify({
                xid: xid, timu: timu, dyzz: dyzz, qtzz: qtzz, isbn: isbn,
                press: press, cip: cip, time: time, zishu: zishu, points: points, reward: reward, fq: fq,
                reward: reward, points: points, worktype: worktype, judgestatus: "2", systime: "-1", zzlx: zzlx
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                gosxszzBydetail();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

//横向项目

function goshxxmpage() {
    window.location.href = httptod + "sjump/goshxxmpage";
}

//横向项目--------------------------------------------------------------------------------------------------------检索查询
function shxxmQueryModal() {
    $("#shxxmQueryModal").modal('show');


}

function shxxmcheck() {
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
        url: httptod + "sjump/hxxmcheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}


function shxxmdelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "sjump/deleteshxxm",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

//横向项目，条件查询 多功能一体
function goshxxmBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var xmname = $("#XMName9").val();
    var principal = $("#Principal9").val();
    var aunit = $("#AUnit9").val();
    var zje = $("#ZJE9").val();
    var cn = $("#ContractNumber9").val();
    var jxtime = $("#JXTime9").val();
    if(xmname == ''&&principal == ''&&aunit == ''&&zje == ''&&cn == ''&&jxtime == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (principal == '') {
        principal = "-1";
    }
    if (aunit == '') {
        aunit = "-1";
    }
    if (zje == '') {
        zje = "-1";
    }
    if (cn == '') {
        cn = "-1";
    }
    if (jxtime == '') {
        jxtime = "-1";
    }


    window.location.href = httptod + "sjump/goshxxmpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&XMName="
        + xmname + "&Principal=" + principal + "&AUnit=" + aunit + "&ZJE=" + zje+ "&ContractNumber=" + cn + "&JXTime=" + jxtime;
}

//横向项目 分页功能页码跳转
function goshxxmnext(pageNum) {
    let arr = hxxmSNextPageMessage();
    window.location.href = httptod + "sjump/goshxxmpage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&XMName=" + arr[5] + "&Principal=" + arr[6] + "&AUnit="
        + arr[7] + "&ZJE=" + arr[8]+ "&ContractNumber=" + arr[9] + "&JXTime=" + arr[10];
}



function passaudithxxm() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "djump/passaudithxxm",
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
                goshxxmBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "djump/passnoaudithxxm",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                goshxxmBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function shxxmshow() {
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

function shxxmupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
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
            // $("#FJSC2").val(res.fjsc);
        },
        error: function (res) {
            console.log(res);
        }
    })
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


    //TODO 需要判断完全
    if (hid == null || hid == "" || xmname == null || xmname == "" || principal == null || principal == "" || zje == null || zje == "" || ybk == null || ybk == "" || contractnumber == null || contractnumber == "" ||
        aunit == null || aunit == "" || qdtime == null || qdtime == "" || stime == "" || stime == null || jztime == null || jztime == "" || jxtime == null || jxtime == "" ||
        jxjy == null || jxjy == "" || jfstatus == null || jfstatus == "" || points == null || points == "") {
        alert("请填写完整！");
    } else {
        $.ajax({
            url: httptod + "tjump/hxxmupdate",
            type: "post",
            data: JSON.stringify({
                hid: hid, xmname: xmname, principal: principal, xmstatus: xmstatus, zje: zje, ybk: ybk,
                contractnumber: contractnumber, aunit: aunit, qdtime: qdtime, stime: stime,
                jztime: jztime, jxtime: jxtime, jxjy: jxjy, jfstatus: jfstatus, points: points,
                reward: reward, remark: remark, judgestatus: "2", systime: "-1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                goshxxmBydetail();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function shxxmexportall() {
    window.location.href = httptod + "sjump/shxxmexportall";
}


function downloadshxxmfjsc() {
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
function goshxlxpage() {
    window.location.href = httptod + "ssssjump/goshxlxpage";
}

//横向立项--------------------------------------------------------------------------------------------------------检索查询
function shxlxQueryModal() {
    $("#shxlxQueryModal").modal('show');


}

function shxlxcheck() {
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
        url: httptod + "ssssjump/hxlxcheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function shxlxdelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "ssssjump/deleteshxlx",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

//横向立项，条件查询 多功能一体
function goshxlxBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var xmname = $("#XMName9").val();
    var principal = $("#Principal9").val();
    var aunit = $("#AUnit9").val();
    var zje = $("#ZJE9").val();
    var cn = $("#ContractNumber9").val();
    var qdtime = $("#QDTime9").val();
    if(xmname == ''&&principal == ''&&aunit == ''&&zje == ''&&cn == ''&&qdtime == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (principal == '') {
        principal = "-1";
    }
    if (aunit == '') {
        aunit = "-1";
    }
    if (zje == '') {
        zje = "-1";
    }
    if (cn == '') {
        cn = "-1";
    }
    if (qdtime == '') {
        qdtime = "-1";
    }


    window.location.href = httptod + "ssssjump/goshxlxpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&XMName="
        + xmname + "&Principal=" + principal + "&AUnit=" + aunit + "&ZJE=" + zje+ "&ContractNumber=" + cn + "&QDTime=" + qdtime;
}

//横向立项 分页功能页码跳转
function goshxlxnext(pageNum) {
    let arr = hxlxSNextPageMessage();
    window.location.href = httptod + "ssssjump/goshxlxpage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&XMName=" + arr[5] + "&Principal=" + arr[6] + "&AUnit="
        + arr[7] + "&ZJE=" + arr[8]+ "&ContractNumber=" + arr[9] + "&QDTime=" + arr[10];
}


function passaudithxlx() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddddjump/passaudithxlx",
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
                goshxlxBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddddjump/passnoaudithxlx",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                goshxlxBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function shxlxshow() {
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

function shxlxupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
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
            // $("#FJSC2").val(res.fjsc);
        },
        error: function (res) {
            console.log(res);
        }
    })
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


    //TODO 需要判断完全
    if (hid == null || hid == "" || xmname == null || xmname == "" || principal == null || principal == "" || zje == null || zje == "" || ybk == null || ybk == "" || contractnumber == null || contractnumber == "" ||
        aunit == null || aunit == "" || qdtime == null || qdtime == "" || stime == "" || stime == null || jztime == null || jztime == ""
        || points == null || points == "") {
        alert("请填写完整！");
    } else {
        $.ajax({
            url: httptod + "ttttjump/hxlxupdate",
            type: "post",
            data: JSON.stringify({
                hid: hid, xmname: xmname, principal: principal, xmstatus: xmstatus, zje: zje, ybk: ybk,
                contractnumber: contractnumber, aunit: aunit, qdtime: qdtime, stime: stime,
                jztime: jztime, points: points, reward: reward, remark: remark, judgestatus: "2", systime: "-1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                goshxlxBydetail();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function shxlxexportall() {
    window.location.href = httptod + "ssssjump/shxlxexportall";
}


function downloadshxlxfjsc() {
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

//软件著作
function gosrjzzpage() {
    window.location.href = httptod + "ssjump/gosrjzzpage";
}

//软件著作--------------------------------------------------------------------------------------------------------检索查询
function srjzzQueryModal() {
    $("#srjzzQueryModal").modal('show');


}

function srjzzcheck() {
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
        url: httptod + "ssjump/rjzzcheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function srjzzdelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "ssjump/deletesrjzz",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

//软件著作，条件查询 多功能一体
function gosrjzzBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var xmname = $("#XMName9").val();
    var dyzz = $("#DYZZ9").val();
    var qtzz = $("#QTZZ9").val();
    var djnumber = $("#DJNumber9").val();
    var djtime = $("#DJTime9").val();
    if(xmname == ''&&dyzz == ''&&qtzz == ''&&djnumber == ''&&djtime == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (dyzz == '') {
        dyzz = "-1";
    }
    if (qtzz == '') {
        qtzz = "-1";
    }
    if (djnumber == '') {
        djnumber = "-1";
    }
    if (djtime == '') {
        djtime = "-1";
    }


    window.location.href = httptod + "ssjump/gosrjzzpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&XMName="
        + xmname + "&DYZZ=" + dyzz + "&QTZZ=" + qtzz + "&DJNumber=" + djnumber+ "&DJTime=" + djtime;
}

//软件著作 分页功能页码跳转
function gosrjzznext(pageNum) {
    let arr = rjzzSNextPageMessage();
    window.location.href = httptod + "ssjump/gosrjzzpage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&XMName=" + arr[5] + "&DYZZ=" + arr[6] + "&QTZZ="
        + arr[7] + "&DJNumber=" + arr[8]+ "&DJTime=" + arr[9] ;
}

function passauditrjzz() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddjump/passauditrjzz",
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
                gosrjzzBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddjump/passnoauditrjzz",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                gosrjzzBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function srjzzshow() {
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
            // $("#FJSC2").val(res.fjsc);
        },
        error: function (res) {
            console.log(res);
        }
    })

}

function srjzzupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
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

            $("#BQType").val(res.bqtype);
            $("#SYFS").val(res.syfs);
            $("#Points").val(res.points);
            // $("#FJSC").val(res.fjsc);
        },
        error: function (res) {
            console.log(res);
        }
    })
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
    var fq = $("#fq").val();

    var bqtype = $("#BQType").val();
    var syfs = $("#SYFS").val();
    var points = $("#Points").val();
    if (rid == null || rid == "" || xmname == null || xmname == "" || dyzz == null || dyzz == "" || djnumber == null || djnumber == "" || finishtime == null ||
        finishtime == "" || djtime == "" || djtime == null || bqtype == "" || bqtype == null
        || syfs == "" || syfs == null || points == "" || points == null) {
        alert("请填写完整！");
    } else {
        $.ajax({
            url: httptod + "ttjump/rjzzupdate",
            type: "post",
            data: JSON.stringify({
                rid: rid,
                xmname: xmname,
                dyzz: dyzz,
                qtzz: qtzz,
                djnumber: djnumber,
                sydw: sydw,
                finishtime: finishtime,
                djtime: djtime, fq: fq,
                bqtype: bqtype,
                syfs: syfs,
                points: points,
                judgestatus: "2", systime: "-1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                gosrjzzBydetail();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function srjzzexportall() {
    window.location.href = httptod + "ssjump/srjzzexportall";
}


function downloadsrjzzfjsc() {
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

//获批专利
function goshpzlpage() {
    window.location.href = httptod + "ssjump/goshpzlpage";
}

//获批专利--------------------------------------------------------------------------------------------------------检索查询
function shpzlQueryModal() {
    $("#shpzlQueryModal").modal('show');


}

function shpzlcheck() {
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
        url: httptod + "ssjump/hpzlcheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function shpzldelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "ssjump/deleteshpzl",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

//获批专利，条件查询 多功能一体
function goshpzlBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var xmname = $("#XMName9").val();
    var fmr = $("#FMR9").val();
    var qtfmr = $("#QTFMR9").val();
    var zlnumber = $("#ZLNumber9").val();
    var ptype = $("#PType9").val();
    var sytime = $("#SYTime9").val();
    if(xmname == ''&&fmr == ''&&qtfmr == ''&&zlnumber == ''&&ptype == ''&&sytime == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (fmr == '') {
        fmr = "-1";
    }
    if (qtfmr == '') {
        qtfmr = "-1";
    }
    if (zlnumber == '') {
        zlnumber = "-1";
    }
    if (ptype == '') {
        ptype = "-1";
    }
    if (sytime == '') {
        sytime = "-1";
    }

    window.location.href = httptod + "ssjump/goshpzlpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&XMName="
        + xmname + "&FMR=" + fmr + "&QTFMR=" + qtfmr + "&ZLNumber=" + zlnumber+ "&PType=" + ptype+ "&SYTime=" + sytime;
}

//获批专利 分页功能页码跳转
function goshpzlnext(pageNum) {
    let arr = hpzlSNextPageMessage();
    window.location.href = httptod + "ssjump/goshpzlpage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&XMName=" + arr[5] + "&FMR=" + arr[6] + "&QTFMR="
        + arr[7] + "&ZLNumber=" + arr[8]+ "&PType=" + arr[9]+ "&SYTime=" + arr[10] ;
}

function passaudithpzl() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddjump/passaudithpzl",
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
                goshpzlBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddjump/passnoaudithpzl",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                goshpzlBydetail();

            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function shpzlshow() {
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

function shpzlupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
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
            $("#fq").val(res.fq);
            $("#Points").val(res.points);
            $("#Reward").val(res.reward);
            // $("#FJSC").val(res.fjsc);
            $('#PType').combotree('setValue', res.ptype);
            $("#Pstatus").val(res.pstatus);
        },
        error: function (res) {
            console.log(res);
        }
    })
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
    var ptype = $("#PType").val();
    var pstatus = $("#Pstatus").val();
    var points = $("#Points").val();
    var fq = $("#fq").val();
    var reward = $("#Reward").val();
    if (hid == null || hid == "" || xmname == null || xmname == "" || zlqr == null || zlqr == "" || fmr == null || fmr == "" ||
        qtfmr == null || qtfmr == "" || bfbm == "" || bfbm == null || zlnumber == null || zlnumber == "" ||
        sqtime == null || sqtime == "" || sytime == null || sytime == "" ||
        ptype == null || ptype == "" || pstatus == null || pstatus == "" ||
        points == null || points == "") {
        alert("请填写完整！");
    } else {
        $.ajax({
            url: httptod + "ttjump/hpzlupdate",
            type: "post",
            data: JSON.stringify({
                hid: hid, xmname: xmname, xmname: xmname, zlqr: zlqr, fmr: fmr,
                qtfmr: qtfmr, bfbm: bfbm, zlnumber: zlnumber, sqtime: sqtime,
                sytime: sytime, ptype: ptype, pstatus: pstatus, points: points, fq: fq,
                reward: reward, judgestatus: "2", systime: "-1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                goshpzlBydetail();

            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function shpzlexportall() {
    window.location.href = httptod + "ssjump/shpzlexportall";
}


function downloadshpzlfjsc() {
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

//学术称号
function gosxschpage() {
    window.location.href = httptod + "ssjump/gosxschpage";
}

//学术称号--------------------------------------------------------------------------------------------------------检索查询
function sxschQueryModal() {
    $("#sxschQueryModal").modal('show');


}

function sxschcheck() {
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
        url: httptod + "ssjump/xschcheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function sxschdelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "ssjump/deletesxsch",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

//学术称号，条件查询 多功能一体
function gosxschBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var xmname = $("#XMName9").val();
    var title = $("#Title9").val();
    var rank = $("#Rank9").val();
    var xfbm = $("#XFBM9").val();
    var time = $("#Time9").val();
    if(xmname == ''&&title == ''&&rank == ''&&xfbm == ''&&time == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (title == '') {
        title = "-1";
    }
    if (rank == '') {
        rank = "-1";
    }
    if (xfbm == '') {
        xfbm = "-1";
    }
    if (time == '') {
        time = "-1";
    }


    window.location.href = httptod + "ssjump/gosxschpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&XMName="
        + xmname + "&Title=" + title + "&Rank=" + rank + "&XFBM=" + xfbm+ "&Time=" + time;
}

//学术称号 分页功能页码跳转
function gosxschnext(pageNum) {
    let arr = xschSNextPageMessage();
    window.location.href = httptod + "ssjump/gosxschpage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&XMName=" + arr[5] + "&Title=" + arr[6] + "&Rank="
        + arr[7] + "&XFBM=" + arr[8]+ "&Time=" + arr[9];
}

function passauditxsch() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddjump/passauditxsch",
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
                gosxschBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddjump/passnoauditxsch",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                gosxschBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function sxschshow() {
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

function sxschupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
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
            $("#fq").val(res.fq);
            $("#XFBM").val(res.xfbm);
            // $("#FJSC").val(res.fjsc);
        },
        error: function (res) {
            console.log(res);
        }
    })
}


function xschupdatesubmit() {
    var xid = $("#xid").val();
    var xmname = $("#xmName").val();
    var title = $("#Title").val();
    var rank = $("#Rank").val();
    var zsnumber = $("#ZSNumber").val();
    var time = $("#Time").val();
    var fq = $("#fq").val();
    var xfbm = $("#XFBM").val();
    if (xid == null || xid == "" || xmname == null || xmname == "" || title == null || title == "" || rank == null || rank == ""
        || time == "" || time == null || xfbm == "" || xfbm == null) {
        alert("请填写完整！");
    } else {
        $.ajax({
            url: httptod + "ttjump/xschupdate",
            type: "post",
            data: JSON.stringify({
                xid: xid, xmname: xmname, title: title, rank: rank, zsnumber: zsnumber, fq: fq,
                time: time, xfbm: xfbm, judgestatus: "2", systime: "-1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                gosxschBydetail();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function sxschexportall() {
    window.location.href = httptod + "ssjump/sxschexportall";
}


function downloadsxschfjsc() {
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

//学术交流
function gosxsjlpage() {
    window.location.href = httptod + "ssjump/gosxsjlpage";
}

//学术交流--------------------------------------------------------------------------------------------------------检索查询
function sxsjlQueryModal() {
    $("#sxsjlQueryModal").modal('show');


}

function sxsjlcheck() {
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
        url: httptod + "ssjump/xsjlcheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function sxsjldelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "ssjump/deletesxsjl",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

//学术交流，条件查询 多功能一体
function gosxsjlBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var xmname = $("#XMName9").val();
    var cyry = $("#CYRY9").val();
    var rank = $("#Rank9").val();
    if(xmname == ''&&cyry == ''&&rank == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (cyry == '') {
        cyry = "-1";
    }
    if (rank == '') {
        rank = "-1";
    }

    window.location.href = httptod + "ssjump/gosxsjlpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&XMName="
        + xmname + "&CYRY=" + cyry + "&Rank=" + rank;
}

//学术交流 分页功能页码跳转
function gosxsjlnext(pageNum) {
    let arr = xsjlSNextPageMessage();
    window.location.href = httptod + "ssjump/gosxsjlpage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&XMName=" + arr[5] + "&CYRY=" + arr[6] + "&Rank="
        + arr[7];
}


function passauditxsjl() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddjump/passauditxsjl",
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
                gosxsjlBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddjump/passnoauditxsjl",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                gosxsjlBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function sxsjlshow() {
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
            $("#fayan2").val(res.fayan);
            $("#Rank2").val(res.rank);
            $("#fq2").val(res.fq);
            $("#LunWen2").val(res.lunwen);

        },
        error: function (res) {
            console.log(res);
        }
    })

}

function sxsjlupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
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
            $("#fayan").val(res.fayan);
            $("#Rank").val(res.rank);
            $("#fq").val(res.fq);
            $("#LunWen").val(res.lunwen);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function xsjlupdatesubmit() {
    var xid = $("#xid").val();
    var xmname = $("#xmName").val();
    var cyry = $("#CYRY").val();
    var wheres = $("#Wheres").val();
    var time = $("#Time").val();
    var zbdw = $("#ZBDW").val();
    var fayan = $("#fayan").val();
    var rank = $("#Rank").val();
    var fq = $("#fq").val();
    var lunwen = $("#LunWen").val();
    if (xid == null || xid == "" || xmname == null || xmname == "" || cyry == null || cyry == "" || wheres == null || wheres == "" || time == null ||
        time == "" || zbdw == "" || zbdw == null || rank == "" || rank == null || lunwen == "" || lunwen == null) {
        alert("请填写完整！");
    } else {
        $.ajax({
            url: httptod + "ttjump/xsjlupdate",
            type: "post",
            data: JSON.stringify({
                xid: xid, xmname: xmname, cyry: cyry, wheres: wheres, time: time, zbdw: zbdw, fq: fq,
                rank: rank, lunwen: lunwen, judgestatus: "2", systime: "-1", fayan: fayan
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                gosxsjlBydetail();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function sxsjlexportall() {
    window.location.href = httptod + "ssjump/sxsjlexportall";
}


function downloadsxsjlfjsc() {
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

//获批奖励
function goshpjlpage() {
    window.location.href = httptod + "sssjump/goshpjlpage";
}

//获批奖励--------------------------------------------------------------------------------------------------------检索查询
function shpjlQueryModal() {
    $("#shpjlQueryModal").modal('show');


}

function shpjlcheck() {
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
        url: httptod + "sssjump/hpjlcheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}


function shpjldelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "sssjump/deleteshpjl",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}
//获批奖励，条件查询 多功能一体
function goshpjlBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var cgname = $("#CGName9").val();
    var jlname = $("#JLName9").val();
    var dyhjr = $("#DYHJR9").val();
    var qthjr = $("#QTHJR9").val();
    var jixdbm = $("#JIXDBM9").val();
    var jlrank = $("#JLRank9").val();
    var hjtime = $("#HJTime9").val();
    if(cgname == ''&&jlname == ''&&dyhjr == ''&&qthjr == ''&&jixdbm == ''&&jlrank == ''&&hjtime == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (cgname == '') {
        cgname = "-1";
    }
    if (jlname == '') {
        jlname = "-1";
    }
    if (dyhjr == '') {
        dyhjr = "-1";
    }
    if (qthjr == '') {
        qthjr = "-1";
    }
    if (jixdbm == '') {
        jixdbm = "-1";
    }
    if (jlrank == '') {
        jlrank = "-1";
    }
    if (hjtime == '') {
        hjtime = "-1";
    }

    window.location.href = httptod + "sssjump/goshpjlpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&CGName="
        + cgname + "&JLName=" + jlname + "&DYHJR=" + dyhjr+ "&QTHJR=" + qthjr + "&JIXDBM=" + jixdbm + "&JLRank=" + jlrank + "&HJTime=" + hjtime;
}

//获批奖励 分页功能页码跳转
function goshpjlnext(pageNum) {
    let arr = hpjlSNextPageMessage();
    window.location.href = httptod + "sssjump/goshpjlpage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&CGName=" + arr[5] + "&JLName=" + arr[6] + "&DYHJR="
        + arr[7]+ "&QTHJR=" + arr[8] + "&JIXDBM=" + arr[9] + "&JLRank=" + arr[10] + "&HJTime=" + arr[11];
}

function passaudithpjl() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "dddjump/passaudithpjl",
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
                goshpjlBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "dddjump/passnoaudithpjl",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                goshpjlBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function shpjlshow() {
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

function shpjlupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
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
            $("#JLName").val(res.jlname);
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
        },
        error: function (res) {
            console.log(res);
        }
    })
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
    var jlrank = $("#JLRank").val();
    var hjrank = $("#HJRank").val();
    var remark = $("#Remark").val();
    var points = $("#Points").val();
    var kyjl = $("#KYJL").val();
    if (hid == null || hid == "" || cgname == null || cgname == "" || jlname == null || jlname == "" ||
        dyhjr == null || dyhjr == "" || qthjr == null || qthjr == "" || jixdbm == "" || jixdbm == null ||
        points == null || points == "" || zsnumber == null || zsnumber == ""
        || hjtime == null || hjtime == "" || jlrank == "" || jlrank == null) {
        alert("请填写完整！");
    } else {
        //先 添加一下文件 然后传递到数据库
        $.ajax({
            url: httptod + "tttjump/hpjlupdate",
            type: "post",
            data: JSON.stringify({
                hid: hid, cgname: cgname, jlname: jlname, dyhjr: dyhjr, qthjr: qthjr, jixdbm: jixdbm,
                zsnumber: zsnumber, hjtime: hjtime, jlrank: jlrank, hjrank: hjrank,
                remark: remark, points: points, kyjl: kyjl, judgestatus: "2", systime: "-1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                goshpjlBydetail();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function shpjlexportall() {
    window.location.href = httptod + "sssjump/shpjlexportall";
}


function downloadshpjlfjsc() {
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
function gosymhjpage() {
    window.location.href = httptod + "sssjump/gosymhjpage";
}
//音美获奖--------------------------------------------------------------------------------------------------------检索查询
function symhjQueryModal() {
    $("#symhjQueryModal").modal('show');


}

function symhjcheck() {
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
        url: httptod + "sssjump/ymhjcheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function symhjdelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "sssjump/deletesymhj",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

//音美获奖，条件查询 多功能一体
function gosymhjBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var timu = $("#TiMu9").val();
    var aname = $("#AwardName9").val();
    var dyzz = $("#DYZZ9").val();
    var qtzz = $("#QTZZ9").val();
    var org = $("#Organizer9").val();
    var rsort = $("#RewardSort9").combotree('getText');
    var atime = $("#AwardTime9").val();
    if(timu == ''&&aname == ''&&dyzz == ''&&qtzz == ''&&org == ''&&rsort == ''&&atime == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (timu == '') {
        timu = "-1";
    }
    if (aname == '') {
        aname = "-1";
    }
    if (dyzz == '') {
        dyzz = "-1";
    }
    if (qtzz == '') {
        qtzz = "-1";
    }
    if (org == '') {
        org = "-1";
    }
    if (rsort == '') {
        rsort = "-1";
    }
    if (atime == '') {
        atime = "-1";
    }

    window.location.href = httptod + "sssjump/gosymhjpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&TiMu="
        + timu + "&AwardName=" + aname + "&DYZZ=" + dyzz+ "&QTZZ=" + qtzz + "&Organizer=" + org + "&RewardSort=" + rsort + "&AwardTime=" + atime;
}

//音美获奖 分页功能页码跳转
function gosymhjnext(pageNum) {
    let arr = ymhjSNextPageMessage();
    window.location.href = httptod + "sssjump/gosymhjpage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&TiMu=" + arr[5] + "&AwardName=" + arr[6] + "&DYZZ="
        + arr[7]+ "&QTZZ=" + arr[8] + "&Organizer=" + arr[9] + "&RewardSort=" + arr[10] + "&AwardTime=" + arr[11];
}

function passauditymhj() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "dddjump/passauditymhj",
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
                gosymhjBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "dddjump/passnoauditymhj",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                gosymhjBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function symhjshow() {
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

function symhjupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
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
            $("#Reward").val(res.reward);
            $("#Points").val(res.points);
            $("#Remark").val(res.remark);

        },
        error: function (res) {
            console.log(res);
        }
    })
}

function ymhjupdatesubmit() {
    var yid = $("#yid").val();
    var timu = $("#TiMu").val();
    var awardname = $("#AwardName").val();
    var dyzz = $("#DYZZ").val();
    var qtzz = $("#QTZZ").val();
    var organizer = $("#Organizer").val();
    var awardtime = $("#AwardTime").val();
    var rewardsort = $("#RewardSort").val();
    var reward = $("#Reward").val();
    var points = $("#Points").val();
    var remark = $("#Remark").val();
    if (timu == null || timu == "" || awardname == null || awardname == "" ||
        dyzz == null || dyzz == "" || qtzz == null || qtzz == "" || organizer == "" || organizer == null ||
        awardtime == null || awardtime == "" || rewardsort == null || rewardsort == "" ||
        points == null || points == "" || yid == null || yid == "") {
        alert("请填写完整！");
    } else {
        //先 添加一下文件 然后传递到数据库
        $.ajax({
            url: httptod + "tttjump/ymhjupdate",
            type: "post",
            data: JSON.stringify({
                yid: yid, timu: timu, awardname: awardname, dyzz: dyzz, qtzz: qtzz, organizer: organizer,
                awardtime: awardtime, rewardsort: rewardsort, reward: reward, points: points, remark: remark,
                judgestatus: "2", systime: "-1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                gosymhjBydetail();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

function symhjexportall() {
    window.location.href = httptod + "sssjump/symhjexportall";
}


function downloadsymhjfjsc() {
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
function gostyhjpage() {
    window.location.href = httptod + "sssjump/gostyhjpage";
}
//体育获奖--------------------------------------------------------------------------------------------------------检索查询
function styhjQueryModal() {
    $("#styhjQueryModal").modal('show');


}

function styhjcheck() {
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
        url: httptod + "sssjump/tyhjcheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function styhjdelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "sssjump/deletestyhj",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}
//体育获奖，条件查询 多功能一体
function gostyhjBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var adee = $("#Awardee9").val();
    var ename = $("#Entryname9").val();
    var sportslv = $("#SportsLV9").val();
    var wintime = $("#WinTime9").val();
    if(adee == ''&&ename == ''&&sportslv == ''&&wintime == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (adee == '') {
        adee = "-1";
    }
    if (ename == '') {
        ename = "-1";
    }
    if (sportslv == '') {
        sportslv = "-1";
    }
    if (wintime == '') {
        wintime = "-1";
    }

    window.location.href = httptod + "sssjump/gostyhjpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&Awardee="
        + adee + "&Entryname=" + ename + "&SportsLV=" + sportslv+ "&WinTime=" + wintime;
}

//体育获奖 分页功能页码跳转
function gostyhjnext(pageNum) {
    let arr = tyhjSNextPageMessage();
    window.location.href = httptod + "sssjump/gostyhjpage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&Awardee=" + arr[5] + "&Entryname=" + arr[6] + "&SportsLV="
        + arr[7]+ "&WinTime=" + arr[8];
}

function passaudittyhj() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "dddjump/passaudittyhj",
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
                gostyhjBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "dddjump/passnoaudittyhj",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                gostyhjBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function styhjshow() {
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

function styhjupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
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
            $("#Ranking").val(res.ranking);
            $("#BJBM").val(res.bjbm);
            $("#Points").val(res.points);
            $("#fq").val(res.fq);
            $("#Reward").val(res.reward);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function tyhjupdatesubmit() {
    var tid = $("#tid").val();
    var awardee = $("#Awardee").val();
    var entryname = $("#Entryname").val();
    var wintime = $("#WinTime").val();
    var certificateid = $("#CertificateID").val();
    var sportslv = $("#SportsLV").val();
    var ranking = $("#Ranking").val();
    var bjbm = $("#BJBM").val();
    var points = $("#Points").val();
    var reward = $("#Reward").val();
    var fq = $("#fq").val();
    if (tid == null || tid == "" || awardee == null || awardee == "" || entryname == null || entryname == "" ||
        wintime == null || wintime == "" || bjbm == null || bjbm == "" || sportslv == "" || sportslv == null ||
        ranking == null || ranking == "" || points == null || points == "" || certificateid == "" || certificateid == null) {
        alert("请填写完整！");
    } else {
        $.ajax({
            url: httptod + "tttjump/tyhjupdate",
            type: "post",
            data: JSON.stringify({
                tid: tid, awardee: awardee, entryname: entryname, wintime: wintime, bjbm: bjbm,
                sportslv: sportslv, ranking: ranking, sportslv: sportslv, points: points, fq: fq,
                reward: reward, certificateid: certificateid, judgestatus: "2", systime: "-1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                gostyhjBydetail();
            },
            error: function (res) {
                console.log(res);
            }
        })

    }
}

function styhjexportall() {
    window.location.href = httptod + "sssjump/styhjexportall";
}


function downloadstyhjfjsc() {
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
function goscgpage() {
    window.location.href = httptod + "ssssjump/goscgpage";
}


function goscgBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    window.location.href = httptod + "ssssjump/goscgpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department;
}

function goscgnext(pageNum) {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    window.location.href = httptod + "ssssjump/goscgpage?pageNum=" + pageNum + "&start=" + start + "&end=" + end + "&judgestatus=" + judgestatus;
}

function passauditcg() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddddjump/passauditcg",
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
                goscgBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddddjump/passnoauditcg",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                goscgBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function scgshow() {
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

function scgupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
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
        },
        error: function (res) {
            console.log(res);
        }
    })
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
    if (cid == null || cid == "" || cgname == null || cgname == "" || cgtype == null || cgtype == "" ||
        dyzz == null || dyzz == "" || qtzz == null || qtzz == "" || cnbm == "" || cnbm == null ||
        cgdz == null || cgdz == "" || time == null || time == "" || reward == "" || reward == null || points == "" || points == null) {
        alert("请填写完整！");
    } else {
        //先 添加一下文件 然后传递到数据库
        $.ajax({
            url: httptod + "ttttjump/cgupdate",
            type: "post",
            data: JSON.stringify({
                cgname: cgname, cgtype: cgtype, dyzz: dyzz, qtzz: qtzz, cnbm: cnbm,
                cgdz: cgdz, time: time, points: points, reward: reward, judgestatus: "2", cid: cid, systime: "-1"
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                goscgBydetail();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }


}

function scgexportall() {
    window.location.href = httptod + "ssssjump/scgexportall";
}


function downloadscgfjsc() {
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
function goshpxmpage() {
    window.location.href = httptod + "ssssjump/goshpxmpage";
}

//纵向立项--------------------------------------------------------------------------------------------------------检索查询
function shpxmQueryModal() {
    $("#hpxmQueryModal").modal('show');


}

function shpxmcheck() {
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
        url: httptod + "ssssjump/hpxmcheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}



function shpxmdelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "ssssjump/deleteshpxm",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

//纵向立项，条件查询 多功能一体
function goshpxmBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var xmname = $("#xmname9").val();
    var hoster = $("#hoster9").val();
    var cyry = $("#cyry9").val();
    var xdbm = $("#xdbm9").val();
    var xmsource = $("#xmsource9").combotree('getText');
    var prorank = $("#prorank9").val();
    var proproperty = $("#proproperty9").val();
    var lxtime = $("#lxtime9").val();
    if(xmname == ''&&hoster == ''&&cyry == ''&&xdbm == ''&&xmsource == ''&&prorank == ''&&proproperty == ''&&lxtime == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (hoster == '') {
        hoster = "-1";
    }
    if (cyry == '') {
        cyry = "-1";
    }
    if (xdbm == '') {
        xdbm = "-1";
    }
    if (xmsource == '') {
        xmsource = "-1";
    }
    if (prorank == '') {
        prorank = "-1";
    }
    if (proproperty == '') {
        proproperty = "-1";
    }
    if (lxtime == '') {
        lxtime = "-1";
    }

    window.location.href = httptod + "ssssjump/goshpxmpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&xmname="
        + xmname + "&hoster=" + hoster + "&cyry=" + cyry + "&xdbm=" + xdbm+ "&xmsource=" + xmsource + "&prorank=" + prorank +
        "&proproperty=" + proproperty + "&lxtime=" + lxtime;
}

//纵向立项 分页功能页码跳转
function goshpxmnext(pageNum) {
    let arr = hpxmSNextPageMessage();
    window.location.href = httptod + "ssssjump/goshpxmpage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&xmname=" + arr[5] + "&hoster=" + arr[6] + "&cyry="
        + arr[7] + "&xdbm=" + arr[8]+ "&xmsource=" + arr[9] + "&prorank=" + arr[10]+ "&proproperty=" + arr[11] + "&lxtime=" + arr[12];
}


function passaudithpxm() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddddjump/passaudithpxm",
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
                goshpxmBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddddjump/passnoaudithpxm",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                goshpxmBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function shpxmshow() {
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

function shpxmupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
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
    if (hid == null || hid == "" || xmname == null || xmname == "" || pronumber == null || pronumber == "" || wbjf == null || wbjf == "" || ptjf == null || ptjf == "" ||
        xdbm == null || xdbm == "" || xmsource == null || xmsource == "" || prorank == null || prorank == "" || proproperty == "" || proproperty == null ||
        hoster == null || hoster == "" || prorank == null || prorank == "" || points == "" || points == null
        || cyry == "" || cyry == null || lxtime == "" || lxtime == null) {
        alert("请填写完整！");
    } else {
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
                judgestatus: "2",
                systime: "-1",
                hid: hid
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.href = httptod + "ssssjump/goshpxmpage";
            },
            error: function (res) {
                console.log(res);
            }
        })


    }


}

function shpxmexportall() {
    window.location.href = httptod + "ssssjump/shpxmexportall";
}


function downloadshpxmfjsc() {
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

//纵向结项  jxys
function gosjxyspage() {
    window.location.href = httptod + "ssssjump/gosjxyspage";
}

//纵向结项--------------------------------------------------------------------------------------------------------检索查询
function sjxysQueryModal() {
    $("#sjxysQueryModal").modal('show');


}

function sjxyscheck() {
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
        url: httptod + "ssssjump/jxyscheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function sjxysdelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "ssssjump/deletesjxys",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}

//纵向结项，条件查询 多功能一体
function gosjxysBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var xmname = $("#xmname9").val();
    var hoster = $("#hoster9").val();
    var cyry = $("#cyry9").val();
    var xdbm = $("#xdbm9").val();
    var xmly = $("#xmly9").combotree('getText');
    // var xmly = $("#xmly9").val();
    var rank = $("#rank9").val();
    var proproperty = $("#proproperty9").val();
    var time = $("#time9").val();
    if(xmname == ''&&hoster == ''&&cyry == ''&&xdbm == ''&&xmly == ''&&rank == ''&&proproperty == ''&&time == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (xmname == '') {
        xmname = "-1";
    }
    if (hoster == '') {
        hoster = "-1";
    }
    if (cyry == '') {
        cyry = "-1";
    }
    if (xdbm == '') {
        xdbm = "-1";
    }
    if (xmly == '') {
        xmly = "-1";
    }
    if (rank == '') {
        rank = "-1";
    }
    if (proproperty == '') {
        proproperty = "-1";
    }
    if (time == '') {
        time = "-1";
    }

    window.location.href = httptod + "ssssjump/gosjxyspage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&xmname="
        + xmname + "&hoster=" + hoster + "&cyry=" + cyry + "&xdbm=" + xdbm+ "&xmly=" + xmly + "&rank=" + rank +
        "&proproperty=" + proproperty + "&time=" + time;
}

//纵向结项 分页功能页码跳转
function gosjxysnext(pageNum) {
    let arr = jxysSNextPageMessage();
    window.location.href = httptod + "ssssjump/gosjxyspage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&xmname=" + arr[5] + "&hoster=" + arr[6] + "&cyry="
        + arr[7] + "&xdbm=" + arr[8]+ "&xmly=" + arr[9] + "&rank=" + arr[10]+ "&proproperty=" + arr[11] + "&time=" + arr[12];
}


function passauditjxys() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddddjump/passauditjxys",
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
                gosjxysBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddddjump/passnoauditjxys",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                gosjxysBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function sjxysshow() {
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

function sjxysupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
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
    if (jid == null || jid == "" || xmname == null || xmname == "" || pronumber == null || pronumber == "" || xmly == null || xmly == "" ||
        rank == null || rank == "" || hoster == null || hoster == "" || proproperty == null || proproperty == "" || xdbm == "" || xdbm == null ||
        cyry == null || cyry == "" || time == "" || time == null || points == "" || points == null) {
        alert("请填写完整！");
    } else {
        //先 添加一下文件 然后传递到数据库
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
                judgestatus: "2",
                systime: "-1",
                jid: jid

            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                gosjxysBydetail();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }


}

function sjxysexportall() {
    window.location.href = httptod + "ssssjump/sjxysexportall";
}


function downloadsjxysfjsc() {
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
function goszkjspage() {
    window.location.href = httptod + "ssssjump/goszkjspage";
}
//智库建设--------------------------------------------------------------------------------------------------------检索查询
function szkjsQueryModal() {
    $("#szkjsQueryModal").modal('show');


}

function szkjscheck() {
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
        url: httptod + "ssssjump/zkjscheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function szkjsdelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "ssssjump/deleteszkjs",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}
//智库建设，条件查询 多功能一体
function goszkjsBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var cgname = $("#cgname9").val();
    var cnjb = $("#cnjb9").val();
    var dyzz = $("#dyzz9").val();
    var qtzz = $("#qtzz9").val();
    var cnbm = $("#cnbm9").val();
    var cntime = $("#cntime9").val();
    if(cgname == ''&&cnjb == ''&&dyzz == ''&&qtzz == ''&&cnbm == ''&&cntime == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (cgname == '') {
        cgname = "-1";
    }
    if (cnjb == '') {
        cnjb = "-1";
    }
    if (dyzz == '') {
        dyzz = "-1";
    }
    if (qtzz == '') {
        qtzz = "-1";
    }
    if (cnbm == '') {
        cnbm = "-1";
    }
    if (cntime == '') {
        cntime = "-1";
    }

    window.location.href = httptod + "ssssjump/goszkjspage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&cgname="
        + cgname + "&cnjb=" + cnjb + "&dyzz=" + dyzz+ "&qtzz=" + qtzz+ "&cnbm=" + cnbm+ "&cntime=" + cntime;
}

//智库建设 分页功能页码跳转
function goszkjsnext(pageNum) {
    let arr = zkjsSNextPageMessage();
    window.location.href = httptod + "ssssjump/goszkjspage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&cgname=" + arr[5] + "&cnjb=" + arr[6] + "&dyzz="
        + arr[7]+ "&qtzz=" + arr[8]+ "&cnbm=" + arr[9]+ "&cntime=" + arr[10];
}

function passauditzkjs() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddddjump/passauditzkjs",
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
                goszkjsBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddddjump/passnoauditzkjs",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                goszkjsBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function szkjsshow() {
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
            $("#points2").val(res.points);
            $("#fq2").val(res.fq);
        },
        error: function (res) {
            console.log(res);
        }
    })

}

function szkjsupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
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
            //把数据渲染到 页面上
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
    if (zid == null || zid == "" || cgname == null || cgname == "" || cnjb == null || cnjb == "" || dyzz == null ||
        dyzz == "" || qtzz == null || qtzz == "" || cnbm == null || cnbm == "" || cntime == null || cntime == "" ||
        points == "" || points == null) {
        alert("请填写完整！");
    } else {
        //先 添加一下文件 然后传递到数据库
        $.ajax({
            url: httptod + "ttttjump/zkjsupdate",
            type: "post",
            data: JSON.stringify({
                cgname: cgname, cnjb: cnjb, dyzz: dyzz, qtzz: qtzz, cnbm: cnbm, cntime: cntime, reward: reward, fq: fq,
                points: points, judgestatus: "2", zid: zid
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                goszkjsBydetail();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }


}

function szkjsexportall() {
    window.location.href = httptod + "ssssjump/szkjsexportall";
}


function downloadszkjsfjsc() {
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
            }
        })
    }
}

//成果转化
function goscgzhpage() {
    window.location.href = httptod + "ssssjump/goscgzhpage";
}
//成果转化--------------------------------------------------------------------------------------------------------检索查询
function scgzhQueryModal() {
    $("#scgzhQueryModal").modal('show');


}

function scgzhcheck() {
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
        url: httptod + "ssssjump/cgzhcheck",
        type: "post",
        data: {id: list[0]},
        dataType: "json",
        success: function (res) {
            // var timus="与以下题目相似度过高："+'\n';
            var timus="";
            for(var i = 0;i < res.length; i++){
                timus=timus+res[i]+'\n';

            }
            alert(timus);
        },
        error: function (res) {
            console.log(res);
        }
    })
}

function scgzhdelete() {
    let list = getSchoolSelect();
    console.log(list);
    if (list != false) {
        $.ajax({
            url: httptod + "ssssjump/deletescgzh",
            type: "post",
            data: JSON.stringify(list),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                window.location.reload();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
}
//成果转化，条件查询 多功能一体
function goscgzhBydetail() {
    var start = $("#start").val();
    var end = $("#end").val();
    var judgestatus = $("#judgestatus").val();
    var number = $("#number").val();
    var department = $("#department").val();

    var cgname = $("#cgname9").val();
    var dyzz = $("#dyzz9").val();
    var qtzz = $("#qtzz9").val();
    var zhdw = $("#zhdw9").val();
    var zrlx = $("#zrlx9").val();
    var zhtime = $("#zhtime9").val();
    if(cgname == ''&&dyzz == ''&&qtzz == ''&&zhdw == ''&&zrlx == ''&&zhtime == ''&&start == ''&&end == ''&&judgestatus == ''&&number == ''&&department == ''){
        alert("检索条件为空！");
        return ;
    }

    if (start == '') {
        start = "-1";
    }
    if (end == '') {
        end = "-1";
    }
    if (number == '') {
        number = "-1";
    }
    if (department == '') {
        department = "-1";
    }
    if (cgname == '') {
        cgname = "-1";
    }
    if (dyzz == '') {
        dyzz = "-1";
    }
    if (qtzz == '') {
        qtzz = "-1";
    }
    if (zhdw == '') {
        zhdw = "-1";
    }
    if (zrlx == '') {
        zrlx = "-1";
    }
    if (zhtime == '') {
        zhtime = "-1";
    }
    window.location.href = httptod + "ssssjump/goscgzhpage?start=" + start + "&end="
        + end + "&judgestatus=" + judgestatus + "&number=" + number + "&department=" + department+ "&cgname="
        + cgname  + "&dyzz=" + dyzz+ "&qtzz=" + qtzz+ "&zhdw=" + zhdw+ "&zrlx=" + zrlx+ "&zhtime=" + zhtime;
}

//成果转化 分页功能页码跳转
function goscgzhnext(pageNum) {
    let arr = cgzhSNextPageMessage();
    window.location.href = httptod + "ssssjump/goscgzhpage?pageNum=" + pageNum + "&department=" + arr[0] + "&number=" + arr[1]
        + "&start=" + arr[2] + "&end=" + arr[3] + "&judgestatus=" + arr[4] + "&cgname=" + arr[5]  + "&dyzz=" + arr[6]+ "&qtzz=" + arr[7]+ "&zhdw=" + arr[8]+ "&zrlx=" + arr[9]+ "&zhtime=" + arr[10];
}

function passauditcgzh() {
    var list = new Array();
    var otherlist = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddddjump/passauditcgzh",
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
                goscgzhBydetail();
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
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        if (judgestatus == 2) {
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
            url: httptod + "ddddjump/passnoauditcgzh",
            type: "post",
            data: JSON.stringify({ids: list, judgestatus: "5", schoolopinion: audit}),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                if (otherlist.length == 0) {
                    alert(res.description)
                } else {
                    alert("部分审核成功(状态为等待院系审核的部分操作完成，其它未操作)")
                }
                goscgzhBydetail();
            },
            error: function (res) {
                alert(res);
            }
        })
    }
}

function scgzhshow() {
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

function scgzhupdate() {
    var list = new Array();
    $.each($('input[name="selected"]:checkbox:checked'), function () {
        //针对其中状态 不合适的不让他能打开这个页面
        var row = $(this).parents("tr");
        var judgestatus = row.find("[name='judgestatus']").html();//注意html()和val()
        //只有处于学校审核状态  和 学校通过状态的才可以做修改  修改完毕后  再次审核通过与否
        if (judgestatus == 2 || judgestatus == 3) {
            list.push($(this).val());
        }
    });
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
    if (cid == null || cid == "" || cgname == null || cgname == "" || zrlx == null || zrlx == "" || zrdzjf == null || zrdzjf == ""
        || dyzz == null || dyzz == "" || qtzz == null || qtzz == "" || zhdw == null || zhdw == "" || zhtime == null
        || zhtime == "" || points == "" || points == null) {
        alert("请填写完整！");
    } else {
        //先 添加一下文件 然后传递到数据库
        $.ajax({
            url: httptod + "ttttjump/cgzhupdate",
            type: "post",
            data: JSON.stringify({
                cgname: cgname, zrlx: zrlx, zrdzjf: zrdzjf, dyzz: dyzz, qtzz: qtzz, zhdw: zhdw, zhtime: zhtime, fq: fq,
                reward: reward, points: points, judgestatus: "2", cid: cid
            }),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                alert(res.description);
                goscgzhBydetail();
            },
            error: function (res) {
                console.log(res);
            }
        })
    }


}

function scgzhexportall() {
    window.location.href = httptod + "ssssjump/scgzhexportall";
}


function downloadscgzhfjsc() {
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


function showRetransmissionModal(id, fjscname, type, name, number, department) {
    $("#newfile").val("");
    $("#newid").val(id);
    $("#newname").val(name);
    $("#newnumber").val(number);
    $("#newdepartment").val(department);
    $("#newfjscname").val(fjscname);
    $("#newtype").val(type);
    $("#retransmissionFile").modal("show");
}

function retransmission() {

    let id = $("#newid").val();
    let name = $("#newname").val();
    let number = $("#newnumber").val();
    let department = $("#newdepartment").val();
    let fjscname = $("#newfjscname").val();
    let type = $("#newtype").val();

    var newfile = $("#newfile")[0].files[0];
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
    formdata.append("file", newfile);

    formdata.append("id", id);
    formdata.append("name", name);
    formdata.append("number", number);
    formdata.append("department", department);
    formdata.append("fjscname", fjscname);
    formdata.append("type", type);

    $.ajax({
        url: httptod + "file/retransmission",
        type: "post",
        data: formdata,
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        async: false,
        success: function (res) {
            alert(res.description);
            $("#retransmissionFile").modal("hide");
            window.location.reload();

        },
        error: function (res) {
            alert(res.description)
        }
    })
}


