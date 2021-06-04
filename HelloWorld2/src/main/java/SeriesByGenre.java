

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dataAccessLayer.EmbeddedNeo4j;

import org.json.simple.JSONArray;

/**
 * Servlet implementation class SeriesByGenre
 */
@WebServlet("/SeriesByGenre")
public class SeriesByGenre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeriesByGenre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
	 	response.setContentType("application/json");
	 	response.setCharacterEncoding("UTF-8");
	 	JSONObject myResponse = new JSONObject();
	 	
	 	JSONArray PeliculasActor = new JSONArray();
	 	
		//Se llama el parametro del nombre del genero.
	 	String myActor = request.getParameter("genre_name");
	 	 try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://localhost:7687", "Proyecto", "Prueba1234" ) )
	        {
			 	//Se llama al Neo4j y donde se obtiene las series por genero y se guarda en el JSONArray de un LinkedList. 
			 	LinkedList<String> myactors = greeter.getSeriesByGenre(myActor);
			 	
			 	for (int i = 0; i < myactors.size(); i++) {
			 		 //out.println( "<p>" + myactors.get(i) + "</p>" );
			 		PeliculasActor.add(myactors.get(i));
			 	}
	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	
		//Se guarda en el JSONObject la canitdad de series guardadas y el nombre de las series.
	 	myResponse.put("conteo", PeliculasActor.size()); 
	 	myResponse.put("series", PeliculasActor);
	 	out.println(myResponse);
	 	out.flush();  
	 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
