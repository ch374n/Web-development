<%
    int number = Integer.parseInt(request.getParameter("number"));
    for(int i = 1; i <= 10; i++) {
        out.println(number + " * " + i + " = " + (number * i) + "</br>");
    }
%>
