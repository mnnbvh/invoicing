<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head runat="server">
    <title></title>
    <script src="../lib/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
    <style type="text/css">
        .t1
        {
            clear: both;
            border: 1px solid #c9dae4;
        }
        .t1 tr th
        {
            color: #0d487b;
            background: #f2f4f8 url(../CSS/Table/images/sj_title_pp.jpg) repeat-x left bottom;
            line-height: 28px;
            border-bottom: 1px solid #9cb6cf;
            border-top: 1px solid #e9edf3;
            font-weight: normal;
            text-shadow: #e6ecf3 1px 1px 0px;
            padding-left: 5px;
            padding-right: 5px;
        }
        .t1 tr td
        {
            border-bottom: 1px solid #e9e9e9;
            padding-bottom: 5px;
            padding-top: 5px;
            color: #444;
            border-top: 1px solid #FFFFFF;
            padding-left: 5px;
            padding-right: 5px;
            word-break: break-all;
        }
        /* white-space:nowrap; text-overflow:ellipsis; */
        tr.alt td
        {
            background: #ecf6fc; /*这行将给所有的tr加上背景色*/
        }
        tr.over td
        {
            background: #bcd4ec; /*这个将是鼠标高亮行的背景色*/
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () { //这个就是传说的ready  
            $(".t1 tr").mouseover(function () {
                //如果鼠标移到class为stripe的表格的tr上时，执行函数  
                $(this).addClass("over");
            }).mouseout(function () {
                //给这行添加class值为over，并且当鼠标一出该行时执行函数  
                $(this).removeClass("over");
            }) //移除该行的class  
            $(".t1 tr:even").addClass("alt");
            //给class为stripe的表格的偶数行添加class值为alt
        });
    </script>
</head>
<body>
    <form id="form1" runat="server" action="../sale/addSaleOrder">
   
    <table width="100%" id="ListArea" border="0" class="t1" align="center" cellpadding="0"
        cellspacing="0">
        <tr align="center">
            <th>
               客户ID
            </th>
            <th>
                商品名称
            </th>
            <th>
                单价
            </th>
            <th>
                数量
            </th>
           
        </tr>
        <c:forEach items="${itemList}" var="saleItem">        
	        <tr align="center">	            
	            <td>
	                ${saleItem.customerId}
	            </td>
	            <td>
	                ${saleItem.name}
	            </td>
	            <td>
	                ${saleItem.price}
	            </td>
	            <td>
	                ${saleItem.count}
	            </td>
	        </tr>
        </c:forEach>
        <tr>
        	<th>
        		支付方式：
        	</th>
        	<th>
        		<select name="pay" class="select">
					<option value="现金">现金</option>
					<option value="银行">银行卡</option>
					<option value="支付宝">支付宝</option>
					<option value="网银">网银</option>					
				</select>
        	</th>
        	<th>
        		<input type="submit" value="提交">
        	</th>
        	<th>
        		<input type="reset" value="重置">
        	</th>
        </tr>
        
    </table>
    </form>
</body>
</html>