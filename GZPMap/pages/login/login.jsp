<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <title>广州港务局应急管理平台一期</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">  
    <link href="${pageContext.request.contextPath}/content/css/login/login.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
    <script>
    function changeImg(){
		var img = document.getElementById("checkCodeImage1");
		img.src= "${pageContext.request.contextPath}/login.do?getImg&"+new Date().getTime(); 
	}
	
	 /** 按了回车键也可以进行登录 */
	 $(document).keydown(function(event){
		 if(event.keyCode == 13){
			 $("#login").trigger("click");
		 }
	 })
	
	$(function(){
		$("#login").click(function(){
			var name = $("#name");
			var pwd = $("#pwd");
			var checkCode = $("#checkCode");
			var msg="";
			if($.trim(name.val())==""){
				msg="用户名不能为空";
				name.focus();
			}else if(name.val().length<2||name.val().length>30){
				msg="用户名长度不符合要求";
				name.focus();
			}else if($.trim(pwd.val())==""){
				msg="密码不能为空";
				pwd.focus();
			}else if(pwd.val().length<2||pwd.val().length>30){
				msg="密码长度不符合要求";
				pwd.focus();
			}/* else if($.trim(checkCode.val())==""){
				msg="验证码不能为空";
				checkCode.focus();
			}else if(!/^[a-zA-Z0-9]{4}$/.test($.trim(checkCode.val()))){
				msg="验证码格式不符合要求";
				checkCode.focus();
			}
			 */
			if(!msg==""){
				$("#spanId").html(msg);
			}else{
				var params = $("#form").serialize();
				$.ajax({
					url:"${pageContext.request.contextPath}/login.do?check",
					type:"post",
					dataType:"json",
					data:params,
					success:function(data){
						if(data.success){
							window.location="${pageContext.request.contextPath}/home.do?homeUI";
						}else{
							$("#spanId").html(data.msg);
						}
					}
				});
			
			}
			
			
		});
		
		//绑定手形
		$("#login").mouseover(function(){
			$(this).css("cursor","pointer");
		});
	})
    </script>
  </head>
<body>

<div class="logoNav">
     <div class="logoImg"></div>
</div>
<div class="content">
	<form id="form" action="#" method="post">
    	<div class="login_top">
        	<div class="login_01"></div>
            <div class="login_02">
            	<div class="cee_1"><span>欢迎登录广州港务局应急管理平台一期</span></div>
                 <div class="dem">
                   <div class="regItem">
                   		 <div style="width:234px; height:25px;color:red;font-size:14px; line-height:25px; text-indent:0.8em; ">
             				<span id="spanId" style="color:red;font-size:14px;"></span>
                          </div>
                         <dl>
                             <dt>用户名：</dt>
                             <dd><input class="r_input" type="text"  id="name" name="name" type="text" class="user" placeholder="请输入用户名" value="admin" ></dd>
                         </dl>
                          <dl>
                             <dt>密&nbsp;&nbsp;&nbsp;码：</dt>
                             <dd><input id="pwd" class="r_input" name = "pwd" type="password" class="psw" placeholder="请输入密码" value="admin" /></dd>
                         </dl>
					 	<dl class="form_space">
                            <input type="button" class="btn-primary" id="login" value="登&nbsp;录" />
                            <input type="button" class="btn-primary" id="reset" value="重&nbsp;置" />
                         </dl>
                          
                      </div>
                   </div>
              </div>
          </div>
      </form>
</div>
<div class="footer">©21usc.com Inc. All Rights Reserved
广东邦鑫勘测科技股份有限公司 版权所有
</div>
</body>
</html>