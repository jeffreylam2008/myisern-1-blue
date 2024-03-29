<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head><title>Add Collaboration</title></head>
<body style="margin:0px;background:#ffffff">
<table align="center">
  <tr>
	<td><u><b>Add New Collaboration</b></u></td>
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
<stripes:form id="CollabAddForm" action="/Collaboration.action">
  <table align="center">
	<tr bgcolor="#C0C0C0">
		<td>Name : </td>
		<td><stripes:text name="collabName"/></td>
	</tr>
	<tr>
		<td>Collaborating-Organizations : </td>
		<td><stripes:text name="collabOrganizations"/></td>
	</tr>
	<tr bgcolor="#C0C0C0">
		<td>Collaboration-Type : </td>
		<td><stripes:text name="collabType"/></td>
	</tr>
	<tr>
		<td>Years : </td>
		<td><stripes:text name="collabYears"/></td>
	</tr>
	<tr bgcolor="#C0C0C0">
		<td>Outcome-Types : </td>
		<td><stripes:text name="collabOutcomeTypes"/></td>
	</tr>
	<tr>
		<td>Description : </td>
		<td><stripes:textarea name="collabDescription"/></td>
	</tr>
  </table>
  <table align="center">
    <tr align="center">
	  <td><stripes:submit value="Add" name="collabAdd"/>&nbsp;&nbsp;<stripes:reset value="Reset" name="reset"/></td>
    </tr>
  </table>
</stripes:form>
<p>
<table align="center" id="ErrorMessageTable">
  <tr><td><font color="red">${actionBean.errorMessage}</font></td></tr>
</table>
</body>
</html>