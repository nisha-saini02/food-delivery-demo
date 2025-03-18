package com.infosys.presentation.viewmodel

import app.cash.turbine.test
import com.infosys.data.model.cart.Cart
import com.infosys.data.model.order.Order
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.CartsDetailLocalRepository
import com.infosys.domain.repository.MenuCartLocalRepository
import com.infosys.domain.repository.OrdersLocalRepository
import com.infosys.domain.usecase.AllCartItemsLocalUseCase
import com.infosys.domain.usecase.CountCartItemsLocalUseCase
import com.infosys.domain.usecase.DeleteAllCartsLocalUseCase
import com.infosys.domain.usecase.DeleteCartLocalUseCase
import com.infosys.domain.usecase.FetchOrderLocalUserCase
import com.infosys.domain.usecase.GrandTotalCartItemsLocalUseCase
import com.infosys.domain.usecase.InsertCartItemLocalUseCase
import com.infosys.domain.usecase.InsertOrderItemLocalUseCase
import com.infosys.domain.usecase.OrderListLocalUseCase
import com.infosys.domain.usecase.UpdateCartItemLocalUseCase
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

//@RunWith(MockitoJUnitRunner::class)
class OrdersLocalViewModelTest {

    private lateinit var localViewModel: OrdersLocalViewModel
    private lateinit var localUseCase: LocalUseCase
    private lateinit var allCartItemsLocalUseCase: AllCartItemsLocalUseCase
    @Mock private lateinit var allCartItemsLocalRepository: CartsDetailLocalRepository
    private lateinit var fetchCartItemLocalUseCase: FetchCartItemLocalUseCase
    @Mock private lateinit var fetchCartItemLocalRepository: FetchCartItemLocalRepository
    private lateinit var insertCartItemLocalUseCase: InsertCartItemLocalUseCase
    @Mock private lateinit var insertCartItemLocalRepository: MenuCartLocalRepository
    private lateinit var updateCartItemLocalUseCase: UpdateCartItemLocalUseCase
    @Mock private lateinit var updateCartItemLocalRepository: UpdateCartItemLocalRepository
    private lateinit var deleteCartItemLocalUseCase: DeleteCartLocalUseCase
    @Mock private lateinit var deleteCartItemLocalRepository: DeleteCartItemLocalRepository
    private lateinit var orderListLocalUseCase: OrderListLocalUseCase
    @Mock private lateinit var orderListLocalRepository: OrdersLocalRepository
    private lateinit var insertOrderItemLocalUseCase: InsertOrderItemLocalUseCase
    @Mock private lateinit var insertOrderItemLocalRepository: InsertOrderItemLocalRepository
    private lateinit var countCartItemsLocalUseCase: CountCartItemsLocalUseCase
    @Mock private lateinit var countCartItemsLocalRepository: CountCartItemsLocalRepository
    private lateinit var grandTotalCartItemsLocalUseCase: GrandTotalCartItemsLocalUseCase
    @Mock private lateinit var grandTotalCartItemsLocalRepository: GrandTotalCartItemsLocalRepository
    private lateinit var deleteAllCartsLocalUseCase: DeleteAllCartsLocalUseCase
    @Mock private lateinit var deleteAllCartsLocalRepository: DeleteAllCartsLocalRepository
    private lateinit var fetchOrderLocalUserCase: FetchOrderLocalUserCase
    @Mock private lateinit var fetchOrderLocalRepository: FetchOrderLocalRepository
    
    private lateinit var cart: Cart
    
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        
        allCartItemsLocalUseCase = AllCartItemsLocalUseCase(allCartItemsLocalRepository)
        fetchCartItemLocalUseCase = FetchCartItemLocalUseCase(fetchCartItemLocalRepository)
        insertCartItemLocalUseCase = InsertCartItemLocalUseCase(insertCartItemLocalRepository)
        updateCartItemLocalUseCase = UpdateCartItemLocalUseCase(updateCartItemLocalRepository)
        deleteCartItemLocalUseCase = DeleteCartLocalUseCase(deleteCartItemLocalRepository)
        orderListLocalUseCase = OrderListLocalUseCase(orderListLocalRepository)
        insertOrderItemLocalUseCase = InsertOrderItemLocalUseCase(insertOrderItemLocalRepository)
        countCartItemsLocalUseCase = CountCartItemsLocalUseCase(countCartItemsLocalRepository)
        grandTotalCartItemsLocalUseCase = GrandTotalCartItemsLocalUseCase(grandTotalCartItemsLocalRepository)
        deleteAllCartsLocalUseCase = DeleteAllCartsLocalUseCase(deleteAllCartsLocalRepository)
        fetchOrderLocalUserCase = FetchOrderLocalUserCase(fetchOrderLocalRepository)
        
        localUseCase = LocalUseCase(allCartItemsLocalUseCase, fetchCartItemLocalUseCase, insertCartItemLocalUseCase, updateCartItemLocalUseCase, deleteCartItemLocalUseCase, orderListLocalUseCase, insertOrderItemLocalUseCase, countCartItemsLocalUseCase, grandTotalCartItemsLocalUseCase, deleteAllCartsLocalUseCase, fetchOrderLocalUserCase)
        
        localViewModel = OrdersLocalViewModel(localUseCase)
        
        cart = Cart("1", "Test", "DESC", "", 1)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun dispose() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch cart items returns SUCCESS`() = runTest {
            `when`(
                localUseCase.allCartItemsLocalUseCase.fetchAllItems()
            ).thenReturn(flowOf(Resource.Success(emptyList())))

            localViewModel.getAllCartItems()
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.cart.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.allCartItemsLocalUseCase.fetchAllItems())
    }

    @Test
    fun `fetch cart items returns empty`() {
        runTest {
            `when`(
                localUseCase.allCartItemsLocalUseCase.fetchAllItems()
            ).thenReturn(flowOf(Resource.Success(emptyList())))

            localViewModel.getAllCartItems()
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.cart.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.allCartItemsLocalUseCase.fetchAllItems())
        }
    }

    @Test
    fun `fetch cart items returns ERROR`() {
        runTest {
            `when`(
                localUseCase.allCartItemsLocalUseCase.fetchAllItems()
            ).thenThrow(RuntimeException::class.java)

            localViewModel.getAllCartItems()
            dispatcher.scheduler.advanceUntilIdle()

            localViewModel.cart.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

//            verify(localUseCase.allCartItemsLocalUseCase.fetchAllItems())
        }
    }

    @Test
    fun `fetch cart item returns SUCCESS`() {
        runTest {
            `when`(
                localUseCase.fetchCartItemLocalUseCase.fetchItem("1")
            ).thenReturn(flowOf(Resource.Success(cart)))

            localViewModel.getCartItem("1")
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.cartItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.fetchCartItemLocalUseCase.fetchItem("1"))
        }
    }

    @Test
    fun `fetch cart item returns null`() {
        runTest {
            `when`(
                localUseCase.fetchCartItemLocalUseCase.fetchItem("1")
            ).thenReturn(flowOf(Resource.Success(null)))

            localViewModel.getCartItem("1")
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.cartItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.fetchCartItemLocalUseCase.fetchItem("1"))
        }
    }

    @Test
    fun `fetch cart item returns ERROR`() {
        runTest {
            `when`(
                localUseCase.fetchCartItemLocalUseCase.fetchItem("1")
            ).thenThrow(RuntimeException::class.java)

            localViewModel.getCartItem("1")
            dispatcher.scheduler.advanceUntilIdle()

            localViewModel.cartItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

//            verify(localUseCase.fetchCartItemLocalUseCase.fetchItem("1"))
        }
    }

    @Test
    fun `insert cart item returns SUCCESS`() {
        runTest {
            `when`(
                localUseCase.insertCartItemLocalUseCase.insertItem(cart)
            ).thenReturn(flowOf(Resource.Success(1)))

            localViewModel.insertItem(cart)
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.insertItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.insertCartItemLocalUseCase.insertItem(cart))
        }
    }

    @Test
    fun `insert cart item returns -1`() {
        runTest {
            `when`(
                localUseCase.insertCartItemLocalUseCase.insertItem(cart)
            ).thenReturn(flowOf(Resource.Success(-1)))

            localViewModel.insertItem(cart)
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.insertItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.insertCartItemLocalUseCase.insertItem(cart))
        }
    }

    @Test
    fun `insert cart item returns ERROR`() {
        runTest {
            `when`(
                localUseCase.insertCartItemLocalUseCase.insertItem(cart)
            ).thenThrow(RuntimeException::class.java)

            localViewModel.insertItem(cart)
            dispatcher.scheduler.advanceUntilIdle()

            localViewModel.insertItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

//            verify(localUseCase.insertCartItemLocalUseCase.insertItem(cart))
        }
    }

    @Test
    fun `update cart item returns SUCCESS`() {
        runTest {
            `when`(
                localUseCase.updateCartItemLocalUseCase.updateItem(cart)
            ).thenReturn(flowOf(Resource.Success(1)))

            localViewModel.updateItem(cart)
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.updateItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.updateCartItemLocalUseCase.updateItem(cart))
        }
    }

    @Test
    fun `update cart item returns -1`() {
        runTest {
            `when`(
                localUseCase.updateCartItemLocalUseCase.updateItem(cart)
            ).thenReturn(flowOf(Resource.Success(-1)))

            localViewModel.updateItem(cart)
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.updateItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.updateCartItemLocalUseCase.updateItem(cart))
        }
    }

    @Test
    fun `update cart item returns ERROR`() {
        runTest {
            `when`(
                localUseCase.updateCartItemLocalUseCase.updateItem(cart)
            ).thenThrow(RuntimeException::class.java)

            localViewModel.updateItem(cart)
            dispatcher.scheduler.advanceUntilIdle()

            localViewModel.updateItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

//            verify(localUseCase.updateCartItemLocalUseCase.updateItem(cart))
        }
    }

    @Test
    fun `delete cart item returns SUCCESS`() {
        runTest {
            `when`(
                localUseCase.deleteCartItemLocalUseCase.delete(cart)
            ).thenReturn(flowOf(Resource.Success(1)))

            localViewModel.deleteItem(cart)
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.deleteItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.deleteCartItemLocalUseCase.delete(cart))
        }
    }

    @Test
    fun `delete cart item returns -1`() {
        runTest {
            `when`(
                localUseCase.deleteCartItemLocalUseCase.delete(cart)
            ).thenReturn(flowOf(Resource.Success(-1)))

            localViewModel.deleteItem(cart)
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.deleteItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.deleteCartItemLocalUseCase.delete(cart))
        }
    }

    @Test
    fun `delete cart item returns ERROR`() {
        runTest {
            `when`(
                localUseCase.deleteCartItemLocalUseCase.delete(cart)
            ).thenThrow(RuntimeException::class.java)

            localViewModel.deleteItem(cart)
            dispatcher.scheduler.advanceUntilIdle()

            localViewModel.deleteItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

//            verify(localUseCase.deleteCartItemLocalUseCase.delete(cart))
        }
    }

    @Test
    fun `reset delete cart observer returns Unit`() {
        runTest {
            val result = localViewModel.resetDeleteObserver()
            dispatcher.scheduler.advanceUntilIdle()
            assertTrue(result == Unit)
        }
    }

    @Test
    fun `count cart item returns SUCCESS`() {
        runTest {
            `when`(
                localUseCase.countCartItemsLocalUseCase.getCartListCount()
            ).thenReturn(flowOf(Resource.Success(1)))

            localViewModel.countCartItems()
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.countCartItems.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.countCartItemsLocalUseCase.getCartListCount())
        }
    }

    @Test
    fun `count cart item returns null`() {
        runTest {
            `when`(
                localUseCase.countCartItemsLocalUseCase.getCartListCount()
            ).thenReturn(flowOf(Resource.Success(null)))

            localViewModel.countCartItems()
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.countCartItems.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.countCartItemsLocalUseCase.getCartListCount())
        }
    }

    @Test
    fun `count cart item returns ERROR`() {
        runTest {
            `when`(
                localUseCase.countCartItemsLocalUseCase.getCartListCount()
            ).thenThrow(RuntimeException::class.java)

            localViewModel.countCartItems()
            dispatcher.scheduler.advanceUntilIdle()

            localViewModel.countCartItems.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

//            verify(localUseCase.countCartItemsLocalUseCase.getCartListCount())
        }
    }

    @Test
    fun `total cart item returns SUCCESS`() {
        runTest {
            `when`(
                localUseCase.grandTotalCartItemsLocalUseCase.getCartGrandSum()
            ).thenReturn(flowOf(Resource.Success(1f)))

            localViewModel.grandTotalCartItems()
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.grandTotalCartItems.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.grandTotalCartItemsLocalUseCase.getCartGrandSum())
        }
    }

    @Test
    fun `total cart item returns null`() {
        runTest {
            `when`(
                localUseCase.grandTotalCartItemsLocalUseCase.getCartGrandSum()
            ).thenReturn(flowOf(Resource.Success(null)))

            localViewModel.grandTotalCartItems()
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.grandTotalCartItems.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.grandTotalCartItemsLocalUseCase.getCartGrandSum())
        }
    }

    @Test
    fun `total cart item returns ERROR`() {
        runTest {
            `when`(
                localUseCase.grandTotalCartItemsLocalUseCase.getCartGrandSum()
            ).thenThrow(RuntimeException::class.java)

            localViewModel.grandTotalCartItems()
            dispatcher.scheduler.advanceUntilIdle()

            localViewModel.grandTotalCartItems.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

//            verify(localUseCase.grandTotalCartItemsLocalUseCase.getCartGrandSum())
        }
    }

    @Test
    fun `delete cart items returns SUCCESS`() {
        runTest {
            `when`(
                localUseCase.deleteAllCartsLocalUseCase.deleteAllCarts()
            ).thenReturn(flowOf(Resource.Success(1)))

            localViewModel.deleteAllCarts()
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.deleteAllCarts.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.deleteAllCartsLocalUseCase.deleteAllCarts())
        }
    }

    @Test
    fun `delete cart items returns -1`() {
        runTest {
            `when`(
                localUseCase.deleteAllCartsLocalUseCase.deleteAllCarts()
            ).thenReturn(flowOf(Resource.Success(-1)))

            localViewModel.deleteAllCarts()
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.deleteAllCarts.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.deleteAllCartsLocalUseCase.deleteAllCarts())
        }
    }

    @Test
    fun `delete cart items returns ERROR`() {
        runTest {
            `when`(
                localUseCase.deleteAllCartsLocalUseCase.deleteAllCarts()
            ).thenThrow(RuntimeException::class.java)

            localViewModel.deleteAllCarts()
            dispatcher.scheduler.advanceUntilIdle()

            localViewModel.deleteAllCarts.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

//            verify(localUseCase.deleteAllCartsLocalUseCase.deleteAllCarts())
        }
    }

    @Test
    fun `fetch orders returns SUCCESS`() {
        runTest {
            `when`(
                localUseCase.orderListLocalUseCase.orderList()
            ).thenReturn(flowOf(Resource.Success(listOf(Order()))))

            localViewModel.orderList()
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.orders.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.orderListLocalUseCase.orderList())
        }
    }

    @Test
    fun `fetch orders returns empty`() {
        runTest {
            `when`(
                localUseCase.orderListLocalUseCase.orderList()
            ).thenReturn(flowOf(Resource.Success(emptyList())))

            localViewModel.orderList()
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.orders.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.orderListLocalUseCase.orderList())
        }
    }

    @Test
    fun `fetch orders returns ERROR`() {
        runTest {
            `when`(
                localUseCase.orderListLocalUseCase.orderList()
            ).thenThrow(RuntimeException::class.java)

            localViewModel.orderList()
            dispatcher.scheduler.advanceUntilIdle()

            localViewModel.orders.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

//            verify(localUseCase.orderListLocalUseCase.orderList())
        }
    }

    @Test
    fun `insert order item returns SUCCESS`() {
        runTest {
            `when`(
                localUseCase.insertOrderItemLocalUseCase.insertItem(Order())
            ).thenReturn(flowOf(Resource.Success(1)))

            localViewModel.insertOrderItem(Order())
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.insertOrder.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.insertOrderItemLocalUseCase.insertItem(Order()))
        }
    }

    @Test
    fun `insert order item returns -1`() {
        runTest {
            `when`(
                localUseCase.insertOrderItemLocalUseCase.insertItem(Order())
            ).thenReturn(flowOf(Resource.Success(-1)))

            localViewModel.insertOrderItem(Order())
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.insertOrder.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.insertOrderItemLocalUseCase.insertItem(Order()))
        }
    }

    @Test
    fun `insert order item returns ERROR`() {
        runTest {
            `when`(
                localUseCase.insertOrderItemLocalUseCase.insertItem(Order())
            ).thenThrow(RuntimeException::class.java)

            localViewModel.insertOrderItem(Order())
            dispatcher.scheduler.advanceUntilIdle()

            localViewModel.insertOrder.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

//            verify(localUseCase.insertOrderItemLocalUseCase.insertItem(Order()))
        }
    }

    @Test
    fun `fetch order item returns SUCCESS`() {
        runTest {
            `when`(
                localUseCase.fetchOrderLocalUserCase.getOrder("1")
            ).thenReturn(flowOf(Resource.Success(Order())))

            localViewModel.getOrder("1")
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.fetchOrder.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.fetchOrderLocalUserCase.getOrder("1"))
        }
    }

    @Test
    fun `fetch order item returns null`() {
        runTest {
            `when`(
                localUseCase.fetchOrderLocalUserCase.getOrder("1")
            ).thenReturn(flowOf(Resource.Success(null)))

            localViewModel.getOrder("1")
            dispatcher.scheduler.advanceUntilIdle()
            localViewModel.fetchOrder.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }

//            verify(localUseCase.fetchOrderLocalUserCase.getOrder("1"))
        }
    }

    @Test
    fun `fetch order item returns ERROR`() {
        runTest {
            `when`(
                localUseCase.fetchOrderLocalUserCase.getOrder("1")
            ).thenThrow(RuntimeException::class.java)

            localViewModel.getOrder("1")
            dispatcher.scheduler.advanceUntilIdle()

            localViewModel.fetchOrder.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }

//            verify(localUseCase.fetchOrderLocalUserCase.getOrder("1"))
        }
    }

}