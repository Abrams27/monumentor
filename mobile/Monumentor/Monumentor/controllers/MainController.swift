import UIKit
import MapKit
import CoreLocation

class MainController: UIViewController, CLLocationManagerDelegate {
	
	let locationManager = CLLocationManager()
	
	var categoriesOfMonuments: [String] = []
	var isAdded: [Bool] = []
	var numberOfMonuments: Optional<Int> = Optional.none
	var distanceOfMonuments: Optional<Int> = Optional.none
	
	override func viewDidLoad() {
		super.viewDidLoad()
		
		self.locationManager.requestAlwaysAuthorization()
		self.locationManager.requestWhenInUseAuthorization()
		
		if CLLocationManager.locationServicesEnabled() {
			locationManager.delegate = self
			locationManager.desiredAccuracy = kCLLocationAccuracyNearestTenMeters
			locationManager.startUpdatingLocation()
		}
	}

	@IBAction func selectCategories(_ sender: Any) {
		performSegue(withIdentifier: "main-categories", sender: self)
	}
	
	@IBAction func selectNumber(_ sender: Any) {
		let alertController = createAlertControllerWithNumberInsertion(
			title: "Wpisz maksymalną liczbę zabytków:", 
			placeHolder: "Maksymalna liczba zabytków", 
			previousValue: intToOptionalString(value: self.numberOfMonuments),
			textHandler: { (text) in 
				self.numberOfMonuments = Int(text)
			})
			
        self.present(alertController, animated: true, completion: nil)
	}
	
	@IBAction func selectDistance(_ sender: Any) {
		let alertController = createAlertControllerWithNumberInsertion(
			title: "Wpisz maksymalną odległość zabytków od Twojego położenia w kilometrach:", 
			placeHolder: "Maksymalna odległość zabytków",
			previousValue: intToOptionalString(value: self.distanceOfMonuments),
			textHandler: { (text) in 
				self.distanceOfMonuments = Int(text)
			})
			
        self.present(alertController, animated: true, completion: nil)
	}
	
	@IBAction func showSelectedList(_ sender: Any) {
		performSegue(withIdentifier: "home-list", sender: self)
	}
	
	func createAlertControllerWithNumberInsertion(
		title: String, 
		placeHolder: String, 
		previousValue: Optional<String>,
		textHandler: @escaping (String) -> Void
	) -> UIAlertController {
		
		let alertController = UIAlertController(
			title: title, 
			message: nil, 
			preferredStyle: .alert
		)
		
        let confirmAction = UIAlertAction(
			title: "ZAPISZ", 
			style: .default, 
			handler: { (_) in
				if let txtField = alertController.textFields?.first, let text = txtField.text {
					textHandler(text)
				}
		})
		
        let cancelAction = UIAlertAction(
			title: "ANULUJ", 
			style: .cancel, 
			handler: nil
		)
		
        alertController.addAction(confirmAction)
        alertController.addAction(cancelAction)
        alertController.addTextField { (textField) in
            textField.placeholder = placeHolder
			textField.keyboardType = UIKeyboardType.numberPad
			textField.text = previousValue
        }
		
		return alertController
	}
	
	override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
		if segue.identifier == "home-list" {
			guard let locValue: CLLocationCoordinate2D = locationManager.location?.coordinate else { return }
			
			let selectedListController = segue.destination as! SelectedListViewController
			selectedListController.categoriesOfMonuments = self.categoriesOfMonuments
			selectedListController.numberOfMonuments = self.numberOfMonuments
			selectedListController.distanceOfMonuments = self.distanceOfMonuments
			selectedListController.longitude = locValue.longitude
			selectedListController.latitude = locValue.latitude
		}
		
		if segue.identifier == "main-categories" {
			let categoryTableViewController = segue.destination as! CategoryTableViewController
			categoryTableViewController.isAdded = self.isAdded
		}
	}
	
	func intToOptionalString(value: Optional<Int>) -> Optional<String> {
		if let unwrappedValue = value {
			return String(unwrappedValue)
		} else {
			return Optional.none
		} 
	}
}

 
