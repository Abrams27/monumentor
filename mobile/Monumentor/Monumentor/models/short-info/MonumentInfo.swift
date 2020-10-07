import Foundation

struct MonumentInfo : Codable {
	var id: CLong
	var name: String
	var creationDate: String
	var categories: [String]
	var locality: String
	
	var latitude: Double
	var longitude: Double
}
