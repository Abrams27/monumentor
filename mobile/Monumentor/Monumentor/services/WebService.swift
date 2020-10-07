import Foundation

public class WebService {
	
	let DOMAIN: String = "http://localhost:"
	let PORT: String  = "2137"
	
	let MONUMENTOR_PREFIX = "/monumentor"
	let GET_CATEGORIES_ENDPOINT = "/categories"
	let GET_DETAILS_ENDPOINT = "/details"
	let GET_PROPOSITIONS_ENDPOINT = "/propositions"
	
	let dispatchGroup = DispatchGroup()
	var categoriesResult: [String] = []

	
	func getCategories() -> [String] {
		let request: URLRequest = URLRequest(url: buildUrl(endpoint: GET_CATEGORIES_ENDPOINT))
		let response: AutoreleasingUnsafeMutablePointer<URLResponse?>? = nil
		var result: [Category] = []
		
		do {
			let data = try NSURLConnection.sendSynchronousRequest(request, returning: response)
			result = try  JSONDecoder().decode([Category].self, from: data)
		} catch let error {
			 print(error)
		}
		
		return mapCatgories(categories: result)
	}
	
	
	func mapCatgories(categories: [Category]) -> [String] {
		var result: [String] = []
		
		for category in categories {
			result.append(category.name)
		}
		
		return result
	}
	
	func getPropositions(
		latitude: Double,
		longitude: Double,
		numberOfMonuments: Optional<Int>,
		distanceOfMonuments: Optional<Int>,
		categories: [String]
	) -> [MonumentInfo] {
		let request: URLRequest = URLRequest(url: 
			buildUrl(
				endpoint: GET_PROPOSITIONS_ENDPOINT, 
				latitude: latitude,
				longitude: longitude,
				numberOfMonuments: numberOfMonuments,
				distanceOfMonuments: distanceOfMonuments,
				categories: categories
			)
		)
		let response: AutoreleasingUnsafeMutablePointer<URLResponse?>? = nil
		var result: [MonumentInfo] = []
		
		do {
			let data = try NSURLConnection.sendSynchronousRequest(request, returning: response)
			result = try  JSONDecoder().decode([MonumentInfo].self, from: data)
		} catch let error {
			 print(error)
		}
		
		return result
	}
	
	func getFullInfo(id: CLong) -> Monument {
		let request: URLRequest = URLRequest(url: buildUrl(endpoint: GET_DETAILS_ENDPOINT, id: id))
		let response: AutoreleasingUnsafeMutablePointer<URLResponse?>? = nil
		var result: Optional<Monument> = Optional.none
		
		do {
			let data = try NSURLConnection.sendSynchronousRequest(request, returning: response)
			result = try  JSONDecoder().decode(Monument.self, from: data)
		} catch let error {
			 print(error)
		}
		
		return result!
	}
	
	func buildUrl(endpoint: String) -> URL {
		return URL(string: DOMAIN + PORT + MONUMENTOR_PREFIX + endpoint)!
	}
	
	func buildUrl(endpoint: String, id: CLong) -> URL {
		return URL(string: DOMAIN + PORT + MONUMENTOR_PREFIX + endpoint + "/\(id)")!
	}
	
	func buildUrl(
		endpoint: String,
		latitude: Double,
		longitude: Double,
		numberOfMonuments: Optional<Int>,
		distanceOfMonuments: Optional<Int>,
		categories: [String]
	) -> URL {
		
		var url = URLComponents(string: DOMAIN + PORT + MONUMENTOR_PREFIX + endpoint)!
		var items: [URLQueryItem] = []
		
		items.append(URLQueryItem(name: "latitude", value: String(latitude)))
		items.append(URLQueryItem(name: "longitude", value: String(longitude)))
		
		if let number = numberOfMonuments {
			items.append(URLQueryItem(name: "number", value: String(number)))
		}
		
		if let distance = distanceOfMonuments {
			items.append(URLQueryItem(name: "distance", value: String(distance)))	
		}
		
		for category in categories {
			items.append(URLQueryItem(name: "categories", value: category))
		}
		
		url.queryItems = items
		
		return url.url!
	}
	
	func getData(from url: URL, completion: @escaping (Data?, URLResponse?, Error?) -> ()) {
		URLSession.shared.dataTask(with: url, completionHandler: completion).resume()
	}
}
