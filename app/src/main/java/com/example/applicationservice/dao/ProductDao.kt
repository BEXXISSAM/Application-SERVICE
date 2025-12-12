package com.example.applicationservice.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.applicationservice.models.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM Products ORDER BY ProductPrice ASC")
    fun getProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM Products where ProductId= :pId")
    fun getProduct(pId: Int): Product?

    @Insert(onConflict = OnConflictStrategy.IGNORE) // Ignorer l'insertion des products déjà existés
    suspend fun insertProduct(product:Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Query("DELETE FROM Products")
    suspend fun deleteProducts()
}