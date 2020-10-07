import UIKit

class CategoryTableViewController: UITableViewController {
	
	var categories: [String] = []
	var isAdded: [Bool] = []
	let webService = WebService()

	override func viewDidLoad() {
        super.viewDidLoad()
		self.categories = webService.getCategories()
		
		if isAdded.count < categories.count {
			isAdded = Array(repeating: false, count: categories.count)
		}
    }
	

	override func tableView(
		_ tableView: UITableView, 
		numberOfRowsInSection section: Int
	) -> Int {
		return categories.count
    }
	
    override func tableView(
		_ tableView: UITableView, 
		cellForRowAt indexPath: IndexPath
	) -> UITableViewCell {
		
        let cell = tableView
			.dequeueReusableCell(
				withIdentifier: "category-cell", 
				for: indexPath
			)
		
		cell.textLabel?.text = categories[indexPath.row].replacingOccurrences(of: "_", with: " ")
		
		if isAdded[indexPath.row] {
			cell.accessoryType = UITableViewCell.AccessoryType.checkmark
		}
		
		return cell
    }
	
	override func tableView(
		_ tableView: UITableView, 
		didSelectRowAt indexPath: IndexPath
	) {
		if tableView.cellForRow(at: indexPath)?.accessoryType == UITableViewCell.AccessoryType.checkmark {
			tableView.cellForRow(at: indexPath)?.accessoryType = UITableViewCell.AccessoryType.none
			isAdded[indexPath.row] = false
		} else {
			tableView.cellForRow(at: indexPath)?.accessoryType = UITableViewCell.AccessoryType.checkmark
			isAdded[indexPath.row] = true
		}
	}
	
	@IBAction func save(_ sender: Any) {
		performSegue(withIdentifier: "categories-home", sender: self)
	}
	
	override func prepare(
		for segue: UIStoryboardSegue, 
		sender: Any?
	) {
		let mainController = segue.destination as! MainController
			
		mainController.categoriesOfMonuments = filterCategories()
		mainController.isAdded = self.isAdded
	}
	
	func filterCategories() -> [String] { 
		var result: [String] = []
		
		for i in 0..<categories.count {
			if isAdded[i] {
				result.append(categories[i])
			}
		}
		
		return result
	}
}
 
