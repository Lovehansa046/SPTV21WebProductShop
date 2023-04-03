

<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <h3 class="w-100 d-flex justify-content-center mt-5">Добавить продукт</h3>
        <div class="w-100 p-3 d-flex justify-content-center">
            <form action="createBook" method="POST">
                <div class="card border-0 m-2" style="width: 30rem;">
                    <div class="mb-3 row ">
                        <a class="w-100 d-flex justify-content-center" href="addCover">Загрузить обложку для продукта</a>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputName" class="col-sm-4 col-form-label">Название продукта</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="inputName" name="producName" value="${productName}">
                        </div>
                    </div>
<!--                    <div class="mb-3 row">
                        <label for="selectAuthor" class="col-sm-4 col-form-label">Авторы</label>
                        <div class="col-sm-8">
                            <select class="form-select" name="authors" multiple rows="5" cols="20" id="selectAuthor">
                                <c:forEach var="author" items="${listAuthors}">
                                    <option value="${author.id}">${author.firstname} ${author.lastname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>-->
<!--                    <div class="mb-3 row">
                        <label for="inputYear" class="col-sm-4 col-form-label">Год издания</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control w-50" id="inputYear" name="publishedYear" value="${publishedYear}">
                        </div>
                    </div>-->
                    <div class="mb-3 row">
                        <label for="inputQuantity" class="col-sm-4 col-form-label">Количество товара</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control w-25" id="inputQuantity" name="quantity" value="${quantity}">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputQuantity" class="col-sm-4 col-form-label">Цена товара</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control w-25" id="inputPrice" name="price" value="${price}">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="selectCover" class="col-sm-4 col-form-label">Обложки</label>
                        <div class="col-sm-8">
                            <select class="form-select" name="coverId" rows="5" cols="20" id="selectCover">
                                <c:forEach var="cover" items="${listCovers}">
                                    <option value="${cover.id}">${cover.description}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 d-flex justify-content-end">
                            <button class="btn btn-primary w-25" type="submit">Добавить</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    
