# food-delivery-demo

## Test cases Study
'''
1. mockk<ClassNam>(): Creates a mock instance of that class.
2. coEvery { method call } returns "Mocked Data": Mocks the suspend function to return "Mocked Data" instead of the real data.
3. coVerify { method call }: Verifies that the mocked function was called ONCE during the test.
4. coVerify (exactly = n) {} : Verifies that the mocked function was called exactly (n = 1,2,...) times during the test.
5. assertEquals("Mocked Data", result): Asserts that the result returned from fetchData is the mocked value.
'''