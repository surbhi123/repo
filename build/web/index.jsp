<%-- 
    Document   : index
    Created on : 1 Apr, 2014, 10:47:29 PM
    Author     : Aman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>bookmark</title>
    </head>
    <body>
        <form name="add" action="servlet1" method="POST">
            ADD: <input onclick="this.value='';" name="a1" type="text" value="<url>,<tag1>,<tag2>,<tag3>,..." />
            <input  name="add" type="submit" value="add"/>
        </form>
        <form name ="search" action="servlet2" method="POST">
            SEARCH: <input onclick="this.value='';" name="s1" type="text" value="<tag>"/>
            <input  name="search" type="submit" value="search"/>
        </form>
    </body>
</html>
