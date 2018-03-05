package practicoagencias

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.GeocodingResult
import grails.converters.JSON
import groovy.json.JsonSlurper

class GeneralController {

    GeneralService servicio = new GeneralService()

    static String path=""

    def index() {
        def listaTickets = servicio.getListaAgencias()
        render(view: "index", model: [lista: listaTickets])
    }

    def buscar(){
        String direccion = params.direccion
        String rango = params.rango
        String mediopago = params.mediopago
        def lugares = servicio.getAgencias(direccion, rango, mediopago)
        render(view: "lugares", model: [lugares: lugares.results, agencia: mediopago])
        //render lugares as JSON
    }

    /*def getInfoAgencia(){
        def name = params.name
        def lugares = getLugares(path)
        def obj
        def aux
        lugares = lugares.results
        def listaTicket = new ArrayList()
        lugares.each{
            if(it.description.equals(name)){
                aux = it.address
                obj = [desc:it.description, code: it.agency_code, dire: aux.address_line]
                listaTicket.add(obj)
            }
        }
        render listaTicket as JSON
    }*/

    /*def getCoordenadas(direccion){
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAhGLw_clzUFAtFbjTnwCPOmgoNiz4ecgs")
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context, direccion).await()
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        def lat = gson.toJson(results[0].geometry.location.lat).toString()
        def lng = gson.toJson(results[0].geometry.location.lng).toString()
        return [lat: lat, lng: lng]
    }

    def getLugares(String uri){
        def url = new URL(uri)
        def connection = (HttpURLConnection)url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozzilla/5.0")
        JsonSlurper json = new JsonSlurper()
        return json.parse(connection.getInputStream())
    }*/


}
