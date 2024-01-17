package com.example.sellseeds.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.model.plants.PlantDao
import com.example.sellseeds.model.plants.entenies.PlantDbEntity
import com.example.sellseeds.model.shop.ShopDao
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class PlantPagingSource(private val plantDao: PlantDao ,private val pageSize:Int ,val shopId:Int) : PagingSource<Int, Seed>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Seed> {
        val pageIndex =params.key ?: 0


        return try{
        val plantsList =getSeed(pageIndex ,params.loadSize ,shopId) ?: listOf()

            if(plantsList.isEmpty()){
                Log.d("12312321ss","Eball")

            }
            return LoadResult.Page(
                data = plantsList,
                // index of the previous page if exists
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                // index of the next page if exists;
                // please note that 'params.loadSize' may be larger for the first load (by default x3 times)
                nextKey = if (plantsList.size == params.loadSize) pageIndex + (params.loadSize / pageSize) else null
            )

    } catch (e: Exception) {
        // failed to load users -> need to return LoadResult.Error
        LoadResult.Error(
            throwable = e
        )
    }




        }
    override  fun getRefreshKey(state: PagingState<Int, Seed>): Int? {
        // get the most recently accessed index in the users list:
        val anchorPosition = state.anchorPosition ?: return null
        // convert item index to page index:
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)

    }

    private suspend fun getSeed(pageIndex:Int ,pageSize:Int, not_shopId:Int ):List<Seed>?{



            delay(2000)//artificial delay
            val offset =pageIndex*pageSize
            val list =plantDao.getAllPlants()?.map{PlantDbEntity -> PlantDbEntity!!.toSeed()}
            return list

        }



    }
