import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//TRAEMOS EL PATH
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        //CREAMOS LA SESION PARA PODER ENVIAR LOS PRODUCTOS
        HttpSession session = req.getSession();
        //CREAMOS UNA LISTA PARA EL ARTICULO Y VALOR
        List<String> articulo=(List<String>)session.getAttribute("articulo");
        List<Integer> valor=(List<Integer>)session.getAttribute("valor");
        //CREAMOS UN CONDICIONAL PARA VER SI LA LISTA ESTA VACIA
        if (articulo==null||valor==null) {
            //INICIALIZAMOS LA LISTA
            articulo=new ArrayList<>();
            //AQUI SETEAMOS EL ARTICULO CON LLAVE Y SU VALOR
            session.setAttribute("articulo",articulo);
        }
        if (valor==null) {
            valor=new ArrayList<>();
            //AQUI SETEAMOS EL VALOR
            session.setAttribute("valor",valor);
        }
        //PROCESAMOS EL NUEVO ARTICULO
        String articuloNuevo = req.getParameter("articulo");
        int valorNuevo=Integer.parseInt(req.getParameter("valor"));
        //VAMOS A HACER EL CONDICIONAL PARA VERIFICAR
        //SI EL NUEVO ARTICULO NO TENGA ESPACIOS
        //Y QUE EL ARTICULO NO TENGA ESPACIOS
        //valorNuevo!=0 (VERIFICA EL VALOR DE LA VARIABLE PARA QUE NO SEA IGUAL A 0)
        if (articuloNuevo!=null && !articuloNuevo.trim().equals("") && valorNuevo!=0) {
            articulo.add(articuloNuevo);
            valor.add(valorNuevo);
        }
        //IMPRIMIR LA LISTA
        try(PrintWriter out = resp.getWriter()){
            out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.print("<head>");
            out.printf("<meta charset=\"utf-8\">");
            out.printf("<title> Hola Mundo </title>");
            out.print("</head>");
            out.print("<body>");
            out.print("<h1>LISTA DE ARTICULOS</h1>");
            out.print("<br>");
            //DESDE AQUI VAMOS A TRAER A LA LISTA
            //ITERANDO TODOS LOS ARTICULOS
            for (String art:articulo) {
                out.print(art);
                out.print("<br>");
            }
            //IMPRIMIMOS LOS VALORES MEDIANTE FOR
            for (Integer val:valor) {
                out.print(val);
                out.print("<br>");
            }
            //CREAMOS UN BOTON PARA IR ATRAS
            out.print("<br>");
            out.print("<br>");
            out.print("<a href='index.html'>IR AL INICIO</a>");
            out.print("</body>");
            out.print("</html>");
        }

    }
}
