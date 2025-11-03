package lat.pam.orderkulitayam

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private data class MenuItem(val name: String, val description: String, val imageRes: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val menuContainer = findViewById<LinearLayout>(R.id.menuContainer)
        val btnNext = findViewById<Button>(R.id.btnNext)

        val menus = listOf(
            MenuItem("Kulit ayam crispy original", "Renyah maksimal dengan rasa gurih alami.", R.drawable.kulit_ayam_crispy_original),
            MenuItem("Kulit ayam sambal balado", "Pedas nikmat khas balado yang menggugah selera.", R.drawable.kulit_ayam_sambal_balado),
            MenuItem("Kulit ayam goreng tepung pedas", "Kombinasi tepung renyah dan cabai menggoda.", R.drawable.kulit_ayam_goreng_tepung_pedas),
            MenuItem("Kulit ayam kecap", "Manis gurih dengan aroma kecap khas rumahan.", R.drawable.kulit_ayam_kecap),
            MenuItem("Kulit ayam rica-rica", "Pedas menggigit dengan bumbu khas Manado.", R.drawable.kulit_ayam_rica_rica),
            MenuItem("Kulit ayam goreng bumbu kuning", "Harum kunyit dan rempah Nusantara.", R.drawable.kulit_ayam_goreng_bumbu_kuning),
            MenuItem("Kulit ayam saus mentega", "Gurih manis dengan sentuhan butter lembut.", R.drawable.kulit_ayam_saus_mentega),
            MenuItem("Kulit ayam goreng bawang", "Aroma bawang yang harum dan bikin nagih.", R.drawable.kulit_ayam_goreng_bawang),
            MenuItem("Kulit ayam teriyaki", "Kelezatan Jepang dalam setiap gigitan.", R.drawable.kulit_ayam_teriyaki),
            MenuItem("Kulit ayam asam manis", "Rasa seimbang antara asam segar dan manis gurih.", R.drawable.kulit_ayam_asam_manis)
        )

        var selectedMenu: MenuItem? = null

        for (menu in menus) {
            val view = LayoutInflater.from(this).inflate(R.layout.item_menu, menuContainer, false)
            val imgMenu = view.findViewById<ImageView>(R.id.imgMenu)
            val txtName = view.findViewById<TextView>(R.id.txtName)
            val txtDesc = view.findViewById<TextView>(R.id.txtDesc)
            val radioSelect = view.findViewById<RadioButton>(R.id.radioSelect)

            imgMenu.setImageResource(menu.imageRes)
            txtName.text = menu.name
            txtDesc.text = menu.description

            radioSelect.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedMenu = menu
                    for (i in 0 until menuContainer.childCount) {
                        val other = menuContainer.getChildAt(i).findViewById<RadioButton>(R.id.radioSelect)
                        if (other != radioSelect) other.isChecked = false
                    }
                }
            }

            menuContainer.addView(view)
        }

        btnNext.setOnClickListener {
            if (selectedMenu != null) {
                val intent = Intent(this, OrderActivity::class.java)
                intent.putExtra("pesanan", selectedMenu!!.name)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Pilih salah satu varian terlebih dahulu!", Toast.LENGTH_SHORT).show()
            }
        }

        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> true
                R.id.nav_order -> {
                    startActivity(Intent(this, OrderActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
