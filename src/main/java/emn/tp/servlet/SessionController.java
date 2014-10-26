package emn.tp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

import emn.tp.bean.jpa.UsersEntity;

/**
 * Servlet Filter implementation class Controller
 */
@WebFilter(
		urlPatterns = "/*",
		initParams = {
				// Style, javascript & fonts
				@WebInitParam(name="freeFiles", value="css,js,eot,svg,ttf,woff"),
				// Non logged-in accessible servlets
				@WebInitParam(name="freeServlets", value="Login,Register")
		}
)
public class SessionController implements Filter {

	private ArrayList<String> freeFiles ;
	private ArrayList<String> freeServlets ;
	
    /**
     * Default constructor. 
     */
    public SessionController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Get the current url
		System.out.println("SESSION CONTROLLER : Getting current URL...");
		String url = ((HttpServletRequest)request).getRequestURI() ;
		// If we try to load a "free file" or a "free servlet", we can chain
		System.out.println("SESSION CONTROLLER : Testing current URL [" + url + "]...");
		if (this.freeFile(url) || this.freeServlet(url)) {
			System.out.println("SESSION CONTROLLER : Free file or servlet...");
			chain.doFilter(request, response);
		// Otherwise
		}else{
			// Get the current user session
			System.out.println("SESSION CONTROLLER : Getting session variables...");
			UsersEntity user = (UsersEntity) ((HttpServletRequest)request).getSession().getAttribute("user");
			String loginError = (String) ((HttpServletRequest)request).getSession().getAttribute("loginError");
			// If it exists, we can chain
			if (user != null || loginError != null) {
				System.out.println("SESSION CONTROLLER : Session variable exists, chain processing...");
				chain.doFilter(request, response);
			// Otherwise, we redirect on the "login" page
			}else{
				System.out.println("SESSION CONTROLLER : Session variable does not exist, redirecting to Login...");
				request.getRequestDispatcher("/Login").forward(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// Init parameters
		String files = fConfig.getInitParameter("freeFiles") ;
		String servlets = fConfig.getInitParameter("freeServlets") ;
		StringTokenizer parseFiles = new StringTokenizer(files, ",");
		StringTokenizer parseServlets = new StringTokenizer(servlets, ",");
		this.freeFiles = new ArrayList<String>();
		this.freeServlets = new ArrayList<String>();
		// Type of files allowed (jsessionid is concat at the session creation)
		while (parseFiles.hasMoreTokens()) {
			freeFiles.add(".*\\." + parseFiles.nextToken() + "(;jsessionid.*){0,1}$");
		}
		// Servlets allowed
		while (parseServlets.hasMoreTokens()) {
			freeServlets.add(".*\\/" + parseServlets.nextToken() + "$");
		}
		// "/" is allowed too
		freeServlets.add(".*" + fConfig.getServletContext().getContextPath() + "\\/$");
	}

	/**
	 * @param url
	 * @return True if "url" has the type of one of the file type in freeFiles, false otherwise.
	 */
	private boolean freeFile(String url){
		boolean isFree = false ;
		for (String freeFile : this.freeFiles){
			if (Pattern.matches(freeFile, url)) {
				isFree = true ;
				break ;
			}
		}
		return isFree ;
	}
	
	/**
	 * @param url
	 * @return True if "url" finishes by one of the servlets in freeServlets, false otherwise.
	 */
	private boolean freeServlet(String url){
		boolean isFree = false ;
		for (String freeServlet : this.freeServlets){
			if (Pattern.matches(freeServlet, url)) {
				isFree = true ;
				break ;
			}
		}
		return isFree ;		
	}

}
