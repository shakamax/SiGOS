package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Content/Layout/Header.jsp", out, false);
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Content/Layout/Menu.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("    <div class=\"card\">\n");
      out.write("        <div class=\"card-header bg-gradient-info\">\n");
      out.write("            <h1 class=\"h3 mb-4 text-gray-100\">Bem vindo</h1>\n");
      out.write("            <h2 class=\"h4 mb-4 text-gray-100\"> Situação Ordem de serviço </h2>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"card-body\">\n");
      out.write("                <!-- GRÁFICOS DE BARRA -->\n");
      out.write("                <div class=\"card-body\">\n");
      out.write("                  <h4 class=\"small font-weight-bold\">O.S Recem Abertas <span class=\"float-right\">10%</span></h4>\n");
      out.write("                  <div class=\"progress mb-4\">\n");
      out.write("                    <div class=\"progress-bar bg-danger\" role=\"progressbar\" style=\"width: 10%\" aria-valuenow=\"10\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div>\n");
      out.write("                  </div>\n");
      out.write("                  <h4 class=\"small font-weight-bold\">O.S Em andamento <span class=\"float-right\">10%</span></h4>\n");
      out.write("                  <div class=\"progress mb-4\">\n");
      out.write("                    <div class=\"progress-bar bg-warning\" role=\"progressbar\" style=\"width: 10%\" aria-valuenow=\"10\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div>\n");
      out.write("                  </div>\n");
      out.write("                  \n");
      out.write("                  <h4 class=\"small font-weight-bold\">O.S Concluídas <span class=\"float-right\">80%</span></h4>\n");
      out.write("                  <div class=\"progress\">\n");
      out.write("                    <div class=\"progress-bar bg-success\" role=\"progressbar\" style=\"width: 80%\" aria-valuenow=\"90\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div>\n");
      out.write("                  </div>\n");
      out.write("                </div>\n");
      out.write("              </div>\n");
      out.write("            \n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("    \n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Content/Layout/Footer.jsp", out, false);
      out.write("\n");
      out.write("     \n");
      out.write("       ");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
