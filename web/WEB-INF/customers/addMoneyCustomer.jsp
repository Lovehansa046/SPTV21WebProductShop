<%-- 
    Document   : addMoneyCustomer
    Created on : 03.04.2023, 19:15:16
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="w-100 d-flex justify-content-center mt-5">Добавить клиенту денег</h3>
<div class="w-100 p-3 d-flex justify-content-center">

    <div class="card border-0 m-2" style="width: 23rem;">
        <form action="addMoneyCustomer" method="POST">
            <div class="mb-3 row ">
                <select name="customerId" class="form-select form-select-sm" aria-label=".form-select-sm example">
                    <option value="#" disabled selected>Выберите покупателя</option>
                    <c:forEach var="customer" items="${listCustomers}">
                        <option value="${customer.id}">${customer.firstname} ${customer.lastname}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3 row">
                        <label for="inputMoney" class="col-sm-3 col-form-label">Вносимая сумма денег</label>
                        <div class="col-sm-9">
                          <input type="text" class="form-control" id="inputPhone" name="money" value="${money}">
                        </div>
                    </div>
            <div class="mb-3 row d-flex justify-content-center">    
                <button type="submit" class="btn btn-secondary w-50">Обновить денежные средства</button>
            </div>
        </form>
    </div>