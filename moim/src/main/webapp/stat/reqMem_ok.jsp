<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="mdto" class="com.moim.stat.StatDTO"></jsp:useBean>
<jsp:setProperty property="*" name="mdto"/>
<jsp:useBean id="mdao" class="com.moim.stat.StatDAO"></jsp:useBean>

<%
int idx_member=0;
int idx_info=0;
String content=request.getParameter("contentApply");

int result=mdao.reqMem(idx_member, idx_info, content);
String msg=result>0?"가입 신청이 완료되었습니다.":"가입 신청을 실패하였습니다. 다시 시도해주세요.";
%>