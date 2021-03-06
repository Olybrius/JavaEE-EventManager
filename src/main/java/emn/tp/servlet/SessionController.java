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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import emn.tp.bean.jpa.UsersEntity;

/**
 * Servlet Filter permet le contr�le d'acc�s aux diverses pages. 
 */
@WebFilter(
		urlPatterns = "/*",
		initParams = {
				// Style, javascript & fonts
				@WebInitParam(name="freeFiles", value="css|js|eot|svg|ttf|woff|png"),
				// Non logged-in accessible servlets
				@WebInitParam(name="beforeConnection", value="Login|Register"),
				// Always accessible
				@WebInitParam(name="freeServlets", value="Publish|Participate|Events")
		}
)
public class SessionController implements Filter {
	private static final Logger logger = LogManager.getLogger(SessionController.class);

	private ArrayList<String> freeFiles ;
	private ArrayList<String> beforeConnection ;
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
		logger.debug("SESSION CONTROLLER : Getting current URL...");
		String url = ((HttpServletRequest)request).getRequestURI() ;
		logger.debug("SESSION CONTROLLER : Testing current URL [" + url + "]...");
		
		// If we try to load a "free file" or a servlet always accessible
		if (this.freeFile(url) || this.freeServlet(url)) {	
			logger.debug("SESSION CONTROLLER : Free file or servlet...");
			chain.doFilter(request, response);
			
		// If we try to load a servlet accessible only before a connection
		}else if (this.beforeConnection(url)){
			// Get the current user session
			logger.debug("SESSION CONTROLLER : Servlet accessible before connection, testing the existence of a session...");
			UsersEntity user = (UsersEntity) ((HttpServletRequest)request).getSession().getAttribute("user");
			// If it exists, we need to redirect to the events of the current user
			if (user != null){
				logger.debug("SESSION CONTROLLER : There is a session, redirecting to Events...");
				request.getRequestDispatcher("/MyEvents").forward(request, response);
			// Otherwise, we can chain
			}else{
				logger.debug("SESSION CONTROLLER : There is no session, chain processing...");
				chain.doFilter(request, response);
			}
			
		// Otherwise
		}else{
			// Get the current user session
			logger.debug("SESSION CONTROLLER : Getting session variables...");
			UsersEntity user = (UsersEntity) ((HttpServletRequest)request).getSession().getAttribute("user");
			String loginError = (String) ((HttpServletRequest)request).getSession().getAttribute("loginError");
			// If it exists, we can chain
			if (user != null || loginError != null) {
				logger.debug("SESSION CONTROLLER : Session variable exists, chain processing...");
				chain.doFilter(request, response);
			// Otherwise, we redirect on the "login" page
			}else{
				logger.debug("SESSION CONTROLLER : Session variable does not exist, redirecting to Login...");
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
		String before = fConfig.getInitParameter("beforeConnection") ;
		String servlets = fConfig.getInitParameter("freeServlets") ;
		StringTokenizer parseFiles = new StringTokenizer(files, "|");
		StringTokenizer parseBefore = new StringTokenizer(before, "|");
		StringTokenizer parseServlet = new StringTokenizer(servlets, "|");
		this.freeFiles = new ArrayList<String>();
		this.beforeConnection = new ArrayList<String>();
		this.freeServlets = new ArrayList<String>();
		// Type of files allowed (jsessionid is concat at the session creation)
		while (parseFiles.hasMoreTokens()) {
			freeFiles.add(".*\\." + parseFiles.nextToken() + "(;jsessionid.*){0,1}$");
		}
		// Servlets before connection
		while (parseBefore.hasMoreTokens()) {
			beforeConnection.add(".*\\/" + parseBefore.nextToken() + "$");
		}
		// "/" is allowed too
		beforeConnection.add(".*" + fConfig.getServletContext().getContextPath() + "\\/$");
		// Servlets always accessible
		while (parseServlet.hasMoreTokens()) {
			freeServlets.add(".*\\/" + parseServlet.nextToken() + "$");
		}
	}

	/**
	 * @param url
	 * @return True if "url" has the type of one of the file type in freeFiles, false otherwise.
	 */
	private boolean freeFile(String url){
		boolean isFree = false ;
		for (String currentFile : this.freeFiles){
			if (Pattern.matches(currentFile, url)) {
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
	private boolean beforeConnection(String url){
		boolean isBeforeConnection = false ;
		for (String currentServlet : this.beforeConnection){
			if (Pattern.matches(currentServlet, url)) {
				isBeforeConnection = true ;
				break ;
			}
		}
		return isBeforeConnection ;		
	}
	
	/**
	 * @param url
	 * @return True if "url" finishes by one of the servlets in alwaysAccessible, false otherwise.
	 */
	private boolean freeServlet(String url){
		boolean isBeforeConnection = false ;
		for (String currentServlet : this.freeServlets){
			if (Pattern.matches(currentServlet, url)) {
				isBeforeConnection = true ;
				break ;
			}
		}
		return isBeforeConnection ;		
	}	
	
	/**
	 * @param url
	 * @return True if the user try to access to the events, false otherwise.
	 */
	@SuppressWarnings("unused")
	private boolean events(String url){
		return Pattern.matches(".*\\/Events(?=([0-9]*)){0,1}$", url) || Pattern.matches(".*/Publish$", url) || Pattern.matches(".*/Participate$", url) ;
	}

}
