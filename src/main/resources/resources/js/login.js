$(document).ready(function(){
	$("#submit").bind("click", function(){
			regist(validate);
		});
};
function regist(validate){	
	//校验name, password，校验如果失败的话不提交
	//if(validate.form()){
		if($("#checkBox").attr("checked")){
			alert("11111");
			var md5 = new MD5();
			$.ajax({
				url: "./manager/login",
				type: "post",
				data: {
					username: $("#username").val(),
					password: md5.MD5($("#password").val()),
				},
				dataType: "json",
				beforeSend: function(){
					$('.loading').show();
				},
				success: function(data){
					alert(data);
					$('.loading').hide();
						if(data == "0"){
							//登录失败
							window.location.href = "login.html";
						}else{
							//登录成功
							window.location.href = "index?userId="+data;
						}
				}
			});
		}else{
			alert("333333333");
			//勾选隐私政策和服务条款
			$(".login-error").show();
			$(".login-error").html($.i18n.prop("Error.ReadAndAgree"));
		}
	//}
}