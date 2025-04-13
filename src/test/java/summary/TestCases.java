package summary;

class TestCases {

  static Object[][] getCases() {
    return new Object[][]{
        {
            new TestData(
                new String[]{"Mexico", "Spain", "Germany", "Uruguay", "Argentina"},
                new String[]{"Canada", "Brazil", "France", "Italy", "Australia"},
                new int[]{0, 10, 2, 6, 3},
                new int[]{5, 2, 2, 6, 1},
                """
                    1. Uruguay 6 - Italy 6
                    2. Spain 10 - Brazil 2
                    3. Mexico 0 - Canada 5
                    4. Argentina 3 - Australia 1
                    5. Germany 2 - France 2"""
            )
        },
        {
            new TestData(
                new String[]{"England", "Portugal", "Netherlands", "Belgium", "Croatia"},
                new String[]{"USA", "Sweden", "Denmark", "Switzerland", "Japan"},
                new int[]{3, 1, 4, 5, 2},
                new int[]{3, 2, -1, 1, 2},
                """
                    1. Belgium 5 - Switzerland 1
                    2. England 3 - USA 3
                    3. Croatia 2 - Japan 2
                    4. Portugal 1 - Sweden 2
                    5. Netherlands 0 - Denmark 0"""
            )
        },
        {
            new TestData(
                new String[]{"Italy", "Spain", "Germany", "Italy", "Germany"},
                new String[]{"Brazil", "France", "Mexico", "Argentina", "France"},
                new int[]{2, 3, 1, 4, 5},
                new int[]{1, 2, 2, 0, 0},
                """
                    1. Spain 3 - France 2
                    2. Germany 1 - Mexico 2
                    3. Italy 2 - Brazil 1"""
            )
        },
        {
            new TestData(
                new String[]{},
                new String[]{},
                new int[]{},
                new int[]{},
                ""
            )
        }
    };
  }
}
