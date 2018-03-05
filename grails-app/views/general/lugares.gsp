<!doctype html>
<html>
<head>
    <title>Welcome to Grails</title>
    <asset:javascript src="application.js"/>
    <asset:stylesheet src="application.css"/>
    <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
          rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
          crossorigin="anonymous">
    <style>

    #map {
        height: 100%;
    }

    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
    }
    </style>
</head>
<body style="background-color:#fffd7a">
<div class="container">
    <h1 id="titulo">Lugares con ${agencia}:
    </h1>

    <table id="tabla" style="width:100%">
        <tr>
            <th>Local</th>
            <th>Distancia</th>
            <th>Info</th>
        </tr>
        <g:each in="${lugares}">
            <tr>
                <td>${it.description}</td>
                <td>${it.distance}</td>
                <td> <i class="fa fa-plus lupita" name="${it.address}"></i></td>
            </tr>
        </g:each>
    </table>

</div>

<div id="map"></div>

<script>
    var neighborhoods = [];

    var loc = "";
    <g:each in="${lugares}">
        loc = "";
        loc = "${it.address.location}";
        loc = loc.split(",");

        neighborhoods.push({lat: parseFloat(loc[0]), lng: parseFloat(loc[1])});

    </g:each>
    console.log(neighborhoods);

    var markers = [];
    var map;

    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            zoom: 15,
            center: {lat: -31.4175139, lng: -64.1846438}
        });
        for (var i = 0; i < neighborhoods.length; i++) {
            addMarker(neighborhoods[i]);
        }
    }

    function addMarker(position) {
        window.setTimeout(function() {
            markers.push(new google.maps.Marker({
                position: position,
                map: map,
                animation: google.maps.Animation.DROP
            }));
        });
    }

</script>

<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD1ZUDttXsM1IfOHKpSeQgElavORd64Wu8&callback=initMap">
</script>

</body>

<script>
    $(document).ready(function(){
        $(".lupita").on("click", function(){
            var name = $(this).attr("name");
            <g:each in="${lugares}">
            if(("${it.address}")==name){
                $.alert({
                    boxWidth: '50%',
                    useBootstrap: false,
                    title: "<strong>Agencia: </strong>${it.description}",
                    content: "<strong>Direccion:</strong> ${it.address.address_line}<br>"+
                    "<strong>Ciudad:</strong> ${it.address.city}<br>"+
                    "<strong>Pais:</strong> ${it.address.country}<br>"+
                    "<strong>Provincia:</strong> ${it.address.state}<br>"+
                    "<strong>Codigo Postal:</strong> ${it.address.zip_code}",
                });
            }
            </g:each>
        });
        /*$("#tabla").on("click", function(){
            $(this).slideUp(500);
        });*/
    });
</script>

</html>