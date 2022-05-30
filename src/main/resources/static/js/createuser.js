$(document).ready(function () {
    $("#sisukses").hide();
    $("#sigagal").hide();
});

$("#btnSubmit").click(function () {
    var form = $('#my-form')[0];
    var data = new FormData(form);
    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/user/upload",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 800000,
        headers: {Authorization: localStorage.getItem("token")},
        success: function (data) {
            if (data.status == 200) {
                $("#sisukses").show();
                setTimeout(function () {
                    location.href = "/hcms/datauser";
                }, 1000);
            }
        },
        error: function (result) {
            if (result.status == 401) {
                alert(result.responseJSON.message);
                localStorage.removeItem("token");
                location.href = "/";
            }
        }
    });
});

$("#submit").click(function () {
    var confir = confirm("Apakah anda yakin akan melakukan submit ?");
    if (confir == true) {
        var user = "{" +
            "\"nik\" : \"" + $("#nikKaryawan").val() + "\"," +
            "\"nama\" : \"" + $("#namaKaryawan").val() + "\"," +
            "\"email\" : \"" + $("#emailKaryawan").val() + "\"," +
            "\"tanggal_lahir\" : \"" + $("#ttlKaryawan").val() + "\"," +
            "\"alamat\" : \"" + $("#alamatKaryawan").val() + "\"," +
            "\"divisi\" : \"" + $("#divisiKaryawan").val() + "\"," +
            "\"nik_manager\" : \"" + $("#nikManager").val() + "\"," +
            "\"role\": \"" + $("#roleKaryawan").val() + "\"," +
            "\"password\" : \"" + $("#nikKaryawan").val() + "\"" +
            "}";

        $.ajax({
            url: '/api/user/',
            type: 'POST',
            data: user,
            dataType: 'json',
            contentType: 'application/json',
            headers: {Authorization: localStorage.getItem("token")},
            success: function (result) {
                if (result.status == 200) {
                    $("#sisukses").show();
                    setTimeout(function () {
                        location.href = "/hcms/datauser";
                    }, 1000);
                } else {
                    $("#sigagal").show();
                    setTimeout(function () {
                        location.reload();
                    }, 1000)
                }
            },
            error: function (result) {
                if (result.status == 401) {
                    location.href = "/";
                }
            }
        });
    }
});

