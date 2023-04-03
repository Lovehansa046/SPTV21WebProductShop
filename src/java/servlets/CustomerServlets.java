
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Product;
import entity.Customer;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.persistence.Id;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.HistoryFacade;
import session.CustomerFacade;

/**
 *
 * @author user
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {
    "/addCustomer",
    "/createCustomer",
    "/listCustomers",
    "/addMoneyCustomer"

})
public class CustomerServlets extends HttpServlet {

    @EJB
    CustomerFacade customerFacade;
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
            case "/addCustomer":
                request.getRequestDispatcher("/WEB-INF/customers/addCustomer.jsp").forward(request, response);
                break;
            case "/createCustomer":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                String money = request.getParameter("money");
                Customer customer = new Customer();
                customer.setPhone(phone);
                customer.setFirstname(firstname);
                customer.setLastname(lastname);
                customer.setMoney(Float.valueOf(money));
                customerFacade.create(customer);
                request.setAttribute("info", "Клиент успешно добавлен");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listCustomers":
                Map<Customer, List<Product>> mapCustomers = new HashMap();
                List<Customer> listCustomers = customerFacade.findAll();
                for (int i = 0; i < listCustomers.size(); i++) {
                    Customer c = listCustomers.get(i);
                    mapCustomers.put(c, historyFacade.getSaleProduct(c));
                }
                request.setAttribute("mapCustomers", mapCustomers);
                request.getRequestDispatcher("/WEB-INF/customers/listCustomers.jsp").forward(request, response);
                break;
            case "/addMoneyCustomer":
                Customer customers = new Customer();
                String addMoney = request.getParameter("money"); //Из строки забираем значение
                float addAccount = Float.valueOf(addMoney) + customers.getMoney(); //Производим добавление денег
                customers.setMoney(addAccount); //сохраняем данные
                request.setAttribute("info", "Деньги клиенту успешно добавлны");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                
//                customer.setMoney(Float.valueOf(money));
                
//                customer.setMoney(Float.valueOf(money));
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
