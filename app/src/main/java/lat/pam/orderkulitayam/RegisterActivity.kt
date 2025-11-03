package lat.pam.orderkulitayam

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts

class RegisterActivity : AppCompatActivity() {

    private lateinit var imgProfile: ImageView
    private lateinit var etFullName: EditText
    private lateinit var etTTL: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etTelepon: EditText
    private lateinit var etEmail: EditText
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var btnToLogin: Button
    private lateinit var btnUploadPhoto: Button

    private var selectedImageUri: Uri? = null

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            selectedImageUri = result.data?.data
            try {
                val inputStream = contentResolver.openInputStream(selectedImageUri!!)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imgProfile.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Gagal memuat foto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        imgProfile = findViewById(R.id.imgProfile)
        etFullName = findViewById(R.id.etFullName)
        etTTL = findViewById(R.id.etTTL)
        etAlamat = findViewById(R.id.etAlamat)
        etTelepon = findViewById(R.id.etTelepon)
        etEmail = findViewById(R.id.etEmail)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnRegister = findViewById(R.id.btnRegister)
        btnToLogin = findViewById(R.id.btnToLogin)
        btnUploadPhoto = findViewById(R.id.btnUploadPhoto)

        btnUploadPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickImageLauncher.launch(intent)
        }

        btnRegister.setOnClickListener {
            val sharedPref = getSharedPreferences("user_data", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("full_name", etFullName.text.toString())
                putString("ttl", etTTL.text.toString())
                putString("alamat", etAlamat.text.toString())
                putString("telepon", etTelepon.text.toString())
                putString("email", etEmail.text.toString())
                putString("username", etUsername.text.toString())
                putString("password", etPassword.text.toString())
                putString("foto_uri", selectedImageUri?.toString() ?: "")
                apply()
            }

            Toast.makeText(this, "Registrasi berhasil! Silahkan login.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
