
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.nimbalkar.chetan.DataController, com.nimbalkar.chetan.Book, com.nimbalkar.chetan.SignIn"%>
<%! DataController controller = new DataController(); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">        

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
    </head>
    <body>



    <nav class="navbar navbar-expand-sm bg-primary navbar-dark">      
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="../index.jsp">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="register.html">Register</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="login.html">Login</a>
        </li>

        <li class="nav-item">
          <a class="nav-link" href="Sell">Sell a book</a>
        </li>
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        Categories
      </a>
    
      <div class="dropdown-menu">
        <a class = "dropdown-item" href="search.jsp?categories=Action and Adventure">Action and Adventure</a> 
         <a class = "dropdown-item" href = "search.jsp?categories=Anthologies">Anthologies</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Art">Art</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Autobiographies">Autobiographies</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Biographies">Biographies</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Children">Children</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Comics">Comics</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Cookbooks">Cookbooks</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Diaries">Diaries</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Dictionaries">Dictionaries</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Drama">Drama</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Encyclopedia">Encyclopedia</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Educational">Educational</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Fantasy">Fantasy</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Guide">Guide</a>
         <a class = "dropdown-item" href = "search.jsp?categories=History">History</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Horror">Horror</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Math">Math</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Mystery">Mystery</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Poetry">Poetry</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Prayer books">Prayer books</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Religious">Religious</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Romance">Romance</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Satire">Satire</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Science">Science</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Science Fiction">Science Fiction</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Self help">Self help</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Series">Series</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Travel">Travel</a>
         <a class = "dropdown-item" href = "search.jsp?categories=Trilogies">Trilogies</a>
         <a class = "dropdown-item" href = "search.jsp?categories=ctional">Fictional</a>
      </div>
    </li>


        <a class="invisible">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Vero sit eaque minus dolor autem, totam option Lorem</a>
        <% if(SignIn.getSignIn()) { %>
        <a data-toggle="tooltip" title="Cart" href="" class="text-white mt-2">            
            <i class="fas fa-shopping-cart mr-2"></i>              
        </a>
        <a data-toggle="tooltip" title="Logout" href="LogOut" class="btn btn-info btn-md ml-3">
          <i class="fa fa-sign-out" aria-hidden="true"></i>
        </a>            
        <% } %>    
      </ul>
    </nav>

    <main role="main">

      </section>
      <div class="text-left">
          <h3>Search Results</h3>
      </div>
      <div class="album py-5 bg-light">
        <div class="container">

          <div class="row">
        <% String value = request.getParameter("categories"); %>
        <% String search = request.getParameter("searchterm");%>
                        
        <% for(Book b : controller.getSearchedCategory(value, search)) { %>
            <div class="col-md-4">
              <div class="card mb-4 box-shadow border border-dark p-2">
                    <div class="text-center bg-primary text-white p-5 display-5"><%= b.getName().toUpperCase() %></div>

                    <% if(search != null) {%>
                    <div class="text-center bg-info p-2 font-italic"><%= b.getCategory()%></div><% }%>                 

                    <div class="text-center bg-warning p-2"><%= b.getCondition() %></div>
                    <div class="text-center bg-success p-2">&dollar;<%= b.getPrice() %></div>
                <div class="card-body">
                
                  <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                      <button type="button" class="btn btn-sm btn-outline-secondary">Purchase</button>
                      <button type="button" class="btn btn-sm btn-outline-secondary">Add to cart</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
           <% } %>
           </div>
      </div>
     </div>
    </main>


		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

        
    </body>
</html>



