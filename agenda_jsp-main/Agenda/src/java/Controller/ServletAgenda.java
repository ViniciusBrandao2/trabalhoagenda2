package Controller;

import Model.Contato;
import Model.ContatoDAOImpl;
import Model.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletAgenda extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (!request.getSession().isNew() && usuario != null) {
            ArrayList<Contato> contatos = new ContatoDAOImpl().carregar(usuario);
            if (!contatos.isEmpty()) {
                request.setAttribute("contatos", contatos);
            } else {
                request.setAttribute("agendavazia", "A agenda ainda não possui contatos.");
            }
        }

        request.getRequestDispatcher("agenda.jsp").forward(request, response);
    }
}
