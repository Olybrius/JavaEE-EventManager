package servlet;

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
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class Controller
 */
@WebFilter(
		urlPatterns = "/*",
		initParams = {
				@WebInitParam(name="freeFiles", value="css,js,eot,svg,ttf,woff"),
				@WebInitParam(name="freeServlets", value="Login,Register")
		}
)
public class Controller implements Filter {

	private ArrayList<String> freeFiles ;
	private ArrayList<String> freeServlets ;
	
    /**
     * Default constructor. 
     */
    public Controller() {
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
		// Get and test url
		String url = ((HttpServletRequest)request).getRequestURI() ;
		System.out.println(url);
		if (this.freeFile(url) || this.freeServlet(url)) {
			chain.doFilter(request, response);
		}else{
			String user = (String) ((HttpServletRequest)request).getSession().getAttribute("user");
			if (user != null) {
				chain.doFilter(request, response);
			}else{
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
		// Type of files allowed (regex *.type$)
		while (parseFiles.hasMoreTokens()) {
			freeFiles.add(".*\\." + parseFiles.nextToken() + "$");
		}
		// Servlets allowed
		while (parseServlets.hasMoreTokens()) {
			freeServlets.add(".*\\/" + parseServlets.nextToken() + "$");
		}
	}

	/**
	 * @param url
	 * @return True if "url" has the type of one of the file type in freeFiles, false otherwise.
	 */
	private boolean freeFile(String url){
		boolean isFree = false ;
		for (String freeFile : this.freeFiles){
			System.out.println(freeFile + " [url] " + url);
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
			System.out.println(freeServlet + " [servlet] " + url);
			if (Pattern.matches(freeServlet, url)) {
				isFree = true ;
				break ;
			}
		}
		return isFree ;		
	}

}
