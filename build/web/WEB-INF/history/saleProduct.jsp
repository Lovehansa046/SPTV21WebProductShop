<%-- 
    Document   : listBooks
    Created on : Feb 28, 2023, 11:10:00 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

       <h3 class="w-100 d-flex justify-content-center mt-5">Продать продукт покупателю</h3>
       <div class="w-100 p-3 d-flex justify-content-center">
           
            <div class="card border-0 m-2" style="width: 23rem;">
                <form action="createHistory" method="POST">
                    
                    <div class="card-body">
                        <div class="mb-3 row ">
                            <select name="productId" class="form-select form-select-sm" aria-label=".form-select-sm example">
                                <option value="#" disabled selected>Выберите продукт</option>
                                <c:forEach var="product" items="${listProducts}">
                                    <option value="${product.id}">${product.productName} ${product.price}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3 row ">
                            <select name="customerId" class="form-select form-select-sm" aria-label=".form-select-sm example">
                                <option value="#" disabled selected>Выберите покупателя</option>
                                <c:forEach var="customer" items="${listCustomers}">
                                    <option value="${customer.id}">${customer.firstname} ${customer.lastname}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3 row d-flex justify-content-center">    
                            <button type="submit" class="btn btn-secondary w-50">Продать продукт</button>
                        </div>
                    </div>
                </form>
             </div>
           
       </div>
  