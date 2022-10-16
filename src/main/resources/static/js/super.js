function canLogin(number) {
    console.log(number)
    let loginstatus = prompt("1为可以登录，0为不可以登录");
    if (loginstatus == null) {
        return;
    } else if (loginstatus == '1' || loginstatus == '0') {
        $.ajax({
            url: httptod + "login/canLoginUpdate",
            type: "post",
            data: {
                number: number,
                loginstatus: loginstatus,
            },
            dataType: "json",
            success: function (data) {
                alert(data.description);
                location.reload();
            },
            error: function (data) {
                alert(data.description);
            }
        })
        return;
    } else {
        alert("请不要输入其它");
        return;
    }
}

function goSuperNext(pageNum) {
    var select_department = $("#select_department").val();
    var select_tea_id = $("#select_tea_id").val();
    var select_name = $("#select_name").val();
    if (select_tea_id == '') {
        select_tea_id = "-1";
    }
    if (select_name == '') {
        select_name = "-1";
    }
    window.location.href = httptod + "super/gosuperpage?pageNum="
        + pageNum + "&department=" + select_department + "&number=" + select_tea_id + "&name=" + select_name;
}

function oldDatamodlaShow() {
    $("#oldDataModal").modal('show');
}

function oldDataImport() {
    let formFile = new FormData();
    let file = $("#oldDataFile")[0].files[0];
    let excelTypeFile = $("#ExcelfileType").val();
    let filetype = getFileType(file.name);
    formFile.append("file", file); //加入文件对象
    formFile.append("filetype", filetype); //加入文件对象
    formFile.append("excelTypeFile", excelTypeFile); //加入文件对象
    console.log(formFile);

    $.ajax({
        url: httptod + "file/oldDataImport",
        type: "post",
        data: formFile,
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        dataType: "json",
        async: false,
        success: function (data) {
            alert(data.description);
            $("#oldDataFile").modal("hide");
            $("#oldDataFile").val("");
            // location.href = httptod + "jump/goListPeople";
            location.reload(true);
        },
        error: function (res) {
            alert("导入失败");
        }
    })
}