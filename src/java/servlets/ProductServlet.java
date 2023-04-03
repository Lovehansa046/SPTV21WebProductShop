/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

//import entity.Author;
import entity.Product;
import entity.Cover;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import session.AuthorFacade;
import session.ProductFacade;
import session.CoverFacade;

/**
 *
 * @author user
 */
@WebServlet(name = "ProductServlet", urlPatterns = {
    "/addProduct",
    "/createProduct",
    "/listProducts",
    "/product",
    
})
public class ProductServlet extends HttpServlet {
//    @EJB AuthorFacade authorFacade;
    @EJB ProductFacade productFacade;
    @EJB CoverFacade coverFacade;
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
            case "/addProduct":
//                request.setAttribute("listAuthors", authorFacade.findAll());
                request.setAttribute("listCovers", coverFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/products/addProduct.jsp").forward(request, response);
                break;
            case "/createProduct":
                String productName = request.getParameter("productName");
//                String[] authors = request.getParameterValues("authors");
//                String publishedYear = request.getParameter("publishedYear");
                String quantity = request.getParameter("quantity");
                String price = request.getParameter("price");
                String coverId = request.getParameter("coverId");
                if(productName.isEmpty() || quantity.isEmpty() || price.isEmpty()){
                    request.setAttribute("productName", productName);
//                    request.setAttribute("publishedYear", publishedYear);
                    request.setAttribute("quantity", quantity);
                    request.setAttribute("price", price);
                    request.setAttribute("info", "Заполните все поля.");
                    request.getRequestDispatcher("/addProduct").forward(request, response);
                    break;
                }
//                if(authors == null){
//                    request.setAttribute("bookName", bookName);
//                    request.setAttribute("publishedYear", publishedYear);
//                    request.setAttribute("quantity", quantity);
//                    request.setAttribute("info", "Вы не выбрали автора");
//                    request.getRequestDispatcher("/addBook").forward(request, response);
//                    break;
//                }
                if(coverId == null){
                    request.setAttribute("productName", productName);
//                    request.setAttribute("publishedYear", publishedYear);
                    request.setAttribute("quantity", quantity);
                    request.setAttribute("price", price);
                    request.setAttribute("info", "Вы не выбрали обложку для книги");
                    request.getRequestDispatcher("/addProduct").forward(request, response);
                    break;
                }
//                List<Author> listAuthors = new ArrayList<>();
//                for (int i = 0; i < authors.length; i++) {
//                   listAuthors.add(authorFacade.find(Long.parseLong(authors[i])));
//                }
                Product product = new Product();
//                product.setAuthors(listAuthors);
                product.setProductName(productName);
//                product.setPublishedYear(Integer.parseInt(publishedYear));
                product.setQuantity(Integer.parseInt(quantity));
                product.setPrice(Integer.parseInt(price));
                Cover cover = coverFacade.find(Long.parseLong(coverId));
                product.setCover(cover);
                productFacade.create(product);
//                for (int i = 0; i < listAuthors.size(); i++) {
//                    Author author = listAuthors.get(i);
//                    author.getBooks().add(book);
//                    authorFacade.edit(author);
//                }
                request.setAttribute("info", "Продукт добавлен успешно");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listProducts":
                request.setAttribute("listBooks", productFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/books/listBooks.jsp").forward(request, response);
                break;
            case "/product":
                String id = request.getParameter("id");
                request.setAttribute("product", productFacade.find(Long.parseLong(id)));
                request.getRequestDispatcher("/WEB-INF/products/product.jsp").forward(request, response);
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
