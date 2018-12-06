package br.unisul.projeto.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.unisul.projeto.domain.Advogado;

public class LoginFilter implements javax.servlet.Filter{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Advogado advogado = null;
        HttpSession sess = ((HttpServletRequest)request).getSession(false);
        
        if (sess != null){
              advogado = (Advogado) sess.getAttribute("usuarioLogado");
        }      

        if (advogado == null) {
           String contextPath = ((HttpServletRequest)request).getContextPath();
           ((HttpServletResponse) response).sendRedirect(contextPath + "/root/login.xhtml");
        } else {
              chain.doFilter(request, response);
        }
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		HibernateUtil.getFabricaDeSessoes();
		
	}
}
