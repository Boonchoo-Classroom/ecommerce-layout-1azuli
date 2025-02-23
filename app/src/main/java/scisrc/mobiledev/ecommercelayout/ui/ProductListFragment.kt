package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import scisrc.mobiledev.ecommercelayout.CartManager
import scisrc.mobiledev.ecommercelayout.FavoritesManager
import scisrc.mobiledev.ecommercelayout.ProductModel
import scisrc.mobiledev.ecommercelayout.R

class ProductListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<ProductModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        recyclerView = view.findViewById(R.id.recycler_product_list)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // ✅ Mock Data สำหรับแสดงผลสินค้า
        productList.apply {
            add(ProductModel("ปากกา", "ปากกาลื่น เขียนง่าย", 50.00, R.drawable.ic_pen))
            add(ProductModel("สมุดโน้ต", "สมุดโน้ตลายมินิมอล 80 หน้า", 120.00, R.drawable.ic_notebook))
            add(ProductModel("ดินสอไม้", "ดินสอไม้อย่างดี", 80.00, R.drawable.ic_pencil))
            add(ProductModel("ยางลบ", "ยางลบลบสะอาด", 20.00, R.drawable.ic_eraser))
            add(ProductModel("ไม้บรรทัด", "ไม้บรรทัด 30 ซม.", 35.00, R.drawable.ic_ruler))
            add(ProductModel("กล่องดินสอ", "กล่องดินสอใส่ของได้เยอะ", 150.00, R.drawable.ic_pencil_case))
        }

        // ✅ ตั้งค่า Adapter พร้อม Callback
        adapter = ProductAdapter(productList) { product ->
            toggleFavorite(product)
        }
        recyclerView.adapter = adapter

        return view
    }

    // ✅ ฟังก์ชันจัดการการกดหัวใจ
    private fun toggleFavorite(product: ProductModel) {
        product.isFavorite = !product.isFavorite
        if (product.isFavorite) {
            FavoritesManager.addToFavorites(product) // ✅ เพิ่มสินค้าใน Favorites
        } else {
            FavoritesManager.removeFromFavorites(product) // ✅ ลบออกจาก Favorites
        }
        adapter.notifyDataSetChanged()
    }
}
