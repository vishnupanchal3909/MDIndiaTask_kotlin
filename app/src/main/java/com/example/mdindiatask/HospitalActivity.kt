package com.example.mdindiatask

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import org.json.JSONObject
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.AsyncTask
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection

class HospitalActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var hospitalAdapter: HospitalAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hospital)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch data from API
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            // Handle FAB click here
            FetchHospitalDataTask().execute()
        }

        FetchHospitalDataTask().execute()
    }
    private inner class FetchHospitalDataTask : AsyncTask<Void, Void, List<Hospital>>() {

        override fun doInBackground(vararg params: Void?): List<Hospital> {
            val hospitalList = mutableListOf<Hospital>()
            val urlString = "http://mdiserviceweb.mdindia.com:8060/CommonAPIServiceWeb/WCFCommonServices.svc/rest/getLocationList?statename=Maharashtra&district=Pune&cityname=Pune&MobileUniqId=0000002"

            try {
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val inputStream = connection.inputStream
                    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                    val response = bufferedReader.readText()
                    bufferedReader.close()

                    val jsonResponse = JSONObject(response)
                    val dataArray = jsonResponse.getJSONObject("response").getJSONArray("data")

                    for (i in 0 until dataArray.length()) {
                        val jsonObject = dataArray.getJSONObject(i)
                        val hospital = Hospital(
                            Provider_Code = jsonObject.getString("Provider_Code"),
                            HospitalName = jsonObject.getString("HospitalName"),
                            HospitalAddress = jsonObject.getString("HospitalAddress"),
                            PinCode = jsonObject.getString("PinCode"),
                            Contact_Mobile_No = jsonObject.optString("Contact_Mobile_No", "N/A"),
                            Latitude = jsonObject.getString("Latitude"),
                            Longitude = jsonObject.getString("Longitude")
                        )
                        hospitalList.add(hospital)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return hospitalList
        }

        override fun onPostExecute(result: List<Hospital>) {
            if (result.isNotEmpty()) {
                hospitalAdapter = HospitalAdapter(result)
                recyclerView.adapter = hospitalAdapter
            } else {
                Toast.makeText(this@HospitalActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}