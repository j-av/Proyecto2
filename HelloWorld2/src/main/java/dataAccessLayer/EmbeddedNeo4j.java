package dataAccessLayer;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;

import static org.neo4j.driver.Values.parameters;

import java.util.LinkedList;
import java.util.List;
/**
 * @author Administrator
 *
 */
public class EmbeddedNeo4j implements AutoCloseable{

    private final Driver driver;
    

    public EmbeddedNeo4j( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }

    public void printGreeting( final String message )
    {
        try ( Session session = driver.session() )
        {
            String greeting = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "CREATE (a:Greeting) " +
                                                     "SET a.message = $message " +
                                                     "RETURN a.message + ', from node ' + id(a)",
                            parameters( "message", message ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
    }
    
    public LinkedList<String> getSeries()
    {
    	 try ( Session session = driver.session() )
         {
    		 
    		 
    		 LinkedList<String> series = session.readTransaction( new TransactionWork<LinkedList<String>>()
             {
                 @Override
                 public LinkedList<String> execute( Transaction tx )
                 {
                     Result result = tx.run( "MATCH (serie:Serie) RETURN serie.Nombre");
                     LinkedList<String> myseries = new LinkedList<String>();
                     List<Record> registros = result.list();
                     for (int i = 0; i < registros.size(); i++) {
                    	 //myactors.add(registros.get(i).toString());
                    	 myseries.add(registros.get(i).get("serie.Nombre").asString());
                     }
                     
                     return myseries;
                 }
             } );
             
             return series;
         }
    }
    public LinkedList<String> getActors()
    {
    	 try ( Session session = driver.session() )
         {
    		 
    		 
    		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
             {
                 @Override
                 public LinkedList<String> execute( Transaction tx )
                 {
                     Result result = tx.run( "MATCH (actor:Actor) RETURN actor.Nombre");
                     LinkedList<String> myactors = new LinkedList<String>();
                     List<Record> registros = result.list();
                     for (int i = 0; i < registros.size(); i++) {
                    	 //myactors.add(registros.get(i).toString());
                    	 myactors.add(registros.get(i).get("actor.Nombre").asString());
                     }
                     
                     return myactors;
                 }
             } );
             
             return actors;
         }
    }
    public LinkedList<String> getSeriesByGenre(String genero)
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 
   		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (tom:Genero {Nombre: \"" + genero + "\"})-[:Genero]->(Serie) RETURN Serie.Nombre");
                    LinkedList<String> myactors = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 //myactors.add(registros.get(i).toString());
                   	 myactors.add(registros.get(i).get("Serie.Nombre").asString());
                    }
                    
                    return myactors;
                }
            } );
            
            return actors;
        }
   }
    
    public LinkedList<String> getSeriesByPais(String pais)
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 
   		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (tom:Pais {Nombre: \"" + pais + "\"})-[:Origen]->(Serie) RETURN Serie.Nombre");
                    LinkedList<String> myactors = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 //myactors.add(registros.get(i).toString());
                   	 myactors.add(registros.get(i).get("Serie.Nombre").asString());
                    }
                    
                    return myactors;
                }
            } );
            
            return actors;
        }
   }
    
    public LinkedList<String> getSeriesBySeasonHigh()
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (n:Serie) WHERE 5 < n.Temporadas RETURN n.Nombre");
                    LinkedList<String> myactors = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 //myactors.add(registros.get(i).toString());
                   	 myactors.add(registros.get(i).get("n.Nombre").asString());
                    }
                    
                    return myactors;
                }
            } );
            
            return actors;
        }
   }
    
    public LinkedList<String> getSeriesBySeasonLow()
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (n:Serie) WHERE 5 >= n.Temporadas RETURN n.Nombre");
                    LinkedList<String> myactors = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 //myactors.add(registros.get(i).toString());
                   	 myactors.add(registros.get(i).get("n.Nombre").asString());
                    }
                    
                    return myactors;
                }
            } );
            
            return actors;
        }
   }
    
    public LinkedList<String> getSeriesByYear(String season1, String season2)
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (n:Serie) WHERE " + season1 + " <= n.Anio <= " + season2 + " RETURN n.Nombre");
                    LinkedList<String> myactors = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 //myactors.add(registros.get(i).toString());
                   	 myactors.add(registros.get(i).get("n.Nombre").asString());
                    }
                    
                    return myactors;
                }
            } );
            
            return actors;
        }
   }
    
    public void insert(String name, String actor, String season, String year, String genre, String pais)
    {
    	try (Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run(""
                        + "CREATE (n:Serie {Nombre: \"" + name + "\", Temporadas: " + season + ", Anio: " + year + "})\n"
                        + "CREATE (g:Genero {Nombre: \"" + genre + "\"}) -[:Genero]-> (n)\n"
                        + "CREATE (o:Pais {Nombre: \"" + pais + "\"}) -[:Origen]-> (n)\n"
                        + "CREATE (p:Actor {Nombre: \"" + actor + "\"}) -[:Elenco]-> (n)\n");
                return null;
            });
            
        }
   }

}
