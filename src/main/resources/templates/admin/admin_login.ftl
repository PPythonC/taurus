<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Gentelella Alela! | </title>

    <!-- Bootstrap -->
    <link href="/admin/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="/admin/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="/admin/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="/admin/vendors/animate.css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="/admin/build/css/custom.min.css" rel="stylesheet">
</head>

<body class="login">
<div>
    <a class="hiddenanchor" id="signup"></a>
    <a class="hiddenanchor" id="signin"></a>

    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form class="loginBody">
                    <h1><@spring.message code='login.page.title'/></h1>
                    <div>
                        <input type="text"  id="loginName" name="loginName" class="form-control" placeholder="<@spring.message code='login.form.loginName'/>" required="" />
                    </div>
                    <div>
                        <input id="loginPwd" name="loginPwd" type="password" class="form-control" placeholder="<@spring.message code='login.form.loginPwd'/>" required="" />
                    </div>
                    <div>
                        <a class="btn btn-default submit" id="btnLogin" onclick="doLogin()"><@spring.message code="login.btn.login"/></a>
                        <a class="reset_pass" href="#"><@spring.message code='login.link.lostpassword'/></a>
                    </div>

                    <div class="clearfix"></div>

                    <div class="separator">
                        <p class="change_link"><@spring.message code='login.link.new'/>
                            <a href="#signup" class="to_register"> <@spring.message code='login.link.create_account'/> </a>
                        </p>

                        <div class="clearfix"></div>
                        <br />

                        <div>
                            <h1><i class="fa fa-paw"></i> <@spring.message code='page.company'/></h1>
                            <p><@spring.message code='page.message'/></p>
                        </div>
                    </div>
                </form>
            </section>
        </div>

        <div id="register" class="animate form registration_form">
            <section class="login_content">
                <form>
                    <h1><@spring.message code='login.page.tit'/></h1>
                    <div>
                        <input type="text" class="form-control" placeholder="<@spring.message code='login.form.registName'/>" required="" />
                    </div>
                    <div>
                        <input type="email" class="form-control" placeholder="<@spring.message code='login.form.reEmail'/>" required="" />
                    </div>
                    <div>
                        <input type="password" class="form-control" placeholder="<@spring.message code='login.form.registPassword'/>" required="" />
                    </div>
                    <div>
                        <a class="btn btn-default submit" href="/admin/index"><@spring.message code='login.btn.regist'/></a>
                    </div>

                    <div class="clearfix"></div>

                    <div class="separator">
                        <p class="change_link"><@spring.message code='login.page.tip'/>
                            <a href="#signin" class="to_register"><@spring.message code='login.btn.login'/></a>
                        </p>

                        <div class="clearfix"></div>
                        <br />

                        <div>
                            <h1><i class="fa fa-paw"></i> <@spring.message code='page.company'/></h1>
                            <p><@spring.message code='page.message'/></p>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>
</body>
<script src="/admin/vendors/jquery/dist/jquery.min.js"></script>
<script src="/admin/js/login.js"></script>
</html>
