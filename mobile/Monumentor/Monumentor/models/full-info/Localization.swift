import  Foundation

struct Localization : Codable {
	var address: String
	
	var latitude: Double
	var longitude: Double
	
	var locality: String
	var borough: String
	var county: String
	var voivodeship: String
}
