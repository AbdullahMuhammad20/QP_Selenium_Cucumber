Feature:
  Scenario: Success Registration
    Given user open the chrome browser
    When  user open the URL "https://www.demoblaze.com/"
    And   user has signup and login with username as "Test601" and password as "P@ssw0rd"
    And   user has see Category has items
    And   user has add random item to cart
    And   user has delete item from cart
    Then  user has make checkout with random item