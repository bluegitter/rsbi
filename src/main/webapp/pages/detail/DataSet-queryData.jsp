<%
/*
 * Copyright 2018 本系统版权归成都睿思商智科技有限公司所有 
 * 用户不能删除系统源码上的版权信息, 使用许可证地址:
 * https://www.ruisitech.com/licenses/index.html
 */
%>﻿<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" pageEncoding="utf-8"%>
<%
List ds = (List)request.getAttribute("ls");
List tit = (List)ds.get(0);
%>
(前50行数据。)
<table class="grid3" id="T_report54" cellpadding="0" cellspacing="0">
<thead>
<tr class="scrollColThead" style="background-color:#FFF">
<%
for(int i=0; i<tit.size(); i++){
%>
	<th><div><%=tit.get(i)%></div></th>
<%
}
%>
</tr>
<%
for(int i=1; i<ds.size(); i++){
	Map m = (Map)ds.get(i);
	out.print("<tr>");
	for(int j=0; j<tit.size(); j++){
%>
	<td class='kpiData1 grid3-td'><%=(m.get(tit.get(j)) == null ? "" : m.get(tit.get(j)))%></td>	
<%
	}
	out.print("</tr>");
}
%>
 </thead>
</table>