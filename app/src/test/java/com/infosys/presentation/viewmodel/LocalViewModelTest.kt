package com.infosys.presentation.viewmodel

import app.cash.turbine.test
import com.infosys.data.model.cart.Cart
import com.infosys.data.model.order.Order
import com.infosys.data.model.usecase.LocalUseCase
import com.infosys.data.remote.Resource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocalViewModelTest {

    private lateinit var localViewModel: LocalViewModel
    private val localUseCase: LocalUseCase = mockk<LocalUseCase>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        localViewModel = LocalViewModel(localUseCase)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun dispose() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch cart items returns SUCCESS`() {
        runTest {
            coEvery {
                localUseCase.allCartItemsLocalUseCase.fetchAllItems()
            } returns flowOf(Resource.Success(listOf()))

            localViewModel.getAllCartItems()
            advanceUntilIdle()
            localViewModel.cart.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch cart items returns empty`() {
        runTest {
            coEvery {
                localUseCase.allCartItemsLocalUseCase.fetchAllItems()
            } returns flowOf(Resource.Success(emptyList()))

            localViewModel.getAllCartItems()
            runCurrent()
            localViewModel.cart.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch cart items returns ERROR`() {
        runTest {
            coEvery {
                localUseCase.allCartItemsLocalUseCase.fetchAllItems()
            } throws Exception("Test Exception")

            localViewModel.getAllCartItems()
            runCurrent()

            localViewModel.cart.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch cart item returns SUCCESS`() {
        runTest {
            coEvery {
                localUseCase.fetchCartItemLocalUseCase.fetchItem("1")
            } returns flowOf(Resource.Success(Cart()))

            localViewModel.getCartItem("1")
            runCurrent()
            localViewModel.cartItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch cart item returns null`() {
        runTest {
            coEvery {
                localUseCase.fetchCartItemLocalUseCase.fetchItem("1")
            } returns flowOf(Resource.Success(null))

            localViewModel.getCartItem("1")
            runCurrent()
            localViewModel.cartItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch cart item returns ERROR`() {
        runTest {
            coEvery {
                localUseCase.fetchCartItemLocalUseCase.fetchItem("1")
            } throws Exception("Test Exception")

            localViewModel.getCartItem("1")
            runCurrent()

            localViewModel.cartItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert cart item returns SUCCESS`() {
        runTest {
            coEvery {
                localUseCase.insertCartItemLocalUseCase.insertItem(Cart())
            } returns flowOf(Resource.Success(1))

            localViewModel.insertItem(Cart())
            runCurrent()
            localViewModel.insertItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert cart item returns -1`() {
        runTest {
            coEvery {
                localUseCase.insertCartItemLocalUseCase.insertItem(Cart())
            } returns flowOf(Resource.Success(-1))

            localViewModel.insertItem(Cart())
            runCurrent()
            localViewModel.insertItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert cart item returns ERROR`() {
        runTest {
            coEvery {
                localUseCase.insertCartItemLocalUseCase.insertItem(Cart())
            } throws Exception("Test Exception")

            localViewModel.insertItem(Cart())
            runCurrent()

            localViewModel.insertItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `update cart item returns SUCCESS`() {
        runTest {
            coEvery {
                localUseCase.updateCartItemLocalUseCase.updateItem(Cart())
            } returns flowOf(Resource.Success(1))

            localViewModel.updateItem(Cart())
            runCurrent()
            localViewModel.updateItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `update cart item returns -1`() {
        runTest {
            coEvery {
                localUseCase.updateCartItemLocalUseCase.updateItem(Cart())
            } returns flowOf(Resource.Success(-1))

            localViewModel.updateItem(Cart())
            runCurrent()
            localViewModel.updateItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `update cart item returns ERROR`() {
        runTest {
            coEvery {
                localUseCase.updateCartItemLocalUseCase.updateItem(Cart())
            } throws Exception("Test Exception")

            localViewModel.updateItem(Cart())
            runCurrent()

            localViewModel.updateItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `delete cart item returns SUCCESS`() {
        runTest {
            coEvery {
                localUseCase.deleteCartItemLocalUseCase.delete(Cart())
            } returns flowOf(Resource.Success(1))

            localViewModel.deleteItem(Cart())
            runCurrent()
            localViewModel.deleteItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `delete cart item returns -1`() {
        runTest {
            coEvery {
                localUseCase.deleteCartItemLocalUseCase.delete(Cart())
            } returns flowOf(Resource.Success(-1))

            localViewModel.deleteItem(Cart())
            runCurrent()
            localViewModel.deleteItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `delete cart item returns ERROR`() {
        runTest {
            coEvery {
                localUseCase.deleteCartItemLocalUseCase.delete(Cart())
            } throws Exception("Test Exception")

            localViewModel.deleteItem(Cart())
            runCurrent()

            localViewModel.deleteItem.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `reset delete cart observer returns Unit`() {
        runTest {
            val result = localViewModel.resetDeleteObserver()
            runCurrent()
            assertTrue(result == Unit)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `count cart item returns SUCCESS`() {
        runTest {
            coEvery {
                localUseCase.countCartItemsLocalUseCase.getCartListCount()
            } returns flowOf(Resource.Success(1))

            localViewModel.countCartItems()
            runCurrent()
            localViewModel.countCartItems.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `count cart item returns null`() {
        runTest {
            coEvery {
                localUseCase.countCartItemsLocalUseCase.getCartListCount()
            } returns flowOf(Resource.Success(null))

            localViewModel.countCartItems()
            runCurrent()
            localViewModel.countCartItems.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `count cart item returns ERROR`() {
        runTest {
            coEvery {
                localUseCase.countCartItemsLocalUseCase.getCartListCount()
            } throws Exception("Test Exception")

            localViewModel.countCartItems()
            runCurrent()

            localViewModel.countCartItems.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `total cart item returns SUCCESS`() {
        runTest {
            coEvery {
                localUseCase.grandTotalCartItemsLocalUseCase.getCartGrandSum()
            } returns flowOf(Resource.Success(1f))

            localViewModel.grandTotalCartItems()
            runCurrent()
            localViewModel.grandTotalCartItems.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `total cart item returns null`() {
        runTest {
            coEvery {
                localUseCase.grandTotalCartItemsLocalUseCase.getCartGrandSum()
            } returns flowOf(Resource.Success(null))

            localViewModel.grandTotalCartItems()
            runCurrent()
            localViewModel.grandTotalCartItems.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `total cart item returns ERROR`() {
        runTest {
            coEvery {
                localUseCase.grandTotalCartItemsLocalUseCase.getCartGrandSum()
            } throws Exception("Test Exception")

            localViewModel.grandTotalCartItems()
            runCurrent()

            localViewModel.grandTotalCartItems.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `delete cart items returns SUCCESS`() {
        runTest {
            coEvery {
                localUseCase.deleteAllCartsLocalUseCase.deleteAllCarts()
            } returns flowOf(Resource.Success(1))

            localViewModel.deleteAllCarts()
            runCurrent()
            localViewModel.deleteAllCarts.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `delete cart items returns -1`() {
        runTest {
            coEvery {
                localUseCase.deleteAllCartsLocalUseCase.deleteAllCarts()
            } returns flowOf(Resource.Success(-1))

            localViewModel.deleteAllCarts()
            runCurrent()
            localViewModel.deleteAllCarts.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `delete cart items returns ERROR`() {
        runTest {
            coEvery {
                localUseCase.deleteAllCartsLocalUseCase.deleteAllCarts()
            } throws Exception("Test Exception")

            localViewModel.deleteAllCarts()
            runCurrent()

            localViewModel.deleteAllCarts.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch orders returns SUCCESS`() {
        runTest {
            coEvery {
                localUseCase.orderListLocalUseCase.orderList()
            } returns flowOf(Resource.Success(listOf(Order())))

            localViewModel.orderList()
            runCurrent()
            localViewModel.orders.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch orders returns empty`() {
        runTest {
            coEvery {
                localUseCase.orderListLocalUseCase.orderList()
            } returns flowOf(Resource.Success(listOf()))

            localViewModel.orderList()
            runCurrent()
            localViewModel.orders.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch orders returns ERROR`() {
        runTest {
            coEvery {
                localUseCase.orderListLocalUseCase.orderList()
            } throws Exception("Test Exception")

            localViewModel.orderList()
            runCurrent()

            localViewModel.orders.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert order item returns SUCCESS`() {
        runTest {
            coEvery {
                localUseCase.insertOrderItemLocalUseCase.insertItem(Order())
            } returns flowOf(Resource.Success(1))

            localViewModel.insertOrderItem(Order())
            runCurrent()
            localViewModel.insertOrder.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert order item returns -1`() {
        runTest {
            coEvery {
                localUseCase.insertOrderItemLocalUseCase.insertItem(Order())
            } returns flowOf(Resource.Success(-1))

            localViewModel.insertOrderItem(Order())
            runCurrent()
            localViewModel.insertOrder.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert order item returns ERROR`() {
        runTest {
            coEvery {
                localUseCase.insertOrderItemLocalUseCase.insertItem(Order())
            } throws Exception("Test Exception")

            localViewModel.insertOrderItem(Order())
            runCurrent()

            localViewModel.insertOrder.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch order item returns SUCCESS`() {
        runTest {
            coEvery {
                localUseCase.fetchOrderLocalUserCase.getOrder("1")
            } returns flowOf(Resource.Success(Order()))

            localViewModel.getOrder("1")
            runCurrent()
            localViewModel.fetchOrder.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch order item returns null`() {
        runTest {
            coEvery {
                localUseCase.fetchOrderLocalUserCase.getOrder("1")
            } returns flowOf(Resource.Success(null))

            localViewModel.getOrder("1")
            runCurrent()
            localViewModel.fetchOrder.test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch order item returns ERROR`() {
        runTest {
            coEvery {
                localUseCase.fetchOrderLocalUserCase.getOrder("1")
            } throws Exception("Test Exception")

            localViewModel.getOrder("1")
            runCurrent()

            localViewModel.fetchOrder.test {
                val result = awaitItem()
                assertTrue(result is Resource.Error)
            }
        }
    }

}