import Foundation
import UIKit

class SelectedListViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
	
	let webService = WebService()
	let googleMapsService = GoogleMapsService()
	
	var categoriesOfMonuments: [String] = []
	var numberOfMonuments: Optional<Int> = Optional.none
	var distanceOfMonuments: Optional<Int> = Optional.none
	
	var latitude: Double = 0
	var longitude: Double = 0
	
	var selectedMonuments: [MonumentInfo] = []
	var actualIndex: Int = 0
	
	@IBOutlet weak var selectedMonumentsTable: UITableView!
	
	override func viewDidLoad() {
        super.viewDidLoad()
		self.selectedMonumentsTable.delegate = self
		self.selectedMonumentsTable.dataSource = self
	
		
		self.selectedMonuments = webService
			.getPropositions(
				latitude: latitude,
				longitude: longitude,
				numberOfMonuments: numberOfMonuments, 
				distanceOfMonuments: distanceOfMonuments,
				categories: categoriesOfMonuments
			)
		
    }
	
	@IBAction func showOnMap(_ sender: Any) {
		googleMapsService.showOnMapPlaces(monuments: self.selectedMonuments)
	}
	
	func tableView(
		_ tableView: UITableView, 
		numberOfRowsInSection section: Int
	) -> Int {
		return selectedMonuments.count
    }
	
	func tableView(
		_ tableView: UITableView, 
		cellForRowAt indexPath: IndexPath
	) -> UITableViewCell {
		
        let cell = tableView
			.dequeueReusableCell(
				withIdentifier: "selectedMonuments-cell", 
				for: indexPath
			) as! SelectedMonumentsCell
		
		setCellProperties(cell: cell, row: indexPath.row)
		
		return cell
    }

	func tableView(
		_ tableView: UITableView, 
		didSelectRowAt indexPath: IndexPath
	) {
		self.actualIndex = indexPath.row
		performSegue(withIdentifier: "list-details", sender: self)
	}
	
	override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
		if segue.identifier == "list-details" {
			let detailsViewController = segue.destination as! DetailsViewController
			
			detailsViewController.id = selectedMonuments[self.actualIndex].id
			
			detailsViewController.categoriesOfMonuments = self.categoriesOfMonuments
			detailsViewController.numberOfMonuments = self.numberOfMonuments
			detailsViewController.distanceOfMonuments = self.distanceOfMonuments
		}
	}
	
	func setCellProperties(cell: SelectedMonumentsCell, row: Int) {
		cell.monumentNameAndCreationDate.text = buildNameAndDate(row: row)
		cell.monumentLocation.text = selectedMonuments[row].locality
		cell.monumentCategories.text = selectedMonuments[row].categories
			.joined(separator: ", ").replacingOccurrences(of: "_", with: " ")
	}
	
	func buildNameAndDate(row: Int) -> String {
		return selectedMonuments[row].name 
			+ " | " 
			+ selectedMonuments[row].creationDate
	}
}
