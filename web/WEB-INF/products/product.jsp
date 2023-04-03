<%-- 
    Document   : book
    Created on : Mar 2, 2023, 11:05:35 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 p-3 d-flex justify-content-center">
    <div class="card m-2" style="width: 13rem;">
        <img src="insertFile/${product.cover.url}"/>
        <div class="card-body">
        <h5 class="card-title">${product.productName}</h5>
<!--        <p class="card-text">Авторы: 
             <c:forEach var="author" items="${book.authors}">
                 ${author.firstname} ${author.lastname}
             </c:forEach>
        </p>-->
<!--        <p class="card-text">Год издания: ${book.publishedYear}</p>-->
        <p class="card-text">Количество товара: ${product.quantity}</p>
        <p class="card-text">Цена товара: ${product.price}</p>
        </div>
    </div>
</div>
