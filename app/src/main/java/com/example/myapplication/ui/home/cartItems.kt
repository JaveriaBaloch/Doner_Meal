package com.example.myapplication.ui.home
import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable


data  class cartItems(
    val title:String, val imageResource: String, val price: Double, val category:String,
    val quantity: String
) :
    Serializable {

    companion object {
        fun getMenuItems(filename: String, context: Context): ArrayList<cartItems> {
            //create ArrayList of Book objects
            val menuItemList = ArrayList<cartItems>()

            try {
                //read json file
                val inputStream = context.assets.open(filename)
                val buffer = ByteArray(inputStream.available())
                inputStream.read(buffer)
                inputStream.close()
                val json = JSONObject(String(buffer, Charsets.UTF_8))
                val menuItems = json.getJSONArray("FoodItem")


                for (i in 0 until menuItems.length()) {
                    menuItemList.add(
                        cartItems(
                            menuItems.getJSONObject(i).getString("name"),
                            menuItems.getJSONObject(i).getString("image_url"),
                            menuItems.getJSONObject(i).getDouble("price"),
                            menuItems.getJSONObject(i).getString("category"),
                            menuItems.getJSONObject(i).getString("quantity")
                        )
                    )
                }

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            //return the List of Book objects
            return menuItemList
        }
    }
}


