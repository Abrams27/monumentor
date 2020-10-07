import Foundation
import GoogleMaps

class GoogleMapsService {
	
	let ZOOM = 12
	let PREFIX = "https://www.google.com/maps"
	
	
	func showOnMapOnePlace(latitude: Double, longitude: Double) {
		let url = URL(string: buildOneStopString(latitude: latitude, longitude: longitude))!
		callApp(url: url)
	}
	
	func showOnMapPlaces(monuments: [MonumentInfo]) {
		if monuments.count > 0 {
			let url = URL(string: buildMultipleStopsString(monuments: monuments))!
			callApp(url: url)
		}
	}
	
	func callApp(url: URL) {
		UIApplication.shared.open(url, options: [:], completionHandler: nil)
	}
	
	func buildOneStopString(latitude: Double, longitude: Double) -> String {
		return PREFIX 
			+ "?q=\(latitude),\(longitude)&center=\(latitude),\(longitude)" 
			+ zoomString()
	}
	
	func buildMultipleStopsString(monuments: [MonumentInfo]) -> String {
		let lastMonument = monuments.last!
		let stops = buildStopsString(monuments: monuments, size: monuments.count - 1).joined(separator: "")
		let destination = buildDestinationString(latitude: lastMonument.latitude, longitude: lastMonument.longitude)
		
		return PREFIX 
			+ destination
			+ stops
			+ zoomString()
	}
	
	func buildDestinationString(latitude: Double, longitude: Double) -> String {
		return "?daddr=\(latitude),\(longitude)"
	}
	
	func buildStopsString(monuments: [MonumentInfo], size: Int) -> [String] {
		var result: [String] = []
		
		for i in 0..<size {
			result.append(buildStopString(latitude: monuments[i].latitude, longitude: monuments[i].longitude))
		}
		
		return result
	}
	
	func buildStopString(latitude: Double, longitude: Double) -> String {
		return "+to:\(latitude),\(longitude)"
	}
	
	func zoomString() -> String {
		return "&zoom=\(ZOOM)"
	}
}
