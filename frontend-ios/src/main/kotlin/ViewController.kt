import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.*
import io.monkeypatch.bbl.kotlin.Sale
import io.monkeypatch.bbl.kotlin.TimeStamp

@ExportObjCClass
class ViewController : UIViewController, UITableViewDataSourceProtocol {

    constructor(aDecoder: NSCoder) : super(aDecoder)

    override fun initWithCoder(aDecoder: NSCoder) = initBy(ViewController(aDecoder))

    @ObjCOutlet
    lateinit var tableView: UITableView

    private val sale = Sale(timeStamp = TimeStamp())

    override fun viewDidLoad() {
        NSTimer.scheduledTimerWithTimeInterval(1.0, true) {
            loadNewProducts()
        }
    }

    private fun loadNewProducts() {
        Container.productService.getNewProduct({ p ->
            println("Got product $p")
            sale.products.add(p)
            tableView.reloadData()
            title = "Total : ${sale.totalAmount().toInt()} €"
        }, { err ->
            println("Loading error $err")
        })
    }

    override fun tableView(tableView: UITableView, cellForRowAtIndexPath: NSIndexPath): UITableViewCell {
        return tableView.dequeueReusableCellWithIdentifier("Cell")?.apply {
            val product = sale.products[cellForRowAtIndexPath.row.toInt()]
            textLabel?.text = "${product.title} : ${product.price} € * ${product.quantity} = ${product.totalPrice()} €"
        } ?: error("Non existing Cell identifier")
    }

    override fun tableView(tableView: UITableView, numberOfRowsInSection: Long): Long = sale.products.size.toLong()

}

