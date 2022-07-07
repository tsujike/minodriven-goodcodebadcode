class Sample14_3_test {

	@Test
	void characterizationTest() {
		int actual = MoneyManager.calc(1100, true);
		assertEquals(1100, actual);
	}
}
