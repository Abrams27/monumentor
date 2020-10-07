import  Foundation

struct Monument : Codable {
	var name: String
	var additionalDescription: String
	var creationDate: String
	var localization: Localization
	var photos: [Photo]
}
