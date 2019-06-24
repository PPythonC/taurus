function doLogin() {
    console.log("hhhha");
    var btnLogin = $('#btnLogin');
    var name = $("#loginName");
    var pwd = $("#loginPwd");
    if (name.val() === "") {
        alert("请输入用户名/密码");
        window.location.href="/admin/index";
        btnLogin.button('reset');
    } else if (pwd.val() == "") {
        alert("请输入密码");
    } else {
        $.post("/admin/getLogin",{loginName:name.val(), loginPwd :pwd.val()},function (data) {
            localStorage.setItem("loginName",name.val());
            if(data.code==1)
            {
                alert("成功");
                window.location.href="/admin/index";
            }

        },"JSON");

    }
}

