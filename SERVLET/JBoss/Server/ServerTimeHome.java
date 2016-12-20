package it.html.ejb.session.stateless;

public interface ServerTimeHome
  extends javax.ejb.EJBHome{
  public static final String COMP_NAME=”java:comp/env/ejb/ServerTime”;
  public static final String JNDI_NAME=”ServerTime”;
  
  public it.html.ejb.session.stateless.ServerTime create()
    throws javax.ejb.CreateException,java.rmi.RemoteException;

} 
