<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head><title>Edit Organization</title></head>
<body style="margin:0px;background:#ffffff">
<table align="center">
  <tr>
	<td><u><b>Edit Organization</b></u></td>
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
<stripes:form id="OrgEditForm" action="/MyIsern.action">
  <table align="center">
	<tr bgcolor="#99CCFF">
		<td>Name : </td>
    <td><stripes:text name="organizationName" value="${actionBean.organizationName}"/></td>
	</tr>
	<tr>
		<td>Type : </td>
		<td><stripes:text name="organizationType" value="${actionBean.organizationType}"/></td>
	</tr>
	<tr bgcolor="#99CCFF">
		<td>Contact : </td>
    <td><stripes:text name="organizationContact" value="${actionBean.organizationContact}"/></td>
	</tr>
	<tr>
		<td>Affiliated-Researchers : </td>
    <td><stripes:text name="organizationResearchers" value="${actionBean.organizationResearchers}"/></td>
	</tr>
	<tr bgcolor="#99CCFF">
		<td >Country : </td>
    <td><stripes:text name="organizationCountry" value="${actionBean.organizationCountry}"/></td>
	</tr>
	<tr>
		<td>Research Keywords : </td>
    <td><stripes:text name="organizationKeywords" value="${actionBean.organizationKeywords}"/></td>
	</tr>
	<tr bgcolor="#99CCFF">
		<td>Research Description : </td>
    <td><stripes:textarea name="organizationDescription" value="${actionBean.organizationDescription}"/></td>
	</tr>
	<tr>
		<td>Home-Page : </td>
    <td><stripes:text name="organizationHomepage" value="${actionBean.organizationHomepage}"/></td>
	</tr>
  </table>
  <table align="center">
    <tr align="center">
	  <td><stripes:submit value="Edit" name="orgEdit"/>&nbsp;&nbsp;<stripes:reset value="Reset" name="reset"/></td>
    </tr>
  </table>
</stripes:form>
</body>
</html>