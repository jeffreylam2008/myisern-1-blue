<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head><title>MyIsern (version 1.3)</title></head>
<body>

<table align="center">
	<tr align="center">
		<td><u><b>Add New Entry</b></u></td>
	</tr>
	<tr>
		<td>
		<stripes:form id="TypeForm" action="/MyIsern.action">
		  <stripes:select size="1" name="type">
            <stripes:option value="1">Collaboration</stripes:option>
            <stripes:option value="2">Organization</stripes:option>
            <stripes:option value="3">Researcher</stripes:option>
          </stripes:select>
          <stripes:submit value="GO!" name="addType"/>
		</stripes:form>	
		</td>
	<tr>
</table>
<hr align="center" width="300">
<table align="center">
  <tr>
   <td>
    <table align="center">
      <tr>
        <td bgcolor="#6699FF" width="40"><h4><b>Collaboration</b></h4></td>
        <td bgcolor="#666699" width="50"><stripes:link href="/view_organization.jsp"><h4><font color="#FFFFFF">Organization</font></h4></stripes:link></td>
        <td bgcolor="#666699" width="50"><stripes:link href="/view_researcher.jsp"><h4><font color="#FFFFFF">Researcher</font></h4></stripes:link></td>
      </tr>
    </table>
    <table align="center" border="1">
      <tr>
        <td bgcolor="#99CCFF">Name</td>
        <td>Collaborating-Organizations</td>
        <td bgcolor="#99CCFF">Collaboration-Type</td>
        <td>Years</td>
        <td bgcolor="#99CCFF">Outcome-Types</td>
        <td>Description</td>
      </tr>
    </table>
   </td>
  </tr>
</table>
</body>
</html>