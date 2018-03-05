package practicoagencias

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.GeocodingResult
import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper

@Transactional
class GeneralService {

    def getListaAgencias() {
        def url = new URL('https://api.mercadolibre.com/sites/MLA/payment_methods')
        def connection = (HttpURLConnection)url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozzilla/5.0")
        JsonSlurper json = new JsonSlurper()
        def lista = json.parse(connection.getInputStream())
        def listaTicket = new ArrayList()
        def obj
        lista.each{
            if(it.payment_type_id == "ticket"){
                obj = [id:it.id, name: it.name]
                listaTicket.add(obj)
            }
        }
        return listaTicket
    }

    def getAgencias(String direccion, String rango, String mediopago){
        def coordenadas = getCoordenadas(direccion)
        def lat = coordenadas.lat.toString()
        def lng = coordenadas.lng.toString()
        String uri = 'https://api.mercadolibre.com/sites/MLA/payment_methods/'+mediopago+'/agencies?near_to='+lat+','+lng+','+rango+'&limit=10'
        def lugares = getLugares(uri)
        return lugares
    }

    def getCoordenadas(direccion){
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
    }



}
