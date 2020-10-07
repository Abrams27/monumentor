import UIKit
import GoogleMaps

class DetailsViewController: UIViewController {
	
	let webService = WebService()
	let googleMapsService = GoogleMapsService()
	
	var categoriesOfMonuments: [String] = []
	var numberOfMonuments: Optional<Int> = Optional.none
	var distanceOfMonuments: Optional<Int> = Optional.none
	
	
	var id: Int = 0
	var imageIndex: Optional<Int> = 0
	var monument: Optional<Monument> = Optional.none

	@IBOutlet weak var monumentName: UILabel!
	@IBOutlet weak var monumentLocalizationCityAndVoivodeship: UILabel!
	@IBOutlet weak var monumentCreationDate: UILabel!
	
	@IBOutlet weak var monumentImage: UIImageView!
	@IBOutlet weak var monumentImageAuthorAndDate: UILabel!
	@IBOutlet weak var monumentImagesControl: UIPageControl!
	
	@IBOutlet weak var additionalDescription: UILabel!
	
	override func viewDidLoad() {
        super.viewDidLoad()
		
		print(self.id)
		self.monument = self.webService.getFullInfo(id: self.id)
		
		buildViews()
		buildGestures() 
    }
	
	@IBAction func showOnMap(_ sender: Any) {
		self.googleMapsService
			.showOnMapOnePlace(
				latitude: self.monument!.localization.latitude, 
				longitude: self.monument!.localization.longitude)
	}
	
	func buildViews() {
		self.monumentName.text = self.monument!.name
		self.monumentCreationDate.text = self.monument!.creationDate
		self.monumentLocalizationCityAndVoivodeship.text = self.buildLocalityName()
		
		self.additionalDescription.text = self.monument!.additionalDescription
		
		buildImageView()
	}
	
	func buildLocalityName() -> String {
		return self.monument!.localization.locality + ", " + self.monument!.localization.voivodeship 
	}
	
	func buildImageView() {
		if let index = imageIndex {
			self.monumentImagesControl.numberOfPages = getNumberOfPhotos()
			self.monumentImagesControl.currentPage = 0
			setImageWithIndex(index: index)
		}
	}
	
	func buildGestures() {
		self.monumentImage.isUserInteractionEnabled = true
		let swipeRightImage = UISwipeGestureRecognizer(target: self, action: #selector(DetailsViewController.swipedImage(gesture:)))
		swipeRightImage.direction = UISwipeGestureRecognizer.Direction.right
		self.monumentImage.addGestureRecognizer(swipeRightImage) 
		
		let swipeLeftImage = UISwipeGestureRecognizer(target: self, action: #selector(DetailsViewController.swipedImage(gesture:)))
		swipeLeftImage.direction = UISwipeGestureRecognizer.Direction.left
		self.monumentImage.addGestureRecognizer(swipeLeftImage)
		
		let swipeRight = UISwipeGestureRecognizer(target: self, action: #selector(DetailsViewController.swiped(gesture:)))
		swipeRightImage.direction = UISwipeGestureRecognizer.Direction.right
		self.view.addGestureRecognizer(swipeRight)
	}
	
	func getAuthorAndDateOfPhoto(photo: Photo) -> String {
		return photo.author.name 
			+ " - " 
			+ photo.creationDate
	}
	
	func downloadImage(from url: URL) {
		webService.getData(from: url) { data, response, error in
			guard let data = data, error == nil else { return }
			
			DispatchQueue.main.async() {
				self.monumentImage.image = UIImage(data: data)
			}
		}
	}
	
	@objc func swipedImage(gesture: UIGestureRecognizer) {
		if let swipeGesture = gesture as? UISwipeGestureRecognizer {
			switch swipeGesture.direction {
			case UISwipeGestureRecognizer.Direction.right :
				setPreviousImage()
			case UISwipeGestureRecognizer.Direction.left:
				setNextImage()
			default:
				break
			}
		}
	}
	
	@objc func swiped(gesture: UIGestureRecognizer) {
		if let swipeGesture = gesture as? UISwipeGestureRecognizer {
			switch swipeGesture.direction {
			case UISwipeGestureRecognizer.Direction.right :
				performSegue(withIdentifier: "details-list", sender: self)
			default:
				break
			}
		}
	}
	
	override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
		if segue.identifier == "details-list" {
			let selectedListViewController = segue.destination as! SelectedListViewController
			
			selectedListViewController.categoriesOfMonuments = self.categoriesOfMonuments
			selectedListViewController.numberOfMonuments = self.numberOfMonuments
			selectedListViewController.distanceOfMonuments = self.distanceOfMonuments
		}
	}
	
	func setNextImage() {
		self.imageIndex = min(self.imageIndex! + 1, getNumberOfPhotos() - 1)
		
		setImageWithIndex(index: self.imageIndex!)
	}
	
	func setPreviousImage() {
		self.imageIndex = max(self.imageIndex! - 1, 0)
		
		setImageWithIndex(index: self.imageIndex!)
	}
	
	func setImageWithIndex(index: Int) {
		if (monument!.photos.count > 0) {
			downloadImage(from: URL(string: monument!.photos[index].url)!)
			self.monumentImageAuthorAndDate.text = getAuthorAndDateOfPhoto(photo: self.monument!.photos[index])
			self.monumentImagesControl.currentPage = index
		}
	}
	
	func getNumberOfPhotos() -> Int {
		return self.monument!.photos.count
	}

}
