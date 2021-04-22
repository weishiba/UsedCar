$(function () {
    $("#datas").load(base + "/permission/book/list");
    $("#searchForm").submit(function (event) {
        event.preventDefault();
        $("#datas").load(base + "/permission/book/list?searchName=" + $("#searchName").val());
    });

    //异步登录提示
    $("#loginForm").submit(function (event) {
        event.preventDefault();
        $.ajax({
            url: "/usedcar/user/login",
            type: "post",
            data: $(this).serialize(),
            success: function (data) {
                if (data == 1) {
                    //登录成功,跳转到主页面
                    window.location.href = "/usedcar/car/home";
                } else if (data == -1) {
                    //用户名或密码不正确
                    $("#loginHint").html("<b>用户名或密码不正确</b>");
                } else if (data == 0) {
                    //说明验证码不正确
                    $("#loginHint").html("<b>验证码不正确!</b>");
                }
            }
        });
    });
    //异步注册
    $("#registerForm").submit(function (event) {
        event.preventDefault();
        $.ajax({
            url: "/usedcar/user/register2",
            type: "post",
            data: $(this).serialize(),
            success: function (data) {
                if (data == 1) {
                    //注册成功,跳转到登录页面
                    window.location.href ="/usedcar/user/index?info=1";
                } else if (data == 0) {
                    //用户名不正确
                    $("#rigisterHint").html("<b>用户名已被注册</b>");
                } else if (data == -1) {
                    //
                    $("#rigisterHint").html("<b>两次密码不一致</b>");
                }
            }
        });
    });
    $("#icon").click(function () {
        var type = $("#pwd").attr("type");
        if (type == "password") {
            $("#icon").attr("class", "iconfont icon-showpwd");
            $("#pwd").prop("type", "text");
            $("#pwd1").prop("type", "text");
        } else {
            $("#icon").attr("class", "iconfont icon-hiddenpwd");
            $("#pwd").prop("type", "password");
            $("#pwd1").prop("type", "password");
        }
    });
});

function go(pageNow) {
    $("#datas").load(base + "/permission/book/list?pageNow=" + pageNow + "&searchName=" + $("#searchName").val());
}


function list() {
    window.location.href = base + "/permission/book/index";
}

function uploadImg(id) {
    window.location.href = base + "/permission/book/uploadImg?id=" + id;
}

function login() {
    window.location.href = "/usedcar/user/index";
}

function register() {
    window.location.href = "/usedcar/user/register";
}

function editBook(id) {
    window.location.href = base + "/permission/book/editBook?info=2&id=" + id;
}

function deleteBook(id) {
    if (confirm("确定要删除这本书吗？")) {
        window.location.href = base + "/permission/book/deleteBook?id=" + id;
    } else {
        window.location.href = base + "/permission/book/index";
    }
}

//购买图书
function buyBook(id, uid) {
    window.location.href = base + "/permission/book/buyBook?id=" + id + "&uid  =" + uid;
}

function addBook() {
    window.location.href = base + "/permission/book/addBook?info=1";
}



function leave() {
    alert("谢谢使用，已安全退出。");
    window.location.href = "/usedcar/user/leave";
}

