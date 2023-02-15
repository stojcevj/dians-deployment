const working_time = (w_time) => {
    let time = w_time.split(",");
    let str = '';
    for(let i = 0; i < time.length; i++){
        str += `<b>${time[i]}</b></br>`
    }
    return str;
}
const closestToMe = (latlng) =>{
    let min = 90000000;
    let lat;
    let lng;

    for(let i = 0; i < list.length; i++) {
        let distance = latlng.distanceTo(L.latLng(list[i]['lon'], list[i]['lat']));

        if(distance < min){
            min = distance;
            lat = list[i]['lat'];
            lng = list[i]['lon'];
        }
    }

    return L.latLng(lng, lat);
}
const onLocationFound = (e) => {
    L.Routing.control({
        waypoints: [
            L.latLng(e.latlng),
            L.latLng(closestToMe(e.latlng))
        ]
    }).addTo(map);
}

const setMap = () => {
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map);
}

const deletePharmaciesDetails = () => {
    map.eachLayer((layer) => {
        layer.remove();
    });
}
const goToRoute = (e) => {
    let lat = e.getAttribute('lat');
    let lon = e.getAttribute('lon');

    deletePharmaciesDetails()
    setMap()

    L.Routing.control({
        waypoints: [
            L.latLng(user_lat, user_lon),
            L.latLng(lat, lon)
        ]
    }).addTo(map);
}
const displayPharmaciesDetails = () => {
    for(let i = 0; i < list.length; i++){
        let marker = L.marker([list[i]['lon'], list[i]['lat']]).addTo(map);

        marker.bindPopup(`
            <strong>Име на аптека: ${list[i]['name']}</strong>
            <br>
            <strong>Град: ${list[i]['city']}</strong>
            <br>
            <strong>Улица: ${list[i]['location']}</strong>
            <br>
            <strong>Телефонски број: ${list[i]['phone_number']}</strong>
            <br>
            <strong>Вебсајт: ${list[i]['website']}</strong>
            <br>
            <strong>Работно време: ${working_time(list[i]['workingTime'])}</strong>
            <br>
            <button class="btn btn-primary" onclick="goToRoute(this)" lat="${list[i]['lon']}" lon="${list[i]['lat']}">GO</button>
            `)
            .openPopup();
    }
}

const addMarker = (e) =>{
    deletePharmaciesDetails();
    setMap();
    let newMarker = new L.marker(e.latlng).addTo(map);
    document.getElementById("lat").value = newMarker._latlng.lat;
    document.getElementById("lon").value = newMarker._latlng.lng;
}
