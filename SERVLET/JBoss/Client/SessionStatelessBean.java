import jnpserver.jar;
import jboss-j2ee.jar;


public static void main (String[] args)
{
	Properties props = new Properties();
// Bisogna valorizzare alcuni parametri, che rappresentano il
// modo con cui accediamo al JNDI dell’application server

// La classe concreta (importare il package jnpserver.jar)

props.put(Context.INITIAL_CONTEXT_FACTORY,”org.jnp.interfaces.NamingContextFactory”);

// La url dell’application server e la porta a cui risponde JNDI (default)

props.put(Context.PROVIDER_URL, “jnp://localhost:1099″);

// Il context rappresenta la virtualizzazione dell’application server

Context ctx=new InitialContext(props); 


/**
* Chiediamo all’application server (mediante JNDI) la Home del bean
* chiamata ServerTimeHome.JNDI_NAME (variabile statica che rappresenta
* il nome con cui quel bean è registrato).
* Bisogna effettuare un’operazione di cast, in quanto il tipo di ritorno è un Object
*/
ServerTimeHome sth = (ServerTimeHome) PortableRemoteObject.narrow(ctx.lookup(ServerTimeHome.JNDI_NAME), ServerTimeHome.class); 
