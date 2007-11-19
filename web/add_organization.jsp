<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head><title>Add Organization</title></head>
<body style="margin:0px;background:#ffffff">
<table align="center">
  <tr>
	<td><u><b>Add New Organization</b></u></td>
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
<stripes:form id="OrgAddForm" action="/Organization.action">
  <table align="center">
	<tr bgcolor="#C0C0C0">
		<td>Name : </td>
		<td><stripes:text name="orgName"/></td>
	</tr>
	<tr>
		<td>Type : </td>
		<td><stripes:text name="orgType"/></td>
	</tr>
	<tr bgcolor="#C0C0C0">
		<td>Contact : </td>
		<td><stripes:text name="orgContact"/></td>
	</tr>
	<tr>
		<td>Affiliated-Researchers : </td>
		<td><stripes:text name="orgAffiliated"/></td>
	</tr>
	<tr bgcolor="#C0C0C0">
		<td >Country : </td>
		<td><stripes:text name="orgCountry"/></td>
	</tr>
	<tr>
		<td>Research Keywords : </td>
		<td><stripes:text name="orgKeyword"/></td>
	</tr>
	<tr bgcolor="#C0C0C0">
		<td>Research Description : </td>
		<td><stripes:textarea name="orgDescription"/></td>
	</tr>
	<tr>
		<td>Home-Page : </td>
		<td><stripes:text value="http://" name="orgHomePage"/></td>
	</tr>
  </table>
  <table align="center">
    <tr align="center">
	  <td><stripes:submit value="Add" name="orgAdd"/>&nbsp;&nbsp;<stripes:reset value="Reset" name="reset"/></td>
    </tr>
  </table>
</stripes:form>
<p>
<table align="center" id="ErrorMessageTable">
  <tr><td><font color="red">${actionBean.errorMessage}</font></td></tr>
</table>
</body>
</html>