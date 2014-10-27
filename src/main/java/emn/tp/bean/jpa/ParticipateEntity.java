/*
 * Created on 27 oct. 2014 ( Time 13:31:45 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
// This Bean has a composite Primary Key  


package emn.tp.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import javax.persistence.*;

/**
 * Persistent class for entity stored in table "PARTICIPATE"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="PARTICIPATE", schema="APP" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="ParticipateEntity.countAll", query="SELECT COUNT(x) FROM ParticipateEntity x" )
} )
public class ParticipateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
    private ParticipateEntityKey compositePrimaryKey ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public ParticipateEntity() {
		super();
		this.compositePrimaryKey = new ParticipateEntityKey();       
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
    public void setParticipantid( Integer participantid ) {
        this.compositePrimaryKey.setParticipantid( participantid ) ;
    }
    public Integer getParticipantid() {
        return this.compositePrimaryKey.getParticipantid() ;
    }
    public void setEventid( Integer eventid ) {
        this.compositePrimaryKey.setEventid( eventid ) ;
    }
    public Integer getEventid() {
        return this.compositePrimaryKey.getEventid() ;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        if ( compositePrimaryKey != null ) {  
            sb.append(compositePrimaryKey.toString());  
        }  
        else {  
            sb.append( "(null-key)" ); 
        }  
        sb.append("]:"); 
        return sb.toString(); 
    } 

}