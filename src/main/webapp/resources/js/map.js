/**
 * 구글맵 검색, 바인딩
 */

var gMap = {	
	geocoder : null,
	map : null,
	markersArray : [],
	addresscode : null,
	lat : null,
	lng : null,
	latCntrlId : 'mapCnLae',
	lngCntrlId : 'mapCnLoe',
	
    /**
     * 구글맵 최초 바인딩
     * @param lat 위도
     * @param lng 경도 
     * @param mapId 구글맵을 바인딩할 div id 
     * @returns
     */
	initMap : function (lat, lng, mapId, callback)
	{
		var latlng = new google.maps.LatLng(lat, lng);
		var myOptions = {
			zoom: 15,
			center: latlng,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		};
		
		gMap.map = new google.maps.Map(document.getElementById(mapId), myOptions);
		
		gMap.addMarker(latlng);

		if( typeof callback == 'function')
			google.maps.event.addListener(gMap.map, 'click', callback);
	},
	
    /**
     * 구글맵 검색 하여 바인딩
     * @param address 검색 주소 값.
     * @param mapId 구글맵을 바인딩할 div id 
     * @param callback 클릭시 호출할 함수
     * @returns
     */
	codeAddress : function (address, mapId, callback) {
		
		if(address == "")
		{
			alert('Google Map 정보를 등록해 주세요.');
			return false;
		}
		else
		{
			gMap.geocoder = new google.maps.Geocoder();
			gMap.geocoder.geocode( { 'address': address}, getGeocode);
			var myOptions = {
				zoom: 15,
				center: gMap.addresscode,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};
			//gMap.SetPosition(gMap.addresscode);
			gMap.map = new google.maps.Map(document.getElementById(mapId), myOptions);
		}
		
		if( typeof callback == 'function')
			google.maps.event.addListener(gMap.map, 'click', callback);
		
	},
	
	addMarker : function(location) {
		var marker = new google.maps.Marker({
			position: location,
			map: gMap.map,
			title : "Select location"
		});
		gMap.markersArray.push(marker);
		gMap.SetPosition(location);
		gMap.map.setCenter(location);
	},
	
	SetPosition : function (pos)
	{
		if(pos != null)
		{
			gMap.lat = pos.toJSON().lat;
			gMap.lng = pos.toJSON().lng;
			$("#" + gMap.latCntrlId).val(pos.toJSON().lat);
			$("#" + gMap.lngCntrlId).val(pos.toJSON().lng);
		}
	},
	
	clearOverlays : function () {
		if (gMap.markersArray) {
			for (i in gMap.markersArray) {
				gMap.markersArray[i].setMap(null);
			}
		}
	}
};

function getGeocode(results, status) {
	if (status == google.maps.GeocoderStatus.OK) {
		if(gMap != null)
		{
			gMap.addresscode = results[0].geometry.location;
			gMap.map.setCenter(gMap.addresscode);
			var marker = new google.maps.Marker({
					map: gMap.map, 
					position: gMap.addresscode,
					title : "Address location"
			});
			
			if(gMap.addresscode != null)
			{
				gMap.SetPosition(gMap.addresscode);
			}
		}
		
	} else {
		/*alert("Geocode was not successful for the following reason: " + status);*/
		alert("주소가 존재하지 않습니다.");
	}
}
