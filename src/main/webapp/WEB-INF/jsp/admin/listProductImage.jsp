<%--
  Created by IntelliJ IDEA.
  User: Jhon
  Date: 2018/8/3
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<ol class="breadcrumb">
    <li><a href="admin_category_list">所有分类</a></li>
    <li class="active">产品1</li>
    <li class="active">产品图片管理 </li>
</ol>
<div class="workingArea" style="position: absolute;left: 150px; top: 150px;">
<div class="listDataTableDiv">
    <table class="table table-striped table-bordered table-hover  table-condensed">
        <thead>
        <tr class="success">
            <th>ID</th>
            <th>产品单个缩略图</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pisSingle}" var="pi">

            <tr>
                <td>${pi.id}</td>
                <td><img height="40px" src="img/productImage/${pi.id}.jpg"></td>
                <td><a deleteLink="true" href="admin_productImage_delete?id=${pi.id}&pid=${p.id}"><span class="   glyphicon glyphicon-trash"></span></a></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="panel panel-warning addDiv">
    <div class="panel-heading">新增单个图片</div>
    <div class="panel-body">
        <form method="post" id="addForm" action="admin_productImage_add" enctype="multipart/form-data">
            <table class="addTable">
                <tr>
                    <td>分类图片</td>
                    <td>
                        <input id="categoryPic" accept="image/*" type="file" name="image" />
                    </td>
                </tr>
                <tr class="submitTR">
                    <td colspan="2" align="center">
                        <input type="hidden" name="pid" value="${p.id}">
                        <input type="hidden" name="type" value="type_single">
                        <button type="submit" class="btn btn-success">提交</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</div>
<div class="workingArea" style="position: absolute;right: 150px; top: 150px;">
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>产品详情图片缩略图</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pisDetail}" var="pi">

                <tr>
                    <td>${pi.id}</td>
                    <td><img height="40px" src="img/productImage/${pi.id}.jpg"></td>
                    <td><a deleteLink="true" href="admin_productImage_delete?id=${pi.id}&pid=${p.id}"><span class="   glyphicon glyphicon-trash"></span></a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增详情图片</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="admin_productImage_add" enctype="multipart/form-data">
                <table class="addTable">
                    <tr>
                        <td>分类图片</td>
                        <td>
                            <input id="productPic" accept="image/*" type="file" name="image" />
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="pid" value="${p.id}">
                            <input type="hidden" name="type" value="type_detail">
                            <button type="submit" class="btn btn-success">提交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>