import  Foundation

struct Photo : Codable {
	var creationDate: String
	var url: String
	var author: Author
}
