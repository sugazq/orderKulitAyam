package lat.pam.orderkulitayam

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val sharedPref = getSharedPreferences("user_data", MODE_PRIVATE)

        val imgProfile = findViewById<ImageView>(R.id.imgProfile)
        val txtName = findViewById<TextView>(R.id.txtName)
        val txtTTL = findViewById<TextView>(R.id.txtTTL)
        val txtAlamat = findViewById<TextView>(R.id.txtAlamat)
        val txtTelepon = findViewById<TextView>(R.id.txtTelepon)
        val txtEmail = findViewById<TextView>(R.id.txtEmail)
        val txtUsername = findViewById<TextView>(R.id.txtUsername)

        txtName.text = "Nama Lengkap: ${sharedPref.getString("full_name", "-")}"
        txtTTL.text = "Tempat, Tanggal Lahir: ${sharedPref.getString("ttl", "-")}"
        txtAlamat.text = "Alamat: ${sharedPref.getString("alamat", "-")}"
        txtTelepon.text = "Nomor Telepon: ${sharedPref.getString("telepon", "-")}"
        txtEmail.text = "Email: ${sharedPref.getString("email", "-")}"
        txtUsername.text = "Username: ${sharedPref.getString("username", "-")}"
        val fotoUri = sharedPref.getString("foto_uri", "")
        if (!fotoUri.isNullOrEmpty()) {
            imgProfile.setImageURI(Uri.parse(fotoUri))
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_profile
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_order -> {
                    startActivity(Intent(this, OrderActivity::class.java))
                    true
                }
                R.id.nav_profile -> true
                else -> false
            }
        }
    }
}
