<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head><title>Edit Researcher</title></head>
<body style="margin:0px;background:#ffffff">
<table align="center">
  <tr>
	<td><u><b>Edit Researcher</b></u></td>
  </tr>
</table align="center">
<table align="center">
  <tr>
    <stripes:form action="/MyIsern.action">
       <td bgcolor="#666699" width="40"><stripes:submit name="collabLink" value="Collaboration"/></td>
    </stripes:form> 
    <stripes:form action="/MyIsern.action">
       <td bgcolor="#666699" width="40"><stripes:submit name="orgLink" value="Organization"/></td>
    </stripes:form> 
    <stripes:form action="/MyIsern.action">
       <td bgcolor="#666699" width="40"><stripes:submit name="resLink" value="Researcher"/></td>
    </stripes:form>
  </tr>
</table>
<stripes:form id="ResEditForm" action="/MyIsern.action">
  <table align="center">
  <tr>
    
  </tr>
	<tr bgcolor="#99CCFF">
		<td>Name : </td>
		<td><stripes:text name="researcherName" value="${actionBean.researcherName}"/></td>
	</tr>
	<tr>
		<td>Organization : </td> 
		<td><stripes:text name="researcherOrg" value="${actionBean.researcherOrg}"/></td>
	</tr>
	<tr bgcolor="#99CCFF">
		<td>Email : </td>
		<td><stripes:text name="researcherEmail" value="${actionBean.researcherEmail}"/></td>
	</tr>
	<tr>
		<td>Picture-Link : </td>
		<td><stripes:text name="researcherPicLink" value="${actionBean.researcherPicLink}"/></td>
	</tr>
	<tr bgcolor="#99CCFF">
		<td>Bio-Statement : </td>
		<td><stripes:textarea name="researcherBio" value="${actionBean.researcherBio}"/></td>
	</tr>
  </table>
  <table align="center">
    <tr align="center">
	  <td><stripes:submit value="Edit" name="resEdit"/></td>
    </tr>
  </table>
</stripes:form>
</body>
</html>