
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Product;
import entity.History;
import entity.Customer;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.ProductFacade;
import session.HistoryFacade;
import session.CustomerFacade;

/**
 *
 * @author user
 */
@WebServlet(name = "HistoryServlet", urlPatterns = {
    "/saleProduct",
    "/createHistory",
    //    "/returnBook",
    "/updateHistory",
    "/listHistories",})
public class HistoryServlets extends HttpServlet {

    @EJB
    CustomerFacade customerFacade;
    @EJB
    ProductFacade productFacade;
    @EJB
    HistoryFacade historyFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/saleProduct":
                List<Customer> listCustomers = customerFacade.findAll();
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listCustomers", listCustomers);
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/WEB-INF/history/saleProduct.jsp").forward(request, response);
                break;
            case "/createHistory":
                String productId = request.getParameter("productId");
                String customerId = request.getParameter("customerId");
                if (productId == null || productId.isEmpty() || customerId == null || customerId.isEmpty()) {
                    request.setAttribute("info", "Продукт или клиент не выбраны!");
                    request.getRequestDispatcher("/saleProduct").forward(request, response);
                    break;
                }
                Product product = productFacade.find(Long.parseLong(productId));
                if (product.getQuantity() > 0) {
                    Customer customer = customerFacade.find(Long.parseLong(customerId));
                    String countProduct = request.getParameter("countProduct"); //Клиент вводит кол-во товара

                    if (product.getQuantity() >= Integer.valueOf(countProduct)) { //Проверяем сколько есть товара в наличии
                        float priceProduct = Float.valueOf(countProduct) * product.getPrice(); //Считаем цену продукта исходя из кол-ва
                        if (priceProduct < customer.getMoney()) { //Проверяем сколько денег у клиента
                            float payCustomer = customer.getMoney() - priceProduct; //считаем сколько должен заплатить клиент
                            product.setQuantity(product.getQuantity() - 1);
                            productFacade.edit(product);
                            History history = new History();
                            history.setProduct(product);
                            float purchaseOnly = payCustomer + history.getPurchase(); //считатет прибыль магазина за все время
                            history.setPurchase(purchaseOnly); //Сохраняет данные
                            history.setPayCustomer(payCustomer); //Сохраняем сколько заплатил клиент
                            history.setCustomer(customer);
                            history.setSaleProduct(new GregorianCalendar().getTime());
                            historyFacade.create(history);
                            request.setAttribute("info", "Продукт продан");

                        } else {
                            request.setAttribute("info", "Продукт не продан, у клиента не достаточно средств");

                        }
                    } else {
                        request.setAttribute("info", "Продукт не продан, данного кол-во товара нет на складе");
                    }

                } else {
                    request.setAttribute("info", "Продукт не продан, товара нет на складе");

                }
                request.getRequestDispatcher("/saleProduct").forward(request, response);
                break;
//            case "/returnBook":
//                List<History> listHistoryWithTakedBooks = historyFacade.getHistoriesWithTakedBooks();
//                request.setAttribute("listHistoryWithTakedBooks", listHistoryWithTakedBooks);
//                request.getRequestDispatcher("/WEB-INF/history/returnBook.jsp").forward(request, response);
//                break;
//            case "/updateHistory":
//                String historyId = request.getParameter("historyId");
//                if(historyId == null || historyId.isEmpty()){
//                    request.setAttribute("info","Книга не выбрана");
//                    request.getRequestDispatcher("/returnBook").forward(request, response);
//                    break;
//                }
//                History history = historyFacade.find(Long.parseLong(historyId));
//                history.setReturnBook(new GregorianCalendar().getTime());
//                book = bookFacade.find(history.getBook().getId());
//                book.setQuantity(book.getQuantity()+1);
//                bookFacade.edit(book);
//                historyFacade.edit(history);
//                request.setAttribute("info","Книга возвращена");
//                request.getRequestDispatcher("/returnBook").forward(request, response);
//                break;
            case "/listHistories":
                request.setAttribute("listCustomers", customerFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/customers/listCustomers.jsp").forward(request, response);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
