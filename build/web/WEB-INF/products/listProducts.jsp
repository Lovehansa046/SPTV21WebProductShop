<%-- 
    Document   : listBooks
    Created on : Feb 28, 2023, 11:10:00 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

       <h3 class="w-100 d-flex justify-content-center mt-5">Список продуктов</h3>
       <div class="w-100 p-3 d-flex justify-content-center">
           <c:forEach var="book" items="${listProducts}">
            <div class="card m-2" style="width: 13rem;">
                <img src="insertFile/${product.cover.url}" width="200" height="290"/> 
                <div class="card-body">
                    <h5 class="card-title">${product.productName}</h5>
<!--                    <p class="card-text">Авторы: 
                         <c:forEach var="author" items="${product.authors}">
                             ${author.firstname} ${author.lastname}
                         </c:forEach>
                    </p>-->
<!--                    <p class="card-text">Год издания: ${book.publishedYear}</p>-->
                    <p class="card-text">Количество товара: ${product.quantity}</p>
                    <p class="card-text">Цена товара: ${product.price}</p>
                </div>
             </div>
           </c:forEach>
       </div>
  