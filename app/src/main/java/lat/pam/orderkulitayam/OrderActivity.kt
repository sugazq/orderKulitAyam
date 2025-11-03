package lat.pam.orderkulitayam

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val pesanan = intent.getStringExtra("pesanan") ?: ""
        val txtPesanan = findViewById<TextView>(R.id.txtPesanan)
        val imgPesanan = findViewById<ImageView>(R.id.imgPesanan)
        val btnKirim = findViewById<Button>(R.id.btnKirim)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        if (pesanan.isEmpty()) {
            // Kalau belum ada pesanan → sembunyikan semua
            txtPesanan.visibility = View.GONE
            imgPesanan.visibility = View.GONE
            btnKirim.visibility = View.GONE
        } else {
            txtPesanan.visibility = View.VISIBLE
            imgPesanan.visibility = View.VISIBLE
            btnKirim.visibility = View.VISIBLE

            txtPesanan.text = "Pesanan kamu:\n$pesanan"

            val gambarResId = when {
                pesanan.contains("crispy", true) -> R.drawable.kulit_ayam_crispy_original
                pesanan.contains("balado", true) -> R.drawable.kulit_ayam_sambal_balado
                pesanan.contains("tepung", true) -> R.drawable.kulit_ayam_goreng_tepung_pedas
                pesanan.contains("kecap", true) -> R.drawable.kulit_ayam_kecap
                pesanan.contains("rica", true) -> R.drawable.kulit_ayam_rica_rica
                pesanan.contains("kuning", true) -> R.drawable.kulit_ayam_goreng_bumbu_kuning
                pesanan.contains("mentega", true) -> R.drawable.kulit_ayam_saus_mentega
                pesanan.contains("bawang", true) -> R.drawable.kulit_ayam_goreng_bawang
                pesanan.contains("teriyaki", true) -> R.drawable.kulit_ayam_teriyaki
                pesanan.contains("asam", true) -> R.drawable.kulit_ayam_asam_manis
                else -> R.drawable.kulit_ayam_crispy_original
            }


            imgPesanan.setImageResource(gambarResId)

            btnKirim.setOnClickListener {
                val intent = Intent(this, AddressActivity::class.java)
                intent.putExtra("pesanan", pesanan)
                startActivity(intent)
            }
        }

        bottomNav.selectedItemId = R.id.nav_order
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_order -> true
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
