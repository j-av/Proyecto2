

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataAccessLayer.EmbeddedNeo4j;

/**
 * Servlet implementation class NewSeries
 */
@WebServlet("/NewSeries")
public class NewSeries extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewSeries() {
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
	 	
		//Se llama los diferentes parametros para crear una nueva seria a la base de datos.
	 	String mySeries = request.getParameter("series_name");
	 	String myActor = request.getParameter("actor_name");
	 	String myGenre = request.getParameter("genre_name");
	 	String myYear = request.getParameter("year_name");
	 	String mySeason = request.getParameter("season_name");
	 	String myCountry = request.getParameter("country_name");
		
		//Se llama el Neo4j para la nueva seria 
	 	 try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://localhost:7687", "Proyecto", "Prueba1234" ) )
	        {
			 	//Metedó para llamar a la serie.
			 	greeter.insert(mySeries, myActor, mySeason, myYear, myGenre, myCountry);
			 	
	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//Se hace put como resultado como llave y datos ingresados como el valor para imprimir.
	 	 myResponse.put("Resultado", "Datos ingresados");
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
