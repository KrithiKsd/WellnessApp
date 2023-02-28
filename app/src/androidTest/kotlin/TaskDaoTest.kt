import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.wellnessapp.data.ItemDao
import com.example.wellnessapp.data.Task
import com.example.wellnessapp.data.TaskDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TaskDaoTest {
    private lateinit var itemDao: ItemDao
    private lateinit var inventoryDatabase: TaskDatabase

    private var item1 = Task(1, "Android app completion", false)
    private var item2 = Task(2, "Get Internship", false)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context, TaskDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        itemDao = inventoryDatabase.itemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        inventoryDatabase.close()
    }

    private suspend fun addOneItemToDb() {
        itemDao.insert(item1)
    }

    private suspend fun addTwoItemsToDb() {
        itemDao.insert(item1)
        itemDao.insert(item2)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsItemIntoDB() = runBlocking {
        addOneItemToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], item1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = itemDao.getAllItems().first()
        Assert.assertEquals(allItems[0], item1)
        Assert.assertEquals(allItems[1], item2)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateItems_updatesItemsInDB()= runBlocking {
        addTwoItemsToDb()

        // private var item1 = Item(1, "Apples", 10.0, 20)
        itemDao.update(Task(1, "Android app completion", true))
        itemDao.update(Task(2, "Get Internship", true))

        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], Task(1, "Android app completion", true))
        assertEquals(allItems[1], Task(2, "Get Internship", true))
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteItems_deletesAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        itemDao.delete(item1)
        itemDao.delete(item2)

        val allItems = itemDao.getAllItems().first()
        Assert.assertTrue(allItems.isEmpty())

    }

    @Test
    @Throws(Exception::class)
    fun daoGetItem_returnsItemFromDB() = runBlocking {
        addOneItemToDb()

        val item = itemDao.getItem(1)
        assertEquals(item.first(), item1)
    }

}