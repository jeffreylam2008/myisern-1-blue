<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head><title>MyIsern (version 1.3)</title></head>
<body>

<table>
	<tr>
		<td><u><b>Add</b></u></td>
	</tr>
	<tr>
		<td>
		<stripes:form id="TypeForm" action="/MyIsern.action">
		  <stripes:select size="1" name="type">
            <stripes:option value="1">Collaboration</stripes:option>
            <stripes:option value="2">Organization</stripes:option>
            <stripes:option value="3">Researcher</stripes:option>
          </stripes:select>
          <stripes:submit value="Add" name="addType"/>
		</stripes:form>	
		</td>
	<tr>
</table>

</body>
</html>