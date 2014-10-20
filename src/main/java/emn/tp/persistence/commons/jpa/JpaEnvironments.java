/*
 * Created on 20 oct. 2014 ( Date ISO 2014-10-20 - Time 21:44:46 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package emn.tp.persistence.commons.jpa;

import java.util.HashMap;
import java.util.Map;

public class JpaEnvironments {

	private final static JpaEnvironments singleInstance = new JpaEnvironments();
	
	private final Map<String,JpaEnvironment> environments ;

	private JpaEnvironments() {
		super();
		this.environments = new HashMap<String,JpaEnvironment>();
	}
	
	public final static JpaEnvironments getInstance() {
		return singleInstance ;
	}
	
	public JpaEnvironment getJpaEnvironment(final String peristanceUnitName ) {
		JpaEnvironment env = environments.get(peristanceUnitName) ;
		if ( env == null ) {
			env = createNewJpaEnvironment( peristanceUnitName );
		}
		return env ;
	}

	private synchronized JpaEnvironment createNewJpaEnvironment(final String peristanceUnitName ) {
		JpaEnvironment env = environments.get(peristanceUnitName) ; // double check 
		if ( env == null ) {
			env = new JpaEnvironment(peristanceUnitName);
			environments.put(peristanceUnitName, env);
		}
		return env ;
	}
}
