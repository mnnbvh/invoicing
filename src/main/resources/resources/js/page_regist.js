$(document).ready(function(){
	
	//获取JS传递的语言参数
	var utils = new Utils();
	var args = utils.getScriptArgs();	
	
	
	//隐藏Loading/注册失败 DIV
	$(".loading").hide();
	$(".login-error").hide();
	registError = $("<label class='error repeated'></label>");
	
	//加载国际化语言包资源
	utils.loadProperties(args.lang);
	
	//输入框激活焦点、移除焦点
	jQuery.focusblur = function(focusid) {
		var focusblurid = $(focusid);
		var defval = focusblurid.val();
		focusblurid.focus(function(){
			var thisval = $(this).val();
			if(thisval==defval){
				$(this).val("");
			}
		});
		focusblurid.blur(function(){
			var thisval = $(this).val();
			if(thisval==""){
				$(this).val(defval);
			}
		});
	 
	};
	/*下面是调用方法*/
	$.focusblur("#name");
	
	//获取表单验证对象[填写验证规则]
	var validate = $("#signupForm").validate({
		rules: {
			name: {
				required: true,
				name: true
			},
			password: {
				required: true,
				minlength: 4,
				maxlength: 16
			},
			passwordAgain: {
				required: true,
				minlength: 4,
				maxlength: 16,
				equalTo: "#password"
			},
			gender: {
				required: true
			},
			age: {
				required: true
			},
			phonenumber: {
				required: true,
				digits:true
			},
			deptName: {
				required: true,
				digits:true
			}
		},
		messages: {
			name: {
				required: $.i18n.prop("请输入你的名字"),
			},
			password: {
				required: $.i18n.prop("请输入你的密码"),
				minlength: jQuery.format($.i18n.prop("密码长度大于3")),
				maxlength: jQuery.format($.i18n.prop("密码长度小于17"))
			},
			passwordAgain: {
				required: $.i18n.prop("确认你的密码"),
				minlength: jQuery.format($.i18n.prop("密码长度大于3")),
				maxlength: jQuery.format($.i18n.prop("密码长度小于17")),
				equalTo: jQuery.format($.i18n.prop("两次密码不一致"))
			},
			gender: {
				required: $.i18n.prop("请输入你的性别")
			},
			age: {
				required: $.i18n.prop("请输入你的年龄")
			},
			phonenumber: {
				required: $.i18n.prop("请输入你的手机号码"),
				digits: $.i18n.prop("格式错误")
			},
			deptName: {
				required: $.i18n.prop("请输入部门名称"),
			}
		}
	});
	
	
	//输入框激活焦点、溢出焦点的渐变特效
	if($("#name").val()){
		$("#name").prev().fadeOut();
	};
	$("#name").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#name").blur(function(){
		if(!$("#name").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#password").val()){
		$("#password").prev().fadeOut();
	};
	$("#password").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#password").blur(function(){
		if(!$("#password").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#passwordAgain").val()){
		$("#passwordAgain").prev().fadeOut();
	};
	$("#passwordAgain").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#passwordAgain").blur(function(){
		if(!$("#passwordAgain").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#gender").val()){
		$("#gender").prev().fadeOut();
	};
	$("#gender").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#gender").blur(function(){
		if(!$("#gender").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#age").val()){
		$("#age").prev().fadeOut();
	};
	$("#age").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#age").blur(function(){
		if(!$("#age").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#phonenumber").val()){
		$("#phonenumber").prev().fadeOut();
	};
	$("#phonenumber").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#phonenumber").blur(function(){
		if(!$("#phonenumber").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#deptName").val()){
		$("#deptName").prev().fadeOut();
	};
	$("#deptName").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#deptName").blur(function(){
		if(!$("#deptName").val()){
			$(this).prev().fadeIn();
		};		
	});
	
	//ajax提交注册信息
	$("#submit").bind("click", function(){
		regist(validate);
	});
	
	$("body").each(function(){
		$(this).keydown(function(){
			if(event.keyCode == 13){
				regist(validate);
			}
		});
	});
	
});



function regist(validate){	
	//校验name, password，校验如果失败的话不提交
	//if(validate.form()){
	/*var baselineType;
	var baselineTypeHtmlCol = document.getEementsByName("user");
	For(var i = 0; I < baselineTypeHtmlCol.length; I ++){
		If(baselineTypeHtmlCol[i].checked) {
			baselineType = baselineTypeHtmlCol[i].value;
		}
	}*/
		if($("#checkBox").attr("checked")){
			var md5 = new MD5();
			$.ajax({
				url: "./manager/register",
				type: "post",
				data: {
					name: $("#name").val(),
					password: md5.MD5($("#password").val()),
					gender: $("#gender").val(),
					age: $("#age").val(),
					phonenumber: $("#phonenumber").val(),
					user:$("input[name='user']:checked").val()


                },
				dataType: "json",
				beforeSend: function(){
					$('.loading').show();
				},
				success: function(data){
					alert("注册成功!!!");
					$('.loading').hide();
						if(data == "0"){
							//注册成功
							window.location.href = "/log/in";
						}else if(data == "1"){
							
							window.location.href = "register.html";
						}else if(data == 2){
							//参数传递失败
							$(".login-error").show();
							$(".login-error").html($.i18n.prop("Error.ParameterError"));
						}else if(data == 3){
							//公司已经被注册
							$("#age").addClass("error");
							$("#age").after(registError);						
							$("#age").next("label.repeated").text($.i18n.prop("Error.CompaniesAlreadyExists"));
							registError.show();
						}else if(data == 4){
							//邮箱已经被注册
							$("#name").addClass("error");
							$("#name").after(registError);
							$("#name").next("label.repeated").text($.i18n.prop("Error.nameAlreadyExists"));
							registError.show();
						}else{
							//系统错误
							$(".login-error").html($.i18n.prop("Error.SysError"));
						}
				}
			});
		}else{
			//勾选隐私政策和服务条款
			$(".login-error").show();
			$(".login-error").html($.i18n.prop("Error.ReadAndAgree"));
		}
	//}
}

var Utils = function(){};

Utils.prototype.loadProperties = function(lang){
	jQuery.i18n.properties({// 加载资浏览器语言对应的资源文件
		name:'ApplicationResources',
		language: lang,
		path:'resources/i18n/',
		mode:'map',
		callback: function() {// 加载成功后设置显示内容
		} 
	});	
};

Utils.prototype.getScriptArgs = function(){//获取多个参数
    var scripts=document.getElementsByTagName("script"),
    //因为当前dom加载时后面的script标签还未加载，所以最后一个就是当前的script
    script=scripts[scripts.length-1],
    src=script.src,
    reg=/(?:\?|&)(.*?)=(.*?)(?=&|$)/g,
    temp,res={};
    while((temp=reg.exec(src))!=null) res[temp[1]]=decodeURIComponent(temp[2]);
    return res;
};
