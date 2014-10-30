/*
 * Created on 29 oct. 2014 ( Time 20:28:05 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
// This Bean has a basic Primary Key (not composite) 

package emn.tp.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "EVENTS"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="EVENTS", schema="APP" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EventsEntity.countAll", query="SELECT COUNT(x) FROM EventsEntity x" ),
  @NamedQuery ( name="EventsEntity.getAllWithUserId", query="SELECT e FROM EventsEntity e WHERE e.users.id = :userid" ),
  @NamedQuery ( name="EventsEntity.checkUserId", query="SELECT e.users.id FROM EventsEntity e WHERE e.id = :eventid")
} )
public class EventsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", nullable=false)
    private Integer    id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="NAME", nullable=false, length=30)
    private String     name         ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="STARTDATE", nullable=false)
    private Date       startdate    ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ENDDATE", nullable=false)
    private Date       enddate      ;

    @Column(name="ADDRESS", nullable=false, length=30)
    private String     address      ;

    @Column(name="PUBLISHED", nullable=false)
    private Short      published    ;

	// "userid" (column "USERID") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="USERID", referencedColumnName="ID")
    private UsersEntity users       ;

    @ManyToMany(mappedBy="listOfEvents", targetEntity=ParticipantsEntity.class)
    private List<ParticipantsEntity> listOfParticipants;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EventsEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : NAME ( VARCHAR ) 
    public void setName( String name ) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    //--- DATABASE MAPPING : STARTDATE ( TIMESTAMP ) 
    public void setStartdate( Date startdate ) {
        this.startdate = startdate;
    }
    public Date getStartdate() {
        return this.startdate;
    }

    //--- DATABASE MAPPING : ENDDATE ( TIMESTAMP ) 
    public void setEnddate( Date enddate ) {
        this.enddate = enddate;
    }
    public Date getEnddate() {
        return this.enddate;
    }

    //--- DATABASE MAPPING : ADDRESS ( VARCHAR ) 
    public void setAddress( String address ) {
        this.address = address;
    }
    public String getAddress() {
        return this.address;
    }

    //--- DATABASE MAPPING : PUBLISHED ( SMALLINT ) 
    public void setPublished( Short published ) {
        this.published = published;
    }
    public Short getPublished() {
        return this.published;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setUsers( UsersEntity users ) {
        this.users = users;
    }
    public UsersEntity getUsers() {
        return this.users;
    }

    public void setListOfParticipants( List<ParticipantsEntity> listOfParticipants ) {
        this.listOfParticipants = listOfParticipants;
    }
    public List<ParticipantsEntity> getListOfParticipants() {
        return this.listOfParticipants;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(name);
        sb.append("|");
        sb.append(startdate);
        sb.append("|");
        sb.append(enddate);
        sb.append("|");
        sb.append(address);
        sb.append("|");
        sb.append(published);
        return sb.toString(); 
    } 

}
