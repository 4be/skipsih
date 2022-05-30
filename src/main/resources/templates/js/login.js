$(document).ready(function () {
    $("#sigagal1").hide();
    $("#sigagal2").hide();
});

$("#login").click(function () {
    var user = "{" +
        "\"username\" : \"" + $("#username").val() + "\"," +
        "\"password\" : \"" + $("#password").val() + "\"" +
        "}";

    $.ajax({
        url: '/api/login/',
        type: 'POST',
        data: user,
        dataType: 'json',
        contentType: 'application/json',
        success: function (result) {
            if (result.status == 200) {
                if (result.data.role == 'GM' || result.data.role == 'MANAGERPERSONALIA') {
                    localStorage.setItem("token", result.data.token);
                    location.href = "/hcms/";
                }if(result.data.role == 'STAFF'){
                    localStorage.setItem("token", result.data.token);
                    location.href = "/hcms/userabsen";
            }else {
                    $("#sigagal2").show();
                    setTimeout(function () {
                        location.reload();
                    }, 2000)
                }
            } else {
                $("#sigagal1").show();
                setTimeout(function () {
                    location.reload();
                }, 2000)
            }
        },
        error: function (result) {
            if (result.status == 401) {
                location.href = "/";
            }
        }
    });
});
