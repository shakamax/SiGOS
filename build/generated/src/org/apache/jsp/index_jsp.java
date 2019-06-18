package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("\n");
      out.write("  <meta charset=\"utf-8\">\n");
      out.write("  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n");
      out.write("  <meta name=\"description\" content=\"\">\n");
      out.write("  <meta name=\"author\" content=\"\">\n");
      out.write("\n");
      out.write("  <title>Login</title>\n");
      out.write("\n");
      out.write("  <!-- Custom fonts for this template-->\n");
      out.write("  <link href=\"https://use.fontawesome.com/releases/v5.8.1/css/all.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("  <link href=\"Content/css/fontawesome/seila.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("\n");
      out.write("\n");
      out.write("  <!-- Custom styles for this template-->\n");
      out.write("  <link href=\"Content/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("  <link href=\"Content/css/sb-admin-2.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("\n");
      out.write("  \n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body class=\"bg-gradient-info\">\n");
      out.write("  <div class=\"container\">\n");
      out.write("\n");
      out.write("    <!-- Outer Row -->\n");
      out.write("    <div class=\"row justify-content-center\">\n");
      out.write("\n");
      out.write("      <div class=\"col-xl-10 col-lg-12 col-md-9\">\n");
      out.write("\n");
      out.write("        <div class=\"card o-hidden border-0 shadow-lg my-5\">\n");
      out.write("          <div class=\"card-body p-0\">\n");
      out.write("            <!-- Nested Row within Card Body -->\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-lg-6 d-none d-lg-block\"><img src=\"Content/img/SiGOS logo 2.jpg\" id=\"logo\" width=\"500px\" style=\"margin-top: 10%\" >\n");
      out.write("                <img id=\"loading\" src=\"Content/img/loading.gif\" width=\"500px\" height=\"200px\" style=\"display: none;\"/>\n");
      out.write("                </div>\n");
      out.write("              <div class=\"col-lg-6\">\n");
      out.write("                <div class=\"p-5\">\n");
      out.write("                  <div class=\"text-center\">\n");
      out.write("<!--                      ALERTAS -->\n");
      out.write("                    ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                      \n");
      out.write("                    <h1 class=\"h4 text-gray-900 mb-4\">Bem-Vindo!</h1>\n");
      out.write("                  </div>\n");
      out.write("                    <form class=\"user\" method=\"post\" action=\"LoginServlet?acao=login\" name=\"formulario\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <input type=\"email\" class=\"form-control form-control-user\" name=\"logemail\" id=\"logemail\" required=\"\" aria-describedby=\"emailHelp\" placeholder=\"Digite seu email\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <input type=\"password\" required=\"\" class=\"form-control form-control-user\" name=\"logpassword\" id=\"logpassword\" placeholder=\"Senha\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("<!--                      <div class=\"custom-control custom-checkbox small\">\n");
      out.write("                        <input type=\"checkbox\" class=\"custom-control-input\" id=\"customCheck\">\n");
      out.write("                        <label class=\"custom-control-label\" for=\"customCheck\">Quero me lembrar desse email</label>\n");
      out.write("                      </div>-->\n");
      out.write("                    </div>\n");
      out.write("                    <button id=\"btn_logar\" type=\"submit\" class=\"btn btn-info btn-user btn-block\">\n");
      out.write("                      Entrar\n");
      out.write("                    </button>\n");
      out.write("                    <hr>\n");
      out.write("                  </form>\n");
      out.write("            \n");
      out.write("                  <hr>\n");
      out.write("                  <div class=\"text-center\">\n");
      out.write("                    <a class=\"small\" href=\"redefinirSenha.jsp\">Esqueceu a senha?</a>\n");
      out.write("                  </div>\n");
      out.write("                  <div class=\"text-center\">\n");
      out.write("                      <button class=\"btn btn-primary text-gray-100 bg-gradient-info\"  data-toggle=\"modal\" data-target=\"#modalExemplo\">Criar uma conta</button>\n");
      out.write("\n");
      out.write("                    <!-- Modal -->\n");
      out.write("                    <div class=\"modal fade\" id=\"modalExemplo\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n");
      out.write("                      <div class=\"modal-dialog modal-xl\" role=\"document\">\n");
      out.write("                        <div class=\"modal-content\">\n");
      out.write("                          <div class=\"modal-header bg-gradient-info\">\n");
      out.write("                              <!--<div class=\"card-header bg-gradient-info\"><h1 class=\"h3 mb-4 text-gray-100\">Cadastrar Cliente</h1></div>-->\n");
      out.write("                            <h1 class=\"modal-title h1 mb-4 text-gray-100\" id=\"exampleModalLabel\">Cadastrar</h1>\n");
      out.write("                            <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Fechar\">\n");
      out.write("                              <span aria-hidden=\"true\">&times;</span>\n");
      out.write("                            </button>\n");
      out.write("                          </div>\n");
      out.write("                          <div class=\"modal-body\" >\n");
      out.write("                              ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "cadastroCliente.jsp", out, false);
      out.write("\n");
      out.write("                          </div>\n");
      out.write("                              ");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                      </div>\n");
      out.write("                    </div>\n");
      out.write("                        \n");
      out.write("<!--                        Fim modal-->\n");
      out.write("                    \n");
      out.write("                  </div>\n");
      out.write("                </div>\n");
      out.write("              </div>\n");
      out.write("            </div>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("  </div>\n");
      out.write("\n");
      out.write("  <!-- Bootstrap core JavaScript-->\n");
      out.write("  \n");
      out.write("  <script src=\"Content/js/jquery.min.js\" type=\"text/javascript\"></script>\n");
      out.write("  \n");
      out.write("  <script src=\"Content/js/bootstrap.bundle.min.js\" type=\"text/javascript\"></script>\n");
      out.write("\n");
      out.write("  <!-- Core plugin JavaScript-->\n");
      out.write("  <script src=\"Content/js/jquery.easing.min.js\" type=\"text/javascript\"></script>\n");
      out.write("  \n");
      out.write("  <!-- Custom scripts for all pages-->\n");
      out.write("  <script src=\"Content/js/sb-admin-2.min.js\" type=\"text/javascript\"></script>\n");
      out.write("\n");
      out.write("   <!--  JavaScript para Máscaras-->\n");
      out.write("  <script src=\"Content/js/jquery.mask.min.js\" type=\"text/javascript\"></script>\n");
      out.write("  \n");
      out.write("  <script src=\"Content/js/jquery.priceformat.min.js\" type=\"text/javascript\"></script>\n");
      out.write("  \n");
      out.write("  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js\" type=\"text/javascript\"></script>\n");
      out.write("  \n");
      out.write("  \n");
      out.write("<script>\n");
      out.write("    $(document).ready(function () { \n");
      out.write("        var $seuCampoCpf = $(\".CPF\");\n");
      out.write("        $seuCampoCpf.mask('999.999.999-99');\n");
      out.write("    });\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("  \n");
      out.write("  \n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>\n");
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

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${msg}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                      <span class=\"alert alert-danger\" id=\"error\" style=\"display:none;\"> Usuário e/ou senha incorreto.</span>\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }
}
