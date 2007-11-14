<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head><title>Add Researcher</title></head>
<body style="margin:0px;background:#ffffff">
<table align="center">
  <tr>
	<td><u><b>Add New Researcher</b></u></td>
  </tr>
</table align="center">
<table align="center">
  <tr>
    <td><stripes:link href="/view_collaboration.jsp"><h4>Collaboration</h4></stripes:link></td>
    <td><stripes:link href="/view_organization.jsp"><h4>Organization</h4></stripes:link></td>
    <td><stripes:link href="/view_researcher.jsp"><h4>Researcher</h4></stripes:link></td>
  </tr>
</table>
<stripes:form id="ResAddForm" action="/MyIsern.action">
  <table align="center">
	<tr bgcolor="#C0C0C0">
		<td>Name : </td>
		<td><stripes:text name="resName"/></td>
	</tr>
	<tr>
		<td>Organization : </td>
		<td><stripes:text name="resOrg"/></td>
	</tr>
	<tr bgcolor="#C0C0C0">
		<td>Email : </td>
		<td><stripes:text name="resEmail"/></td>
	</tr>
	<tr>
		<td>Picture-Link : </td>
		<td><stripes:text value="http://" name="resPicLink"/></td>
	</tr>
	<tr bgcolor="#C0C0C0">
		<td>Bio-Statement : </td>
		<td><stripes:text name="resBio"/></td>
	</tr>
  </table>
  <table align="center">
    <tr align="center">
	  <td><stripes:submit value="Add" name="resAdd"/>&nbsp;&nbsp;<stripes:reset value="Reset" name="reset"/></td>
    </tr>
  </table>
</stripes:form>
</body>
</html>