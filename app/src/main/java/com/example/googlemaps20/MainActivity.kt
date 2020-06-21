package com.example.googlemaps20

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlemaps20.models.Place
import com.example.googlemaps20.models.UserMap
import kotlinx.android.synthetic.main.activity_main.*
///https://guides.codepath.com/android/Using-Intents-to-Create-Flows
//Förklarar hur en intent fungerar som används på rad 36
const val EXTRA_USER_MAP = "EXTRA_USER_MAP"
const val REQUEST_CODE = 1234
const val EXTRA_MAP_TITLE = "EXTRA_MAP_TITLE"
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //The val userMaps is the data that collects the values from the method generateSampleData and
        //puts it in to the listof the Adapter on row 34 and the adapter is responsible for taking the data so in our case the user Maps
        //        // and binding it to a particular view in the recycler view
        val userMaps = recyclerViewPlaces()
        //Set layout manager on the recycler view
        rvMaps.layoutManager = LinearLayoutManager(this)
        // Set adapter on the recycler view
        //layout manager is responsible for telling the recyclerview how to layout the view on the screen


        rvMaps.adapter = MapsAdapter(this, userMaps, object: MapsAdapter.OnClickListener{
            override fun onItemClick(position: Int) {
                Log.i(TAG, "onItemClick $position")

                //When a user taps on view in RV, navigate to new activity
                //The way android does navigation to different screens is through
                //something called an intent system. So the val intent = Intent has two parameters the first one is the context this@MainActivity
                // the second parameter is the one we want to navigate to. And to actually navigate we need to call startActivity(intent)

                val intent = Intent(this@MainActivity, DisplayMapActivity::class.java)
                intent.putExtra(EXTRA_USER_MAP, userMaps[position])
                startActivity(intent)
            }
        })
                fabCreateMap.setOnClickListener {
                    val intent = Intent(this@MainActivity, CreateMapActivity::class.java)
                  intent.putExtra(EXTRA_MAP_TITLE, "new map name")
                    startActivityForResult(intent, REQUEST_CODE)
                }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            //Get new map data from the data

        }
        super.onActivityResult(requestCode, resultCode, data)
    }



    private fun recyclerViewPlaces(): List<UserMap> {
        return listOf(

            //48.154256 11.557191

            //57.725030 12.939450

            UserMap(
                "My Universities",
                listOf(
                    Place("Hochschule Munchen", "Our current University", 48.154256, 11.557191),
                    Place("University of Borås", "Our home University", 57.725030, 12.939450 )
                )
            ),




           UserMap(
                "Where i live in Munich",
                listOf(
                    Place("Olympadorf", "A small house with a balcony", 46.484490, 10.085368))),





            UserMap(
                "Cool places i Munich",
            listOf(

                                    //47.484490 11.085368
                        Place("Ski mountain", "Garmish parken kirsten", 47.484490, 11.085368),
                        Place("Something", "Something", 11.085368, 47.555500),
                Place("N-slottet", "THe big castle in Munich", 45.154365, 10.0857423),
                Place("Antons snase", "ANtons Stora stora Näsa", 13.37777, 13.38888),
                Place ("Hochschule Munchen", "My current School", 48.154396, 11.556827)



                )
            ),

            UserMap(
                "My home town and my current location",
                listOf(
                    Place("Bollnäs", "My home town", 61.348250, 16.394516),
                    Place("Olympiadorf", "My current location", 48.179096, 11.553045)
                )
            ),
            UserMap(
                "Home town of the great Team 5 in mobile application",
            listOf(
                        Place("Munich", "Where the lads lives", 48.140274, 11.578920)
                        )
            ),
            UserMap(
                "Lakes i want to visit in the state of Bayern",
                listOf(
                    Place("Branner Hall", "Best dorm at Stanford", 37.426, -122.163),
                    Place("Gates CS building", "Many long nights in this basement", 37.430, -122.173),
                    Place("Pinkberry", "First date with my wife", 37.444, -122.170)
                )
            ),
            UserMap("Van trip after the semester ends",
                listOf(
                    Place("Tokyo", "Overnight layover", 35.67, 139.65),
                    Place("Ranchi", "Family visit + wedding!", 23.34, 85.31),
                    Place("Singapore", "Inspired by \"Crazy Rich Asians\"", 1.35, 103.82)
                )),
            UserMap("Recommended places i'd like to go to",
                listOf(
                    Place("Gardens by the Bay", "Amazing urban nature park", 1.282, 103.864),
                    Place("Jurong Bird Park", "Family-friendly park with many varieties of birds", 1.319, 103.706),
                    Place("Sentosa", "Island resort with panoramic views", 1.249, 103.830),
                    Place("Botanic Gardens", "One of the world's greatest tropical gardens", 1.3138, 103.8159)
                )
            ),
            UserMap("My favorite places in the Munich",
                listOf(
                    Place("Chicago", "Urban center of the midwest, the \"Windy City\"", 41.878, -87.630),
                    Place("Rochester, Michigan", "The best of Detroit suburbia", 42.681, -83.134),
                    Place("Mackinaw City", "The entrance into the Upper Peninsula", 45.777, -84.727),
                    Place("Michigan State University", "Home to the Spartans", 42.701, -84.482),
                    Place("University of Michigan", "Home to the Wolverines", 42.278, -83.738)
                )
            ),
            UserMap("Restaurants to try in Munich",
                listOf(
                    Place("Champ's Diner", "Retro diner in Brooklyn", 40.709, -73.941),
                    Place("Althea", "Chicago upscale dining with an amazing view", 41.895, -87.625),
                    Place("Shizen", "Elegant sushi in San Francisco", 37.768, -122.422),
                    Place("Citizen Eatery", "Bright cafe in Austin with a pink rabbit", 30.322, -97.739),
                    Place("Kati Thai", "Authentic Portland Thai food, served with love", 45.505, -122.635)
                )
            )
        )
    }


}
