<%--
  Created by IntelliJ IDEA.
  User: Jhon
  Date: 2018/8/3
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<div class="panel panel-warning addDiv">
    <div class="panel-heading">修改产品</div>
    <div class="panel-body">
        <form method="post" id="addForm" action="admin_product_update" enctype="multipart/form-data">
            <table class="addTable">
                <tr>
                    <td>产品名称</td>
                    <td><input  id="name" name="name" type="text" class="form-control"></td>
                </tr>
                <tr>
                    <td>产品小标题</td>
                    <td><input  id="subTitle" name="subTitle" type="text" class="form-control"></td>
                </tr>
                <tr>
                    <td>原价格</td>
                    <td><input  id="originalPrice" name="originalPrice" type="text" class="form-control"></td>
                </tr>
                <tr>
                    <td>优惠价格</td>
                    <td><input  id="promotePrice" name="promotePrice" type="text" class="form-control"></td>
                </tr>
                <tr>
                    <td>库存数量</td>
                    <td><input  id="stock" name="stock" type="text" class="form-control"></td>
                </tr>
                <tr class="submitTR">
                    <td colspan="2" align="center">
                        <input type="hidden" name="cid" value="${cid}">
                        <input type="hidden"  name="id" value="${p.id}">
                        <button type="submit" class="btn btn-success">提交</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
